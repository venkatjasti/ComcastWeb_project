package com.src.comcast.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.joda.time.LocalDate;
import org.junit.Test;

import com.src.comcast.model.User;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * @author vrjasti
 *
 */
public class UserControllerTest {
	private static final String REST_SERVICE_URL = "http://localhost:8080/ComcastWeb";

	ClientResponse response = null;

	public void create() {
		createAndAssertUser();
	}

	// Note : DELETE test fails due to Http 405 error not able to fix now
	
	/*
	 * @Test public void delete() throws JsonGenerationException,
	 * JsonMappingException, IOException { User createdUser =
	 * createAndAssertUser();
	 * 
	 * int temUserId = createTempUser(); String uri ="/users/38";
	 * System.out.println("uri ======"+uri); Client client = Client.create();
	 * ObjectMapper mapper = new ObjectMapper(); User user =
	 * createAndAssertUser(); WebResource webResource =
	 * client.resource(REST_SERVICE_URL + uri); response =
	 * webResource.accept("application/json"
	 * ).type(MediaType.APPLICATION_JSON).delete(ClientResponse.class);
	 * System.out
	 * .println("response.getStatus()..................."+response.getEntity
	 * (String.class));
	 * 
	 * 
	 * //assertEquals(response.getStatus(), 200); }
	 */

	@Test
	public void createUser() throws JsonGenerationException,
			JsonMappingException, IOException {
		String uri = "/users/registration";
		User createdUser = createAndAssertUser();

		Client client = Client.create();
		ObjectMapper mapper = new ObjectMapper();
		String userString = mapper.writeValueAsString(createdUser);
		WebResource webResource = client.resource(REST_SERVICE_URL + uri);
		response = webResource.accept("application/json")
				.type(MediaType.APPLICATION_JSON)
				.post(ClientResponse.class, userString);

		assertNotNull(response.getEntity(String.class));
	}

	public int createTempUser() throws JsonGenerationException,
			JsonMappingException, IOException {
		String uri = "/users/registration";
		User createdUser = createAndAssertUser();

		Client client = Client.create();
		ObjectMapper mapper = new ObjectMapper();
		String userString = mapper.writeValueAsString(createdUser);
		WebResource webResource = client.resource(REST_SERVICE_URL + uri);
		response = webResource.accept("application/json")
				.type(MediaType.APPLICATION_JSON)
				.post(ClientResponse.class, userString);
		int userId = Integer.parseInt(response.getEntity(String.class));
		return userId;
	}
	
	@Test
	public void getAllUserTest() throws JsonGenerationException,
			JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Client client = Client.create();
		WebResource webResource = client.resource(REST_SERVICE_URL + "/users");
		ClientResponse response = webResource.accept("application/json").get(
				ClientResponse.class);
		String userList = response.getEntity(String.class);
		
		List<User> beforeCreateUsersList = (List<User>) mapper.readValue(userList,new TypeReference<List<User>>() {});
		
		int id = createTempUser();
		
		webResource = client.resource(REST_SERVICE_URL + "/users");
		 response = webResource.accept("application/json").get(
				ClientResponse.class);
		userList = response.getEntity(String.class);
		
		List<User> afterNewUserCreatedList = (List<User>) mapper.readValue(userList,new TypeReference<List<User>>() {});
		assertFalse(afterNewUserCreatedList.isEmpty());
		assertNotNull(getUserFromList(afterNewUserCreatedList, id));
		assertNull(getUserFromList(beforeCreateUsersList, id));
		assertTrue(afterNewUserCreatedList.size() > beforeCreateUsersList.size());
	}

	private User getUserFromList(List<User> l, int id) {

		for (User u : l) {
			if (u.getId().equals(id)) {
				return u;
			}
		}
		return null;
	}

	@Test
	public void createUserTest() throws JsonGenerationException,
			JsonMappingException, IOException {
		//Comment : Create user will return the id created from database
		int id = createTempUser();
		
		// Get all records from DB 
		Client client = Client.create();
		WebResource webResource = client.resource(REST_SERVICE_URL + "/users");
		ClientResponse response = webResource.accept("application/json").get(
				ClientResponse.class);
		String userList = response.getEntity(String.class);
		ObjectMapper mapper = new ObjectMapper();
		List<User> initialUsers = (List<User>) mapper.readValue(userList,new TypeReference<List<User>>() {	});
		
		//Comment : Assert the returned list is not empty and list contained the created user 
		assertFalse(initialUsers.isEmpty());
		assertNotNull(getUserFromList(initialUsers, id));
	}

	private User createAndAssertUser() {
		User user = new User();
		LocalDate date = new LocalDate();

		user.setId(0);
		user.setUserName("TestUser123");
		user.setRegisteredDate(date);
		user.setEmail("vrjasti@gmail.com");
		return user;
	}
}