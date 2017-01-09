package com.inlighten.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.google.gson.JsonObject;
import com.inlighten.ajax.AjaxDataBody;
import com.inlighten.ldap.UserRepoImpl;
import com.inlighten.model.Application;
import com.inlighten.model.User;
import com.inlighten.model.UserApplication;
import com.inlighten.service.ApplicationService;
import com.inlighten.service.UserService;

@Controller
@RequestMapping("/")
@EnableWebMvc
public class AppController {

	@Autowired
	UserService service;
	
	@Autowired
	ApplicationService applicationService;
	
	@Autowired
	UserRepoImpl ldapDao;
	
	@Autowired
	MessageSource messageSource;
	
	//@Autowired
	//AuthenticationTrustResolver authenticationTrustResolver;

	@RequestMapping(value = { "/","/login" }, method = RequestMethod.GET)
	public String login(ModelMap model){
		//Add code to check if user is logged in - future
		User user = new User();
		model.addAttribute("login",true);
		model.addAttribute("user",user);
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String processLogin(@Valid User user, BindingResult result,
			ModelMap model){
		System.out.println(user);
		System.out.println(model.containsAttribute("user"));
		return "index";
	}
	
	@RequestMapping(value = { "/register"}, method = RequestMethod.GET)
	public String registration(ModelMap model){
		//Add code to check if user is logged in - future
		User user = new User();
		List<Application> applications = applicationService.findAllApplications();
		List<UserApplication> userApps = new ArrayList<UserApplication>();
		for(Application app : applications){
			UserApplication uapp = new UserApplication();
			uapp.setApplication_code(app.getApplication_code());
			uapp.setApplication_id(app.getApplication_id());
			uapp.setApplication_name(app.getApplication_name());
			uapp.setUser(user);
			uapp.setVerified(false);
			userApps.add(uapp);
		}
		user.setUserApplication(userApps);
		model.addAttribute("user",user);
		model.addAttribute("edit",false);
		model.addAttribute("login",false);
		return "login";
	}
	
	
	@RequestMapping(value = { "/authApp" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	String authApp(@RequestParam("app_id") String app_id, @RequestParam("app_username") String app_username, @RequestParam("app_password") String app_password) {
		//authenticate newly added user application
		
		boolean auth = ldapDao.authenticate(app_username, app_password);
		JsonObject ret = new JsonObject();
		ret.addProperty("verified",auth);
		
		return ret.toString();
	}
	
	
	
	@RequestMapping(value = "/submit", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	String Submit(@RequestParam("name") String name,@RequestParam("location") String location) {
	    // your logic here
		System.out.println("inside submit.htm");
	    return "";
	}
	
	

	/*
	 * This method will be called on form submission, handling POST request for
	 * saving employee in database. It also validates the user input
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result,
			ModelMap model) {
		System.out.println("Inside newUser(post) of AppController");
		if (result.hasErrors()) {
			return "registration";
		}

		/*
		 * Preferred way to achieve uniqueness of field [ssn] should be implementing custom @Unique annotation 
		 * and applying it on field [ssn] of Model class [Employee].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		 */
		if(!service.isUserEmailUnique(user.getUserId(), user.getUserEmail())){
			FieldError emailError =new FieldError("user","email",messageSource.getMessage("non.unique.email", new String[]{user.getUserEmail()}, Locale.getDefault()));
		    result.addError(emailError);
			return "registration";
		}
		
		service.saveUser(user);

		model.addAttribute("success", "User " + user.getUserEmail() + " registered successfully");
		return "success";
	}


	/*
	 * This method will provide the medium to update an existing employee.
	 */
	@RequestMapping(value = { "/edit-{email}-user" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String email, ModelMap model) {
		User user = service.findByEmail(email);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		return "registration";
	}
	
	/*
	 * This method will be called on form submission, handling POST request for
	 * updating employee in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-{email}-user" }, method = RequestMethod.POST)
	public String updateApplication(@Valid User user, BindingResult result,
			ModelMap model, @PathVariable String email) {

		if (result.hasErrors()) {
			return "registration";
		}

		if(!service.isUserEmailUnique(user.getUserId(), user.getUserEmail())){
			FieldError emailError =new FieldError("user","email",messageSource.getMessage("non.unique.email", new String[]{user.getUserEmail()}, Locale.getDefault()));
		    result.addError(emailError);
			return "registration";
		}

		service.updateUser(user);

		model.addAttribute("success", "User " + user.getUserEmail()	+ " updated successfully");
		return "success";
	}

	
	/*
	 * This method will delete an employee by it's SSN value.
	 */
	@RequestMapping(value = { "/delete-{email}-user" }, method = RequestMethod.GET)
	public String deleteApplication(@PathVariable String email) {
		service.deleteUserByEmail(email);
		return "redirect:/list";
	}
	
	/**
	 * This method returns true if users is already authenticated [logged-in], else false.
	 */
//	private boolean isCurrentAuthenticationAnonymous() {
//	    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//	    return authenticationTrustResolver.isAnonymous(authentication);
//	}

}
