package com.ufcg.si1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufcg.si1.model.Sale;

public interface SalesRepository extends JpaRepository<Sale, Long> {

}
