package br.com.alura.forum.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.alura.forum.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query("SELECT DISTINCT u FROM Usuario u LEFT JOIN FETCH u.perfis WHERE u.email = :email")
	Optional<Usuario> findByEmail(@Param(value = "email") String email);
}