package com.faculdade.albumcopa.model;

import jakarta.persistence.*;

@Entity
@Table(name = "figurinhas")
public class Figurinha {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private Integer numero;
	
	public Figurinha()	{
	}
	
	public Figurinha(Integer numero)	{
		this.numero = numero;
	}
	
	public Long getId()	{
		return id;
	}
	
	public void setId(Long id)	{
		this.id = id;
	}
	
	public Integer getNumero() {
		return numero;
	}
	
	public void setNumero(Integer numero)	{
		this.numero = numero;
	}
	
}