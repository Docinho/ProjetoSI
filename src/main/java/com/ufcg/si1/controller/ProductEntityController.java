package com.ufcg.si1.controller;

import java.math.BigDecimal;
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

import com.ufcg.si1.model.Category;
import com.ufcg.si1.model.Pack;
import com.ufcg.si1.model.Product;
import com.ufcg.si1.model.ProductEntity;
import com.ufcg.si1.model.Sale;
import com.ufcg.si1.service.CategoryService;
import com.ufcg.si1.service.PackService;
import com.ufcg.si1.service.ProductEntityServiceImpl;
import com.ufcg.si1.service.ProductService;
import com.ufcg.si1.service.SalesService;
import com.ufcg.si1.util.CustomErrorType;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductEntityController {
	
	@Autowired
	private ProductEntityServiceImpl prodEntService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private SalesService saleService;
	
	@Autowired
	private CategoryService catService;
	
	@Autowired
	private PackService packService;
	
	@RequestMapping(value = "/product/", method = RequestMethod.GET)
	public ResponseEntity<List<ProductEntity>> getProducts(){
		List<ProductEntity> products = prodEntService.findAllProducts();
		return new ResponseEntity<List<ProductEntity>>(products, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/product/{category}", method = RequestMethod.POST)
	public ResponseEntity<ProductEntity> createProduct(@RequestBody Product product, @PathVariable("category") String category, UriComponentsBuilder ucBuilder) {
		String categoryName = category;
		Category categoryAux = catService.findCategoryByName(categoryName);
		
		if(categoryAux == null) {
			categoryAux = new Category(categoryName, 0);
			catService.addCategory(categoryAux);
		} 
		
		boolean productExists = prodEntService.productExists(product.getBarCode());

		if (productExists) {
			return new ResponseEntity(new CustomErrorType("O produto " + product.getProductName() + " do fabricante "
					+ product.getManufacturer() + " ja esta cadastrado!"), HttpStatus.CONFLICT);
		}
		
		ProductEntity newProduct = new ProductEntity(product, categoryAux);
		prodEntService.saveProduct(newProduct);
		System.out.println("Cadastrou o produto " + newProduct.getProductName());

		return new ResponseEntity<ProductEntity>(newProduct, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findProduct(@PathVariable("id") Long id) {

		ProductEntity p = prodEntService.findById(id); // REFACTOR TO FIX COHESION

		if (p == null) {
			return new ResponseEntity(new CustomErrorType("Product with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ProductEntity>(p, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/product/{id}/{newName}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateProduct(@PathVariable("id") Long id, @PathVariable("newName") String newName, @RequestBody BigDecimal newPrice) {
		Product currentProduct = productService.updateProduct(id, newName, newPrice);
		return new ResponseEntity<Product>(currentProduct, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/product/{id}/pack/", method = RequestMethod.POST)
	public ResponseEntity<?> createPack(@PathVariable("id") Long productId, @RequestBody Pack pack) {
		ProductEntity prod = prodEntService.findById(productId);
		if (prod == null) {
			return new ResponseEntity(
					new CustomErrorType("Unable to create pack. Product with id " + productId + " not found."),
					HttpStatus.NOT_FOUND);
		}
		
		pack.setProduct(prod);
		packService.savePack(pack);
		prodEntService.addPackToProduct(prod.getId(), pack);
		return new ResponseEntity<ProductEntity>(prod, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/pack/", method = RequestMethod.GET)
	public ResponseEntity<List<Pack>> listAllPacks() {
		List<Pack> packs = packService.findAllPacks();
		return new ResponseEntity<List<Pack>>(packs, HttpStatus.OK);
	}

	@RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {

		ProductEntity toDelete = prodEntService.findById(id);

		if (toDelete == null) {
			return new ResponseEntity(new CustomErrorType("Unable to delete. Produto with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		prodEntService.deleteProduct(id);
		return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/product/sales/", method = RequestMethod.GET)
	public ResponseEntity<List<Sale>> getAllSales() {
		return new ResponseEntity<List<Sale>>(saleService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/product/{id}/sales/", method = RequestMethod.GET)
	public ResponseEntity<List<Sale>> getProductSales(@PathVariable("id") Long id) {
		ProductEntity found = prodEntService.findById(id);
		if (found == null) {
			return new ResponseEntity(new CustomErrorType("Product with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Sale>>(found.getSales(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/product/{id}/sales/", method = RequestMethod.POST)
	public ResponseEntity<List<Sale>> makeSale(@PathVariable("id") Long id, @RequestBody int sale) {
		ProductEntity found = prodEntService.findById(id);
		if (found == null) {
			return new ResponseEntity(new CustomErrorType("Product with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		
		Sale thisSale = new Sale(sale);
		thisSale.setEntitySale(found);
		saleService.addSale(thisSale);
		
		int res = found.makeSell(thisSale);
		if (res == 0) {	
			return new ResponseEntity(new CustomErrorType("Impossible to sell " + thisSale.getQuantity() + "of " + found.getProductName()),
					HttpStatus.NOT_ACCEPTABLE);
		}
		prodEntService.saveProduct(found);
		
		return new ResponseEntity<List<Sale>>(found.getSales(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/product/{pid}/sales/", method = RequestMethod.PUT)
	public ResponseEntity<ProductEntity> deleteSale(@PathVariable("pid") Long pid, @RequestBody Sale sale) {
		ProductEntity found = prodEntService.findById(pid);
		if (found == null) {
			return new ResponseEntity(new CustomErrorType("Product with id " + pid + " not found."),
					HttpStatus.NOT_FOUND);
		}
		
		found.cancelSale(sale);
		saleService.save(sale);
		prodEntService.saveProduct(found);
		return new ResponseEntity<ProductEntity>(found, HttpStatus.OK);
	}
}
