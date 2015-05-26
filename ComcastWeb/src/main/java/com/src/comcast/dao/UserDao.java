package com.src.comcast.dao;

import java.util.List;

import com.src.comcast.model.User;


/**
 * @author vrjasti
 *
 */

public interface UserDao {

	int saveUser(User user);
	
	List<User> getUsers();
	
	void deleteUser(int id);
}
