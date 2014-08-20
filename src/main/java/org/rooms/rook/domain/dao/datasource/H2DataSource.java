package org.rooms.rook.domain.dao.datasource;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcConnectionPool;

import com.google.inject.Provider;

public class H2DataSource implements Provider<DataSource>{

	@Override
	public DataSource get() {
		return JdbcConnectionPool.create("jdbc:h2:~/RooK", "sa", "");
	}
}
