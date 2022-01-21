package com.example.projectfx.View;

import com.example.projectfx.UserManagement.User;
import com.example.projectfx.MessageManagement.Message;

import java.util.List;
import java.util.Scanner;
import java.io.IOException;

public class MessagePage {
    private static Scanner scanner = new Scanner(System.in);
    private User user;
    public MessagePage(User user) {
        this.user = user;
    }
    
    private void showMenu() {
        try {
            Runtime.getRuntime().exec("cls");
        } catch (IOException ignore) {
        }
        System.out.println("1. Send message to user");
        System.out.println("2. Show user messages");
        System.out.println("3. Go to group chats");
        System.out.println("0. Back to home");
    }

    public void run() {
        while (true) {
        	try {
    			Thread.sleep(300);
    		} catch (InterruptedException e) {
    			Thread.currentThread().interrupt();
    		}
        	System.out.println("_______________________________________");
        	System.out.println("--Message--");
            showMenu();
            System.out.println("choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if(choice == 0) {
                return;
        	}
            if (choice == 1) {
            	sendUserMessage();
            }else if (choice == 2) {
            	showUserMessage();
            }else if (choice == 3) {
            	GroupChatPage groupChatPage = new GroupChatPage(user);
                groupChatPage.run();
            }else {
                System.out.println("Option is not valid! Please try again");
            }
        }
    }

    private void sendUserMessage() {
    	System.out.println("Enter username: ");
    	String username = scanner.nextLine();
    	User u = user.searchUsername(username);
        System.out.println("Enter message: ");
        String message = scanner.nextLine();
        user.sendUserMessage(message, u);
    }

    private void showUserMessage() {
    	List<User> contacts = user.findContacts();
    	if(contacts.isEmpty()){
            System.out.println("Empty!");
            return;
        }
    	System.out.println("Choose a contact: (-1 to exit)");
    	for(int i=0;i<user.getContacts().size();i++) {
    		System.out.println((i + 1) + "- " + contacts.get(i).getUsername());
    	}
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == -1) {
            return;
        }
        --choice;
        if(choice >= contacts.size()){
            System.out.println("Invalid option");
            return;
        }
        List<Message> messages = Message.getMessages();
        User contact = contacts.get(choice);
        for(Message message : messages) {
       		if((message.getReciever() == contact) && (message.getSender() == user)) {
       			System.out.println("<"+user.getUsername()+">:  " + message.getBody());
        	}if((message.getReciever() == user) && (message.getSender() == contact)) {
       			System.out.println("<"+contact.getUsername()+">:  " + message.getBody());
      		}
        }
        
        System.out.println();
    }
}