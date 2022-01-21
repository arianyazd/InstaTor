package com.example.projectfx.View;

import com.example.projectfx.UserManagement.User;
import com.example.projectfx.MessageManagement.GroupChat;
import com.example.projectfx.MessageManagement.Message;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class GroupChatPage {
    private static Scanner scanner = new Scanner(System.in);
    private User user;
    public GroupChatPage(User user) {
        this.user = user;
    }
    
    private void showMenu() {
        try {
            Runtime.getRuntime().exec("cls");
        } catch (IOException ignore) {
        }
        System.out.println("1. Send message to group");
        System.out.println("2. Show group messages");
        System.out.println("3. join group");
        System.out.println("4. create group");
        System.out.println("0. Back to message");
    }

    public void run() {
        while (true) {
        	try {
    			Thread.sleep(300);
    		} catch (InterruptedException e) {
    			Thread.currentThread().interrupt();
    		}
        	System.out.println("_______________________________________");
        	System.out.println("--GROUP CHATS--");
            showMenu();
            System.out.println("choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if(choice == 0) {
                return;
        	}
            if (choice == 1) {
            	sendGroupMessage(1,findGroupChat());
            }else if (choice == 2) {
            	showGroupMessages(findGroupChat());
            }else if (choice == 3) {
            	joinGroup();
            }else if (choice == 4) {
            	createGroup();
            }else {
                System.out.println("Option is not valid! Please try again");
            }
        }
    }

    private GroupChat findGroupChat() {
    	List<GroupChat> groups = user.getGroups();
    	if(groups.isEmpty()){
            System.out.println("Empty!");
            return null;
        }
    	for(int i=0;i<user.getGroups().size();i++) {
    		System.out.println((i + 1) + "- " + groups.get(i).getGroupName());
    	}
    	System.out.println("Choose a group chat: (-1 to exit)");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == -1) {
            return null;
        }
        --choice;
        if(choice >= groups.size()){
            System.out.println("Invalid option");
            return null;
        }
        return groups.get(choice);
    }
    
    private void sendGroupMessage(int accessGP,GroupChat gp) {
    	if(gp!=null) {
    		if(accessGP==0){
    			return;
    		}else if(accessGP==1) {
    			System.out.println("Enter message: ");
        		String message = scanner.nextLine();
        		user.sendGroupMessage(message, gp);
    			System.out.println("Do you want to keep sending message to this group?(0/1)");
    			int t = scanner.nextInt();
    			scanner.nextLine();
    			sendGroupMessage(t,gp);
    		}else{
    			System.out.println("Invalid option!");
    			return;
    		}
    	}
    }

    private void showGroupMessages(GroupChat gp) {
    	if(gp!=null) {
    		System.out.println("Admin:  "+gp.getAdmin().getUsername());
    		for(Message message : gp.getMessages()) {
    			System.out.println("<"+message.getSender().getUsername()+" >:  " + message.getBody());
    		}
    	}
    }
    private void joinGroup() {
    	System.out.println("Enter group ID: ");
    	int groupID = scanner.nextInt();
    	scanner.nextLine();
    	user.joinGroupChat(groupID);
    }
    private void createGroup() {
    	System.out.println("Enter group ID: ");
    	int groupID = scanner.nextInt();
    	scanner.nextLine();
    	System.out.println("Enter group name: ");
    	String groupName = scanner.nextLine();
    	List<User> members = new ArrayList<>();
    	members.add(user);
    	System.out.println("add users: (write END to exit)");
    	int i=1;
    	while(true) {
    		System.out.println("username "+i+": ");
        	String username = scanner.nextLine();
        	User u = user.searchUsername(username);
        	if(u!=null) {
        		members.add(u);
        	}
        	if(username.equals("END")) {
        		break;
        	}
        	i++;
    	}
    	user.createGroupChat(groupName,groupID,members,user);
    }
}