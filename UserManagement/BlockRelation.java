package com.example.projectfx.UserManagement;

import java.util.ArrayList;
import java.util.List;

public class BlockRelation {
	private static List<BlockRelation> blockRelations = new ArrayList<>();
	private User blocker;
	private User blocking;
	
	public BlockRelation(User blocker, User blocking) {
		super();
		this.blocker = blocker;
		this.blocking = blocking;
	}

	public static List<BlockRelation> getBlockRelations() {
		return blockRelations;
	}

	public static void setBlockRelations(List<BlockRelation> blockRelations) {
		BlockRelation.blockRelations = blockRelations;
	}

	public User getBlocker() {
		return blocker;
	}

	public void setBlocker(User blocker) {
		this.blocker = blocker;
	}

	public User getBlocking() {
		return blocking;
	}

	public void setBlocking(User blocking) {
		this.blocking = blocking;
	}
	
}
