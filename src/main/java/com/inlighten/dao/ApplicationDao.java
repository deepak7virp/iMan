package com.inlighten.dao;

import java.util.List;

import com.inlighten.model.Application;


public interface ApplicationDao {
	Application findById(int application_id);
	
	Application findByName(String application_name);
	
	void save(Application application);
	
	void deleteByName(String application_name);
	
	List<Application> findAllApplications();
}
