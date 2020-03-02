package br.com.alura.forum.dto.topico;

import java.time.LocalDateTime;
import java.util.List;

import br.com.alura.forum.dto.resposta.RespostaDTOResponse;
import br.com.alura.forum.model.StatusTopico;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TopicoDTODetailsResponse {

	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao;
	private StatusTopico status;
	private String nomeAutor;
	private List<RespostaDTOResponse> respostas;
}
