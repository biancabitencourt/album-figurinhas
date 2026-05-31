package com.faculdade.albumcopa.model;

import jakarta.persistence.*;

@Entity
@Table(name="usuarios")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String login;
	private String senha;
	
	public Usuario()	{
	}
	
	public Usuario(String login, String senha)	{
		this.login = login;
		this.senha = senha;
	}
	
	public Long getId()	{
		return id;
	}
		
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getSenha() {
		return senha;
	}
	
/*	public boolean verificarSenha(String senhaDigitada)	{
		return new BCryptPasswordEncoder().matches(senhaDigitada, this.senha);
	}
*/
	
}
