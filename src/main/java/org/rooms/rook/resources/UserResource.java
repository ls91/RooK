package org.rooms.rook.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Variant;

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
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        List<User> result = userDao.getAll();
        return Response.ok(result, MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") final int id) {
        Optional<User> result = userDao.findById(id);
        if (result.isPresent()) {
            return Response.ok(result.get(), MediaType.APPLICATION_JSON).build();
        } else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }
    
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user) throws URISyntaxException {
        if (user.isPersisted() || user.getId().isPresent()) {
            throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
        } else {
            final User result = userDao.persist(user);
            return Response.created(new URI("/user/" + result.getId().get())).build();
        }
    }
    
    @PUT
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(User user) throws URISyntaxException {
        if (!user.getId().isPresent()) {
            throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
        } else {
            final User result = userDao.persist(user);
            return Response.created(new URI("/user/" + result.getId().get())).build();
        }
    }
    
    @DELETE
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteUser(User user) {
        userDao.remove(user);
        return Response.ok().build();
    }
}
