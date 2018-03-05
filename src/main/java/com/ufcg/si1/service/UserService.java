package com.ufcg.si1.service;

import java.util.List;

import com.ufcg.si1.model.User;

public interface UserService {
	
	public List<User> findAllUsers();

	public void saveUser(User user);

	public User findById(Long id);

	public void updateUser(User user);

	public void deleteUserById(Long id);

	public int size();

	public boolean doesUserExists(User user);
	
	public User findByLogin(String login);

}