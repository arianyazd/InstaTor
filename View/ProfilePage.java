package com.example.projectfx.View;

import com.example.projectfx.PostManagement.Post;
import com.example.projectfx.PostManagement.PostType;
import com.example.projectfx.UserManagement.User;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ProfilePage {
    private static Scanner scanner = new Scanner(System.in);
    private User user;

    public ProfilePage(User user) {
        this.user = user;
    }

    private void showMenu() {
        try {
            Runtime.getRuntime().exec("cls");
        } catch (IOException ignored) {
        }
        System.out.println("1. Make new post");
        System.out.println("2. Change my privacy");
        System.out.println("3. Show my posts");
        System.out.println("4. Show followers");
        System.out.println("5. Show followings");
        System.out.println("6. Show blocked users");
        if(user.getPrivate()) {
        	System.out.println("7. Show requests");
        }
        System.out.println("0. Back to Home");
    }

    public void run() {
        while (true) {
        	try {
    			Thread.sleep(300);
    		} catch (InterruptedException e) {
    			Thread.currentThread().interrupt();
    		}
        	System.out.println("_______________________________________");
        	System.out.println("--PROFILE--");
        	showInfo();
            showMenu();
            System.out.println("choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if(choice == 0) {
                return;
        	}
            if (choice == 1) {
            	makePost();
            }else if (choice == 2) {
            	changePrivacy();
            } else if (choice == 3) {
            	showMyPosts();
            }else if (choice == 4) {
            	showFollowers();
            }else if (choice == 5) {
            	showFollowings();
            }else if (choice == 6) {
            	showBlockedUsers();
            }
            if(choice==7) {
            	if (user.getPrivate()) {
                    showRequests();
                    continue;
            	}
                System.out.println("Option is not valid! Please try again");
            }else {
                System.out.println("Option is not valid! Please try again");
            }
        }
    }
    private void changePrivacy() {
    	if(user.getPrivate()) {
    		user.setPrivate(false);
    		return;
    	}
    	user.setPrivate(true);
    }
    private void showInfo() {
    	System.out.println("Username:    "+user.getUsername());
    	System.out.println("Private:     "+user.getPrivate());
    	System.out.println("Posts:       "+user.getUserPosts().size());
    	System.out.println("Followers:   "+user.getFollowers().size());
    	System.out.println("Followings:  "+user.getFollowings().size());
    	System.out.println("Requests:    "+user.getRequesters().size()+'\n');
    }
    private void makePost() {
    	System.out.println("Enter caption");
        String caption = scanner.nextLine();
        System.out.println("Enter post url");
        String url = scanner.nextLine();
        System.out.println("Enter post type(photo/video)");
        String type = scanner.nextLine();
        PostType postType;
        if(type.equals("photo")) {
        	postType=PostType.photo;
        }else if(type.equals("video")) {
        	postType=PostType.video;
        }else {
        	postType=PostType.noPost;
        }
        user.makePost(caption,url,postType);
    }
    
    private void showMyPosts() {
        List<Post> posts = user.getUserPosts();
        showPosts(posts);
    }
    private void showPosts(List<Post> posts) {
        if(posts.isEmpty()){
            System.out.println("Empty!");
            return;
        }
        for (int i=0;i<posts.size();i++) {
            System.out.println("<" + (i + 1) + "> ");
        }
        System.out.println("Choose a post by number (-1 to exit): ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == -1) {
            return;
        }
        --choice;
        if(choice >= posts.size()){
            System.out.println("Invalid option");
            return;
        }
        Post post = posts.get(choice);
        PostPage postPage = new PostPage(post, user);
        postPage.run();
    }
    
    
    private void showFollowers() {
    	List<User> followers = user.getFollowers();
        showUsers(followers);
    }
	private void showFollowings() {
		List<User> followings = user.getFollowings();
        showUsers(followings);
    }
	private void showRequests() {
    	List<User> requests = user.getRequesters();
        showUsers(requests);
    }
	private void showBlockedUsers() {
    	List<User> blocks = user.getBlockedUsers();
        showUsers(blocks);
    }
    private void showUsers(List<User> users) {
    	if(users.isEmpty()){
            System.out.println("Empty!");
            return;
        }
        int i = 0;
        for (User user : users) {
            System.out.println((i + 1) + ". " + user.getUsername());
            i++;
        }
        System.out.println("Choose a user by number (-1 to exit): ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == -1) {
            return;
        }
        --choice;
        if(choice >= users.size()){
            System.out.println("Invalid option");
            return;
        }
        User u = users.get(choice);
        UserPage userPage = new UserPage(user,u);
        userPage.run();
    }
}