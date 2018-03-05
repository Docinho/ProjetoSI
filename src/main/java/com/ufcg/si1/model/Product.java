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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import exceptions.ObjetoInvalidoException;

@Entity
public class Product implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private BigDecimal price;

	private String barCode;

	private String manufacturer;

	@JsonBackReference
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "Categoria_id")
	private Category category;

	private boolean isExpired;

	@JsonManagedReference
	@OneToMany(mappedBy = "product")
	private List<Pack> packs;

	public int situation; // usa variaveis estaticas abaixo
	/* situacoes do produto */
	@Transient
	public static final int AVAILABLE = 1;
	@Transient
	public static final int UNAVAILABLE = 2;

	public Product(Long id, String name, String barCode, String manufacturer, Category category,
			boolean isExpired) {
		this.id = id;
		this.name = name;
		this.price = new BigDecimal(0);
		this.barCode = barCode;
		this.manufacturer = manufacturer;
		this.category = category;
		this.situation = Product.UNAVAILABLE;
		this.isExpired = isExpired;
	}

	public Product() {

	}

	public boolean isExpired() {
		return isExpired;
	}

	public void setExpired(boolean isExpired) {
		this.isExpired = isExpired;
	}

	public List<Pack> getPacks() {
		return packs;
	}

	public void setPacks(List<Pack> packs) {
		this.packs = packs;
	}

	public String getName() {
		return name;
	}

	public void changeName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

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

	public void setManufacturer(String newManufacturer) {
		this.manufacturer = newManufacturer;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String newBarCode) {
		this.barCode = newBarCode;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category newCategory) {
		this.category = newCategory;
	}

	public void setSituation(int newSituation) throws ObjetoInvalidoException {
		switch (newSituation) {
		case 1:
			this.situation = Product.AVAILABLE;
			break;
		case 2:
			this.situation = Product.UNAVAILABLE;
			break;

		default:
			throw new ObjetoInvalidoException("Situacao Invalida");
		}
	}

	public int getSituation() throws ObjetoInvalidoException {
		return this.situation;
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

	public void update(Product product) {
		this.changeName(product.getName());
		this.setPrice(product.getPrice());
		this.setBarCode(product.getBarCode());
		this.setManufacturer(product.getManufacturer());
		this.setCategory(product.getCategory());
	}
}