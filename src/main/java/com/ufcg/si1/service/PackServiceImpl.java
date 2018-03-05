package com.ufcg.si1.service;

import java.util.List;

import com.ufcg.si1.model.Pack;
import com.ufcg.si1.repository.PackRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("packService")
public class PackServiceImpl implements PackService {

	@Autowired
	PackRepository packRepository;
	

	@Override
	public Pack savePack(Pack pack) {
		return packRepository.save(pack);
	}

	@Override
	public Pack findById(Long id) {
		return packRepository.findOne(id);
	}

	@Override
	public void updateProduct(Pack pack) {
		packRepository.save(pack);

	}

	@Override
	public void deletePackById(long id) {
		Pack pack = findById(id);
		packRepository.delete(pack);
	}

	@Override
	public List<Pack> findAllPacks() {
		return packRepository.findAll();
	}

	@Override
	public int size() {
		return packRepository.findAll().size();
	}
}
