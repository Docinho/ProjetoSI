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

import com.ufcg.si1.model.Produto;
import com.ufcg.si1.service.ProdutoService;
import com.ufcg.si1.util.CustomErrorType;

import exceptions.ObjetoInvalidoException;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProdutoController {
	

	@Autowired
	ProdutoService produtoService;
	

	@RequestMapping(value = "/produto/", method = RequestMethod.GET)
	public ResponseEntity<List<Produto>> listarProdutos() {
		List<Produto> produtos = produtoService.findAllProdutos();
		return new ResponseEntity<List<Produto>>(produtos, HttpStatus.OK);
	}

	// -------------------Criar um
	// Produto-------------------------------------------

	@RequestMapping(value = "/produto/", method = RequestMethod.POST)
	public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto, UriComponentsBuilder ucBuilder) {

		boolean produtoExiste = false;

		for (Produto p : produtoService.findAllProdutos()) {
			if (p.getCodigoBarra().equals(produto.getCodigoBarra())) {
				produtoExiste = true;
			}
		}

		if (produtoExiste) {
			return new ResponseEntity(new CustomErrorType("O produto " + produto.getNome() + " do fabricante "
					+ produto.getFabricante() + " ja esta cadastrado!"), HttpStatus.CONFLICT);
		}

		try {
			produto.mudaSituacao(Produto.INDISPONIVEL);
		} catch (ObjetoInvalidoException e) {
			return new ResponseEntity(new CustomErrorType("Error: Produto" + produto.getNome() + " do fabricante "
					+ produto.getFabricante() + " alguma coisa errada aconteceu!"), HttpStatus.NOT_ACCEPTABLE);
		}

		produtoService.saveProduto(produto);
		System.out.println("Cadastrou o produto" + produto.getNome());

		// HttpHeaders headers = new HttpHeaders();
		// headers.setLocation(ucBuilder.path("/api/produto/{id}").buildAndExpand(produto.getId()).toUri());

		return new ResponseEntity<Produto>(produto, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/produto/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> consultarProduto(@PathVariable("id") Long id) {

		Produto p = null;

		for (Produto produto : produtoService.findAllProdutos()) {
			if (produto.getId() == id) {
				p = produto;
			}
		}

		if (p == null) {
			return new ResponseEntity(new CustomErrorType("Produto with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Produto>(p, HttpStatus.OK);
	}

	@RequestMapping(value = "/produto/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateProduto(@PathVariable("id") Long id, @RequestBody Produto produto) {

		Produto currentProduto = null;

		for (Produto p : produtoService.findAllProdutos()) {
			if (p.getId() == id) {
				currentProduto = p;
			}
		}

		if (currentProduto == null) {
			return new ResponseEntity(new CustomErrorType("Unable to upate. Produto with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentProduto.update(produto);

		// resolvi criar um servi�o na API s� para mudar a situa��o do produto
		// esse c�digo n�o precisa mais
		// try {
		// currentProduto.mudaSituacao(produto.pegaSituacao());
		// } catch (ObjetoInvalidoException e) {
		// return new ResponseEntity(new CustomErrorType("Unable to upate. Produto with
		// id " + id + " invalid."),
		// HttpStatus.NOT_FOUND);
		// }

		produtoService.updateProduto(currentProduto);
		return new ResponseEntity<Produto>(currentProduto, HttpStatus.OK);
	}

	@RequestMapping(value = "/produto/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteProduto(@PathVariable("id") Long id) {

		Produto produtoDeletado = produtoService.findById(id);

		

		if (produtoDeletado == null) {
			return new ResponseEntity(new CustomErrorType("Unable to delete. Produto with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		produtoService.deleteProdutoById(id);
		return new ResponseEntity<Produto>(HttpStatus.NO_CONTENT);
	}

}
