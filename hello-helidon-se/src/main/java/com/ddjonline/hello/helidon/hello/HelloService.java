package com.ddjonline.hello.helidon.hello;

import java.util.concurrent.atomic.AtomicLong;

import io.helidon.webserver.http.HttpRules;
import io.helidon.webserver.http.HttpService;
import io.helidon.webserver.http.ServerRequest;
import io.helidon.webserver.http.ServerResponse;

/**
 * A simple service to greet you. Examples:
 *
 * Get default greeting message:
 * curl -X GET http://localhost:8080/hello
 *
 * Get pi calculated to the 20K decimal:
 * curl -X GET http://localhost:8080/naptime
 */
public class HelloService implements HttpService {

    private final AtomicLong counter = new AtomicLong();

    @Override
    public void routing(HttpRules rules) {
        rules.get("/hello", this::getHelloMessageHandler)
                .get("/naptime", this::getNaptimeMessageHandler);
    }

    private void getHelloMessageHandler(ServerRequest request, ServerResponse response) {
        response.send("Hello (" + counter.incrementAndGet() + ")");
    }

    private void getNaptimeMessageHandler(ServerRequest request, ServerResponse response) {
        response.send(piDigits(20000));
    }

    private static String piDigits(int digits) {
        int scale = 10000;
        int arrayInit = 2000;
        StringBuilder pi = new StringBuilder();
        int[] arr = new int[digits + 1];
        int carry = 0;

        for (int i = 0; i <= digits; ++i) {
            arr[i] = arrayInit;
        }

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
        return "your slice of pi is " + pi;
    }
}
