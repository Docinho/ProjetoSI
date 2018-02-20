package com.ufcg.si1.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.si1.model.Produto;
import com.ufcg.si1.repository.ProdutoRepository;

@Service("produtoService")
public class ProdutoServiceImpl implements ProdutoService {
	
	@Autowired
	ProdutoRepository produtoRep;

	@Override
	public List<Produto> findAllProdutos() {
		return produtoRep.findAll();
	}

	@Override
	public void saveProduto(Produto produto) {
		produtoRep.save(produto);
		
	}

	@Override
	public Produto findById(long id) {
		return produtoRep.findOne(id);
	}

	@Override
	public void updateProduto(Produto produto) {
		produtoRep.save(produto);
	}

	@Override
	public void deleteProdutoById(long id) {
		Produto produto = findById(id);
		produtoRep.delete(produto);
		
	}

	@Override
	public int size() {
		return produtoRep.findAll().size();
	}

	@Override
	public Iterator<Produto> getIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean doesProdutoExist(Produto produto) {
		if(produtoRep.findOne(produto.getId()) != null) {
			return true;
		}
		
		return false;
	}


}
