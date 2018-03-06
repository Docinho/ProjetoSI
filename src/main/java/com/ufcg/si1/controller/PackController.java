//package com.ufcg.si1.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.ufcg.si1.model.Pack;
//import com.ufcg.si1.model.Product;
//import com.ufcg.si1.service.PackService;
//import com.ufcg.si1.service.ProductService;
//import com.ufcg.si1.util.CustomErrorType;
//
//import exceptions.ObjetoInvalidoException;
//
//@RestController
//@CrossOrigin
//@RequestMapping("/api")
//public class PackController {
//
//	@Autowired
//	ProductService productService;
//
//	@Autowired
//	PackService packService;
//
//	@RequestMapping(value = "/product/{id}/pack/", method = RequestMethod.POST)
//	public ResponseEntity<?> criarLote(@PathVariable("id") Long productId, @RequestBody Pack pack) {
//		Product product = productService.findById(productId);
//
//		if (product == null) {
//			return new ResponseEntity(
//					new CustomErrorType("Unable to create lote. Produto with id " + productId + " not found."),
//					HttpStatus.NOT_FOUND);
//		}
//
//		// Lote lote = loteService.saveLote(new Lote(product,
//		// loteDTO.getNumeroDeItens(), loteDTO.getDataDeValidade()));
//		pack.setProduct(product);
//		product.getPacks().add(pack);
//		packService.savePack(pack);
//
//		try {
//			if (product.getSituation() == Product.UNAVAILABLE) {
//				if (pack.getItemNumber() > 0) {
//					Product availableProduct = product;
//					availableProduct.situation = Product.AVAILABLE;
//					productService.updateProduct(availableProduct);
//				}
//			}
//		} catch (ObjetoInvalidoException e) {
//			e.printStackTrace();
//		}
//
//		return new ResponseEntity<>(pack, HttpStatus.CREATED);
//	}
//
//	@RequestMapping(value = "/pack/", method = RequestMethod.GET)
//	public ResponseEntity<List<Pack>> listAllLotess() {
//		List<Pack> lotes = packService.findAllPacks();
//		return new ResponseEntity<List<Pack>>(lotes, HttpStatus.OK);
//	}
//
//}
