package it.francescozanoni.files;

import it.francescozanoni.Utils;
import it.francescozanoni.files.prodcons.Consumer;
import it.francescozanoni.files.prodcons.Producer;

import java.io.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class created a copy of a text file, by converting all characters to upper-case.
 *
 * Since start and end date/time are displayed, it's useful to benchmark several implementations with large input files.
 */
public class SerialUpperCaseConverter {

    public static void main(String[] args) throws IOException {

        System.out.println(Utils.getCurrentDateTime());

        String inputFilePath = Utils.getAbsoluteFilePathFromBase("storage/input.txt");
        String outputFilePath = Utils.getAbsoluteFilePathFromBase("storage/output.txt");

        SerialUpperCaseConverter.implementationB(inputFilePath, outputFilePath);

        System.out.println(Utils.getCurrentDateTime());

    }

    /**
     * This implementation takes about 37 seconds with .
     *
     * @param inputFilePath  path ot file to read text from
     * @param outputFilePath path to file to write upper-case text to
     * @throws IOException if something goes wrong with file access
     * @see <a href="https://www.baeldung.com/reading-file-in-java"></a>
     */
    public static void implementationA(String inputFilePath, String outputFilePath) throws IOException {

        try (BufferedReader inputReader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter outputWriter = new BufferedWriter(new FileWriter(outputFilePath))) {

            String line;
            while ((line = inputReader.readLine()) != null) {
                outputWriter.append(line.toUpperCase()).append("\n");
            }

        }

    }

    public static void implementationB(String inputFilePath, String outputFilePath) {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(10000);

        try {
            executor.execute(new Producer(queue, inputFilePath));
            executor.execute(new Consumer(queue, outputFilePath));
        } catch (Exception e) {
            e.printStackTrace();
        }

        executor.shutdown();

        while (!executor.isTerminated()) {

        }

    }

}