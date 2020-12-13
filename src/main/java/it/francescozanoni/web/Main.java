package it.francescozanoni.web;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

// @see https://dev.to/piczmar_0/framework-less-rest-api-in-java-1jbl

class Main {

    public static void main(String[] args) throws IOException {
        int serverPort = 8000;
        HttpServer server = HttpServer.create(new InetSocketAddress(serverPort), 0);
        server.createContext("/hello", (exchange -> {
            String respText = "Hi!";
            exchange.sendResponseHeaders(200, respText.getBytes().length);
            OutputStream output = exchange.getResponseBody();
            output.write(respText.getBytes());
            output.flush();
            exchange.close();
        }));
        server.setExecutor(null); // creates a default executor
        System.out.println("HTTP server listening at URL http://<thishost>:" + serverPort + "/hello");
        server.start();  
    }
}