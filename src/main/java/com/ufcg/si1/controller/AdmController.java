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

import com.ufcg.si1.model.Admin;
import com.ufcg.si1.service.AdministradorService;
import com.ufcg.si1.util.CustomErrorType;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class AdmController {
	
	@Autowired
	AdministradorService administradorService;
	
	Admin administradorLogado;
	
	@RequestMapping(method = RequestMethod.POST, value = "/adm")
	public ResponseEntity<Admin> cadastrarCliente(@RequestBody Admin adm) {
		boolean adminExiste = false;

		for (Admin a : administradorService.findAllAdmin()) {
			if (a.getLogin().equals(adm.getLogin())) {
				adminExiste = true;
			}
		}

		if (adminExiste) {
			return new ResponseEntity(new CustomErrorType("Já existe um usuario com o login " + adm.getLogin()),
					HttpStatus.CONFLICT);
		}

		administradorService.saveAdmin(adm);
		System.out.println("Cadastrou o admin" + adm.getLogin());

		return new ResponseEntity<Admin>(adm, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/adms/", method = RequestMethod.GET)
	public ResponseEntity<List<Admin>> listAllAdmins() {
		List<Admin> adms = administradorService.findAllAdmin();

		if (adms.isEmpty()) {
			return new ResponseEntity<List<Admin>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Admin>>(adms, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/adm")
	public ResponseEntity<Admin> logarCliente(@RequestBody Admin adm) {
		Admin administradorLogin = administradorService.findById(adm.getId());

		if (administradorLogin != null) {
			administradorLogado = administradorLogin;
			return new ResponseEntity<Admin>(administradorLogado, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

}
