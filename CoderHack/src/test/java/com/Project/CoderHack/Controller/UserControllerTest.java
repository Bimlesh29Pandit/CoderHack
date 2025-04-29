package com.Project.CoderHack.Controller;

import java.util.List;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import com.Project.CoderHack.Model.User;
import com.Project.CoderHack.Service.UserService;






	@ExtendWith(MockitoExtension.class)
	public class UserControllerTest {

		@InjectMocks
		private UserController userController;

		@Mock
		private UserService userService;

		@Test
		public void testGetAllUsers() {
			List<User> mockUsers = Arrays.asList(new User("1", "John", 100), new User("2", "Jane", 200));
			when(userService.getAllUsers()).thenReturn(mockUsers);

			List<User> users = userController.getAllUsers();

			assertEquals(2, users.size());
			verify(userService, times(1)).getAllUsers();
		}

		@Test
		public void testGetUserById() {
			User mockUser = new User("1", "John", 100);
			when(userService.getUserById("1")).thenReturn(mockUser);

			User user = userController.getUserById("1");

			assertNotNull(user);
			assertEquals("John", user.getName());
			verify(userService, times(1)).getUserById("1");
		}

		@Test
		public void testRegisterUser() {
			User mockUser = new User("1", "John", 100);
			when(userService.registerUser(any(User.class))).thenReturn(mockUser);

			User user = userController.registerUser(mockUser);

			assertNotNull(user);
			assertEquals("John", user.getName());
			verify(userService, times(1)).registerUser(mockUser);
		}

		@Test
		public void testUpdateScore() {
			User mockUser = new User("1", "John", 150);
			when(userService.updateScore("1", 150)).thenReturn(mockUser);

			User user = userController.updateScore("1", 150);

			assertNotNull(user);
			assertEquals(150, user.getScore());
			verify(userService, times(1)).updateScore("1", 150);
		}

		@Test
		public void testDeleteUser() {
			doNothing().when(userService).deleteUser("1");

			ResponseEntity<String> response = userController.deleteUser("1");

			assertEquals("User deleted", response.getBody());
			verify(userService, times(1)).deleteUser("1");
		}
	}
	
}
