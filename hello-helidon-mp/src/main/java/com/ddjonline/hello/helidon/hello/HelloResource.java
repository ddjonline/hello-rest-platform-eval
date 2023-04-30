package com.ddjonline.hello.helidon.hello;

import java.util.concurrent.atomic.AtomicLong;

import jakarta.inject.Singleton;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
@Singleton
public class HelloResource {
  
    private final AtomicLong counter = new AtomicLong();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello (" + counter.incrementAndGet() + ")";
    }
}
