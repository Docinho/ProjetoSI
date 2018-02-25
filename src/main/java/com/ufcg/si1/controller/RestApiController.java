package com.ufcg.si1.controller;

import java.util.List;

import com.ufcg.si1.model.DTO.LoteDTO;
import com.ufcg.si1.model.Admin;
import com.ufcg.si1.model.Lote;

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
import com.ufcg.si1.service.AdministradorService;
import com.ufcg.si1.service.ProdutoService;
import com.ufcg.si1.util.CustomErrorType;

import exceptions.ObjetoInvalidoException;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class RestApiController {

	@Autowired
	ProdutoService produtoService;
	LoteController loteController = new LoteController();
	@Autowired
	AdministradorService administradorService;
	
	Admin administradorLogado;

	@RequestMapping(method = RequestMethod.POST, value = "/adm")
	public ResponseEntity<Admin> cadastrarCliente(@RequestBody Admin adm) {
		boolean adminExiste = false;

		for (Admin a : administradorService.findAllAdmin()) {
			if (a.getLogin().equals(adm.getLogin())) {
				adminExiste = true;
			}
		}

		if (adminExiste) {
			return new ResponseEntity(new CustomErrorType("Já existe um usuario com o login " + adm.getLogin()),
					HttpStatus.CONFLICT);
		}

		administradorService.saveAdmin(adm);
		System.out.println("Cadastrou o admin" + adm.getLogin());

		return new ResponseEntity<Admin>(adm, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/adms/", method = RequestMethod.GET)
	public ResponseEntity<List<Admin>> listAllAdmins() {
		List<Admin> adms = administradorService.findAllAdmin();

		if (adms.isEmpty()) {
			return new ResponseEntity<List<Admin>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Admin>>(adms, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/adm")
	public ResponseEntity<Admin> logarCliente(@RequestBody Admin adm) {
		Admin administradorLogin = administradorService.findById(adm.getId());

		if (administradorLogin != null) {
			administradorLogado = administradorLogin;
			return new ResponseEntity<Admin>(administradorLogado, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@RequestMapping(value = "/produto/", method = RequestMethod.GET)
	public ResponseEntity<List<Produto>> listAllProducts() {
		List<Produto> produtos = produtoService.findAllProdutos();

		if (produtos.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Produto>>(produtos, HttpStatus.OK);
	}

	

	@RequestMapping(value = "/produto/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {

		Produto user = null;

		for (Produto produto : produtoService.findAllProdutos()) {
			if (produto.getId() == id) {
				user = produto;
			}
		}

		if (user == null) {
			return new ResponseEntity(new CustomErrorType("Unable to delete. Produto with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		produtoService.deleteProdutoById(id);
		return new ResponseEntity<Produto>(HttpStatus.NO_CONTENT);
	}

}
