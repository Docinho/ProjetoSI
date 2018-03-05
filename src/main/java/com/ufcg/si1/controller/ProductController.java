package com.ufcg.si1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ufcg.si1.model.Product;
import com.ufcg.si1.service.ProductService;
import com.ufcg.si1.util.CustomErrorType;

import exceptions.ObjetoInvalidoException;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

	@Autowired
	ProductService productService;

	@RequestMapping(value = "/product/", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> listProducts() {
		List<Product> products = productService.findAllProducts();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	// -------------------Criar um
	// Produto-------------------------------------------

	@RequestMapping(value = "/product/", method = RequestMethod.POST)
	public ResponseEntity<Product> createProduct(@RequestBody Product product, UriComponentsBuilder ucBuilder) {

		boolean productExists = false;

		for (Product p : productService.findAllProducts()) {
			if (p.getBarCode().equals(product.getBarCode())) {
				productExists = true;
			}
		}

		if (productExists) {
			return new ResponseEntity(new CustomErrorType("O produto " + product.getName() + " do fabricante "
					+ product.getManufacturer() + " ja esta cadastrado!"), HttpStatus.CONFLICT);
		}

		try {
			product.setSituation(Product.UNAVAILABLE);
		} catch (ObjetoInvalidoException e) {
			return new ResponseEntity(
					new CustomErrorType("Error: Produto" + product.getName() + " do fabricante "
							+ product.getManufacturer() + " alguma coisa errada aconteceu!"),
					HttpStatus.NOT_ACCEPTABLE);
		}

		productService.saveProduct(product);
		System.out.println("Cadastrou o produto" + product.getName());

		// HttpHeaders headers = new HttpHeaders();
		// headers.setLocation(ucBuilder.path("/api/product/{id}").buildAndExpand(product.getId()).toUri());

		return new ResponseEntity<Product>(product, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/produto/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> consultarProduto(@PathVariable("id") Long id) {

		Product p = productService.findById(id); // REFACTOR TO FIX COHESION

		if (p == null) {
			return new ResponseEntity(new CustomErrorType("Produto with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Product>(p, HttpStatus.OK);
	}

	@RequestMapping(value = "/product/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {

		Product currentProduct = productService.findById(id); // REFACTOR TO FIX COHESION

		if (currentProduct == null) {
			return new ResponseEntity(new CustomErrorType("Unable to upate. Produto with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentProduct.update(product);

		// resolvi criar um servi�o na API s� para mudar a situa��o do produto
		// esse c�digo n�o precisa mais
		// try {
		// currentProduto.mudaSituacao(produto.pegaSituacao());
		// } catch (ObjetoInvalidoException e) {
		// return new ResponseEntity(new CustomErrorType("Unable to upate. Produto with
		// id " + id + " invalid."),
		// HttpStatus.NOT_FOUND);
		// }

		productService.updateProduct(currentProduct);
		return new ResponseEntity<Product>(currentProduct, HttpStatus.OK);
	}

	@RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteProduto(@PathVariable("id") Long id) {

		Product produtoDeletado = productService.findById(id);

		if (produtoDeletado == null) {
			return new ResponseEntity(new CustomErrorType("Unable to delete. Produto with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		productService.deleteProductById(id);
		return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
	}

}
