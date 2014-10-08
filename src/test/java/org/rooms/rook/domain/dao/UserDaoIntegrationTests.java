package org.rooms.rook.domain.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.DataSource;

import liquibase.exception.LiquibaseException;

import org.junit.Before;
import org.junit.Test;
import org.rooms.rook.domain.User;
import org.rooms.rook.domain.dao.jdbi.UserDaoJdbiImpl;

public class UserDaoIntegrationTests extends DaoTest {
    
    private UserDaoJdbiImpl userDao;
    
    @Before
    public void setUp() throws SQLException, LiquibaseException, FileNotFoundException, IOException {
        DataSource dataSource = getDataSource();
        buildDatabaseSchema(dataSource);
        userDao = new UserDaoJdbiImpl(dataSource);
    }
    
    @Test
    public void shouldPersistNonPersistedUserInstancesAndReturnTheirDatabaseIds() {
        User user = new User("name", "email", "hash");
        assertThat(user.isPersisted()).isFalse();
        
        User persistedUser = userDao.persist(user);
        assertThat(persistedUser.isPersisted()).isTrue();
    }
    
    @Test
    public void shouldReturnEmptyWhenLookingUpUsersThatHaveNotBeenPersisted() {
        Optional<User> userLookup = userDao.findById(1001);
        assertThat(userLookup.isPresent()).isFalse();
    }
    
    @Test
    public void shouldReturnUserInstanceWhenLookedUpByItsId() {
        User user = new User("Test", "test@example.com", "hash");
        User persistedUser = userDao.persist(user);

        long userId = persistedUser.getId().get();
        Optional<User> userLookup = userDao.findById((int)userId);
        
        assertThat(userLookup.isPresent()).isTrue();
        assertThat(userLookup.get().getName()).isEqualTo(user.getName());
        assertThat(userLookup.get().getEmail()).isEqualTo(user.getEmail());
        assertThat(userLookup.get().getPasswordHash()).isEqualTo(user.getPasswordHash());
    }
    
    @Test
    public void shouldUpdateUsersIfTheyAreAlreadyPersisted() {
        User user = new User("Test", "test@example.com", "hash");
        User persistedUser = userDao.persist(user);
        
        assertThat(persistedUser.getName()).isEqualTo("Test");
        assertThat(persistedUser.getEmail()).isEqualTo("test@example.com");
        
        User modifiedUser = 
                persistedUser
                .setName("Test2")
                .setEmail("test2@example.com");
        
        User updatedUser = userDao.persist(modifiedUser);
        
        assertThat(updatedUser.getName()).isEqualTo("Test2");
        assertThat(updatedUser.getEmail()).isEqualTo("test2@example.com");
    }
}