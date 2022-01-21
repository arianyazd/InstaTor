package com.example.projectfx.UserManagement;

import java.util.ArrayList;
import java.util.List;

public class UserRequestRelation {
	private static List<UserRequestRelation> userRequestRelations = new ArrayList<>();
	private User requester;
	private User requesting;
	
	
	public UserRequestRelation(User requester, User requesting) {
		super();
		this.requester = requester;
		this.requesting = requesting;
	}
	public UserRequestRelation() {
		super();
	}

	public static List<UserRequestRelation> getUserRequestRelations() {
		return userRequestRelations;
	}

	public static void setUserRequestRelations(List<UserRequestRelation> userRequestRelations) {
		UserRequestRelation.userRequestRelations = userRequestRelations;
	}

	public User getRequester() {
		return requester;
	}

	public void setRequester(User requester) {
		this.requester = requester;
	}

	public User getRequesting() {
		return requesting;
	}

	public void setRequesting(User requesting) {
		this.requesting = requesting;
	}
	
	
}
