package com.ddjonline.hello.openliberty.hello;

import java.util.concurrent.atomic.AtomicLong;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@RequestScoped
@Path("hello")
public class HelloResource {

    private final AtomicLong counter = new AtomicLong();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello (" + counter.incrementAndGet() + ")";
    }
}
