package br.com.alura.forum.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "topico")
public class Topico implements Serializable {

	private static final long serialVersionUID = -3837706357309444267L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "titulo", length = 255, nullable = false)
	private String titulo;

	@Column(name = "mensagem", length = 255, nullable = false)
	private String mensagem;

	@Column(name = "data_criacao", nullable = false)
	private LocalDateTime dataCriacao;

	@Column(name = "status", length = 45, nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusTopico status;

	@ManyToOne
	private Usuario autor;

	@ManyToOne
	private Curso curso;

	@Default
	@OneToMany(mappedBy = "topico", fetch = FetchType.LAZY)
	private List<Resposta> respostas = new ArrayList<>();

	@PrePersist
	public void prePersist() {
		this.dataCriacao = LocalDateTime.now();
		this.status = StatusTopico.NAO_RESPONDIDO;
	}
}