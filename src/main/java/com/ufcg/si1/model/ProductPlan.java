package com.ufcg.si1.model;

import java.math.BigDecimal;

public interface ProductPlan {
	
	String getProductName();
	
	void setProductName(String name);
	
	void setPrice(BigDecimal price);
	
	BigDecimal getPrice();
	
	String getManufacturer();
	
	void setManufacturer(String manufacturer);
	
	void setBarCode(String barCode);
	
	String getBarCode();
	
	void update(String newName, BigDecimal newPrice);

}
