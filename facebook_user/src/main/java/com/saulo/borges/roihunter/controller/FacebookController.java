package com.saulo.borges.roihunter.controller;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.saulo.borges.roihunter.api.FacebookAPI;
import com.saulo.borges.roihunter.api.model.FacebookUser;
import com.saulo.borges.roihunter.entity.FacebookPageEntity;
import com.saulo.borges.roihunter.entity.FacebookUserEntity;
import com.saulo.borges.roihunter.exception.AppException;
import com.saulo.borges.roihunter.form.FacebookUserForm;
import com.saulo.borges.roihunter.json.FacebookUserJson;
import com.saulo.borges.roihunter.service.FacebookService;

@RestController
public class FacebookController {

	@Autowired
	private FacebookService facebookService;

	private final static Gson gson = new Gson();

	@RequestMapping(value = "/users", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.ALL_VALUE })
	public Response addUser(@ModelAttribute FacebookUserForm form) throws AppException {

		FacebookAPI api = new FacebookAPI();
		FacebookUser userData = api.getUserData(form.getFB_ID(), form.getAccess_token());
		facebookService.saveUser(userData);

		return Response.status(Response.Status.CREATED).entity("A new user has been inserted").build();
	}

	@RequestMapping(value = "/users/{user_id}", method = RequestMethod.DELETE, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.ALL_VALUE })
	public Response deleteUser(@PathVariable("user_id") String userId) throws AppException {

		facebookService.deleteUser(userId);
		return Response.status(Response.Status.NO_CONTENT)// 204
				.entity("User successfully removed from database").build();
	}

	@RequestMapping(value = "/users/{user_id}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.ALL_VALUE })
	public String getUser(@PathVariable("user_id") String userId) throws AppException {

		FacebookUserEntity entity = facebookService.findById(userId);
		FacebookUserJson json = new FacebookUserJson(entity);
		
		return gson.toJson(json);
	}

	@RequestMapping(value = "/users/{user_id}/likes", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.ALL_VALUE })
	public String getUserLikes(@PathVariable("user_id") String userId) throws AppException {

		List<FacebookPageEntity> entity = facebookService.findLikesByUserId(userId);
		return gson.toJson(entity);
	}

}
