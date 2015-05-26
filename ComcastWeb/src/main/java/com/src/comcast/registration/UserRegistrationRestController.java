package com.src.comcast.registration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.src.comcast.model.User;
import com.src.comcast.service.UserService;

/**
 * @author vrjasti
 *
 */

@Controller
@RequestMapping("/users")
public class UserRegistrationRestController {
	
	@Autowired private UserService userService;
	
	@RequestMapping( 
            method=RequestMethod.GET, 
            produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<User> getAllUsers() {
		List<User> user = userService.getUsers();
		return user;
	}
	
	
	/*
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	@RequestMapping(value ="/registration", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public int saveUser(@RequestBody User user) {
		int id = userService.saveUser(user);
		
		return id;
	}
	
	/*
	 * This method will delete an user by it's id value.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value=HttpStatus.OK)
	public String deleteUser(@PathVariable("id") int id) {
		userService.deleteUser(id);
		return "redirect:/users";
	}

}
