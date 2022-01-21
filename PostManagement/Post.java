package com.example.projectfx.PostManagement;

import com.example.projectfx.UserManagement.User;

import java.util.ArrayList;
import java.util.List;

public class Post {
	private List<Comment> comments = new ArrayList<>();
	private PostType postType;
	private String caption;
	private String url;
	
	public Post(String caption, String url, PostType postType) {
		super();
		this.caption = caption;
		this.url = url;
		this.postType = postType;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public PostType getPostType() {
		return postType;
	}
	
	public void setPostType(PostType postType) {
		this.postType = postType;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public int countLikes() {
		int c=0;
		List<PostRelation> postRelations = PostRelation.getPostRelations();
		for(PostRelation postRelation : postRelations) {
			if(postRelation.getPost()==this) {
				c++;
			}
		}
		return c;
	}
	public boolean isLiked(User user) {
		List<PostRelation> postRelations = PostRelation.getPostRelations();
		for(PostRelation postRelation : postRelations) {
			if(postRelation.getUser()==user && postRelation.getPost()==this) {
				return true;
			}
		}
		return false;
	}
	public void newComment(User user,String text) {
		Comment comment = new Comment(user,text);
		comments.add(comment);
	}
	public void changeRelation(User user) {
		List<PostRelation> postRelations = PostRelation.getPostRelations();
		for(PostRelation postRelation : postRelations) {
			if(postRelation.getUser()==user && postRelation.getPost()==this) {
				postRelations.remove(postRelation);
				PostRelation.setPostRelations(postRelations);
				return;
			}
		}
		PostRelation postRelation = new PostRelation(user,this);
		postRelations.add(postRelation);
		PostRelation.setPostRelations(postRelations);
	}
	public User getPostOwner(){
		for(User user: User.getUsers()){
			for(Post post:user.getUserPosts()){
				if(post==this){
					return user;
				}
			}
		}
		return null;
	}
}
