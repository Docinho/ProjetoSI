package com.ufcg.si1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.si1.model.Sale;
import com.ufcg.si1.repository.SalesRepository;

@Service("SalesService")
public class SalesService {
	
	@Autowired
	private SalesRepository saleRep;
	
	public void addSale(Sale sale) {
		saleRep.save(sale);
	}
	
	public Sale findById(Long id) {
		return saleRep.findOne(id);
	}
	
	public List<Sale> findAll() {
		return saleRep.findAll();
	}

	public void delete(Long id) {
		 saleRep.delete(id);
	}

	public void save(Sale sale) {
		saleRep.save(sale);
	}

}
