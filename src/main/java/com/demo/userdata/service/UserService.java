package com.demo.userdata.service;

import java.util.List;

import com.demo.userdata.entities.User;

public interface UserService {

	User saveUser(User user);

	void deleteUser(Long userId);

	User getUser(Long userId) throws Exception;

	List<User> getAllUsers();

}
