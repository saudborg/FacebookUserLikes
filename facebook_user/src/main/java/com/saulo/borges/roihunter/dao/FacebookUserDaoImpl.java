package com.saulo.borges.roihunter.dao;

import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.saulo.borges.roihunter.entity.FacebookUserEntity;
import com.saulo.borges.roihunter.exception.AppException;

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
	public FacebookUserEntity findById(String userId) throws AppException {
		Query query = factory.getCurrentSession().createQuery("FROM FacebookUserEntity where id=:id").setParameter("id",
				userId);
		List<FacebookUserEntity> list = query.list();

		if (list.size() == 0)
			throw new AppException(Response.Status.BAD_REQUEST.getStatusCode(), 400, "This userId doesn't exists");
		if (list.size() > 1)
			throw new AppException(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), 500,
					"More than one result found for the userId:" + userId);

		FacebookUserEntity entity = list.get(0);
		return entity;
	}

	@Override
	public void delete(FacebookUserEntity userEntity) throws AppException {
		Query query = factory.getCurrentSession().createQuery("delete FacebookUserEntity where id = :id")
				.setParameter("id", userEntity.getId());

		int executeUpdate = query.executeUpdate();
		if (executeUpdate != 1)
			throw new AppException(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), 500,
					"Error while deleting the user.");

	}

}
