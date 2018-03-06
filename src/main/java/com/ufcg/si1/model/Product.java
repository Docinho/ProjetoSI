package com.ufcg.si1.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import exceptions.ObjetoInvalidoException;

@Entity
public class Product implements Serializable, ProductPlan {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private BigDecimal price;

	private String barCode;

	private String manufacturer;
	
	@OneToOne
	private ProductEntity entity;

	public Product(Long id, String name, String barCode, String manufacturer) {
		this.id = id;
		this.name = name;
		this.price = new BigDecimal(0);
		this.barCode = barCode;
		this.manufacturer = manufacturer;
	}

	public Product() {

	}

	@Override
	public String getProductName() {
		return name;
	}

	@Override
	public void setProductName(String name) {
		this.name = name;
	}

	@Override
	public BigDecimal getPrice() {
		return price;
	}

	@Override
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long newId) {
		this.id = newId;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	@Override
	public void setManufacturer(String newManufacturer) {
		this.manufacturer = newManufacturer;
	}

	@Override
	public String getBarCode() {
		return barCode;
	}

	@Override
	public void setBarCode(String newBarCode) {
		this.barCode = newBarCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((manufacturer == null) ? 0 : manufacturer.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (manufacturer == null) {
			if (other.manufacturer != null)
				return false;
		} else if (!manufacturer.equals(other.manufacturer))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public void update(String newName, BigDecimal newPrice) {
		this.setProductName(newName);
		this.setPrice(newPrice);
	}
}