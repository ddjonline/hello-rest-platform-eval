package com.ddjonline.hello.quarkus.hello;

import java.util.concurrent.atomic.AtomicLong;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.smallrye.mutiny.Uni;

@Singleton
@Path("/")
public class HelloResource {

    private final AtomicLong counter = new AtomicLong();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/hello")
    public Uni<String> hello() {
        return Uni.createFrom().item("Hello (" + counter.incrementAndGet() + ")");
    }
}