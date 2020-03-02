package br.com.alura.forum.dto.response;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class ForumResponse<T> {

	@Getter
	@Setter
	private T data;

	private Map<String, String> errors;

	public Map<String, String> getErrors() {
		if (errors == null) {
			return errors = new HashMap<>();
		}
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		if (errors == null) {
			errors = new HashMap<>();
			this.errors = errors;
		} else {
			this.errors = errors;
		}
	}
}
