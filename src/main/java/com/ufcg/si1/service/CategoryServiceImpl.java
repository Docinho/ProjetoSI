package com.ufcg.si1.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.si1.model.Category;
import com.ufcg.si1.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryRepository category;
	
	public Category findById(Long id) {
		return category.findOne(id);
	}
	
	public List<Category> listCategories() {
		return category.findAll();
	}
	
	public Category addCategory(Category categoria) {
		return category.save(categoria);
	}
	
	public Category updateCategory(Category categoria) {
		return category.save(categoria);
	}
	
	public Category findCategoryByName(String nome) {
		return category.findByName(nome);
	}

}