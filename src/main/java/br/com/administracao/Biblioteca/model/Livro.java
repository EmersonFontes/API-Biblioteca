package br.com.administracao.Biblioteca.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "livro")
public class Livro {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "matricula", nullable = false)
	private Long matricula;

	@Column(name = "qnt_total", nullable = false)
	private int qnt_total;
	@Column(name = "disponibilidade", nullable = false)
	private String disponibilidade;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

	public String getDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(String disponiilidade) {
		this.disponibilidade = disponiilidade;
	}

	public int getQnt_total() {
		return qnt_total;
	}

	public void setQnt_total(int qnt_total) {
		this.qnt_total = qnt_total;
	}

	public String livroDisponivel() {
		String d;
		if(getQnt_total() > 0) {
			setDisponibilidade("Disponivel");
			return getDisponibilidade();
		}
		setDisponibilidade("Indisponivel");
		return getDisponibilidade();
	}

	public Long gerarMatricula() {

		long m;
		Random radom = new Random();
		 m = radom.nextLong() / 1000000000;
		 Math.abs(m);
		 return m;
	}
	
}
