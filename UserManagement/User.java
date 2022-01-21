package com.example.projectfx.UserManagement;

import com.example.projectfx.MessageManagement.GroupChat;
import com.example.projectfx.MessageManagement.Message;
import com.example.projectfx.PostManagement.Post;
import com.example.projectfx.PostManagement.PostType;

import java.util.ArrayList;
import java.util.List;

public class User{
	private static List<User> users = new ArrayList<>();
	private String phonenumber;
	private String nickName;
	private int age;
	private String biography;
	private List<User> contacts = new ArrayList<>();
	private List<Post> userPosts = new ArrayList<>();
	private List<Post> followingPosts = new ArrayList<>();
	private List<Message> messages = new ArrayList<>();
	private List<Message> groupMessages = new ArrayList<>();
	private List<GroupChat> groups = new ArrayList<>();
	private String username;
	private String password;
	private String email;
	private boolean isPrivate;

	public User(String username, String password, String email, String nickName,String biography,String phonenumber,int age) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.isPrivate= false;
		this.age=age;
		this.biography=biography;
		this.nickName=nickName;
		this.phonenumber=phonenumber;
	}

	public static List<User> getUsers() {
		return users;
	}

	public static void setUsers(List<User> users) {
		User.users = users;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean getPrivate() {
		return isPrivate;
	}

	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	public List<Post> getUserPosts() {
		return userPosts;
	}

	public void setUserPosts(List<Post> userPosts) {
		this.userPosts = userPosts;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public List<Message> getGroupMessages() {
		return groupMessages;
	}

	public void setGroupMessages(List<Message> groupMessages) {
		this.groupMessages = groupMessages;
	}

	public List<GroupChat> getGroups() {
		return groups;
	}

	public void setGroups(List<GroupChat> groups) {
		this.groups = groups;
	}

	public boolean isPrivate() {
		return isPrivate;
	}

	public void setFollowingPosts(List<Post> followingPosts) {
		this.followingPosts = followingPosts;
	}

	public List<User> getContacts() {
		return contacts;
	}

	public void setContacts(List<User> contacts) {
		this.contacts = contacts;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	//com.example.projectfx.UserManagement:
	public void requestFollow(User user,boolean privacy) {
		List<UserRequestRelation> userRequestRelations = UserRequestRelation.getUserRequestRelations();
		List<UserRelation> userRelations = UserRelation.getUserRelations();
		List<BlockRelation> blockRelations = BlockRelation.getBlockRelations();
		
		for(BlockRelation blockRelation : blockRelations) {
			if((blockRelation.getBlocker()==this && blockRelation.getBlocking()==user) || (blockRelation.getBlocker()==user && blockRelation.getBlocking()==this)) {
				return;
			}
		}
		
		for(UserRequestRelation userRequestRelation : userRequestRelations) {
			if(userRequestRelation.getRequester()==this && userRequestRelation.getRequesting()==user) {
				userRequestRelations.remove(userRequestRelation);
				return;
			}
		}
		
		if (privacy){
			UserRequestRelation userRequestRelation = new UserRequestRelation();
			userRequestRelation.setRequester(this);
			userRequestRelation.setRequesting(user);
			userRequestRelations.add(userRequestRelation);
			UserRequestRelation.setUserRequestRelations(userRequestRelations);
			return;
		}
		for(UserRelation userRelation : userRelations) {
			if(userRelation.getFollower()==this && userRelation.getFollowing()==user) {
				userRelations.remove(userRelation);
				UserRelation.setUserRelations(userRelations);
				return;
			}
		}
		UserRelation userRelation = new UserRelation();
		userRelation.setFollower(this);
		userRelation.setFollowing(user);
		userRelations.add(userRelation);
		UserRelation.setUserRelations(userRelations);
		return;
	}
	public void acceptFollow(User user) {
		List<UserRelation> userRelations = UserRelation.getUserRelations();
		List<UserRequestRelation> userRequestRelations = UserRequestRelation.getUserRequestRelations();
		
		for(UserRequestRelation userRequestRelation : userRequestRelations) {
			if(userRequestRelation.getRequester()==user && userRequestRelation.getRequesting()==this) {
				UserRelation userRelation = new UserRelation();
				userRelation.setFollower(this);
				userRelation.setFollowing(user);
				userRelations.add(userRelation);
				userRequestRelations.remove(userRequestRelation);
				UserRequestRelation.setUserRequestRelations(userRequestRelations);
				UserRelation.setUserRelations(userRelations);
				return;
			}
		}
		return;
	}	
	public boolean isFollowed(User user) {
		for (User u : this.getFollowings()) {
			if(u==user) {
				return true;
			}
		}
		return false;
	}
	
	public void blockUser(User user) {
		List<BlockRelation> blocks = BlockRelation.getBlockRelations();
		for(BlockRelation blockRelation : BlockRelation.getBlockRelations()) {
			if(blockRelation.getBlocker()==this && blockRelation.getBlocking()==user) {
				blocks.remove(blockRelation);
				BlockRelation.setBlockRelations(blocks);
				return;
			}
		}
		
		List<UserRelation> userRelations = UserRelation.getUserRelations();
		List<UserRequestRelation> userRequestRelations = UserRequestRelation.getUserRequestRelations();
		BlockRelation blockRelation = new BlockRelation(this, user);
		blocks.add(blockRelation);
		BlockRelation.setBlockRelations(blocks);
		
		for(UserRelation userRelation : userRelations) {
			if(userRelation.getFollower()==user && userRelation.getFollowing()==this) {
				userRelations.remove(userRelation);
				return;
			}
		}
		for(UserRelation userRelation : userRelations) {
			if(userRelation.getFollower()==this && userRelation.getFollowing()==user) {
				userRelations.remove(userRelation);
				return;
			}
		}
		UserRelation.setUserRelations(userRelations);
		
		for(UserRequestRelation userRequestRelation : userRequestRelations) {
			if(userRequestRelation.getRequester()==user && userRequestRelation.getRequesting()==this) {
				userRequestRelations.remove(userRequestRelation);
				return;
			}
		}
		for(UserRequestRelation userRequestRelation : userRequestRelations) {
			if(userRequestRelation.getRequester()==this && userRequestRelation.getRequesting()==user) {
				userRequestRelations.remove(userRequestRelation);
				return;
			}
		}
		UserRequestRelation.setUserRequestRelations(userRequestRelations);
	}
	public List<User> getBlockedUsers() {	
		List<User> blockedUsers = new ArrayList<>();
		
		for(BlockRelation blockRelation : BlockRelation.getBlockRelations()) {
			if(blockRelation.getBlocker()==this) {
				blockedUsers.add(blockRelation.getBlocking());
			}
		}
		return blockedUsers;
	}
	
	public List<User> getFollowings(){
		List<UserRelation> userRelations = UserRelation.getUserRelations();
		List<User> followings = new ArrayList<>();
		
		for(UserRelation userRelation : userRelations) {
			if(userRelation.getFollower()==this) {
				followings.add(userRelation.getFollowing());
			}
		}
		return followings;
	}
	public List<User> getFollowers(){
		List<UserRelation> userRelations = UserRelation.getUserRelations();
		List<User> followers = new ArrayList<>();
		
		for(UserRelation userRelation : userRelations) {
			if(userRelation.getFollowing()==this) {
				followers.add(userRelation.getFollower());
			}
		}
		return followers;
	}
	public List<User> getRequesters(){
		List<UserRequestRelation> userRequestRelations = UserRequestRelation.getUserRequestRelations();
		List<User> requesters = new ArrayList<>();
		
		for(UserRequestRelation userRequestRelation : userRequestRelations) {
			if(userRequestRelation.getRequesting()==this) {
				requesters.add(userRequestRelation.getRequester());
			}
		}
		return requesters;
	}
	
	
	public void changePrivacy() {
		if (this.getPrivate()){
			this.setPrivate(false);
			return;
		}
		this.setPrivate(true);
		return;
		
	}
	
	
	public static User signup(String username,String password,String email,String nickName,String biography,String phonenumber,int age) {
		List<User> users = User.getUsers();
		
		for(User user : users) {
			if(user.getUsername().equals(username)) {
				return null;
			}
		}
		User user = new User(username,password,email, nickName, biography, phonenumber, age);
		users.add(user);
		User.setUsers(users);
		return user;
	}
	public static User login(String username,String password) {
		List<User> users = User.getUsers();
		for(User user : users) {
			if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}
	public User searchUsername(String username) {
		List<User> users = User.getUsers();
		for(User user : users) {
			if(user.getUsername().equals(username)) {
				for(BlockRelation block : BlockRelation.getBlockRelations()) {
					if(block.getBlocker()==user && block.getBlocking()==this) {
						return null;
					}
				}
				return user;
			}
		}
		return null;
	}
	
	
	//com.example.projectfx.PostManagement:
	public Post makePost(String caption,String url,PostType postType) {
		Post post= new Post(caption,url,postType);
		userPosts.add(post);
		return post;
	}
	public List<Post> getFollowingPosts(){
		followingPosts.clear();
		List<User> followings= getFollowings();
		for(User user : followings) {
			followingPosts.addAll(user.getUserPosts());
		}
		return followingPosts;
	}
	
	//com.example.projectfx.MessageManagement:
	public List<User> findContacts() {
		contacts.clear();
		for (Message message : Message.getMessages()) {
			if(message.getReciever() == this) {
				contacts.add(message.getSender());
			}else if(message.getSender() == this) {
				contacts.add(message.getReciever());
			}
		}
		for (BlockRelation blockRelation : BlockRelation.getBlockRelations()) {
			if(blockRelation.getBlocking() == this) {
				contacts.remove(blockRelation.getBlocker());
			}
		}
		return contacts;
	}
	public void sendUserMessage(String body, User reciever) {
		Message message = new Message(body,this,reciever);
		if(reciever != this) {
			messages.add(message);
			Message.addMessages(message);
		}
		
	}
	public void createGroupChat(String groupName,int groupID,List<User> members,User admin) {
		for (GroupChat c : GroupChat.getGroupChats()) {
			if(c.getGroupID()==groupID) {
				System.out.println("!!Group with this ID already exist!!");
				return;
			}
		}
		GroupChat gp=new GroupChat(groupName,groupID,members,admin);
		for(User member : gp.getMembers()) {
			List <GroupChat> groupChats = member.getGroups();
			groupChats.add(gp);
			member.setGroups(groupChats);
		}
		List<GroupChat> g= GroupChat.getGroupChats();
		g.add(gp);
		GroupChat.setGroupChats(g);
			
	}
	public void joinGroupChat(int groupID) {
		for(GroupChat gp : GroupChat.getGroupChats()) {
			if(gp.getGroupID()==groupID) {
				for(User member : gp.getMembers()) {
					if(member==this) {
						return;
					}
				}
				gp.addMember(this);
				List <GroupChat> groupChats = this.getGroups();
				groupChats.add(gp);
				this.setGroups(groupChats);
			}
			
		}
	}
	public void sendGroupMessage(String body, GroupChat gp) {
		Message message = new Message(body,this);
		Message.addGroupMessages(message);
		for(User member : gp.getMembers()) {
			List <Message> gMessages = member.getGroupMessages();
			gMessages.add(message);
			member.setGroupMessages(gMessages);
		}
		
		gp.addMessage(body,this);
	}
}
