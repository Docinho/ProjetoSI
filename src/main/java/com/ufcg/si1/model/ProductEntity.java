package com.ufcg.si1.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	
	@OneToOne(cascade = {CascadeType.ALL})
	private Product product;
	
	@JsonBackReference
	@ManyToOne(cascade = {CascadeType.ALL})
	private Category category;
	
	private int quantity;
	
	private int situation;
	
	public ProductEntity (Product prod, Category cat) {
		this.product = prod;
		this.category = cat;
		this.quantity = 0;
		this.situation = UNAVAILABLE;
	}
	
	public ProductEntity() {
		
	}
	
	public Long getId() {
		return product.getId();
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
		BigDecimal temp1 = product.getPrice();
		BigDecimal temp2 = product.getPrice();
		temp1 = temp1.multiply(new BigDecimal(100));
		temp2 = temp1.multiply(new BigDecimal(getDiscount()));
		
		temp1 = temp1.subtract(temp2);
		temp1 = temp1.divide(new BigDecimal(100));
		
		return temp1; // RETORNA PRODUTO JÁ COM DESCONTO
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
	
	public int updateSituation() {
		int situation = UNAVAILABLE;
		for (Pack pack : packs) {
			if (pack.isAvailable() == AVAILABLE) {
				situation = AVAILABLE;
			}
		}
		return situation;
	}

	public void addPack(Pack pack) {
		this.packs.add(pack);
		if (pack.isAvailable() == 1) {
			this.quantity += pack.getItemNumber();
		}
		Collections.sort(packs);
		this.situation = updateSituation();
	}
	
	public int getSituation() {
		return this.situation;
	}
	
	public List<Pack> getPacks() {
		return this.packs;
	}
	
	public int makeSell(int quantity) {
		int i = 0;
		int madeSell = 0;
		if (this.quantity >= quantity) {
			int maxAble = 0;
			madeSell = 1;
			while (i < this.packs.size()) {
				Pack thisPack = packs.get(i);
				if (thisPack.isAvailable() == AVAILABLE) {
					maxAble = thisPack.getItemNumber();
					this.quantity -= maxAble;
					quantity -= maxAble;
					madeSell = thisPack.makeSell(maxAble, this.getPrice());
				}
				i++;
			}
		} 
		return madeSell;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public String toString() {
		String r = "---PRODUTO---\nID: " + getId();
		r+= "\nNome: " + getProductName();
		r+= "\nQuantidade: " + getQuantity();
		r+= "\nSituacao: " + getSituation();
		r += "---LOTES---";
		for (int i = 0; i < this.packs.size(); i++) {
			r += "\n" + this.packs.get(i).toString();
		}
		
		return r;
	}
	
}
