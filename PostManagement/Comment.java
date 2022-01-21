package com.example.projectfx.PostManagement;

import com.example.projectfx.UserManagement.User;

public class Comment {
	private User user;
	private String text;

	public Comment(User user, String text) {
		super();
		this.user = user;
		this.text = text;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
}
