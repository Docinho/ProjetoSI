package com.ufcg.si1.controller;

import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.si1.model.User;
import com.ufcg.si1.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/autenticar", consumes=MediaType.APPLICATION_JSON_VALUE, method= RequestMethod.POST)
	public LoginResponse authenticate(@RequestBody User user) throws ServletException {
		
		if(user.getLogin() == null || user.getPassword() == null) {
			throw new ServletException("Nome e senha são obrigatórios para autenticação!");
		}
		
		User userAuthenticated = userService.findByLogin(user.getLogin());
		
		
		if(userAuthenticated == null) {
			throw new ServletException("Usuário não encontrado");
		}
		
		
		if(!userAuthenticated.getPassword().equals(user.getPassword())) {
			throw new ServletException("Algum dado está incorreto, tente novamente!");
		}
		
		
		
		
		String token = Jwts.builder()
						.setSubject(userAuthenticated.getLogin())
						.signWith(SignatureAlgorithm.HS512, "livia")
						.setExpiration(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
						.compact();
		
		return new LoginResponse(token);
	}
	
	private class LoginResponse {
		public String token;
		
		public LoginResponse(String token) {
			this.token = token;
		}
		

	}

}
