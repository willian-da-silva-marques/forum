package br.com.alura.forum.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "resposta")
public class Resposta implements Serializable {

	private static final long serialVersionUID = -3236493321743574717L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "mensagem", length = 255, nullable = false)
	private String mensagem;
	
	@ManyToOne
	private Topico topico;
	
	@Column(name = "data_criacao", nullable = false)
	private LocalDateTime dataCriacao;
	
	@ManyToOne
	private Usuario autor;
	
	@Column(name = "solucao", length = 1, nullable = false, columnDefinition = "boolean default false")
	private Boolean solucao;
}
