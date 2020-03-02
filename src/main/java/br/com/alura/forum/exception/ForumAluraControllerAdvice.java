package br.com.alura.forum.exception;

import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.alura.forum.dto.response.ForumResponse;
import br.com.alura.forum.util.ForumUtil;

@RestControllerAdvice
public class ForumAluraControllerAdvice {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ForumResponse<?>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
		ForumResponse<Object> response = new ForumResponse<>();
		response.setData(e.getMessage().substring(0, 17));
		response.setErrors(ForumUtil.mapErrors(e.getBindingResult().getAllErrors()));
		return ResponseEntity.badRequest().body(response);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ForumResponse<String>> messageNotReadableExceptionHandler(HttpMessageNotReadableException e) {
		ForumResponse<String> response = new ForumResponse<>();
		response.setData("R".concat(e.getMessage().substring(10, 22).concat("not found")));
		return ResponseEntity.badRequest().body(response);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<ForumResponse<String>> authenticationExceptionHandler(AuthenticationException e) {
		ForumResponse<String> response = new ForumResponse<>();
		response.setData(e.getMessage());
		return ResponseEntity.badRequest().body(response);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(PropertyReferenceException.class)
	public ResponseEntity<ForumResponse<String>> propertyReferenceExceptionHandler(PropertyReferenceException e) {
		ForumResponse<String> response = new ForumResponse<>();
		response.setData(e.getMessage().substring(0, 12).concat("'".concat(e.getMessage().substring(12, 20).concat("'".concat(e.getMessage().substring(20))))));
		return ResponseEntity.badRequest().body(response);
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(TopicoNaoEncontradoException.class)
	public ResponseEntity<?> topicoNaoEncontradoExceptionHandler(TopicoNaoEncontradoException e) {
		return ResponseEntity.notFound().build();
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(UsuarioNaoEncontradoException.class)
	public ResponseEntity<?> usuarioNaoEncontradoExceptionHandler(UsuarioNaoEncontradoException e) {
		return ResponseEntity.notFound().build();
	}
	
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ForumResponse<String>> methodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e) {
		ForumResponse<String> response = new ForumResponse<>();
		response.setData(e.getMessage());
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(response);
	}
}
