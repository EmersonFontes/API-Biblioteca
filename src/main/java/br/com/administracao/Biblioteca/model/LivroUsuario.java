package br.com.administracao.Biblioteca.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


@Entity
@Table(name = "livro_usuario")
public class LivroUsuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "data_entrega")
	private Date dataEntrega = new Date();

	@ManyToOne(targetEntity = Usuario.class)
	 private Usuario usuario = new Usuario();
	@ManyToOne(targetEntity = Livro.class)
	 private Livro livro = new Livro();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Date calculaDataEntrega () {

		Date d = new Date();
		Calendar c = Calendar.getInstance();
		c.getTimeZone();
		c.setTime(d);
		c.add(c.DATE, 5);
		d = (c.getTime());
		System.out.println(d);
		return d;
	}
	
}
