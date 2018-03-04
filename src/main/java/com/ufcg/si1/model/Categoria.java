package com.ufcg.si1.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Categoria implements Serializable {
	
	@Transient
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	private String nomeDaCategoria;
	private int desconto;
	@JsonManagedReference
	@OneToMany(mappedBy="categoria", cascade = {CascadeType.ALL})
	private List<Produto> produto;
	
	public Categoria() {}
	
	public Categoria(String nomeDaCategoria, int desconto) {
		this.nomeDaCategoria = nomeDaCategoria;
		this.desconto = desconto;
	}
	
	public Categoria(String nomeDaCategoria) {
		this.nomeDaCategoria = nomeDaCategoria;

	}
	


	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeDaCategoria() {
		return nomeDaCategoria;
	}

	public void setNomeDaCategoria(String nomeDaCategoria) {
		this.nomeDaCategoria = nomeDaCategoria;
	}

	public int getDesconto() {
		return desconto;
	}

	public void setDesconto(int desconto) {
		this.desconto = desconto;
	}
	
	
}
