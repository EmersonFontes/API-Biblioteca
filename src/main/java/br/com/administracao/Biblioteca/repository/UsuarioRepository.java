package br.com.administracao.Biblioteca.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.administracao.Biblioteca.model.Usuario;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

    List<Usuario> findAllByNome(String nome);
}
