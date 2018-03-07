package com.ufcg.si1.model.DTO;

public class PackDTO {

	private int numeroDeItens;
	private String dataDeValidade;

	public PackDTO() {
	}

	public PackDTO(int numeroDeItens, String dataDeValidade) {
		super();
		this.numeroDeItens = numeroDeItens;
		this.dataDeValidade = dataDeValidade;
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
}
