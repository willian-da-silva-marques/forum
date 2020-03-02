package br.com.alura.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.dto.curso.CursoDTOResponse;
import br.com.alura.forum.dto.response.ForumResponse;
import br.com.alura.forum.mapper.CursoMapper;
import br.com.alura.forum.service.curso.CursoService;
import br.com.alura.forum.util.ForumUtil;

@RestController
@RequestMapping("/cursos")
public class CursoController {
	
	@Autowired
	private CursoMapper cursoMapper;
	
	@Autowired
	private CursoService cursoService; 

	@GetMapping
	public ResponseEntity<ForumResponse<Page<CursoDTOResponse>>> findAll(@RequestParam(required = false) String nomeCurso, @PageableDefault(size = 10, sort = "nome" ,direction = Direction.ASC) Pageable pageable) {
		ForumResponse<Page<CursoDTOResponse>> response = new ForumResponse<>();
		if (!ForumUtil.hasParameter(nomeCurso)) {
			response.setData(this.cursoService.findAll(pageable).map(this.cursoMapper::cursoToCursoDTOResponse));
			return ResponseEntity.ok(response);
		} else {
			response.setData(this.cursoService.findByNome(nomeCurso, pageable).map(this.cursoMapper::cursoToCursoDTOResponse));
			return ResponseEntity.ok(response);
		}
	}
}
