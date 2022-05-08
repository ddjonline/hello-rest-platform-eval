package com.ddjonline.hello.openliberty.hello;

import java.util.concurrent.atomic.AtomicLong;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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