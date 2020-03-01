package br.com.alura.forum.dto.topico;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TopicoDTOSaveRequest {

	@NotEmpty(message = "título é obrigatório")
	private String titulo;
	
	@NotEmpty(message = "mensagem é obrigatória")
	private String mensagem;
	
	@NotEmpty(message = "nome do curso é obrigatório")
	private String nomeCurso;
}