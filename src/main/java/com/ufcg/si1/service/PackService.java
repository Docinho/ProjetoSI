package com.ufcg.si1.service;

import java.util.List;

import com.ufcg.si1.model.Pack;

public interface PackService {

	List<Pack> findAllPacks();

	Pack findById(Long id);

	void updateProduct(Pack user);

	void deletePackById(long id);

	int size();

	Pack savePack(Pack pack);
}
