package com.ddjonline.hello.helidon.hello;

import io.helidon.config.Config;
import io.helidon.logging.common.LogConfig;
import io.helidon.webserver.WebServer;
import io.helidon.webserver.http.HttpRouting;

/**
 * The application main class.
 */
public final class Main {

    private Main() {
    }

    /**
     * Application main entry point.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        LogConfig.configureRuntime();
        Config config = Config.create();

        WebServer server = WebServer.builder()
                .config(config.get("server"))
                .routing(Main::routing)
                .build()
                .start();

        System.out.println("WEB server is up! http://localhost:" + server.port());
    }

    /**
     * Updates HTTP routing.
     */
    static void routing(HttpRouting.Builder routing) {
        routing.register("/", new HelloService());
    }
}
