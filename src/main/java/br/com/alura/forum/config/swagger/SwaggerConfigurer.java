package br.com.alura.forum.config.swagger;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.alura.forum.model.Usuario;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfigurer {
	
	@Value("${forum.jwt.authorization}")
	private String authorization;

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.alura.forum"))
				.paths(PathSelectors.ant("/**"))
				.build()
				.apiInfo(this.apiInfo())
				.ignoredParameterTypes(Usuario.class)
				.globalOperationParameters(Arrays.asList(
						new ParameterBuilder()
						.name(this.authorization)
						.description("Header para token JWT")
						.modelRef(new ModelRef("string"))
						.parameterType("header")
						.required(false)
						.build()));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("API Forum da Alura")
				.description("Documentação da API Forum da Alura para acesso aos endpoints.")
				.version("1.0")
				.build();
	}
}