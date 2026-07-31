package com.ddjonline.hello.helidon.hello;

import io.helidon.http.Status;
import io.helidon.webclient.http1.Http1Client;
import io.helidon.webclient.http1.Http1ClientResponse;
import io.helidon.webserver.http.HttpRouting;
import io.helidon.webserver.testing.junit5.ServerTest;
import io.helidon.webserver.testing.junit5.SetUpRoute;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;

@ServerTest
class MainTest {

    private final Http1Client client;

    MainTest(Http1Client client) {
        this.client = client;
    }

    @SetUpRoute
    static void routing(HttpRouting.Builder builder) {
        Main.routing(builder);
    }

    @Test
    void testHello() {
        try (Http1ClientResponse response = client.get("/hello").request()) {
            assertThat(response.status(), is(Status.OK_200));
            assertThat(response.as(String.class), is("Hello (1)"));
        }
    }

    @Test
    void testNaptime() {
        try (Http1ClientResponse response = client.get("/naptime").request()) {
            assertThat(response.status(), is(Status.OK_200));
            assertThat(response.as(String.class), startsWith("your slice of pi is "));
        }
    }
}
