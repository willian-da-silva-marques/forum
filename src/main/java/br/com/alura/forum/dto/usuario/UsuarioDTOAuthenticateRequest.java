package br.com.alura.forum.dto.usuario;

import javax.validation.constraints.NotEmpty;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsuarioDTOAuthenticateRequest {

	@NotEmpty(message = "email é obrigatório")
	private String email;
	
	@NotEmpty(message = "senha é obrigatória")
	private String senha;

	public UsernamePasswordAuthenticationToken usuarioDTOAuthenticateRequestToUsernamePasswordAuthenticationToken() {
		return new UsernamePasswordAuthenticationToken(email, senha);
	}
}
