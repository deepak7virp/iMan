package com.inlighten.dao;

import java.util.List;

import com.inlighten.model.User;


public interface UserDao {

	User findById(int id);
	
	User findByEmail(String sso);
	
	void save(User user);
	
	void deleteByEmail(String sso);
	
	List<User> findAllUsers();

}

