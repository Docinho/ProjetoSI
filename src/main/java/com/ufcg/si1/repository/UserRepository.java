package com.ufcg.si1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ufcg.si1.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query(value = "Select u from User u where u.login=:plogin")
	public User findByLogin(@Param("plogin") String login);
}