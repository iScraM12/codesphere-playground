package com.iscram.codesphere.adapter.in.rest;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import com.iscram.codesphere.application.port.out.FruitRepositoryPort;
import com.iscram.codesphere.domain.entity.Fruit;

/**
 * Sample REST resource showcasing basic CRUD operations.
 */
@Path("/fruits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FruitResource {

    @Inject
    FruitRepositoryPort fruitStore;

    @GET
    public List<Fruit> list() {
        return fruitStore.list();
    }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") String id) {
        return fruitStore.find(id)
                .map(fruit -> Response.ok(fruit).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    public Response create(Fruit fruit) {
        return Response.status(Response.Status.CREATED).entity(fruitStore.create(fruit)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {
        if (!fruitStore.delete(id)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }
}
