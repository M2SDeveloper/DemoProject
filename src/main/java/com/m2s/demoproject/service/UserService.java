package com.m2s.demoproject.service;

import java.util.List;

import org.aspectj.apache.bcel.classfile.Module.Uses;

import com.m2s.demoproject.entity.User;

public interface UserService {
	
	public String createUser(User user);

	public User getUserById(Long userId);

	public List<User> getAllUsers(String search);

	public String updateUser(User user);

	public String deleteUser(Long id);

}
