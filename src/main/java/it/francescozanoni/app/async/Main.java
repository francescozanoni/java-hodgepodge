package it.francescozanoni.app.async;

import java.util.concurrent.*;
import java.util.function.Function;

// @see https://www.baeldung.com/java-future#1-implementing-futures-with-futuretask

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Awaited task ("::" makes the method treated as a lambda expression,
        // i.e. a reference to that method).
        // @see https://www.baeldung.com/java-8-double-colon-operator#colonOperator
        Future<String> future1 = executor.submit(Task::runAwaited);

        // Non awaited task - 1.
        executor.execute(() -> {
            // Static method used as-is.
            Task.runNonAwaited("http://example.org");
        });

        // Non awaited task - 2.
        executor.execute(() -> {
            // Static method used by reference, i.e. as lambda expression.
            Function<String, String> runNonAwaited = Task::runNonAwaited;
            runNonAwaited.apply("http://example.com");
        });

        // Non awaited task - 3.
        executor.execute(() -> {
            // Public method used by reference, i.e. as lambda expression.
            Function<String, String> runNonAwaited2 = new Task()::runNonAwaited2;
            runNonAwaited2.apply("http://example.net");
        });

        // Stop the executor service, but wait for tasks to execute.
        // @see https://www.baeldung.com/java-executor-service-tutorial#shutting
        executor.shutdown();

        while (!future1.isDone()) {
            System.out.print(System.currentTimeMillis() + "\r");
        }

        String result = future1.get();

        System.out.println("runAwaited: " + result.length() + " bytes downloaded");

    }

}