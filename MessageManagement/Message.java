package com.example.projectfx.MessageManagement;

import com.example.projectfx.UserManagement.User;

import java.util.ArrayList;
import java.util.List;

public class Message {
	private static List<Message> messages = new ArrayList<>();
	private static List<Message> groupMessages = new ArrayList<>();
	private User sender;
	private User reciever;
	private String body;
	
	public Message(String body,User sender, User reciever) {
		super();
		this.sender = sender;
		this.reciever = reciever;
		this.body = body;
	}
	public Message(String body,User sender) {
		super();
		this.sender = sender;
		this.body = body;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReciever() {
		return reciever;
	}

	public void setReciever(User reciever) {
		this.reciever = reciever;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	public static List<Message> getMessages() {
		return messages;
	}
	
	public static List<Message> getGroupMessages() {
		return groupMessages;
	}
	public static void setGroupMessages(List<Message> groupMessages) {
		Message.groupMessages = groupMessages;
	}
	public static void setMessages(List<Message> messages) {
		Message.messages = messages;
	}
	
	public static void addMessages(Message message) {
		messages.add(message);
	}
	public static void addGroupMessages(Message message) {
		groupMessages.add(message);
	}
	
}
