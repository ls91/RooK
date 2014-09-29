package org.rooms.rook.resources;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.rooms.rook.domain.User;
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
    
    @GET
    @Path("/{id}")
    public Response getUser(@PathParam("id") final int id) {
        Optional<User> result = userDao.findById(id);
        if (result.isPresent()) {
            return Response.ok(result.get(), "application/json").build();
        } else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }
}
