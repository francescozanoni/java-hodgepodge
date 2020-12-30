package it.francescozanoni.files.prodcons;

// http://www2.cs.uregina.ca/~nova/Java_Study/Examples9/

import java.io.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable {

    private final ArrayBlockingQueue<String> queue;
    private final String outputFilePath;

    public Consumer(ArrayBlockingQueue<String> queue, String outputFilePath) {
        this.queue = queue;
        this.outputFilePath = outputFilePath;
    }

    public void run() {

        try (BufferedWriter outputWriter = new BufferedWriter(new FileWriter(outputFilePath))) {

            while (true) {
                try {
                    String value = queue.poll(2, TimeUnit.SECONDS);
                    if (value == null) {
                        break;
                    }
                    outputWriter.append(value.toUpperCase()).append("\n");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

