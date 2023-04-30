package com.ddjonline.hello.helidon.hello;

import io.helidon.microprofile.server.Server;

import java.io.IOException;

public final class Main {
    private Main() { }

    public static void main(final String[] args) throws IOException {
        Server server = startServer();
        System.out.println("http://localhost:" + server.port() + "/hello");
        System.out.println("http://localhost:" + server.port() + "/naptime");
    }

    static Server startServer() {
        return Server.create().start();
    }
}
