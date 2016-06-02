package com.saulo.borges.roihunter.service;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.saulo.borges.roihunter.api.FacebookUser;
import com.saulo.borges.roihunter.dao.FacebookUserDao;
import com.saulo.borges.roihunter.entity.FacebookPageEntity;
import com.saulo.borges.roihunter.entity.FacebookUserEntity;
import com.saulo.borges.roihunter.exception.AppException;

@Service
public class FacebookServiceImpl implements FacebookService {

	@Autowired
	FacebookUserDao userDao;

	@Override
	public void saveUser(FacebookUser user) {
		FacebookUserEntity entity = new FacebookUserEntity(user);
		userDao.save(entity);
	}

	@Override
	public void deleteUser(String userId) throws AppException {
		FacebookUserEntity userEntity = this.findById(userId);
		userDao.delete(userEntity);
	}

	public FacebookUserEntity findById(String userId) throws AppException {
		if (StringUtils.isEmpty(userId)) {
			throw new AppException(Response.Status.BAD_REQUEST.getStatusCode(), 400, "FB_ID musn't be null.");
		}
		
		FacebookUserEntity user = userDao.findById(userId);
		
		if (user ==null) {
			throw new AppException(Response.Status.BAD_REQUEST.getStatusCode(), 400, "FB_ID doesn't exists.");
		}
		return user;
	}

	@Override
	public List<FacebookPageEntity> findLikesByUserId(String userId) throws AppException {
		FacebookUserEntity user = userDao.findById(userId);
		return user.getPages();
	}

}
