package br.com.alura.forum.exception;

public class UsuarioNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = -2132330133006499565L;

	public UsuarioNaoEncontradoException() {}

	public UsuarioNaoEncontradoException(String message) {
		super(message);
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}