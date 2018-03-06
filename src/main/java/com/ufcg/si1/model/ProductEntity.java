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
public class ProductEntity implements Serializable, CategoryPlan, ProductPlan {//, PackPlan {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;

	@Transient
	private static final int AVAILABLE = 1;
	
	@Transient
	private static final int UNAVAILABLE = 2;

	@JsonManagedReference
	@OneToMany(mappedBy = "entity")
	private List<Pack> packs;
	
//	@JsonManagedReference
//	private List<Pack> unavailablePacks;
	
	@OneToOne(cascade = {CascadeType.ALL})
	private Product product;
	
	@OneToOne(cascade = {CascadeType.ALL})
	private Category category;
	
	public ProductEntity (Product prod, Category cat) {
		this.product = prod;
		this.category = cat;
	}
	
	public ProductEntity() {
		
	}

	@Override
	public void setDiscount(int discount) {
		category.setDiscount(discount);
	}

	@Override
	public int getDiscount() {
		return category.getDiscount();
	}

	@Override
	public String getCategoryName() {
		return category.getCategoryName();
	}

	@Override
	public void setCategoryName(String name) {
		category.setCategoryName(name);
		
	}

	@Override
	public String getProductName() {
		return product.getProductName();
	}

	@Override
	public void setProductName(String name) {
		product.setProductName(name);
	}

	@Override
	public void setPrice(BigDecimal price) {
		product.setPrice(price);
	}

	@Override
	public BigDecimal getPrice() {
		return product.getPrice();
	}

	@Override
	public String getManufacturer() {
		return product.getManufacturer();
	}

	@Override
	public void setManufacturer(String manufacturer) {
		product.setManufacturer(manufacturer);
		
	}

	@Override
	public void setBarCode(String barCode) {
		product.setBarCode(barCode);
	}

	@Override
	public String getBarCode() {
		return product.getBarCode();
	}

	@Override
	public void update(String newName, BigDecimal newPrice) {
		product.update(newName, newPrice);
	}
	
	public int getSituation() {
		return 2;
	}

	public void addPack(Pack pack) {
		packs.add(pack);
	}
	
	public List<Pack> getPacks() {
		return packs;
	}
	
}
