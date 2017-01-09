package com.inlighten.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inlighten.dao.UserDao;
import com.inlighten.model.User;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao dao;

	//@Autowired
    //private PasswordEncoder passwordEncoder;
	
	public User findById(int id) {
		return dao.findById(id);
	}

	public User findByEmail(String email) {
		User user = dao.findByEmail(email);
		return user;
	}

	public void saveUser(User user) {
		//user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
		user.setUserPassword(user.getUserPassword());
		dao.save(user);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateUser(User user) {
		User entity = dao.findById(user.getUserId());
		if(entity!=null){
			entity.setUserEmail(user.getUserEmail());
			if(!user.getUserPassword().equals(entity.getUserPassword())){
				//entity.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
				entity.setUserPassword(user.getUserPassword());
			}
			entity.setUserApplication(user.getUserApplications());
		}
	}

	
	public void deleteUserByEmail(String email) {
		dao.deleteByEmail(email);
	}

	public List<User> findAllUsers() {
		return dao.findAllUsers();
	}

	public boolean isUserEmailUnique(Integer id, String email) {
		User user = findByEmail(email);
		return ( user == null || ((id != null) && (user.getUserId() == id)));
	}
	
}
