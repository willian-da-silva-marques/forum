package br.com.alura.forum.exception;

public class CursoNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = -5596613189980265665L;

	public CursoNaoEncontradoException() {}

	public CursoNaoEncontradoException(String message) {
		super(message);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}