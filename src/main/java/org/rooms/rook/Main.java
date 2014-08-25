package org.rooms.rook;

import java.util.List;

import javax.inject.Inject;

import org.rooms.rook.domain.User;
import org.rooms.rook.domain.dao.DataSourceModule;
import org.rooms.rook.domain.dao.UserDao;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {
    
    private UserDao userDao;
    
    @Inject
    public Main(final UserDao userDao) {
        this.userDao = userDao;
    }
    
    public UserDao getUserDao() {
        return userDao;
    }

    public static void main(String[] args) {
    	
    	Injector injector = Guice.createInjector(new MainModule(), new DataSourceModule());
    	Main main = injector.getInstance(Main.class);
    	
    	UserDao userDao = main.getUserDao();
        
        User tom = new User("Tom", "tomew88@gmail.com", "adfasdfasdfa");
        System.out.println(tom);
        tom = userDao.persist(tom);
        System.out.println(tom);
        
        System.err.println("\nPRINTING AFTER INSERT");
        List<User> allUsers = userDao.getAll();
        for (User u : allUsers) {
            System.out.println("LOOPING USER: " + u);
        }
        
        
        User updateTom = new User(tom.getId().get(), "Tom UPDATED", "tomew88@gmail.c88888om", "adfasd###############fasdfa");
        System.out.println(updateTom);
        System.out.println(userDao.persist(updateTom));
        
        System.err.println("\nPRINTING AFTER UPDATE");
        allUsers = userDao.getAll();
        for (User u : allUsers) {
            System.out.println("LOOPING USER: " + u);
        }
        
        System.err.println("\nDELETING TOM");
        userDao.remove(updateTom);
        allUsers = userDao.getAll();
        for (User u : allUsers) {
            System.out.println("LOOPING USER: " + u);
        }
    }
}
