package br.com.alura.forum.config.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import br.com.alura.forum.service.token.TokenService;

public class ForumTokenFilter extends OncePerRequestFilter {
	
	private TokenService tokenService;
	
	public ForumTokenFilter(TokenService tokenService) {
		this.tokenService = tokenService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String token = this.tokenService.getToken(request);
		if (token != null)
			if (this.tokenService.isValidToken(token))
				this.tokenService.authenticateClient(token);
		filterChain.doFilter(request, response);
	}	
}