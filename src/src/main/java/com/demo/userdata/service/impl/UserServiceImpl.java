package com.demo.userdata.service.impl;

import com.demo.userdata.entities.User;
import com.demo.userdata.repositories.UserRepository;
import com.demo.userdata.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("userService")
@SuppressWarnings("unused")
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	@CachePut
	public User saveUser(User user) throws Exception {
		User user1 = getUser(user.getId());
		if (user1 != null && user1.getPhoneNumber().equals(user.getPhoneNumber()))
			return userRepository.save(user);
		else
			throw new IllegalArgumentException("Phone number cannot be changed");
	}

	@Override
	@CacheEvict
	public void deleteUser(Long userId) {
		userRepository.deleteById(userId);
	}

	@Override
	@Cacheable(value = "users")
	public User getUser(Long userId) throws Exception {
		return userRepository.findById(userId).orElseThrow(() -> new Exception("No Records Found"));
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll().stream().map(user -> {
			user.setPhoneNumber("xxxxxxxxxx");
			return user;
		}).collect(Collectors.toList());
	}

	@Override
	public User findUserByIdAndPhoneNumeber(User user) {
		return userRepository.findUserByIdAndPhoneNumber(user.getId(), user.getPhoneNumber());
	}

}
