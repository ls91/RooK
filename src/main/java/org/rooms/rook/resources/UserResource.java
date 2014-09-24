package org.rooms.rook.resources;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.rooms.rook.domain.dao.UserDao;

@Path("/user")
@Singleton
public class UserResource {
    
    private final UserDao userDao;
    
    @Inject
    public UserResource(final UserDao userDao) {
        this.userDao = userDao;
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String helloWorld() {
        return "Hello, World!";
    }
}