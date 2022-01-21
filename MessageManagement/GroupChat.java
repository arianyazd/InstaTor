package com.example.projectfx.MessageManagement;

import java.util.ArrayList;
import java.util.List;

import com.example.projectfx.UserManagement.User;


public class GroupChat {
	private static List<GroupChat> groupChats = new ArrayList<>();
	private int groupID;
	private String groupName;
	private List<User> members = new ArrayList<>();
	private List<Message> messages = new ArrayList<>();
	private User admin;
	
	public GroupChat(String groupName,int groupID, List<User> members,User admin) {
		super();
		this.groupID = groupID;
		this.members = members;
		this.groupName = groupName;
		this.admin = admin;
	}

	
	public static List<GroupChat> getGroupChats() {
		return groupChats;
	}

	public static void setGroupChats(List<GroupChat> groupChats) {
		GroupChat.groupChats = groupChats;
	}

	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}


	public int getGroupID() {
		return groupID;
	}

	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public User getAdmin() {
		return admin;
	}

	public void setAdmin(User admin) {
		this.admin = admin;
	}

	

	public void addMember(User user) {
		members.add(user);
	}
	public void addMessage(String body,User sender) {
		Message message = new Message(body,sender);
		messages.add(message);
	}
}
