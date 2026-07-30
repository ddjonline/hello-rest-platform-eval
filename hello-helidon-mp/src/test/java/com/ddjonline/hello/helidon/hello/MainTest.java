
package com.ddjonline.hello.helidon.hello;

import io.helidon.microprofile.testing.junit5.HelidonTest;

import jakarta.inject.Inject;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@HelidonTest
class MainTest {

    private final WebTarget target;

    @Inject
    MainTest(WebTarget target) {
        this.target = target;
    }

    @Test
    void testHelloWorld() {
        String text = target
                .path("hello")
                .request()
                .get(String.class);
        assertThat("default message", text, is("Hello (1)"));

        try (Response r = target
                .path("metrics")
                .request()
                .get()) {
            assertThat("GET metrics status code", r.getStatus(), is(200));
        }

        try (Response r = target
                .path("health")
                .request()
                .get()) {
            assertThat("GET health status code", r.getStatus(), is(200));
        }
    }
}
