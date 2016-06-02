package com.saulo.borges.roihunter.dao;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.saulo.borges.roihunter.entity.FacebookUserEntity;

@Repository
@Transactional
public class FacebookUserDaoImpl implements FacebookUserDao{

	private SessionFactory factory;
	
	@Autowired
	public FacebookUserDaoImpl(SessionFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public void save(FacebookUserEntity user) {
		factory.getCurrentSession().saveOrUpdate(user);
	}

}
