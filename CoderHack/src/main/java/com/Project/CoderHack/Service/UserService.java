package com.Project.CoderHack.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Project.CoderHack.Exception.UserNotFoundException;
import com.Project.CoderHack.Model.User;
import com.Project.CoderHack.Repo.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAllByOrderByScoreDesc();
    }

    public User getUserById(String userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public User registerUser(User user) {
        user.setScore(0);
        user.setBadges(new HashSet<>());
        return userRepository.save(user);
    }

    public User updateScore(String userId, int newScore) {
        if (newScore < 0 || newScore > 100) {
            throw new IllegalArgumentException("Score must be between 0 and 100");
        }

        User user = getUserById(userId);
        user.setScore(newScore);
        user.setBadges(assignBadges(newScore));
        return userRepository.save(user);
    }

    public void deleteUser(String userId) {
        getUserById(userId); // to throw exception if not found
        userRepository.deleteById(userId);
    }

    private Set<String> assignBadges(int score) {
        Set<String> badges = new HashSet<>();
        if (score >= 1) badges.add("Code Ninja");
        if (score >= 30) badges.add("Code Champ");
        if (score >= 60) badges.add("Code Master");
        return badges;
    }
}
