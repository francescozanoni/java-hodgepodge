package it.francescozanoni.concurrency.prodcons;

// http://www2.cs.uregina.ca/~nova/Java_Study/Examples9/

import java.util.concurrent.ArrayBlockingQueue;

public class Consumer implements Runnable {

    private final ArrayBlockingQueue<Integer> queue;
    private final int poisonValue;

    public Consumer(ArrayBlockingQueue<Integer> queue, int poisonValue) {
        this.queue = queue;
        this.poisonValue = poisonValue;
    }

    public void run() {

        while (true) {
            try {
                int value = queue.take();
                // As a convention, the poison value is supplied by the producer to stop the consumer.
                // https://mkyong.com/java/java-blockingqueue-examples
                if (value == poisonValue) {
                    break;
                }
                System.out.println("|          |    " + value + "     |");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}

