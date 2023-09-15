package com.twitter.twitter.service;

import java.util.List;

import com.twitter.twitter.entity.Role;
import com.twitter.twitter.repository.RoleRepo;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {

	private final RoleRepo roleRepo;


	public List<Role> findAll() {

		return roleRepo.findAll();
	}

	public Role findById(Long id) {

		return roleRepo.findById(id).orElse(null);
	}
	
	public Role findByName(String name) {

		return roleRepo.findByName(name);
	}
	
	public Role save(Role entity) {

		return roleRepo.save(entity);
	}
}
