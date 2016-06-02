package com.saulo.borges.roihunter.dao;

import com.saulo.borges.roihunter.entity.FacebookUserEntity;
import com.saulo.borges.roihunter.exception.AppException;

public interface FacebookUserDao {
	
	void save(FacebookUserEntity user);
	
	FacebookUserEntity findById(String userId) throws AppException;

	void delete(FacebookUserEntity userEntity) throws AppException;

}
