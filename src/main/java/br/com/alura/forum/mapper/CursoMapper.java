package br.com.alura.forum.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import br.com.alura.forum.dto.curso.CursoDTOResponse;
import br.com.alura.forum.dto.curso.CursoDTOSaveRequest;
import br.com.alura.forum.model.Curso;

@Mapper(componentModel = "spring")
public interface CursoMapper {

	@Mappings({
		@Mapping(target = "id", source = "curso.id"),
		@Mapping(target = "nome", source = "curso.nome"),
		@Mapping(target = "categoria", source = "curso.categoria")
	})
	public CursoDTOResponse cursoToCursoDTOResponse(Curso curso);
	
	@Mappings({
		@Mapping(target = "nome", source = "cursoDTOSaveRequest.nome"),
		@Mapping(target = "categoria", source = "cursoDTOSaveRequest.categoria")
	})
	public Curso cursoDTOSaveRequestToCurso(CursoDTOSaveRequest cursoDTOSaveRequest);
}