package br.com.administracao.Biblioteca.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.administracao.Biblioteca.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Integer>{


	List<Livro> findAllByNome(String nome);

	@Query(value = "SELECT * FROM Livro l WHERE l.disponibilidade = 'Disponivel'", nativeQuery = true)
	List<Livro> findAllByDisponibilidade();
}
