package com.ufcg.si1.service;

import java.util.Iterator;
import java.util.List;

import com.ufcg.si1.model.Product;

public interface ProductService {

	List<Product> findAllProducts();

	void saveProduct(Product product);

	Product findById(long id);

	void updateProduct(Product prod);

	void deleteProductById(long id);

	int size();

	Iterator<Product> getIterator();

	boolean doesProductExists(Product product);
}
