package it.francescozanoni.concurrency.http;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

// @see https://howtodoinjava.com/java/multi-threading/executorservice-invokeall

/**
 * Download concurrently via HTTP.
 */
public class Downloader {

    /**
     * Concurrently download all web pages reachable by URLs.
     *
     * @param urls URLs to download web pages from
     * @return content of web pages as array of strings
     */
    public static String[] run(String[] urls) {

        String[] urlContents = new String[urls.length];

        ExecutorService executor = Executors.newFixedThreadPool(urls.length);

        List<Callable<String>> taskList = new ArrayList<>();

        // Create all web page download tasks.
        for (String url : urls) {
            taskList.add(Downloader.getTask(url));
        }

        List<Future<String>> resultList = null;

        try {

            // Run all tasks and wait for all to complete.
            resultList = executor.invokeAll(taskList);

            // Alternative: throw a java.util.concurrent.CancellationException if it takes more than 5 seconds.
            // resultList = executor.invokeAll(taskList, 5, TimeUnit.SECONDS);

            executor.shutdown();

            for (int i = 0; i < resultList.size(); i++) {
                Future<String> future = resultList.get(i);
                String result = future.get();
                urlContents[i] = result;
            }

        } catch (ExecutionException | InterruptedException e) {

            executor.shutdown();

        }

        // urlContents cannot be a List (to be cast to String[] at the end), as it triggers this exception:
        //   Exception in thread "main" java.lang.ClassCastException: class java.lang.Object cannot be cast to class java.lang.String

        return urlContents;
    }

    /**
     * Create a task that downloads the content of the input URL via HTTP.
     *
     * @param url URL of the web page to download
     * @return download task
     */
    private static Callable<String> getTask(String url) {

        return () -> {
            HttpClient client = HttpClient.newBuilder().build();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("downloaded " + url);
            return response.body();
        };

    }

}