package it.francescozanoni.app.async;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Task {

    public static String runAwaited() throws IOException, InterruptedException {

        HttpClient client = HttpClient.newBuilder().build();

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://example.com")).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("FINISHED runAwaited");

        return response.body();

    }

    // In order to use this method as a lambda expression, all exceptions must be caught.
    // @see https://stackoverflow.com/questions/27644361/how-can-i-throw-checked-exceptions-from-inside-java-8-streams#27668305
    public static String runNonAwaited(String url) {

        try {

            HttpClient client = HttpClient.newBuilder().build();

            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("FINISHED runNonAwaited with URL " + url);

            return response.body();

        } catch (IOException | InterruptedException e) {

            return "";

        }

    }

    // In order to use this method as a lambda expression, all exceptions must be caught.
    // @see https://stackoverflow.com/questions/27644361/how-can-i-throw-checked-exceptions-from-inside-java-8-streams#27668305
    public String runNonAwaited2(String url) {

        return Task.runNonAwaited(url);

    }

}
