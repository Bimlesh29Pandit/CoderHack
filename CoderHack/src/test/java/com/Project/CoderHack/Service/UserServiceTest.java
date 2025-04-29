package com.Project.CoderHack.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.Project.CoderHack.Exception.UserNotFoundException;
import com.Project.CoderHack.Model.User;
import com.Project.CoderHack.Repo.UserRepository;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@Mock
	UserRepository userRepository;
	@InjectMocks
	UserService userService;
	@Test
	public void getAllUsersTest() {
		System.out.println("get all user ");
		List<User> users = new ArrayList<>();
		Mockito.when(userRepository.findAllByOrderByScoreDesc()).thenReturn(users);
		List<User> Actualusers = userService.getAllUsers();

		Assertions.assertEquals(users, Actualusers);
	}

	@Test
	public void getUserByIdTest() {
		String userId = "123";
		User expectedUser = new User();
		expectedUser.setUserId(userId);
		Mockito.when(userRepository.findById("1")).thenReturn(java.util.Optional.of(expectedUser));
		User actualUser = userService.getUserById("1");

		Assertions.assertEquals(actualUser, expectedUser);



	}

	@Test
	public void registerUserTest() {
		User user = new User();
		user.setUserId("123");
		user.setUserName("Bimlesh");
		user.setScore(0);
		user.setBadges(new HashSet<>());
		Mockito.when(userRepository.save(user)).thenReturn(user);
		User actualUser = userService.registerUser(user);

		Assertions.assertEquals(user, actualUser);


	}
	@Test
	public void updateScoreTest(){
		User user = new User();
		int newScore = 50;
		String userId = "123";
		user.setUserId("123");
		Mockito.when(userRepository.save(user)).thenReturn(user);
		Mockito.when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(user));
		User updatedUser = userService.updateScore(userId,newScore);
		Assertions.assertEquals(newScore, updatedUser.getScore());
		Assertions.assertTrue(updatedUser.getBadges().contains("Code Ninja"));
		Assertions.assertTrue(updatedUser.getBadges().contains("Code Champ"));
	}
	//
	//	@Test
	//	public void getUserByIdNotFoundTest() {
	//		String userId = "123";
	//
	//		Mockito.when(userRepository.findById(userId)).thenReturn(java.util.Optional.empty());
	//
	//		Assertions.assertThrows(UserNotFoundException.class, () -> userService.getUserById(userId));
	//	}
	//
	//	@Test
	//	public void registerUserTest() {
	//		User user = new User();
	//		User savedUser = new User();
	//		savedUser.setScore(0);
	//		savedUser.setBadges(new HashSet<>());
	//
	//		Mockito.when(userRepository.save(user)).thenReturn(savedUser);
	//
	//		User actualUser = userService.registerUser(user);
	//
	//		Assertions.assertEquals(savedUser, actualUser);
	//	}
	//
	//	@Test
	//	public void updateScoreTest() {
	//		String userId = "123";
	//		int newScore = 50;
	//		User user = new User();
	//		user.setId(userId);
	//
	//		Mockito.when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(user));
	//		Mockito.when(userRepository.save(user)).thenReturn(user);
	//
	//		User updatedUser = userService.updateScore(userId, newScore);
	//
	//		Assertions.assertEquals(newScore, updatedUser.getScore());
	//		Assertions.assertTrue(updatedUser.getBadges().contains("Code Ninja"));
	//	}
	//
	//	@Test
	//	public void updateScoreInvalidTest() {
	//		String userId = "123";
	//		int invalidScore = 150;
	//
	//		Assertions.assertThrows(IllegalArgumentException.class, () -> userService.updateScore(userId, invalidScore));
	//	}
	//
	//	@Test
	//	public void deleteUserTest() {
	//		String userId = "123";
	//		User user = new User();
	//		user.setId(userId);
	//
	//		Mockito.when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(user));
	//		Mockito.doNothing().when(userRepository).deleteById(userId);
	//
	//		Assertions.assertDoesNotThrow(() -> userService.deleteUser(userId));
	//	}
}
