package org.rooms.rook;

import javax.inject.Inject;

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
    }
}
