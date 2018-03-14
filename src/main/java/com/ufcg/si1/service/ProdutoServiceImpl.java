package com.ufcg.si1.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.si1.model.Product;
import com.ufcg.si1.repository.ProductRepository;

@Service("productService")
public class ProdutoServiceImpl implements ProductService {
	
	@Autowired
	ProductRepository productRep;

	@Override
	public List<Product> findAllProducts() {
		return productRep.findAll();
	}

	@Override
	public void saveProduct(Product product) {
		productRep.save(product);
		
	}

	@Override
	public Product findById(long id) {
		return productRep.findOne(id);
	}

	@Override
	public void updateProduct(Product product) {
		productRep.save(product);
	}

	@Override
	public void deleteProductById(long id) {
		Product product = findById(id);
		productRep.delete(product);
		
	}

	@Override
	public int size() {
		return productRep.findAll().size();
	}

	@Override
	public boolean doesProductExists(Product product) {
		if(productRep.findOne(product.getId()) != null) {
			return true;
		}
		
		return false;
	}

	@Override
	public Product updateProduct(Long id, String newName, BigDecimal newPrice) {
		 Product found = findById(id);
		 found.update(newName, newPrice);
		 saveProduct(found);
		 return found;
	}


}
