package com.src.comcast.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.src.comcast.dao.UserDao;
import com.src.comcast.model.User;

/**
 * @author vrjasti
 *
 */

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao dao;
	
	public int saveUser(User user) {
		return dao.saveUser(user);
	}

	public List<User> getUsers() {
		return dao.getUsers();
	}

	public void deleteUser(int id) {
		dao.deleteUser(id);
	}

}
