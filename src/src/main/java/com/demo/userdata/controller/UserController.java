package com.demo.userdata.controller;

import com.demo.userdata.entities.User;
import com.demo.userdata.service.UserService;
import java.util.List;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@SuppressWarnings("unused")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostConstruct
	public void postConstruct() {
		logger.info("userController initialized");
	}

	@PostMapping("/save")
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		logger.info("Saving user: " + user);
		try {
			if (userService.findUserByIdAndPhoneNumeber(user) == null) {
				User userInfo = userService.saveUser(user);
				return new ResponseEntity<>(userInfo, HttpStatus.OK);
			} else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception exception) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable("userId") Long userId) {
		logger.info("deleting user with id: " + userId);
		userService.deleteUser(userId);
		return new ResponseEntity<>("User Deleted Successfully", HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<User> updateUser(@RequestBody User user) throws Exception {
		logger.info("Updating user: " + user);
		User userInfo = userService.saveUser(user);
		return new ResponseEntity<>(userInfo, HttpStatus.OK);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<User> getUser(@PathVariable("userId") Long userId) throws Exception {
		logger.info("getUser with id: " + userId);
		User userInfo = userService.getUser(userId);
		return new ResponseEntity<>(userInfo, HttpStatus.OK);
	}

	@GetMapping("/getAllUsers")
	public ResponseEntity<List<User>> getAllUsers() {
		logger.info("Listing all users");
		List<User> userInfo = userService.getAllUsers();
		return new ResponseEntity<>(userInfo, HttpStatus.OK);
	}

}
