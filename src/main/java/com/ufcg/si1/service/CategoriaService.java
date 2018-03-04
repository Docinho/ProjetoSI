package com.ufcg.si1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.si1.model.Categoria;
import com.ufcg.si1.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	CategoriaRepository categoriaRep;
	
	public Categoria procurarPorId(Long id) {
		return categoriaRep.findOne(id);
	}
	
	public List<Categoria> listarCategorias() {
		return categoriaRep.findAll();
	}
	
	public Categoria cadastrarCategoria(Categoria categoria) {
		return categoriaRep.save(categoria);
	}
	
	public Categoria atualizarCategoria(Categoria categoria) {
		return categoriaRep.save(categoria);
	}
	
	public Categoria procurarPorNome(String nome) {
		return categoriaRep.buscarPorNome(nome);
	}

}
