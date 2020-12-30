package it.francescozanoni.files.prodcons;

// http://www2.cs.uregina.ca/~nova/Java_Study/Examples9/

import java.io.*;
import java.util.concurrent.ArrayBlockingQueue;

public class Producer implements Runnable {

    private final ArrayBlockingQueue<String> queue;
    private final String inputFilePath;

    public Producer(ArrayBlockingQueue<String> queue,String inputFilePath) {
        this.queue = queue;
        this.inputFilePath = inputFilePath;
    }

    public void run() {

        try (BufferedReader inputReader = new BufferedReader(new FileReader(inputFilePath))) {

            String line;
            while ((line = inputReader.readLine()) != null) {
                queue.put(line);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
