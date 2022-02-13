package com.m2s.demoproject.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m2s.demoproject.entity.User;
import com.m2s.demoproject.repository.UserRepository;
import com.m2s.demoproject.service.UserService;

@Service
public class UserServiceImpl implements UserService {
  
	@Autowired
	UserRepository userRepository;
	
	@Override
	public String createUser(User user) {
		String msg="";
		
		if(user.getName()==null){
			msg="user name should not null";
		}
		else if(user!=null) {
			userRepository.save(user);
			msg="User save successfully";
		}
		else {
			msg= "not save";
		}
		return msg;
	}

	@Override
	public User getUserById(Long userId) {
		if(userId==null)
			userId=0l;
		 User user= userRepository.findById(userId).orElse(null);
		 
		return user;
	}

	@Override
	public List<User> getAllUsers(String search) {
		List<User> userList= new ArrayList<User>();
		if(search!=null) {
			userList =userRepository.findAllByNameLike("%"+search+"%");
		}else {
			userList =userRepository.findAll();
		}
		return userList;
	}

	@Override
	public String updateUser(User user) {
		String msg="";
		if(user.getId()!=null) {
			userRepository.save(user);
			msg="user updated successfully";
		}else {
			msg="user doesn't exit";
		}
		return msg;
	}

	@Override
	public String deleteUser(Long id) {
		userRepository.deleteById(id);
		return "user deleted successfuuly";
	}

}
