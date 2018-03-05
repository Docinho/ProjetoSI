package com.ufcg.si1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.si1.model.Category;
import com.ufcg.si1.service.CategoryService;
import com.ufcg.si1.service.ProductService;
import com.ufcg.si1.util.CustomErrorType;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class CategoriaController {

	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	/**
	 * 
	 * @param id
	 *            Id do produto na qual a categoria sera alterada
	 * @param discount
	 *            Novo desconto que sera atribuido a categoria, esse desconto deve
	 *            vir na url da requisicao
	 * 
	 * @return
	 */
	@RequestMapping(value = "/category/{id}/{discount}", method = RequestMethod.GET)
	public ResponseEntity<Category> difineDiscount(@PathVariable Long id, @PathVariable int discount) {
		Category category = categoryService.findById(id);
		if (category != null) {

			category.setDiscount(discount);
			categoryService.updateCategory(category);

			return new ResponseEntity<Category>(category, HttpStatus.CREATED);

		} else {
			return new ResponseEntity(new CustomErrorType("Unable to upate. Produto with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "/category/", method = RequestMethod.GET)
	public ResponseEntity<List<Category>> listCategories() {
		List<Category> categories = categoryService.listCategories();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

}