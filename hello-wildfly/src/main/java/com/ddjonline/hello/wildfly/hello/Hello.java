package com.ddjonline.hello.wildfly.hello;

import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/hello")
public class Hello {

    private final AtomicLong counter = new AtomicLong();

    @GET
	@Produces("text/plain")
	public Response doGet() {
		return Response.ok("Hello DIYConf2019 (" + counter.incrementAndGet() + ")").build();
	}
}