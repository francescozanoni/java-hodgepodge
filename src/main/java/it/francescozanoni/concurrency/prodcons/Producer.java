package it.francescozanoni.concurrency.prodcons;

// http://www2.cs.uregina.ca/~nova/Java_Study/Examples9/

import java.util.concurrent.ArrayBlockingQueue;

public class Producer implements Runnable {

    private final ArrayBlockingQueue<Integer> queue;
    private final int poisonValue;

    public Producer(ArrayBlockingQueue<Integer> queue, int poisonValue) {
        this.queue = queue;
        this.poisonValue = poisonValue;
    }

    public void run() {

        // As a convention, the poison value is supplied by the producer to stop the consumer.
        // https://mkyong.com/java/java-blockingqueue-examples
        int[] values = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, poisonValue};

        for (int value : values) {
            try {
                queue.put(value);
                if (value != poisonValue) {
                    System.out.println("|     " + value + "    |          |");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
