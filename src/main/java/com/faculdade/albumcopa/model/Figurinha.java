package com.faculdade.albumcopa.model;

import jakarta.persistence.*;

@Entity
@Table(name = "figurinhas")
public class Figurinha {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	private String jogador;
	private String selecao;
	private boolean rara;
	
	public Figurinha()	{
	}
	
	public Figurinha(String jogador, String selecao, boolean rara)	{
		this.jogador = jogador;
		this.selecao = selecao;
		this.rara = rara;
	}
	
	public Long getId()	{
		return id;
	}
	
	public void setId(Long id)	{
		this.id = id;
	}
	
	public String getJogador()	{
		return jogador;
	}
	
	public void setJogador(String jogador)	{
		this.jogador = jogador;
	}
	
	public String getSelecao()	{
		return selecao;
	}
	
	public void setSelecao(String selecao)	{
		this.selecao = selecao;
	}
	
	public boolean isRara()	{
		return rara;
	}
	
	public void setRara(boolean rara)	{
		this.rara = rara;
	}
}
