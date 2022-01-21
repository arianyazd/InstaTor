package com.example.projectfx.View;

import com.example.projectfx.PostManagement.Post;
import com.example.projectfx.UserManagement.User;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class UserPage {
    private static Scanner scanner = new Scanner(System.in);
    private User mainUser;
    private User secUser;

    public UserPage(User mainUser,User secUser) {
        this.mainUser = mainUser;
        this.secUser = secUser;
    }

    private void showMenu(boolean showPostFlag,boolean acceptFlag,boolean followFlag) {
        try {
            Runtime.getRuntime().exec("cls");
        } catch (IOException ignored) {

        }
    	System.out.println("1. Block / Unblock");
        if(acceptFlag) {
        	if(followFlag) {
        		System.out.println("2. Unfollow");
       		}else {
       			System.out.println("2. Follow");
       		}
            System.out.println("3. Accept");
            	if(showPostFlag) {
                	System.out.println("4. Show posts");
                }
            System.out.println("0. Back to home");
        }else {
        	if(followFlag) {
        		System.out.println("2. Unfollow");
       		}else {
       			System.out.println("2. Follow");
       		}
            if(showPostFlag) {
            	System.out.println("3. Show posts");
            }
            System.out.println("0. Back to home");
        }
        
    }

    public void run() {
    	try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
    	boolean followFlag=mainUser.isFollowed(secUser);;
    	boolean isPrivate=secUser.getPrivate();
    	boolean isAvailable;
    	boolean acceptFlag;
        while (true) {
        	isAvailable=false;
        	acceptFlag=false;
        	for(User u : mainUser.getRequesters()) {
        		if (u==secUser) {
        			acceptFlag=true;
        		}
        	}
        	if(isPrivate) {
        		for(User u : mainUser.getFollowings()) {
        			if (u==secUser) {
        				isAvailable=true;
        			}
        		}
        	}else {
        		isAvailable=true;
        	}
        	System.out.println("_______________________________________");
            System.out.println("--USER--");
            showInfo(isAvailable);
            showMenu(isAvailable,acceptFlag,followFlag);
            System.out.println("choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 0) {
                return;
            }
            if (choice == 1) {
            	block();
            	continue;
            }else if (choice == 2) {
            	follow();
            	continue;
            }
            if(acceptFlag) {
            	if (choice == 3) {
                	accept();
                	continue;
                }
            	if (isAvailable) {
            		if (choice == 4) {
                    	showUserPosts();
                    	continue;
                    }
            	}
            	
            }else {
            	if (isAvailable) {
            		if (choice == 3) {
                    	showUserPosts();
                    	continue;
                    }
            	}
            }
            
        }
    }

    private void showInfo(boolean isAvailable) {
    	System.out.println("Username:    "+secUser.getUsername());
    	System.out.println("Private:     "+secUser.getPrivate());
    	if(isAvailable) {
    		System.out.println("Posts:       "+secUser.getUserPosts().size());
    		System.out.println("Followers:   "+secUser.getFollowers().size());
    		System.out.println("Followings:  "+secUser.getFollowings().size()+'\n');
    	}
    }
    
    private void block() {
    	mainUser.blockUser(secUser);
    	System.out.println("Done");
    }
    private void follow() {
    	mainUser.requestFollow(secUser,secUser.getPrivate());
    	System.out.println("Done");
    }
    private void accept() {
    	mainUser.acceptFollow(secUser);
    	System.out.println("Done");
    }
    
    private void showUserPosts() {
        List<Post> posts = secUser.getUserPosts();
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
        PostPage postPage = new PostPage(post, secUser);
        postPage.run();
    }
}