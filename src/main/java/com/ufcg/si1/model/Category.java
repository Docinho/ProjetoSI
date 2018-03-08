package com.ufcg.si1.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Category implements Serializable, CategoryPlan {

	@Transient
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private int discount;
	
	@Transient
	private static final int DESCONTO_ZERO = 0;
	
	@Transient
	private static final int DESCONTO_UM = 10;
	
	@Transient
	private static final int DESCONTO_DOIS = 25;
	
	@Transient
	private static final int DESCONTO_TRES = 50;
	
	@OneToMany(mappedBy = "category")
	private List<ProductEntity> entity;

	public Category() {
	}

	public Category(String name, int discount) {
		this.name = name;
		if (discount == 0) {
			this.discount = Category.DESCONTO_ZERO;
		} else if (discount == 1) {
			this.discount = Category.DESCONTO_UM;
		} else if (discount == 2) {
			this.discount = Category.DESCONTO_DOIS;
		} else {
			this.discount = Category.DESCONTO_TRES;
		}
	}

	public Category(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return name;
	}

	public void setCategoryName(String name) {
		this.name = name;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
	@Override
	public String toString() {
		String r = "ID: "+ id + "\nCategoria: " + name + "\nDesconto: " + discount;
		
		
		
		
		
		
		return r;
	}
	
	
}