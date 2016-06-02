package com.saulo.borges.roihunter.service;

import java.util.List;

import com.saulo.borges.roihunter.api.FacebookUser;
import com.saulo.borges.roihunter.entity.FacebookPageEntity;
import com.saulo.borges.roihunter.entity.FacebookUserEntity;
import com.saulo.borges.roihunter.exception.AppException;

public interface FacebookService {
	public void saveUser(FacebookUser user);

	public void deleteUser(String userId) throws AppException;

	public FacebookUserEntity findById(String userId) throws AppException;

	public List<FacebookPageEntity> findLikesByUserId(String userId) throws AppException;
}
