package org.rooms.rook.domain.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.FileSystemResourceAccessor;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

import org.rooms.rook.domain.User;
import org.rooms.rook.domain.dao.jdbi.UserDaoJdbiImpl;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.name.Names;

public class UserDaoIntegrationTests {
    
    private static final String DATABASE_URL = "jdbc:h2:mem:test";
    private static final String DATABASE_USERNAME = "sa";
    private static final String DATABASE_PASSWORD = "";
    private static final String DATABASE_CHANGELOG = "src/main/schema/change_log.yaml";
    
    private Injector injector;
    private Connection conn;
    private Liquibase liquibase;
    private UserDao userDao;
    
    @Before
    public void setUp() throws SQLException, LiquibaseException {
        initialiseInjector();
        buildDatabaseSchema();
        userDao = injector.getInstance(UserDao.class);
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

    private void buildDatabaseSchema() throws SQLException, DatabaseException, LiquibaseException {
        DataSource dataSource = injector.getInstance(DataSource.class);
        conn = dataSource.getConnection();
        
        Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(conn));
        
        liquibase = new Liquibase(DATABASE_CHANGELOG, new FileSystemResourceAccessor(), database);
        liquibase.update(new Contexts());
    }

    private void initialiseInjector() {
        injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                Map<String, String> properties = new HashMap<>();
                properties.put("url", DATABASE_URL);
                properties.put("username", DATABASE_USERNAME);
                properties.put("password", DATABASE_PASSWORD);
                
                Names.bindProperties(binder(), properties);
                
                bind(UserDao.class).to(UserDaoJdbiImpl.class);
                bind(DataSource.class).toProvider(H2DataSourceProvider.class).in(Scopes.SINGLETON);
            }
        });
    }
}