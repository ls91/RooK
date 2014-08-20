package org.rooms.rook.domain.dao;

import javax.sql.DataSource;

import org.rooms.rook.domain.dao.datasource.H2DataSource;

import com.google.inject.AbstractModule;

public class DataSourceModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(DataSource.class).toProvider(H2DataSource.class);
	}
}
