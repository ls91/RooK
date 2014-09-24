package org.rooms.rook;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

import org.rooms.rook.domain.dao.DataSourceModule;

public class GuiceServletConfigListener extends GuiceServletContextListener {
    
    @Override
    protected Injector getInjector() {
        return Guice.createInjector(
                new DataSourceModule(),
                new DomainModule(),
                new WebserviceModule());
    }
}