package org.rooms.rook;

import org.rooms.rook.domain.dao.UserDao;
import org.rooms.rook.domain.dao.jdbi.UserDaoJdbiImpl;

import com.google.inject.AbstractModule;

public class DomainModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(UserDao.class).to(UserDaoJdbiImpl.class);
    }
}