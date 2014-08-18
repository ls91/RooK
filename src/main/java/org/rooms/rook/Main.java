package org.rooms.rook;

import java.util.List;

import org.rooms.rook.domain.User;
import org.rooms.rook.domain.dao.UserDao;
import org.rooms.rook.domain.dao.jdbi.UserDaoJdbiImpl;

public class Main {

    public static void main(String[] args) {

        UserDao userDao = new UserDaoJdbiImpl();
        
        User tom = new User("Tom", "tomew88@gmail.com", "adfasdfasdfa");
        System.out.println(tom);
        
        System.out.println(userDao.persist(tom));

        List<User> allUsers = userDao.getAll();
        for (User u : allUsers) {
            System.out.println("LOOPING USER: " + u);
        }

    }
}
