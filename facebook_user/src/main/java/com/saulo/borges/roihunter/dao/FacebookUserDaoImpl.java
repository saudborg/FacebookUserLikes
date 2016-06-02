package com.saulo.borges.roihunter.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.saulo.borges.roihunter.entity.FacebookUserEntity;

@Repository
@Transactional
public class FacebookUserDaoImpl implements FacebookUserDao {

	private SessionFactory factory;

	@Autowired
	public FacebookUserDaoImpl(SessionFactory factory) {
		this.factory = factory;
	}

	@Override
	public void save(FacebookUserEntity user) {
		factory.getCurrentSession().saveOrUpdate(user);
	}

	@Override
	public FacebookUserEntity findById(String userId) {
		Query query = factory.getCurrentSession().createQuery("FROM FacebookUserEntity where id=:id").setParameter("id",
				userId);
		List<FacebookUserEntity> list = query.list();
		FacebookUserEntity entity = list.get(0);
		return entity;
	}

	@Override
	public void delete(FacebookUserEntity userEntity) {
		Query query = factory.getCurrentSession().createQuery("delete FacebookUserEntity where id = :id")
				.setParameter("id", userEntity.getId());
		query.executeUpdate();
	}

}
