package com.ufcg.si1.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Pack implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	@JsonBackReference
	@ManyToOne( cascade = { CascadeType.ALL })
	@JoinColumn(name = "Product_id")
	private Product product;
	private int itemNumber;
	private String expirationDate;

	public Pack(Product product, int itemNumber, String expirationDate) {
		super();
		this.product = product;
		this.itemNumber = itemNumber;
		this.expirationDate = expirationDate;
	}

	public Pack(Long id, Product product, int itemNumber, String expirationDate) {
		this.id = id;
		this.product = product;
		this.itemNumber = itemNumber;
		this.expirationDate = expirationDate;
	}

	public Pack() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(int newItemNumber) {
		this.itemNumber = newItemNumber;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String newExpirationDate) {
		this.expirationDate = newExpirationDate;
	}
}