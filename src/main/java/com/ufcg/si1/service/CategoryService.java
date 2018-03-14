package com.ufcg.si1.service;

import java.util.List;

import com.ufcg.si1.model.Category;

public interface CategoryService {

	public Category findById(Long id);
	
	public List<Category> listCategories();
	
	public Category addCategory(Category categoria);
	
	public Category updateCategory(Category categoria);
	
	public Category findCategoryByName(String nome);
}
