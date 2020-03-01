package br.com.alura.forum.service.topico;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.forum.exception.TopicoNaoEncontradoException;
import br.com.alura.forum.model.Topico;
import br.com.alura.forum.repository.TopicoRepository;

@Service
public class TopicoServiceImpl implements TopicoService {

	@Autowired
	private TopicoRepository topicoRepository;

	@Transactional(readOnly = true)
	@Override
	public Page<Topico> findAll(Pageable pageable) {
		Optional<Page<Topico>> topicos = Optional.ofNullable(this.topicoRepository.findAll(pageable));
		if (!topicos.isPresent()) {
			return Page.empty();
		}
		return topicos.get();
	}

	@Transactional(readOnly = true)
	@Override
	public Page<Topico> findByCursoNome(String nomeCurso, Pageable pageable) {
		Optional<Page<Topico>> topicos = Optional.ofNullable(this.topicoRepository.findByCursoNome(nomeCurso, pageable));
		if (!topicos.isPresent()) {
			return Page.empty();
		}
		return topicos.get();
	}

	@Transactional(readOnly = false)
	@Override
	public Topico save(Topico topico) {
		return this.topicoRepository.save(topico);
	}

	@Transactional(readOnly = true)
	@Override
	public Topico findById(Long id) {
		Optional<Topico> topico = this.topicoRepository.findById(id);
		if (topico.isPresent()) {
			return topico.get();
		}
		throw new TopicoNaoEncontradoException("Tópico não encontrado.");
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean deleteById(Long id) {
		if (id == null) {
			return Boolean.FALSE;
		}
		if (this.topicoRepository.existsById(id)) {
			this.topicoRepository.deleteById(id);
			Optional<Topico> topicoDeleted = this.topicoRepository.findById(id);
			if (topicoDeleted.isPresent()) {
				return Boolean.FALSE;
			} else {
				return Boolean.TRUE;
			}
		} else {
			throw new TopicoNaoEncontradoException("Tópico não encontrado.");
		}
	}

	@Transactional(readOnly = false)
	@Override
	public Topico updateById(Long id, Topico topico) {
		if (id == null || topico == null) {
			throw new TopicoNaoEncontradoException("Tópico não encontrado.");
		} else {
			Optional<Topico> topicoFinded = this.topicoRepository.findById(id);
			if (topicoFinded.isPresent()) {
				Topico topicoEdited = topicoFinded.get();
				topicoEdited.setTitulo(topico.getTitulo());
				topicoEdited.setMensagem(topico.getMensagem());
				return topicoEdited;
			} else {
				throw new TopicoNaoEncontradoException("Tópico não encontrado.");
			}
		}
	}
}