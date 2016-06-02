package com.saulo.borges.roihunter.service;

import com.saulo.borges.roihunter.api.FacebookUser;

public interface FacebookService {
	public void saveUser(FacebookUser user);

	public void deleteUser(String userId);
}
