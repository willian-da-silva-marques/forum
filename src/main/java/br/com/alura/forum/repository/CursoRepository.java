package br.com.alura.forum.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.forum.model.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

	public Curso findByNome(String nome);
	
	public Page<Curso> findByNome(String nome, Pageable pageable);
}