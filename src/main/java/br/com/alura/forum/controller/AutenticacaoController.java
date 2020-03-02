package br.com.alura.forum.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.dto.response.ForumResponse;
import br.com.alura.forum.dto.token.TokenDTOResponse;
import br.com.alura.forum.dto.usuario.UsuarioDTOAuthenticateRequest;
import br.com.alura.forum.service.token.TokenService;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<ForumResponse<TokenDTOResponse>> authenticate(@Valid @RequestBody UsuarioDTOAuthenticateRequest usuarioDTOAuthenticateRequest) {
		ForumResponse<TokenDTOResponse> response = new ForumResponse<>();
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = usuarioDTOAuthenticateRequest.usuarioDTOAuthenticateRequestToUsernamePasswordAuthenticationToken();
		Authentication authentication = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		response.setData(this.tokenService.getTokenDTOResponse(this.tokenService.generateToken(authentication)));
		return ResponseEntity.ok(response);
	}
}
