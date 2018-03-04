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

import com.ufcg.si1.model.Lote;
import com.ufcg.si1.model.Produto;
import com.ufcg.si1.model.DTO.LoteDTO;
import com.ufcg.si1.service.LoteService;
import com.ufcg.si1.service.ProdutoService;
import com.ufcg.si1.util.CustomErrorType;

import exceptions.ObjetoInvalidoException;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class LoteController {
	
	@Autowired
	ProdutoService produtoService;
	
	@Autowired
	LoteService loteService;
	
	@RequestMapping(value = "/produto/{id}/lote/", method = RequestMethod.POST)
	public ResponseEntity<?> criarLote(@PathVariable("id") Long produtoId, @RequestBody Lote lote) {
		Produto product = produtoService.findById(produtoId);

		if (product == null) {
			return new ResponseEntity(
					new CustomErrorType("Unable to create lote. Produto with id " + produtoId + " not found."),
					HttpStatus.NOT_FOUND);
		}

//		Lote lote = loteService.saveLote(new Lote(product, loteDTO.getNumeroDeItens(), loteDTO.getDataDeValidade()));
		lote.setProduto(product);
		product.getLotes().add(lote);
		loteService.saveLote(lote);

		try {
			if (product.getSituacao() == Produto.INDISPONIVEL) {
				if (lote.getNumeroDeItens() > 0) {
					Produto produtoDisponivel = product;
					produtoDisponivel.situacao = Produto.DISPONIVEL;
					produtoService.updateProduto(produtoDisponivel);
				}
			}
		} catch (ObjetoInvalidoException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>(lote, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/lote/", method = RequestMethod.GET)
	public ResponseEntity<List<Lote>> listAllLotess() {
		List<Lote> lotes = loteService.findAllLotes();
		return new ResponseEntity<List<Lote>>(lotes, HttpStatus.OK);
	}

}
