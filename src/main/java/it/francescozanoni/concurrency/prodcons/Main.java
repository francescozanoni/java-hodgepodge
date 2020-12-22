package it.francescozanoni.concurrency.prodcons;

// http://www2.cs.uregina.ca/~nova/Java_Study/Examples9/

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Main {

    // As a convention, the poison value is supplied by the producer to stop the consumer.
    // https://mkyong.com/java/java-blockingqueue-examples
    public static final int poisonValue = 9999;

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(3);

        System.out.println("-----------+-----------");
        System.out.println("| PRODUCER | CONSUMER |");
        System.out.println("-----------+-----------");

        try {
            executor.execute(new Producer(queue, poisonValue));
            executor.execute(new Consumer(queue, poisonValue));
        } catch (Exception e) {
            e.printStackTrace();
        }

        executor.shutdown();

        executor.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("-----------+-----------");

    }

}
