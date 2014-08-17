package org.rooms.rook;

import java.util.List;

import org.rooms.rook.domain.User;
import org.rooms.rook.domain.dao.UserDao;
import org.rooms.rook.domain.dao.jdbi.UserDaoJdbiImpl;

public class Main {

    public static void main(String[] args) {

        UserDao userDao = new UserDaoJdbiImpl();

        User user = userDao.findById(1).get();
        System.err.println("USER: " + user.getName());

        List<User> allUsers = userDao.getAll();
        for (User u : allUsers) {
            System.out.println("LOOPING USER: " + u);
        }

    }
}
