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

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "entity")
	private List<Pack> packs;
	
	@OneToOne(cascade = {CascadeType.ALL})
	private Product product;
	
	@JsonBackReference
	@ManyToOne(cascade = {CascadeType.ALL})
	private Category category;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "entitySale")
	private List<Sale> sales;
	
	private int quantity;
	
	private int situation;
	
	public ProductEntity (Product prod, Category cat) {
		this.product = prod;
		prod.setEntity(this);
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
	
	public BigDecimal getProfit() {
		BigDecimal profit = new BigDecimal(0);
		if (sales != null) {
			for (Sale sale : sales) {
				profit.add(sale.getValue());
			}
		}
		return profit;
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
	
	public int makeSell(Sale sale) {
		int i = 0;
		int madeSell = 0;
		int quantity = sale.getQuantity();
		if (this.quantity >= quantity) {
			sale.setPrice(getPrice());
			int maxAble = 0;
			madeSell = 1;
			while (i < this.packs.size() && quantity != 0) {
				Pack thisPack = packs.get(i);
				if (thisPack.isAvailable() == AVAILABLE) {
					maxAble = thisPack.getItemNumber();
					if (quantity > maxAble) {
						this.quantity -= maxAble;
						quantity -= maxAble;
						thisPack.makeSell(maxAble, sale);
					} else {
						this.quantity -= quantity;
						thisPack.makeSell(quantity, sale);
						quantity = 0;
					}
				}
				sale.addPack(thisPack);
				i++;
			}
			this.sales.add(sale);
		} 
		return madeSell;
	}

	public void cancelSell(Sale sale) {
		this.sales.remove(sale);
		for (Pack p : sale.getPacks()) {
			p.cancelSell(sale);
		}
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
	
	public int getAlert() {
		boolean shAlPack = shouldAlertPack();
		if (this.quantity > 15 && !shAlPack) {
			return 0; // NO ALERTS
		} else if (this.quantity <= 15 && !shAlPack) {
			return 1; // ALERT QUANTITY
		} else if (this.quantity > 15 && shAlPack) {
			return 2; // ALERT EXPIRATION
		} else {
			return 3; // ALERT BOTH QUANTITY AND EXPIRATION
		}
	}

	private boolean shouldAlertPack() {
		if (this.packs != null) {
			for (Pack pack : packs) {
				if (pack.isCloseToExpire() == 1) {
					return true;
				}
			}
		}
		return false;
	}

	
	public List<Sale> getSales() {
		return this.sales;
	}

}
