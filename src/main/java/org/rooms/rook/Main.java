package org.rooms.rook;

import java.util.List;

import org.rooms.rook.domain.User;
import org.rooms.rook.domain.dao.UserDao;
import org.rooms.rook.domain.dao.jdbi.UserDaoJdbiImpl;

public class Main {
	
	public static void main(String[] args) {
		
		UserDao userDao = new UserDaoJdbiImpl();
		
		User user = userDao.findUserById(1);
		System.err.println("USER: " + user.getName());
		
		
		List<User> allUsers = userDao.getAllUsers();
		for (User u : allUsers) {
			System.out.println("LOOPING USER: " + u.getId() + ":" + u.getName() + ":" + u.getEmail());
		}

	}
}
