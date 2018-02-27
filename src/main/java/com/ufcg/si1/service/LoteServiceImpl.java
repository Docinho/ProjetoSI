package com.ufcg.si1.service;

import java.util.Iterator;
import java.util.List;

import com.ufcg.si1.model.Lote;
import com.ufcg.si1.repository.LoteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("loteService")
public class LoteServiceImpl implements LoteService {

	@Autowired
	LoteRepository loteRepository;
	

	@Override
	public Lote saveLote(Lote lote) {
		return loteRepository.save(lote);
	}

	@Override
	public Lote findById(Long id) {
		return loteRepository.findOne(id);
	}

	@Override
	public void updateProduto(Lote lote) {
		loteRepository.save(lote);

	}

	@Override
	public void deleteLoteById(long id) {
		Lote lote = findById(id);
		loteRepository.delete(lote);
	}

	@Override
	public List<Lote> findAllLotes() {
		return loteRepository.findAll();
	}

	@Override
	public int size() {
		return loteRepository.findAll().size();
	}

	@Override
	public Iterator<Lote> getIterator() {
		return null;
	}


}
