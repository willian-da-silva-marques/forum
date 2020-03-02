package br.com.alura.forum.service.token;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.forum.dto.token.TokenDTOResponse;
import br.com.alura.forum.model.Usuario;
import br.com.alura.forum.repository.UsuarioRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenServiceImpl implements TokenService {

	@Value("${forum.jwt.authentication.scheme}")
	private String authenticationScheme;

	@Value("${forum.jwt.expiration}")
	private String expiration;

	@Value("${forum.jwt.secret}")
	private String secret;

	@Value("${forum.jwt.authorization}")
	private String authorization;
	
	@Autowired
	private UsuarioRepository usuarioRepository; 

	@Override
	public String generateToken(Authentication authentication) {
		Usuario usuario = (Usuario) authentication.getPrincipal();
		Date date = new Date();
		Date dateExpiration = new Date(date.getTime() + Long.parseLong(this.expiration));
		return Jwts.builder()
				.setIssuer("API Forum da Alura")
				.setSubject(usuario.getId().toString())
				.setIssuedAt(date)
				.setExpiration(dateExpiration)
				.signWith(SignatureAlgorithm.HS256, this.secret)
				.compact();
	}

	@Override
	public TokenDTOResponse getTokenDTOResponse(String token) {
		return new TokenDTOResponse(token, this.authenticationScheme);
	}

	@Override
	public String getToken(HttpServletRequest request) {
		String token = request.getHeader(this.authorization);
		if (token == null || token.isEmpty() || !token.startsWith(this.authenticationScheme.concat(" "))) {
			return null;
		}
		return token.substring(7, token.length());
	}

	@Override
	public boolean isValidToken(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (JwtException e) {
			return false;
		}
	}

	@Override
	@Transactional
	public void authenticateClient(String token) {
		Long id = this.getId(token);
		Usuario usuario = this.usuarioRepository.findById(id).get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private Long getId(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}
}