package com.saulo.borges.roihunter.dao;

import com.saulo.borges.roihunter.entity.FacebookUserEntity;
import com.saulo.borges.roihunter.exception.AppException;

public interface FacebookUserDao {
	
	public void save(FacebookUserEntity user);
	
	public FacebookUserEntity findById(String userId) throws AppException;

	public void delete(FacebookUserEntity userEntity) throws AppException;

}
