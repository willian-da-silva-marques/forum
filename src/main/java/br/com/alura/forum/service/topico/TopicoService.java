package br.com.alura.forum.service.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.alura.forum.model.Topico;

public interface TopicoService {

	public Page<Topico> findAll(Pageable pageable);
	
	public Page<Topico> findByCursoNome(String nomeCurso, Pageable pageable);
	
	public Topico save(Topico topico);

	public Topico findById(Long id);
	
	public Boolean deleteById(Long id);
	
	public Topico updateById(Long id, Topico topico);
}
