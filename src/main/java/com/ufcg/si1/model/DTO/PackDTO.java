package com.ufcg.si1.model.DTO;

public class PackDTO {

	private int itemNumber;
	private String expirationDate;

	public PackDTO() {
	}

	public PackDTO(int itemNumber, String expirationDate) {
		super();
		this.itemNumber = itemNumber;
		this.expirationDate = expirationDate;
	}

	public int getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
}
