package org.rooms.rook;

import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

import com.google.inject.servlet.GuiceFilter;
import com.sun.jersey.spi.container.servlet.ServletContainer;

public class Main {
    
    public static void main(String[] args) throws Exception {

    	Server server = new Server(8080);
        ServletContextHandler root = 
                new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);
        
        root.addEventListener(new GuiceServletConfigListener());
        root.addFilter(GuiceFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
        root.addServlet(ServletContainer.class, "/*");
        
        server.start();
    }
}