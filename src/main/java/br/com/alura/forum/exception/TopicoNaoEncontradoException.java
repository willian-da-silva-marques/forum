package br.com.alura.forum.exception;

public class TopicoNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = -1020320761402971829L;

	public TopicoNaoEncontradoException() {}

	public TopicoNaoEncontradoException(String message) {
		super(message);
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}