package br.com.alura.forum.service.curso;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.forum.model.Curso;
import br.com.alura.forum.repository.CursoRepository;

@Service
public class CursoServiceImpl implements CursoService {

	@Autowired
	private CursoRepository cursoRepository;
	
	@Transactional(readOnly = true)
	@Override
	public Page<Curso> findAll(Pageable pageable) {
		Optional<Page<Curso>> cursos = Optional.ofNullable(this.cursoRepository.findAll(pageable));
		if (!cursos.isPresent()) {
			return Page.empty();
		}
		return cursos.get();
	}

	@Transactional(readOnly = true)
	@Override
	public Page<Curso> findByNome(String nome, Pageable pageable) {
		Optional<Page<Curso>> cursos = Optional.ofNullable(this.cursoRepository.findByNome(nome, pageable));
		if (!cursos.isPresent()) {
			return Page.empty();
		}
		return cursos.get();
	}
}
