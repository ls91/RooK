package org.rooms.rook.domain.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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
    
    @Before
    public void setUp() throws SQLException, LiquibaseException {
        initialiseInjector();
        buildDatabaseSchema();
    }
    
    @Test
    public void shouldPersistNonPersistedUserInstancesAndReturnTheirDatabaseIds() {
        User user = new User("name", "email", "hash");
        
        assertThat(user.isPersisted()).isFalse();
        
        UserDao userDao = injector.getInstance(UserDao.class);
        User persistedUser = userDao.persist(user);
        
        assertThat(persistedUser.isPersisted()).isTrue();
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