package com.demo.userdata.service.impl;

import com.demo.userdata.entities.User;
import com.demo.userdata.repositories.UserRepository;
import com.demo.userdata.service.UserService;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceImplTest {

	@InjectMocks
	UserService userService = new UserServiceImpl();

	@Mock
	UserRepository userRepository;

	private AutoCloseable closeable;

	private User user = createUser();

	@BeforeEach
	public void beforeAll() {
		closeable = MockitoAnnotations.openMocks(this);
		Mockito.when(userRepository.findById(123L)).thenReturn(Optional.of(user));
		Mockito.when(userRepository.save(user)).thenReturn(user);
		Mockito.doNothing().when(userRepository).deleteById(123L);
	}

	@AfterEach
	void closeService() throws Exception {
		closeable.close();
	}

	@Test
	public void when_initialized_Then_find_noUsers() {
		Assertions.assertEquals(userService.getAllUsers().size(), 0);
	}

	@Test
	public void when_user_Then_save_successfully() {
		Assertions.assertEquals(user, userService.saveUser(user));
	}

	private User createUser() {
		User user = new User();
		user.setId(123L);
		user.setFirstName("Alex");
		user.setLastName("Smith");
		user.setPhoneNumber("+467234590");
		user.setEmailAddress("alex@gmail.com");
		return user;
	}

	@Test
	public void when_user_then_delete_successfully() {
		try {
			User user = createUser();
			userService.saveUser(user);
			userService.deleteUser(123L);
			Assertions.assertEquals(123L, userService.getUser(123L).getId());
		} catch (Exception exception) {
			exception.printStackTrace();
			Assertions.fail();
		}
	}

}
