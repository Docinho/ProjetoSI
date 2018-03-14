package com.ufcg.si1.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Sale {

	@Transient
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	private int quantity;
	private BigDecimal value; 
	private int cancelled;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private ProductEntity entitySale;
	
	@ManyToMany
	@JoinTable(name = "SALE_PACK",
			joinColumns=
            @JoinColumn(name="SALE_ID", referencedColumnName="ID"),
        inverseJoinColumns=
            @JoinColumn(name="PACK_ID", referencedColumnName="ID")
        )
	private List<Pack> packs = new ArrayList<Pack>();
	
	public Sale() {
	}

	public Sale(int quantity) {
		this.quantity = quantity;
		this.cancelled = 0;
	}
	
	public Sale(int quantity, BigDecimal price) {
		this.cancelled = 0;
		this.quantity = quantity;
		this.value = price.multiply(new BigDecimal(quantity));
	}
	
	public Long getId() {
		return this.id;
	}
	
	public ProductEntity getEntitySale() {
		return entitySale;
	}

	public void setEntitySale(ProductEntity entitySale) {
		this.entitySale = entitySale;
	}

	public void cancelSale(Pack pack) {
		this.cancelled = 1;
	}
	
	public int getCancelled() {
		return this.cancelled;
	}
	
	public void setCancelled() {
		this.cancelled = 1;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Sale other = (Sale) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void addPack(Pack thisPack) {
		 this.packs.add(thisPack);
	}

	public List<Pack> getPacks() {
		return this.packs;
	}

	public void setPrice(BigDecimal price) {
		 this.value = price.multiply(new BigDecimal(this.quantity));
	}
	
}
