package com.inlighten.service;

import java.util.List;

import com.inlighten.model.User;


public interface UserService {
	
	User findById(int id);
	
	User findByEmail(String sso);
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUserByEmail(String sso);

	List<User> findAllUsers(); 
	
	boolean isUserEmailUnique(Integer id, String email);

}