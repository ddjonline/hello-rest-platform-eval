package com.ddjonline.hello.springboot.webflux.hello;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class HelloHandler {

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public Mono<ServerResponse> sayHello(ServerRequest request) {
        // return Mono.just("Hello (" + counter.incrementAndGet() + ")");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue("Hello (" + counter.incrementAndGet() + ")"));
    }
}