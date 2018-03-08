package com.ufcg.si1.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Pack implements Comparable<Pack>, Serializable, PackPlan {

	@Transient
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	
	private int itemNumber;
	private String expirationDate;
	
	@OneToMany(mappedBy = "pack")
	private List<Sell> sells; 
	
	@JsonBackReference
	@ManyToOne(cascade = {CascadeType.ALL})
	private ProductEntity entity;

	public Pack(int itemNumber, String expirationDate) {
		super();
		this.itemNumber = itemNumber;
		this.expirationDate = expirationDate;
	}

	public Pack(Long id, Product product, int itemNumber, String expirationDate) {
		this.id = id;
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

	public int getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(int newItemNumber) {
		this.itemNumber = newItemNumber;
	}
	
//	public Calendar getDate() {
//		return this.expirationDate;
//	}

	public String getExpirationDate() {
		return this.expirationDate;
	}

	public void setExpirationDate(String newExpirationDate) {
		this.expirationDate = newExpirationDate;
	}
	
	public int daysToExpire() {
		Date now = new Date();
		Calendar aux = stringToCalendar(expirationDate);
		long diff = aux.getTime().getTime() - now.getTime();
		int days = (int) (diff / (1000 * 60 * 60 * 24));
		return days;
	}

	@Override
	public int compareTo(Pack otherPack) {
		Calendar aux1 = stringToCalendar(otherPack.getExpirationDate());
		Calendar aux2 = stringToCalendar(this.expirationDate);
		return aux2.compareTo(aux1);
	}
	
	public int makeSell(int quantity, BigDecimal price) {
		if (this.itemNumber - quantity >= 0) {
			this.itemNumber -= quantity;
			this.sells.add(new Sell(quantity, price));
			return 1;
		} else {
			return 0;
		}
	}
	
	@Transient
	private Calendar stringToCalendar(String time) {
		String[] formatDate = time.split("/");
		int[] date = new int[formatDate.length];
		for (int i = 0; i < formatDate.length; i++) {
			date[i] = Integer.parseInt(formatDate[i]);
		}
		Calendar aux = Calendar.getInstance();
		aux.set(date[2], date[1]-1, date[0]);
		return aux;
	}
	
	public int isCloseToExpire() {
		int r = 0;
		if (this.daysToExpire() <= 30 && this.daysToExpire() >= 0)
			r = 1;
		return r;
	}
	
	private boolean isExpired() {
		return daysToExpire() < 0;
	}
	
	private boolean hasItems() {
		return this.itemNumber > 0;
	}
	
	public int isAvailable() {
		if (!isExpired() && hasItems()) {
			return 1;
		}
		return 0;
	}

	public void setProduct(ProductEntity prod) {
		this.entity = prod;
	}
	
	@Override
	public String toString() {
		String r = "ID: " + this.id + "\n";
		r += "Itens: " + this.itemNumber + "\n";
		r += "Validade: " + this.expirationDate + "\n";
		r += "Disponivel: " + isAvailable();
		
		return r;
	}
	
	
}