package br.com.alura.forum.mapper;

import br.com.alura.forum.dto.curso.CursoDTOResponse;
import br.com.alura.forum.dto.curso.CursoDTOResponse.CursoDTOResponseBuilder;
import br.com.alura.forum.dto.curso.CursoDTOSaveRequest;
import br.com.alura.forum.model.Curso;
import br.com.alura.forum.model.Curso.CursoBuilder;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(value = "org.mapstruct.ap.MappingProcessor", date = "2020-03-02T09:27:30-0300", comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_231 (Oracle Corporation)")
@Component
public class CursoMapperImpl implements CursoMapper {

	@Override
	public CursoDTOResponse cursoToCursoDTOResponse(Curso curso) {
		if (curso == null) {
			return null;
		}
		CursoDTOResponseBuilder cursoDTOResponse = CursoDTOResponse.builder();
		cursoDTOResponse.nome(curso.getNome());
		cursoDTOResponse.id(curso.getId());
		cursoDTOResponse.categoria(curso.getCategoria());
		return cursoDTOResponse.build();
	}

	@Override
	public Curso cursoDTOSaveRequestToCurso(CursoDTOSaveRequest cursoDTOSaveRequest) {
		if (cursoDTOSaveRequest == null) {
			return null;
		}
		CursoBuilder curso = Curso.builder();
		curso.nome(cursoDTOSaveRequest.getNome());
		curso.categoria(cursoDTOSaveRequest.getCategoria());
		return curso.build();
	}
}