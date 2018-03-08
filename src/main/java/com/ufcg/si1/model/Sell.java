package com.ufcg.si1.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Sell {

	@Transient
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	private int quantity;
	private BigDecimal value; 
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	private Pack pack;
	
	public Sell() {
	}

	public Sell(int quantity, BigDecimal price) {
		this.quantity = quantity;
		this.value = price.multiply(new BigDecimal(quantity));
	}
	
	public BigDecimal getValue() {
		return this.value;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
