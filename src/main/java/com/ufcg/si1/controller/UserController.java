package com.ufcg.si1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.si1.model.User;
import com.ufcg.si1.service.UserService;
import com.ufcg.si1.util.CustomErrorType;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserService userService;

	User userLogado;

	@RequestMapping(method = RequestMethod.POST, value = "/user/")
	public ResponseEntity<User> cadastrarCliente(@RequestBody User user) {
		boolean userExists = false;

		for (User u : userService.findAllUsers()) {
			if (u.getLogin().equals(user.getLogin())) {
				userExists = true;
			}
		}

		if (userExists) {
			return new ResponseEntity(new CustomErrorType("Já existe um usuario com o login " + user.getLogin()),
					HttpStatus.CONFLICT);
		}

		userService.saveUser(user);
		System.out.println("Cadastrou o user " + user.getLogin());

		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {
		List<User> users = userService.findAllUsers();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/user/")
	public ResponseEntity<User> logarCliente(@RequestBody User user) {
		User userLogin = userService.findById(user.getId());

		if (userLogin != null) {
			userLogado = userLogin;
			return new ResponseEntity<User>(userLogado, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

}
