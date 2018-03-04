package com.ufcg.si1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ufcg.si1.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	
	@Query(value="Select u from Categoria u where u.nomeDaCategoria=:pnomeDaCategoria")
	public Categoria buscarPorNome(@Param("pnomeDaCategoria") String nomeDaCategoria);

}
