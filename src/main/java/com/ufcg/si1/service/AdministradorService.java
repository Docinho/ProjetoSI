package com.ufcg.si1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ufcg.si1.model.Admin;
import com.ufcg.si1.repository.AdminRepository;

public class AdministradorService {

	@Autowired
	AdminRepository adminrep;

	public List<Admin> findAllAdmin() {
		return adminrep.findAll();
	}

	public void saveAdmin(Admin admin) {
		adminrep.save(admin);
	}

	public Admin findById(String id) {
		return adminrep.findOne(id);
	}

	public void updateAdmin(Admin admin) {
		adminrep.save(admin);
	}

	public void deleteAdminById(String id) {
		Admin admin = findById(id);
		adminrep.delete(admin);

	}

	public int size() {
		return adminrep.findAll().size();
	}

	public boolean doesAdminExist(Admin admin) {
		if (adminrep.findOne(admin.getId()) != null) {
			return true;
		}

		return false;
	}

}
