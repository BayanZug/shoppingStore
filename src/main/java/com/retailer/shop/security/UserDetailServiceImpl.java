package com.retailer.shop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.retailer.shop.entity.User;
import com.retailer.shop.repo.UserRepo;

/**
 * 
 * Standard Spring security custom UserdetailService Implementation
 *
 */
public class UserDetailServiceImpl implements UserDetailsService {
		 
	    @Autowired
	    private UserRepo userRepository;
	     
	    @Override
	    public UserDetails loadUserByUsername(String username)
	            throws UsernameNotFoundException {
	        User user = userRepository.getUserByUsername(username);
	         
	        if (user == null) {
	            throw new UsernameNotFoundException("Could not find user");
	        }
	         
	        return new MyUserDetails(user);
	    }
	 
	
}
