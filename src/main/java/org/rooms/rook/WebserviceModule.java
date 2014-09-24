package org.rooms.rook;

import com.google.inject.servlet.ServletModule;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class WebserviceModule extends ServletModule {
    
    @Override
    protected void configureServlets() {
        bind(GuiceContainer.class);
        
        PackagesResourceConfig resourceConfig = new PackagesResourceConfig("org.rooms.rook.resources");
        for (Class<?> resource : resourceConfig.getClasses()) {
            bind(resource);
        }
        
        serve("/*").with(GuiceContainer.class);
    }
}