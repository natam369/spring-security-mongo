package com.mindtree.mongosecurity.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.mindtree.mongosecurity.model.Users;
import com.mindtree.mongosecurity.repository.UserRepository;




@Service
public class MongoUserDetailsService implements UserDetailsService {
	
	
	@Autowired
	UserRepository userrepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		System.out.println("inside lodbyusername userdetailservice");
		
		Users user=userrepo.findByUsername(username);
		if(user==null)
		{
			throw new UsernameNotFoundException("User 404");
		}
		return new MongoUserDetails(user);
//		List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("user"));
//	    return new User(user.getUsername(), user.getPassword(), authorities);
	}

}
