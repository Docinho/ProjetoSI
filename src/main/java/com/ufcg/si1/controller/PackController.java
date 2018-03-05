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

import com.ufcg.si1.model.Pack;
import com.ufcg.si1.model.Product;
import com.ufcg.si1.model.DTO.LoteDTO;
import com.ufcg.si1.service.PackService;
import com.ufcg.si1.service.ProductService;
import com.ufcg.si1.util.CustomErrorType;

import exceptions.ObjetoInvalidoException;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class PackController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	PackService packService;
	
	@RequestMapping(value = "/product/{id}/pack/", method = RequestMethod.POST)
	public ResponseEntity<?> criarLote(@PathVariable("id") Long produtoId, @RequestBody Pack lote) {
		Product product = productService.findById(produtoId);

		if (product == null) {
			return new ResponseEntity(
					new CustomErrorType("Unable to create lote. Produto with id " + produtoId + " not found."),
					HttpStatus.NOT_FOUND);
		}

//		Lote lote = loteService.saveLote(new Lote(product, loteDTO.getNumeroDeItens(), loteDTO.getDataDeValidade()));
		lote.setProduct(product);
		product.setPack(lote);
		packService.savePack(lote);

		try {
			if (product.getSituation() == Product.UNAVAILABLE) {
				if (lote.getItemNumber() > 0) {
					Product produtoDisponivel = product;
					produtoDisponivel.situacao = Product.AVAILABLE;
					productService.updateProduct(produtoDisponivel);
				}
			}
		} catch (ObjetoInvalidoException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>(lote, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/pack/", method = RequestMethod.GET)
	public ResponseEntity<List<Pack>> listAllLotess() {
		List<Pack> lotes = packService.findAllPacks();
		return new ResponseEntity<List<Pack>>(lotes, HttpStatus.OK);
	}

}
