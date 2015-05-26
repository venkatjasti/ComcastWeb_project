package com.src.comcast.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.src.comcast.model.User;

/**
 * @author vrjasti
 *
 */

public abstract class AbstractDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	public void persist(Object entity) {
		getSession().persist(entity);
	}
	public int saveOrUpdate(Object entity) {
		 getSession().save(entity);
		 int id = ((User)entity).getId();
		 return id;
	}
	
	public void delete(Object entity) {
		getSession().delete(entity);
	}
}
