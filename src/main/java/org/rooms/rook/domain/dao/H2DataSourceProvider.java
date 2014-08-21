package org.rooms.rook.domain.dao;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

import org.h2.jdbcx.JdbcConnectionPool;

import com.google.inject.Provider;

public class H2DataSourceProvider implements Provider<DataSource>{
    
    private final String url;
    private final String username;
    private final String password;
    
    @Inject
    public H2DataSourceProvider(@Named("url") final String url,
                                @Named("username") final String username,
                                @Named("password") final String password ) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

	@Override
	public DataSource get() {
		return JdbcConnectionPool.create(url, username, password);
	}
}
