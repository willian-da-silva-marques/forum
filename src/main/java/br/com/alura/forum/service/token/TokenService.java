package br.com.alura.forum.service.token;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

import br.com.alura.forum.dto.token.TokenDTOResponse;

public interface TokenService {

	public String generateToken(Authentication authentication);
	
	public TokenDTOResponse getTokenDTOResponse(String token);

	public String getToken(HttpServletRequest request);

	public boolean isValidToken(String token);

	public void authenticateClient(String token);
}