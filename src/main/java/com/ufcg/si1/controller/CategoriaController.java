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

import com.ufcg.si1.model.Categoria;
import com.ufcg.si1.model.Lote;
import com.ufcg.si1.model.Produto;
import com.ufcg.si1.service.CategoriaService;
import com.ufcg.si1.service.ProdutoService;
import com.ufcg.si1.util.CustomErrorType;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class CategoriaController {
	
	@Autowired
	ProdutoService produtoService;
	
	@Autowired
	CategoriaService categoriaService;
	
	
	
	/**
	 * 
	 * @param id
	 * Id do produto na qual a categoria sera alterada
	 * @param desconto
	 * Novo desconto que sera atribuido a categoria, esse desconto deve vir na url da requisicao
	 * 
	 * @return
	 */
	@RequestMapping(value = "/produto/{id}/categoria/{desconto}", method = RequestMethod.POST)
	public ResponseEntity<Produto> definirCategoria(@PathVariable Long id, @PathVariable int desconto) {
		Produto produtoEncontrado = produtoService.findById(id);
		
		if(produtoEncontrado != null ) {
			
			produtoEncontrado.getCategoria().setDesconto(desconto);
			produtoService.updateProduto(produtoEncontrado);
			return new ResponseEntity<Produto>(produtoEncontrado, HttpStatus.CREATED);

		} else {
			return new ResponseEntity(new CustomErrorType("Unable to upate. Produto with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
	
	}
	
	
	@RequestMapping(value = "/categorias/", method = RequestMethod.GET)
	public ResponseEntity<List<Categoria>> listarCategorias() {
		List<Categoria> categorias = categoriaService.listarCategorias();
		return new ResponseEntity<>(categorias, HttpStatus.OK);
	}

}
