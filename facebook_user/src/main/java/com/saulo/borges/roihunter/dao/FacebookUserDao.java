package com.saulo.borges.roihunter.dao;

import com.saulo.borges.roihunter.entity.FacebookUserEntity;

public interface FacebookUserDao {
	
	void save(FacebookUserEntity user);
	
	FacebookUserEntity findById(String userId);

	void delete(FacebookUserEntity userEntity);

}
