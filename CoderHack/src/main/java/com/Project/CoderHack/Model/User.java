package com.Project.CoderHack.Model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "users")
@NoArgsConstructor
@Data
@AllArgsConstructor

public class User {

    @Id
    private String userId;
    
    private String userName;
    private int score;
    private Set<String> badges = new HashSet<>();
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Set<String> getBadges() {
		return badges;
	}
	public void setBadges(Set<String> badges) {
		this.badges = badges;
	}
	public User(String userId, String userName, int score, Set<String> badges) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.score = score;
		this.badges = badges;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", score=" + score + ", badges=" + badges + "]";
	}
    
    
    
}
