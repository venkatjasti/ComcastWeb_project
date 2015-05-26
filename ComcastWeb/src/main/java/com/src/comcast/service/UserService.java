package com.src.comcast.service;

import java.util.List;
import com.src.comcast.model.User;

/**
 * @author vrjasti
 *
 */


public interface UserService {

	int saveUser(User user);
	
	List<User> getUsers(); 
	
	void deleteUser(int id);
}
