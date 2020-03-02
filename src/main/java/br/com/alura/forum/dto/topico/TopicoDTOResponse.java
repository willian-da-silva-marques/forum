package br.com.alura.forum.dto.topico;

import java.time.LocalDateTime;

import br.com.alura.forum.model.StatusTopico;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TopicoDTOResponse {

	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao;
	private StatusTopico status;
}
