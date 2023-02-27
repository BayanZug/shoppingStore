package com.retailer.shop.service;


import java.util.Optional;

import com.retailer.shop.entity.User;


/**
 * 
 * User service for finding and saving user
 *
 */
public interface UserService {

	/**
	 * 
	 * @param username
	 * @return
	 */
    Optional<User> findByUsername(String username);
    /**
     * 
     * @param email
     * @return
     */
    Optional<User> findByEmail(String email);
    /**
     * 
     * @param user
     * @return
     */
    User saveUser(User user);

}
