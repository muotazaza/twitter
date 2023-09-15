package com.twitter.twitter.confg;

import java.util.HashSet;
import java.util.Set;

import com.twitter.twitter.entity.AppUser;
import com.twitter.twitter.entity.Role;
import com.twitter.twitter.service.RoleService;
import com.twitter.twitter.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;



import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StartUpApp implements CommandLineRunner {
	
	private final UserService userService;
	
	private final RoleService roleService;

	@Override
	public void run(String... args) throws Exception {


		if (roleService.findAll().isEmpty()) {			
			roleService.save(new Role(null, "admin"));
			roleService.save(new Role(null, "user"));
			roleService.save(new Role(null, "employee"));
		}
		
		
		if (userService.findAll().isEmpty()) {
			
			Set<Role> adminRoles = new HashSet<Role>();
			adminRoles.add(roleService.findByName("admin"));
			
			Set<Role> userRoles = new HashSet<Role>();
			userRoles.add(roleService.findByName("user"));
			
			Set<Role>  empRoles = new HashSet<Role>();
			empRoles.add(roleService.findByName("employee"));
			AppUser appUser=new AppUser();
			appUser.setUserName("tweeter");
			appUser.setPassword("tweeter");
			appUser.setRoles(adminRoles);
			appUser.setCredentialsNonExpired(true);
			appUser.setAccountNonLocked(true);
			appUser.setAccountNonExpired(true);
			appUser.setEnabled(true);
			userService.save(appUser);

		}
		
	}

}
