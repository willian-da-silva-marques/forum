package br.com.alura.forum.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Generated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alura.forum.dto.resposta.RespostaDTOResponse;
import br.com.alura.forum.dto.topico.TopicoDTOResponse;
import br.com.alura.forum.dto.topico.TopicoDTOSaveRequest;
import br.com.alura.forum.dto.topico.TopicoDTOUpdateRequest;
import br.com.alura.forum.dto.topico.TopicoDTODetailsResponse;
import br.com.alura.forum.model.Curso;
import br.com.alura.forum.model.Resposta;
import br.com.alura.forum.model.Topico;
import br.com.alura.forum.model.Topico.TopicoBuilder;
import br.com.alura.forum.model.Usuario;
import br.com.alura.forum.repository.CursoRepository;

@Generated(value = "org.mapstruct.ap.MappingProcessor", date = "2020-02-27T22:41:57-0300", comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_231 (Oracle Corporation)")
@Component
public class TopicoMapperImpl implements TopicoMapper {

	@Autowired
	private CursoRepository cursoRepository;

	@Autowired
	private RespostaMapper respostaMapper;

	@Override
	public Topico topicoDTOSaveRequestToTopico(TopicoDTOSaveRequest topicoDTOSaveRequest) {
		if (topicoDTOSaveRequest == null) {
			return null;
		}
		Curso curso = this.cursoRepository.findByNome(topicoDTOSaveRequest.getNomeCurso());
		TopicoBuilder topico = Topico.builder();
		topico.titulo(topicoDTOSaveRequest.getTitulo());
		topico.mensagem(topicoDTOSaveRequest.getMensagem());
		topico.curso(curso);
		return topico.build();
	}

	@Override
	public TopicoDTOResponse topicoToTopicoDTOResponse(Topico topico) {
		if (topico == null) {
			return null;
		}
		TopicoDTOResponse topicoDTOResponse = new TopicoDTOResponse();
		topicoDTOResponse.setTitulo(topico.getTitulo());
		topicoDTOResponse.setId(topico.getId());
		topicoDTOResponse.setDataCriacao(topico.getDataCriacao());
		topicoDTOResponse.setMensagem(topico.getMensagem());
		topicoDTOResponse.setStatus(topico.getStatus());
		return topicoDTOResponse;
	}

	@Override
	public TopicoDTODetailsResponse topicoToTopicoDTODetailsResponse(Topico topico) {
		if (topico == null) {
			return null;
		}
		TopicoDTODetailsResponse topicoDTODetailsResponse = new TopicoDTODetailsResponse();
		topicoDTODetailsResponse.setMensagem(topico.getMensagem());
		topicoDTODetailsResponse.setTitulo(topico.getTitulo());
		topicoDTODetailsResponse.setId(topico.getId());
		topicoDTODetailsResponse.setDataCriacao(topico.getDataCriacao());
		topicoDTODetailsResponse.setStatus(topico.getStatus());
		topicoDTODetailsResponse.setNomeAutor(topicoAutorNome(topico));
		topicoDTODetailsResponse.setRespostas(topico.getRespostas().stream()
				.map(this.respostaMapper::respostaToRespostaDTOResponse).collect(Collectors.toList()));
		return topicoDTODetailsResponse;
	}

	@Override
	public Topico topicoDTOUpdateRequestToTopico(TopicoDTOUpdateRequest topicoDTOUpdateRequest) {
		if (topicoDTOUpdateRequest == null) {
			return null;
		}
		TopicoBuilder topico = Topico.builder();
		topico.titulo(topicoDTOUpdateRequest.getTitulo());
		topico.mensagem(topicoDTOUpdateRequest.getMensagem());
		return topico.build();
	}

	private String topicoAutorNome(Topico topico) {
		if (topico == null) {
			return null;
		}
		Usuario autor = topico.getAutor();
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