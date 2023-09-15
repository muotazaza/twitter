package com.twitter.twitter.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.twitter.twitter.entity.AppUser;
import com.twitter.twitter.repository.UserRepo;
import com.twitter.twitter.security.AppUserDetail;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

	private final UserRepo userRepo;
	
	private final PasswordEncoder passwordEncoder;
	
	
	public List<AppUser> findAll (){
		
		return userRepo.findAll();
	}
	
    public AppUser findById (Long id){
		
		return userRepo.findById(id).orElse(null);
	}
    
    public AppUser save(AppUser entity) {
    	entity.setPassword(passwordEncoder.encode(entity.getPassword()));
		return userRepo.save(entity);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
	  Optional<AppUser> appUser =	userRepo.findByUserName(username);
	  
	  if (!appUser.isPresent()) {
		  
		  throw new UsernameNotFoundException("This User Not found with selected user name :- " + username);
	  }
		
		return new AppUserDetail(appUser.get());
	}
	
//	private static List<GrantedAuthority> getAuthorities(AppUser user) {
//		
//		List<GrantedAuthority> authorities = new ArrayList<>();
//		
//		 if(!user.getRoles().isEmpty()) {
//		        	user.getRoles().forEach(role -> {
//		        		authorities.add(new SimpleGrantedAuthority(role.getName()));	
//		        	});
//		        }
//		     return authorities;
//		}
}
