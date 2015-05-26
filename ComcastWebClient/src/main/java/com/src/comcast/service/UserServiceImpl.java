package com.src.comcast.service;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.src.comcast.model.User;
import com.sun.jersey.api.client.ClientResponse;

/**
 * @author vrjasti
 *
 */

@Service("userService")
@Transactional
public class UserServiceImpl extends ClientServiceHelper implements UserService {

	private static final String SERVICE_URL = "http://localhost:8080/ComcastWeb";
	private static final int RESPONSE_STATUS = 200;

	public int saveUser(User user) {
		String uri = "/users/registration";
		ClientResponse response = ClientServiceHelper.serviceCall(uri,user);
		int status  = Integer.parseInt(response.getEntity(String.class));
		return status;
	}

	public List<User> getUsers() {
		List<User> listUsers = null;
		String uri = "/users";
		ClientResponse response = ClientServiceHelper.serviceCall(uri);
		if (response.getStatus() != RESPONSE_STATUS) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}
		String userList = response.getEntity(String.class);
		try {
			listUsers = ClientServiceHelper.getJsonMapper().readValue(userList,new TypeReference<List<User>>() {});
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return listUsers;
	}

	public void deleteUser(int id) {
		String uri = "/users/"+id;
		String method = "delete";
		ClientServiceHelper.serviceCall(uri,method);
		
	}

}
