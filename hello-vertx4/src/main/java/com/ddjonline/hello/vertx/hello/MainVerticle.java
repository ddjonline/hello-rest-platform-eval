package com.ddjonline.hello.vertx.hello;

import java.util.concurrent.atomic.AtomicLong;

import io.vertx.core.http.HttpMethod;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.ext.web.Router;

public class MainVerticle extends AbstractVerticle {

    private final AtomicLong counter = new AtomicLong();

    @Override
    public void start() {
        Router router = Router.router(vertx);
        router.route("/hello").method(HttpMethod.GET).handler(context -> {
            context.response().end("Hello (" + counter.incrementAndGet() + ")");
        });
        router.route("/naptime").method(HttpMethod.GET).blockingHandler(context -> {
            context.response().end("your slice of pi is " + pi_digits(20000));
        });

        vertx.createHttpServer().requestHandler(router).rxListen(8080).subscribe();
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
