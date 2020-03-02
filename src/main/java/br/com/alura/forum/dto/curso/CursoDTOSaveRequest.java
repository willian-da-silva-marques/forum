package br.com.alura.forum.dto.curso;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CursoDTOSaveRequest {

	private String nome;
	private String categoria;
}
