package com.codesphere;

import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Sample REST resource showcasing basic CRUD operations backed by MongoDB.
 */
@Path("/fruits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FruitResource {

    @GET
    public List<Fruit> list() {
        return Fruit.listAll();
    }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") String id) {
        Fruit fruit = Fruit.findById(new org.bson.types.ObjectId(id));
        if (fruit == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(fruit).build();
    }

    @POST
    public Response create(Fruit fruit) {
        fruit.persist();
        return Response.status(Response.Status.CREATED).entity(fruit).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {
        boolean deleted = Fruit.deleteById(new org.bson.types.ObjectId(id));
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }
}
