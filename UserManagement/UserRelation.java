package com.example.projectfx.UserManagement;

import java.util.ArrayList;
import java.util.List;

public class UserRelation {
	private static List<UserRelation> userRelations = new ArrayList<>();
	private User follower;
	private User following;
	
	
	public UserRelation(User follower, User following) {
		super();
		this.follower = follower;
		this.following = following;
	}
	public UserRelation() {
		super();
	}


	public static List<UserRelation> getUserRelations() {
		return userRelations;
	}

	public static void setUserRelations(List<UserRelation> userRelations) {
		UserRelation.userRelations = userRelations;
	}

	public User getFollower() {
		return follower;
	}

	public void setFollower(User follower) {
		this.follower = follower;
	}

	public User getFollowing() {
		return following;
	}

	public void setFollowing(User following) {
		this.following = following;
	}
	
	
}
