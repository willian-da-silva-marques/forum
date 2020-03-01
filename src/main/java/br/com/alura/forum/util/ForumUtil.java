package br.com.alura.forum.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class ForumUtil {

	public static Map<String, String> mapErrors(List<ObjectError> errors) {
		Map<String, String> map = new HashMap<>();
		errors.stream().forEach(error -> {
			String errorField = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			map.put(errorField, errorMessage);
		});
		return map;
	}
	
	public static boolean hasParameter(String param) {
		if (param == null) {
			return false;
		}
		if (param.trim().isEmpty()) {
			return false;
		}
		return true;
	}
	
//	public static void main(String[] args) {
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		System.out.println(passwordEncoder.encode("123456"));
//		
//	}
}