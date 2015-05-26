package com.src.comcast.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.src.comcast.model.User;



@Repository("userDao")
public class UserDaoImpl extends AbstractDao implements UserDao{

	public int  saveUser(User user) {
		int id = saveOrUpdate(user);
		return id;
	}

	@SuppressWarnings("unchecked")
	public List<User> getUsers() {
		Criteria criteria = getSession().createCriteria(User.class);
		return (List<User>) criteria.list();
	}

	public void deleteUser(int id) {
		Query query = getSession().createQuery("delete User where id = :id");
		query.setInteger("id", id);
		query.executeUpdate();
	}
	
}
