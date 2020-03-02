package br.com.alura.forum.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.alura.forum.dto.response.ForumResponse;
import br.com.alura.forum.dto.topico.TopicoDTODetailsResponse;
import br.com.alura.forum.dto.topico.TopicoDTOResponse;
import br.com.alura.forum.dto.topico.TopicoDTOSaveRequest;
import br.com.alura.forum.dto.topico.TopicoDTOUpdateRequest;
import br.com.alura.forum.mapper.TopicoMapper;
import br.com.alura.forum.model.Topico;
import br.com.alura.forum.service.topico.TopicoService;
import br.com.alura.forum.util.ForumUtil;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

	@Autowired
	private TopicoMapper topicoMapper;

	@Autowired
	private TopicoService topicoService;

	@GetMapping
	@Cacheable(value = "findAllTopicos")
	public ResponseEntity<ForumResponse<Page<TopicoDTOResponse>>> findAll(@RequestParam(required = false) String nomeCurso, @PageableDefault(size = 10, sort = "id", direction = Direction.ASC) Pageable pageable) {
		ForumResponse<Page<TopicoDTOResponse>> response = new ForumResponse<>();
		if (!ForumUtil.hasParameter(nomeCurso)) {
			response.setData(this.topicoService.findAll(pageable).map(this.topicoMapper::topicoToTopicoDTOResponse));
			return ResponseEntity.ok(response);
		} else {
			response.setData(this.topicoService.findByCursoNome(nomeCurso, pageable).map(this.topicoMapper::topicoToTopicoDTOResponse));
			return ResponseEntity.ok(response);
		}
	}

	@PostMapping
	@CacheEvict(value = "findAllTopicos", allEntries = true)
	public ResponseEntity<ForumResponse<TopicoDTOResponse>> save(@Valid @RequestBody TopicoDTOSaveRequest topicoDTOSaveRequest) {
		ForumResponse<TopicoDTOResponse> response = new ForumResponse<>();
		Topico topico = this.topicoService.save(this.topicoMapper.topicoDTOSaveRequestToTopico(topicoDTOSaveRequest));
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(topico.getId()).toUri();
		TopicoDTOResponse topicoDTOResponse = this.topicoMapper.topicoToTopicoDTOResponse(topico);
		response.setData(topicoDTOResponse);
		return ResponseEntity.created(location).body(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ForumResponse<TopicoDTODetailsResponse>> findById(@PathVariable Long id) {
		ForumResponse<TopicoDTODetailsResponse> response = new ForumResponse<>();
		response.setData(this.topicoMapper.topicoToTopicoDTODetailsResponse(this.topicoService.findById(id)));
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{id}")
	@CacheEvict(value = "findAllTopicos", allEntries = true)
	public ResponseEntity<ForumResponse<Boolean>> deleteById(@PathVariable Long id) {
		ForumResponse<Boolean> response = new ForumResponse<>();
		response.setData(this.topicoService.deleteById(id));
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/{id}")
	@CacheEvict(value = "findAllTopicos", allEntries = true)
	public ResponseEntity<ForumResponse<TopicoDTOResponse>> updateById(@PathVariable Long id, @Valid @RequestBody TopicoDTOUpdateRequest topicoDTOUpdateRequest) {
		ForumResponse<TopicoDTOResponse> response = new ForumResponse<>();
		Topico topico = this.topicoService.updateById(id, this.topicoMapper.topicoDTOUpdateRequestToTopico(topicoDTOUpdateRequest));
		TopicoDTOResponse topicoDTOResponse = this.topicoMapper.topicoToTopicoDTOResponse(topico);
		response.setData(topicoDTOResponse);
		return ResponseEntity.ok(response);
	}
	
	
}