package com.ufcg.si1.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.si1.model.Pack;
import com.ufcg.si1.model.ProductEntity;
import com.ufcg.si1.repository.ProductEntityRepository;

@Service("productEntityService")
public class ProductEntityServiceImpl {
	
	@Autowired
	private ProductEntityRepository prodEntRepository;

	public List<ProductEntity> findAllProducts() {
		List<ProductEntity> products = prodEntRepository.findAll();
		return products;
	}

	public ProductEntity findById(Long id) {
		return prodEntRepository.findOne(id);
	}
	
	public void update(Long id, String newName, BigDecimal newPrice) {
		ProductEntity prod = findById(id);
		prod.update(newName, newPrice);
		saveProduct(prod);
	}

	public void saveProduct(ProductEntity newProduct) {
		prodEntRepository.save(newProduct);
	}

	public List<Pack> findAllPacks() {
		List<Pack> allPacks = new ArrayList<Pack>();
		for (ProductEntity p : findAllProducts()) {
			allPacks.addAll(p.getPacks());
		}
		return allPacks;
	}
	
	public boolean productExists(String barCode) {
		boolean productExists = false;

		for (ProductEntity p : findAllProducts()) {
			if (p.getBarCode().equals(barCode)) {
				productExists = true;
			}
		}
		
		return productExists;
	}
	
	public void addPackToProduct(Long productId, Pack pack) {
		ProductEntity prod = findById(productId);
		if (prod != null) {
			prod.addPack(pack);
			prodEntRepository.save(prod);
		}
	}

	public void deleteProduct(Long id) {
		ProductEntity prod = findById(id);
		prodEntRepository.delete(prod);
	}
}
