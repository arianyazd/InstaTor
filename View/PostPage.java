package com.example.projectfx.View;

import com.example.projectfx.PostManagement.Comment;
import com.example.projectfx.PostManagement.Post;
import com.example.projectfx.UserManagement.User;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class PostPage {
    private static Scanner scanner = new Scanner(System.in);
    private User user;
    private Post post;

    public PostPage(Post post,User user) {
    	this.post = post;
        this.user = user;
    }

    private void showMenu(boolean isLiked) {
        try {
            Runtime.getRuntime().exec("cls");
        } catch (IOException ignored) {
        }
        System.out.println("1. Show post");
        System.out.println("2. Show caption");
        System.out.println("3. New comment");
        System.out.println("4. Show comments");
        if(isLiked) {
        	System.out.println("5. Unlike post");
        }else {
        	System.out.println("5. Like post");
        }
        System.out.println("0. Back");
    }

    public void run() {
    	boolean liked;
        while (true) {
        	try {
    			Thread.sleep(300);
    		} catch (InterruptedException e) {
    			Thread.currentThread().interrupt();
    		}
        	liked=post.isLiked(user);
        	System.out.println("_______________________________________");
        	System.out.println("--Post--");
        	showInfo();
            showMenu(liked);
            System.out.println("choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if(choice == 0) {
                return;
        	}
            if (choice == 1) {
//            	showPost();
            }else if (choice == 2) {
            	showCaption();
            } else if (choice == 3) {
            	newComment();
            }else if (choice == 4) {
            	showComments();
            }else if (choice == 5) {
            	likeUnlike();
            }else {
                System.out.println("Option is not valid! Please try again");
            }
        }
    }
    private void showInfo() {
    	System.out.println("Likes:      "+post.countLikes());
    	System.out.println("Comments:   "+post.getComments().size());
    }
        
//    private void showPost() {
//        PostType type = post.getPostType();
//        if(type == PostType.photo) {
//        	new SwingOpenImage(post.getUrl());
//        }else if(type == PostType.video) {
//        	System.out.println("In Development...");
//        }else {
//        	System.out.println("Post not available");
//        }
//    }
    private void showCaption() {
    	System.out.println(post.getCaption());
    }

    private void newComment() {
    	System.out.println("Write your comment:");
    	String text = scanner.nextLine();
    	post.newComment(user, text);;
    }
	private void showComments() {
		List<Comment> comments = post.getComments();
		if(comments.isEmpty()){
            System.out.println("Empty!");
            return;
        }
        for (Comment comment : comments) {
            System.out.println("<" + comment.getUser().getUsername() + "> " + comment.getText());
        }
    }
	private void likeUnlike() {
    	post.changeRelation(user);
    }
}