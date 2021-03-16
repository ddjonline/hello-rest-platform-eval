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

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/naptime")
    public Uni<String> naptime() {
        return pi_digits(20000);
    }

    private static Uni<String> pi_digits(int digits) {
        return Uni.createFrom().emitter(em -> {
            int scale = 10000;
            int array_init = 2000;
            StringBuffer pi = new StringBuffer();
            int[] arr = new int[digits + 1];
            int carry = 0;
    
            for (int i = 0; i <= digits; ++i)
                arr[i] = array_init;
    
            for (int i = digits; i > 0; i -= 14) {
                int sum = 0;
                for (int j = i; j > 0; --j) {
                    sum = sum * j + scale * arr[j];
                    arr[j] = sum % (j * 2 - 1);
                    sum /= j * 2 - 1;
                }
    
                pi.append(String.format("%04d", carry + sum / scale));
                carry = sum % scale;
            }
            em.complete("your slice of pi is " + pi.toString());
        });
    }
}