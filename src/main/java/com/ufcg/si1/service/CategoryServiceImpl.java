package com.ufcg.si1.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.si1.model.Category;
import com.ufcg.si1.repository.CategoryRepository;

@Service("CategoryService")
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryRepository categoryRep;
	
	public Category findById(Long id) {
		return categoryRep.findOne(id);
	}
	
	public List<Category> listCategories() {
		System.out.println();
		System.out.println("--------------------");
		System.out.println("-----CATEGORIES-----");
		System.out.println("--------------------");
		
		List<Category> all = categoryRep.findAll();
		for (Category category : all) {
			System.out.println(category.toString());
		}
		
		return all;
	}
	
	public Category addCategory(Category categoria) {
		return categoryRep.save(categoria);
	}
	
	public Category updateCategory(Category categoria) {
		return categoryRep.save(categoria);
	}
	
	public Category findCategoryByName(String nome) {
		return categoryRep.findByName(nome);
	}

}