package br.com.alura.forum.mapper;

import br.com.alura.forum.dto.resposta.RespostaDTOResponse;
import br.com.alura.forum.model.Resposta;
import br.com.alura.forum.model.Usuario;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(value = "org.mapstruct.ap.MappingProcessor", date = "2020-02-27T22:41:56-0300", comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_231 (Oracle Corporation)")
@Component
public class RespostaMapperImpl implements RespostaMapper {

	@Override
	public RespostaDTOResponse respostaToRespostaDTOResponse(Resposta resposta) {
		if (resposta == null) {
			return null;
		}
		RespostaDTOResponse respostaDTOResponse = new RespostaDTOResponse();
		respostaDTOResponse.setId(resposta.getId());
		respostaDTOResponse.setDataCriacao(resposta.getDataCriacao());
		respostaDTOResponse.setMensagem(resposta.getMensagem());
		respostaDTOResponse.setNomeAutor(respostaAutorNome(resposta));
		return respostaDTOResponse;
	}

	private String respostaAutorNome(Resposta resposta) {
		if (resposta == null) {
			return null;
		}
		Usuario autor = resposta.getAutor();
		if (autor == null) {
			return null;
		}
		String nome = autor.getNome();
		if (nome == null) {
			return null;
		}
		return nome;
	}
}