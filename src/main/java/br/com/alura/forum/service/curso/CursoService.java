package br.com.alura.forum.service.curso;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.alura.forum.model.Curso;

public interface CursoService {

	public Page<Curso> findAll(Pageable pageable);
	
	public Page<Curso> findByNome(String nome, Pageable pageable);
}
