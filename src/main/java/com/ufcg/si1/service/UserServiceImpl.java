package com.ufcg.si1.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.si1.model.User;
import com.ufcg.si1.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userrep;

	public List<User> findAllUsers() {
		return userrep.findAll();
	}

	public void saveUser(User user) {
		userrep.save(user);
	}

	public User findById(Long id) {
		return userrep.findOne(id);
	}

	public void updateUser(User user) {
		userrep.save(user);
	}

	public void deleteUserById(Long id) {
		User user = findById(id);
		userrep.delete(user);
	}

	public int size() {
		return userrep.findAll().size();
	}

	public boolean doesUserExists(User user) {
		if (userrep.findOne(user.getId()) != null) {
			return true;
		}

		return false;
	}
	
	public User findByLogin(String login) {
		return userrep.findByLogin(login);
	}

}
