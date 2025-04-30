package com.Project.CoderHack.Controller;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.ObjectInputFilter.Status;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import com.Project.CoderHack.Model.User;
import com.Project.CoderHack.Service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UserController.class)
public class UserControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private UserService userService;
	@Autowired
	private ObjectMapper objectMapper;
	User user;

	@Test
	public void getAllUsersTest() throws Exception {
		List<User> users = new ArrayList<User>();
		user = new User();
		user.setUserId("123");
		user.setUserName("Bimlesh");
		users.add(user);
		user = new User();
		user.setUserId("124");
		user.setUserName("Vedant");
		users.add(user);
		
		Mockito.when(userService.getAllUsers()).thenReturn(users);
		
		mockMvc.perform(get("/users"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.length()").value(users.size()))
		.andExpect(jsonPath("[0].userId").value("123"))
		.andExpect(jsonPath("$[1].userId").value("124"));
	}
	
	@Test
	public void getUserByIdTest() throws Exception {
		User user = new User();
		user.setUserId("123");
		user.setUserName("Bimlesh");
		
		Mockito.when(userService.getUserById(user.getUserId())).thenReturn(user);
		mockMvc.perform(get("/users/123"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.userId").value("123"));
		
		
		
	}
//			@Test
//			public void testGetUserById() {
//				User mockUser = new User("1", "John", 100, new HashSet<String>());
//				when(userService.getUserById("1")).thenReturn(mockUser);
//	
//				User user = userController.getUserById("1");
//	
//				assertNotNull(user);
//				assertEquals("John", user.getName());
//				verify(userService, times(1)).getUserById("1");
//			}
	
	@Test
	public void registerUserTest() throws Exception {
		User user = new User();
		user.setUserId("123");
		user.setUserName("Bimlesh");
		
		Mockito.when(userService.registerUser(user)).thenReturn(user);
		
		mockMvc.perform(post("/users").contentType("application/json").content(objectMapper.writeValueAsString(user)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.userId").value("123"));
		
	}
	//
	//		@Test
	//		public void testRegisterUser() {
	//			User mockUser = new User("1", "John", 100);
	//			when(userService.registerUser(any(User.class))).thenReturn(mockUser);
	//
	//			User user = userController.registerUser(mockUser);
	//
	//			assertNotNull(user);
	//			assertEquals("John", user.getName());
	//			verify(userService, times(1)).registerUser(mockUser);
	//		}
	//
	@Test
	public void updateScoreTest() throws Exception {
		int score = 50;
		User user = new User();
		user.setScore(score);
		user.setUserId("123");
		user.setUserName("Bimlesh");
		
		Mockito.when(userService.updateScore("123", score)).thenReturn(user);
		mockMvc.perform(put("/users/123")
				.param("score", String.valueOf(score))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath( "$.score").value(50));
		
		
	}
	//		@Test
	//		public void testUpdateScore() {
	//			User mockUser = new User("1", "John", 150);
	//			when(userService.updateScore("1", 150)).thenReturn(mockUser);
	//
	//			User user = userController.updateScore("1", 150);
	//
	//			assertNotNull(user);
	//			assertEquals(150, user.getScore());
	//			verify(userService, times(1)).updateScore("1", 150);
	//		}
	//
	@Test
	public void deleteUserTest() throws Exception {
		String userId = "123";
		Mockito.doNothing().when(userService).deleteUser(userId);
		
		mockMvc.perform(delete("/users/{userId}",userId))
		.andExpect(status().isOk())
		.andExpect((ResultMatcher) content().string("User deleted"));
	}
	//		@Test
	//		public void testDeleteUser() {
	//			doNothing().when(userService).deleteUser("1");
	//
	//			ResponseEntity<String> response = userController.deleteUser("1");
	//
	//			assertEquals("User deleted", response.getBody());
	//			verify(userService, times(1)).deleteUser("1");
	//		}
	//	}

}
