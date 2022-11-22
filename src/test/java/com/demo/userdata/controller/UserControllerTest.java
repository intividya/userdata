package com.demo.userdata.controller;

import com.demo.userdata.entities.User;
import com.demo.userdata.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@Test
	public void contextLoads() {

	}

	@Test
	public void when_api_queryby_Id_then_return_user() throws Exception {
		User user = createUser();
		ObjectMapper objectMapper = new ObjectMapper();
		Mockito.when(userService.getUser(123L)).thenReturn(user);
		this.mockMvc.perform(get("/api/v1/user/123")
						.contextPath("/api/v1"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(user)));
	}

	@Test
	public void when_api_create_user_then_save_user() throws Exception {
		User user = createUser();
		ObjectMapper objectMapper = new ObjectMapper();
		Mockito.when(userService.saveUser(Mockito.any(User.class))).thenReturn(user);
		this.mockMvc.perform(post("/api/v1/user/save")
						.contextPath("/api/v1")
						.content(objectMapper.writeValueAsString(user))
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(user)));
	}

	@Test
	public void when_api_delete_user_then_delete_user() throws Exception {
		Mockito.doNothing().when(userService).deleteUser(123L);
		this.mockMvc.perform(delete("/api/v1/user/delete/123")
						.contextPath("/api/v1"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	private User createUser() {
		User user = new User();
		user.setUserId(123L);
		user.setFirstName("Alex");
		user.setLastName("Smith");
		user.setPhoneNumber("+467234590");
		user.setEmailAddress("alex@gmail.com");
		return user;
	}

}
