package org.rooms.rook.domain.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.FileSystemResourceAccessor;

public class DaoTest {
    
    private static final String TEST_PROPERTIES_FILE = "src/test/resources/test-config.properties";

    protected static DataSource getDataSource() throws FileNotFoundException, IOException, DatabaseException, SQLException, LiquibaseException {
        Properties prop = new Properties();
        prop.load(new FileReader(new File(TEST_PROPERTIES_FILE)));
        H2DataSourceProvider h2dp = new H2DataSourceProvider(
                prop.getProperty("DATABASE_URL"), 
                prop.getProperty("DATABASE_USERNAME"), 
                prop.getProperty("DATABASE_PASSWORD"));
        return h2dp.get();
    }
    
    protected void buildDatabaseSchema(DataSource dataSource) throws SQLException, DatabaseException, LiquibaseException {
        Connection conn = dataSource.getConnection();
        Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(conn));
        Liquibase liquibase = new Liquibase("src/main/schema/change_log.yaml", new FileSystemResourceAccessor(), database);
        liquibase.update(new Contexts());
    }    
}
