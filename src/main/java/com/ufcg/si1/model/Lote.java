package com.ufcg.si1.model;

import java.io.Serializable;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Lote implements Serializable{

	@Transient
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
    private Long id;
	@JsonBackReference
	@OneToOne
	@JoinColumn(name="Produto_id")
    private Produto produto;
    private int numeroDeItens;
    private String dataDeValidade;


    public Lote(Produto produto, int numeroDeItens, String dataDeValidade) {
        super();
        this.produto = produto;
        this.numeroDeItens = numeroDeItens;
        this.dataDeValidade = dataDeValidade;
    }

    public Lote(Long id, Produto produto, int numeroDeItens, String dataDeValidade) {
        this.id = id;
        this.produto = produto;
        this.numeroDeItens = numeroDeItens;
        this.dataDeValidade = dataDeValidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getNumeroDeItens() {
        return numeroDeItens;
    }

    public void setNumeroDeItens(int numeroDeItens) {
        this.numeroDeItens = numeroDeItens;
    }

    public String getDataDeValidade() {
        return dataDeValidade;
    }

    public void setDataDeValidade(String dataDeValidade) {
        this.dataDeValidade = dataDeValidade;
    }

//    @Override
//    public String toString() {
//        return "Lote{" +
//                "id=" + id +
//                ", produto=" + produto.getId() +
//                ", numeroDeItens=" + numeroDeItens +
//                ", dataDeValidade='" + dataDeValidade + '\'' +
//                '}';
//    }
}
