package com.ufcg.si1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.si1.model.Product;
import com.ufcg.si1.service.ProductService;

@RestController
@CrossOrigin
public class UserProductsController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/userProducts", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> listProducts() {
		List<Product> products = productService.findAllProducts();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
}
