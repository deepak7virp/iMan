package com.inlighten.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inlighten.dao.ApplicationDao;
import com.inlighten.model.Application;
import com.inlighten.model.User;

@Service("applicationService")
@Transactional
public class ApplicationServiceImpl implements ApplicationService{

	@Autowired
	private ApplicationDao dao;
	
	@Override
	public Application findById(int application_id) {
		// TODO Auto-generated method stub
		return dao.findById(application_id);
	}

	@Override
	public Application findByName(String application_name) {
		// TODO Auto-generated method stub
		Application application = dao.findByName(application_name);
		return application;
	}

	@Override
	public void saveApplication(Application application) {
		// TODO Auto-generated method stub
		dao.save(application);
	}

	@Override
	public void updateApplication(Application application) {
		// TODO Auto-generated method stub
		Application entity = dao.findById(application.getApplication_id());
		entity.setApplication_name(application.getApplication_name());
		entity.setApplication_code(application.getApplication_code());
	}

	@Override
	public void deleteApplicationByName(String application_name) {
		// TODO Auto-generated method stub
		dao.deleteByName(application_name);
	}

	@Override
	public List<Application> findAllApplications() {
		// TODO Auto-generated method stub
		return dao.findAllApplications();
	}

	@Override
	public boolean isApplicationNameUnique(Integer id, String name) {
		// TODO Auto-generated method stub
		Application application = findByName(name);
		return ( application == null || ((id != null) && (application.getApplication_id() == id)));
	}

}
