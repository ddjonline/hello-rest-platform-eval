package com.ddjonline.hello.springboot.webflux.hello;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
public class NaptimeHandler {

    @RequestMapping(value = "/naptime", method = RequestMethod.GET)
    public Mono<ServerResponse> naptime(ServerRequest request) {
        // return Mono.just("your slice of pi is " + pi_digits(20000));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue("your slice of pi is " + pi_digits(20000)));
    }

    private static String pi_digits(int digits) {
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
        return pi.toString();
    }
}