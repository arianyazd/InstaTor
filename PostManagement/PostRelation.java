package com.example.projectfx.PostManagement;

import java.util.ArrayList;
import java.util.List;

import com.example.projectfx.UserManagement.User;

public class PostRelation {
	private static List<PostRelation> postRelations= new ArrayList<>();
	private User user;
	private Post post;
	
	public PostRelation(User user, Post post) {
		super();
		this.user = user;
		this.post = post;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public static List<PostRelation> getPostRelations() {
		return postRelations;
	}

	public static void setPostRelations(List<PostRelation> postRelations) {
		PostRelation.postRelations = postRelations;
	}
	
}
