package br.com.administracao.Biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.administracao.Biblioteca.model.LivroUsuario;

public interface LivroUsuarioRepository extends JpaRepository<LivroUsuario, Integer>{

}
