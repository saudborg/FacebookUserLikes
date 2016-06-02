package com.saulo.borges.roihunter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.saulo.borges.roihunter.api.FacebookAPI;
import com.saulo.borges.roihunter.api.FacebookUser;
import com.saulo.borges.roihunter.form.FacebookUserForm;
import com.saulo.borges.roihunter.service.FacebookService;

@RestController
public class FacebookController {

	 @Autowired
	 private FacebookService facebookService;

	@RequestMapping(value = "/users", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.ALL_VALUE })
	public String addUser(@ModelAttribute FacebookUserForm form) {

		JsonObject response = new JsonObject();

		FacebookAPI api = new FacebookAPI();
		try {
			FacebookUser userData = api.getUserData(form.getFB_ID(), form.getAccess_token());
			facebookService.saveUser(userData);
			
			response.addProperty("status", "ok");
			response.addProperty("message", "user registered : " + userData.getId());
		} catch (Exception e) {
			response.addProperty("status", "error");
			response.addProperty("message", e.getMessage());
		}
		return response.toString();
	}

}
