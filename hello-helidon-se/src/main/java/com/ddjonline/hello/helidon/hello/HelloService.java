
package com.ddjonline.hello.helidon.hello;

import java.util.concurrent.atomic.AtomicLong;

import io.helidon.config.Config;
import io.helidon.webserver.Routing;
import io.helidon.webserver.ServerRequest;
import io.helidon.webserver.ServerResponse;
import io.helidon.webserver.Service;

/**
 * A simple service to greet you. Examples:
 *
 * Get default greeting message:
 * curl -X GET http://localhost:8080/hello
 *
 * Get pi calculated to the 20K decimal:
 * curl -X GET http://localhost:8080/naptime
 *
 */

public class HelloService implements Service {

    private final AtomicLong counter = new AtomicLong();
    
    HelloService(Config config) { }

    /**
     * A service registers itself by updating the routing rules.
     * @param rules the routing rules.
     */
    @Override
    public void update(Routing.Rules rules) {
        rules
            .get("/hello", this::getHelloMessageHandler)
            .get("/naptime", this::getNaptimeMessageHandler);
    }

    /**
     * Return a friendly greeting message.
     * @param request the server request
     * @param response the server response
     */
    private void getHelloMessageHandler(ServerRequest request, ServerResponse response) {
        response.send("Hello (" + counter.incrementAndGet() + ")");
    }

    /**
     * Return pi after a short nap
     * @param request the server request
     * @param response the server response
     */
    private void getNaptimeMessageHandler(ServerRequest request, ServerResponse response) {
        response.send(pi_digits(20000));
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
        return "your slice of pi is " + pi.toString();
    }
}