package com.example.projectfx.View;

import com.example.projectfx.PostManagement.Post;
import com.example.projectfx.UserManagement.User;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class HomePage {
    private static Scanner scanner = new Scanner(System.in);
    private User user;

    public HomePage(User user) {
        this.user = user;
    }

    private void showMenu() {
        try {
            Runtime.getRuntime().exec("cls");
        } catch (IOException ignored) {
        }
        System.out.println("1. Show followers posts");
        System.out.println("2. Go to Profile");
        System.out.println("3. Search user");
        System.out.println("4. Send message");
        System.out.println("0. Log out");
    }

    public void run() {
        while (true) {
        	try {
    			Thread.sleep(300);
    		} catch (InterruptedException e) {
    			Thread.currentThread().interrupt();
    		}
        	System.out.println("_______________________________________");
            System.out.println("--HOME--");
            showMenu();
            System.out.println("choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 0) {
                return;
            }
            if (choice == 1) {
            	showFollowingPosts();
            } else if (choice == 2) {
            	ProfilePage profilePage = new ProfilePage(user);
                profilePage.run();
            } else if (choice == 3) {
            	User u = searchUser();
            	if(u==null) {
            		System.out.println("User not found!");
            	}else if(u==user){
            		ProfilePage profilePage = new ProfilePage(user);
            		profilePage.run();
            	}else {
            		UserPage userPage = new UserPage(user,u);
            		userPage.run();
            	}
            }else if (choice == 4) {
            	MessagePage messagePage = new MessagePage(user);
                messagePage.run();
            }else {
                System.out.println("Option is not valid! Please try again");
            }
        }
    }

    private User searchUser() {
    	System.out.println("Enter a username:");
        String username = scanner.nextLine();
        User u = user.searchUsername(username);
        return u;
    }
    
    private void showFollowingPosts() {
        List<Post> posts = user.getFollowingPosts();
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
}