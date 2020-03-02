package br.com.alura.forum.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import br.com.alura.forum.dto.topico.TopicoDTOSaveRequest;
import br.com.alura.forum.dto.topico.TopicoDTOUpdateRequest;
import br.com.alura.forum.dto.topico.TopicoDTOResponse;
import br.com.alura.forum.dto.topico.TopicoDTODetailsResponse;
import br.com.alura.forum.model.Topico;

@Mapper(componentModel = "spring")
public interface TopicoMapper {

	public Topico topicoDTOSaveRequestToTopico(TopicoDTOSaveRequest topicoDTOSaveRequest);

	@Mappings({
		@Mapping(target = "id", source = "topico.id"),
		@Mapping(target = "titulo", source = "topico.titulo"),
		@Mapping(target = "mensagem", source = "topico.mensagem"),
		@Mapping(target = "dataCriacao", source = "topico.dataCriacao"),
		@Mapping(target = "status", source = "topico.status")
	})
	public TopicoDTOResponse topicoToTopicoDTOResponse(Topico topico);
	
	@Mappings({
		@Mapping(target = "id", source = "topico.id"),
		@Mapping(target = "titulo", source = "topico.titulo"),
		@Mapping(target = "mensagem", source = "topico.mensagem"),
		@Mapping(target = "dataCriacao", source = "topico.dataCriacao"),
		@Mapping(target = "status", source = "topico.status"),
		@Mapping(target = "nomeAutor", source = "topico.autor.nome"),
		@Mapping(target = "respostas", source = "topico.respostas")
	})
	public TopicoDTODetailsResponse topicoToTopicoDTODetailsResponse(Topico topico);
	
	@Mappings({
		@Mapping(target = "titulo", source = "topicoDTOUpdateRequest.titulo"),
		@Mapping(target = "mensagem", source = "topicoDTOUpdateRequest.mensagem")
	})
	public Topico topicoDTOUpdateRequestToTopico(TopicoDTOUpdateRequest topicoDTOUpdateRequest);
}
