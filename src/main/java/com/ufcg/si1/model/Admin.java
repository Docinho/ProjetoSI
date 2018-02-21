package com.ufcg.si1.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Admin {

	@Id
	private String id;
	private String login, senha;

	public Admin(String login, String senha) {
		this.id = login+senha;
		this.login = login;
		this.senha = senha;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
