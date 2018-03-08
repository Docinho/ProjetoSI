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
public class Pack implements Comparable<Pack>, Serializable, PackPlan {

	@Transient
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	
	private int itemNumber;
	private String expirationDate;
	
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

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String newExpirationDate) {
		this.expirationDate = newExpirationDate;
	}

	@Override
	public int compareTo(Pack otherPack) {
		String[] data = this.expirationDate.split("/");
		String[] otherData = otherPack.getExpirationDate().split("/");
		if (data[2].compareTo(otherData[2]) > 0) {
			return 1;
		}
		if (data[2].compareTo(otherData[2]) < 0) {
			return 1;
		}
		return 0;
	}
}