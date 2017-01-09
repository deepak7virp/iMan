package com.inlighten.service;

import java.util.List;

import com.inlighten.model.Application;


public interface ApplicationService {
	Application findById(int application_id);
	
	Application findByName(String application_name);
	
	void saveApplication(Application application);
	
	void updateApplication(Application application);
	
	void deleteApplicationByName(String application_name);

	List<Application> findAllApplications(); 
	
	boolean isApplicationNameUnique(Integer id, String name);
}
