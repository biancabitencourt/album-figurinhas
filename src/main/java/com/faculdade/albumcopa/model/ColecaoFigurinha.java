package com.faculdade.albumcopa.model;

import jakarta.persistence.*;

@Entity
@Table(name = "colecao_figurinha")
public class ColecaoFigurinha {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Usuario usuario;
	
	
	@ManyToOne
	private Figurinha figurinha;
	
	private int quantidade;
	
	public ColecaoFigurinha()	{
	}
	
	public ColecaoFigurinha(Usuario usuario, Figurinha figurinha, int quantidade)	{
		this.usuario = usuario;
		this.figurinha = figurinha;
		this.quantidade = quantidade;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Figurinha getFigurinha() {
		return figurinha;
	}

	public void setFigurinha(Figurinha figurinha) {
		this.figurinha = figurinha;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

}	
