package com.m2s.demoproject.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.m2s.demoproject.entity.User;
import com.m2s.demoproject.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/demo")
	public String helloWord() {
		String S="Hello this is demo project, demo";
		return S;
	}
	
	@PostMapping("/create/user")
	@ResponseBody
	public String createUser(@Validated @RequestBody  User user , HttpServletRequest req, HttpServletResponse res) {
		 
		String msg= userService.createUser(user);
		
		return msg;
	}
	
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable("id") Long userId) {
		return userService.getUserById(userId);
	}
	
	@GetMapping("/users")
	public List<User> getAllUser( @RequestParam(name="search", required = false) String search ){
		List<User> userlist= new ArrayList<User>();
		userlist=userService.getAllUsers(search);
		return userlist;
	}
	
	@PutMapping("/update/user")
	@ResponseBody
	public String updateUser(@Validated @RequestBody  User user , HttpServletRequest req, HttpServletResponse res) {
		 
		String msg= userService.updateUser(user);
		
		return msg;
	}
	
	@DeleteMapping("delete/{id}")
	public String deleteUser(@PathVariable("id") Long id) {
		
		String msg=  userService.deleteUser(id);
		return msg;
	}
	

}
