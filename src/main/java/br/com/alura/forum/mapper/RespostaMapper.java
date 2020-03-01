package br.com.alura.forum.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import br.com.alura.forum.dto.resposta.RespostaDTOResponse;
import br.com.alura.forum.model.Resposta;

@Mapper(componentModel = "spring")
public interface RespostaMapper {

	@Mappings({
		@Mapping(target = "id", source = "resposta.id"),
		@Mapping(target = "mensagem", source = "resposta.mensagem"),
		@Mapping(target = "dataCriacao", source = "resposta.dataCriacao"),
		@Mapping(target = "nomeAutor", source = "resposta.autor.nome")
	})
	public RespostaDTOResponse respostaToRespostaDTOResponse(Resposta resposta);	
}