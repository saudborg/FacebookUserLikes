package com.saulo.borges.roihunter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saulo.borges.roihunter.api.FacebookUser;
import com.saulo.borges.roihunter.dao.FacebookUserDao;
import com.saulo.borges.roihunter.entity.FacebookUserEntity;

@Service
public class FacebookServiceImpl implements FacebookService{
	
	@Autowired
	FacebookUserDao userDao;

	@Override
	public void saveUser(FacebookUser user) {
		FacebookUserEntity entity = new FacebookUserEntity(user);
		userDao.save(entity);
	}

}
