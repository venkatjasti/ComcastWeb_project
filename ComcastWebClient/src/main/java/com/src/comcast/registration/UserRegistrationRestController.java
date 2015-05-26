package com.src.comcast.registration;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.src.comcast.model.User;
import com.src.comcast.service.UserService;


/**
 * @author vrjasti
 *
 */

@Controller
@RequestMapping("/")
public class UserRegistrationRestController {
	
	@Autowired private UserService userService;

	@RequestMapping(value="/users", 
            method=RequestMethod.GET)
	
	public String getAllUsers(ModelMap model) {
		List<User> users = userService.getUsers();
		model.addAttribute("users", users);
		return "allusers";
	}
	
	/*
	 * This method will provide the medium to add a new user.
	 */
	@RequestMapping(value = { "/users/registration" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		return "registration";
	}
	
	/*
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/users/registration" }, method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result,
			ModelMap model) {
		if (result.hasErrors()) {
			return "registration";
		}
		int  userId  = userService.saveUser(user);
		
		model.addAttribute("success", "User " + user.getUserName()
				+"User Id  "+ userId +" registered successfully");
		return "success";
	}
	
	/*
	 * This method will delete an user by it's id value.
	 */
	@RequestMapping(value ="/users/{id}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable("id") int id) {
		userService.deleteUser(id);
		return "redirect:/users";
	}
}
