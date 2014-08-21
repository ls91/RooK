package org.rooms.rook.domain.dao;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import javax.sql.DataSource;

import com.google.common.io.ByteSource;
import com.google.common.io.Resources;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.name.Names;

public class DataSourceModule extends AbstractModule {

	private static final String DATABASE_PROPERTIES = "liquibase.properties";

    @Override
	protected void configure() {
	    Names.bindProperties(binder(), loadProperties());
	    bind(DataSource.class).toProvider(H2DataSourceProvider.class).in(Scopes.SINGLETON);
	}
	
	private Properties loadProperties() {
	    Properties props = new Properties();
	    try {
	        URL url = Resources.getResource(DATABASE_PROPERTIES);
	        ByteSource bs = Resources.asByteSource(url);
	        InputStream is = bs.openBufferedStream();
	        props.load(is);
	    }
	    catch (IOException e) {
	        // TODO: Log this
	    }
	    return props;
	}
}
