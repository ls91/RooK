package org.rooms.rook.domain.dao;

import java.util.List;

import org.rooms.rook.domain.User;

public interface UserDao {
	
	public User findUserById(int id);
	
	public List<User> getAllUsers();
	
}
