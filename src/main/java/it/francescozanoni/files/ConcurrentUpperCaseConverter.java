package it.francescozanoni.files;

import it.francescozanoni.Utils;

import java.io.*;

import static java.lang.Math.toIntExact;

import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// @see https://stackoverflow.com/questions/40412008/how-to-read-a-file-using-multiple-threads-in-java-when-a-high-throughput3gb-s

public class ConcurrentUpperCaseConverter {

    public static void main(String[] args) {

        System.out.println(Utils.getCurrentDateTime());

        String inputFilePath = Utils.getAbsoluteFilePathFromBase("storage/input.txt");
        String outputFilePath = Utils.getAbsoluteFilePathFromBase("storage/output.txt");

        try (FileChannel inputChannel = new FileInputStream(inputFilePath).getChannel();
             FileChannel outputChannel = new FileOutputStream(outputFilePath).getChannel()) {

            int numberOfThreads = 10;
            long fileSize = inputChannel.size(); // get the total number of bytes in the file
            int chunkSize = 100000000;

            // thread pool
            ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

            long remainingSize = fileSize;
            long chunkStartLocation = 0;
            int chunkCounter = 0;
            while (remainingSize >= chunkSize) {
                // launches a new thread
                executor.execute(new Task(chunkStartLocation, chunkSize, inputChannel, chunkCounter, outputChannel));
                remainingSize -= chunkSize;
                chunkStartLocation += chunkSize;
                chunkCounter++;
            }

            // load the last remaining piece
            executor.execute(new Task(chunkStartLocation, toIntExact(remainingSize), inputChannel, chunkCounter, outputChannel));

            // TODO output file has chunks in random order: sort them correctly

            // tear down
            executor.shutdown();

            // wait for all threads to finish
            while (!executor.isTerminated()) {
                // wait for infinity time
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(Utils.getCurrentDateTime());
    }

    private static class Task implements Runnable {

        private final FileChannel _inputChannel;
        private final long _chunkStartLocation;
        private final int _size;
        private final int _chunkNumber;
        private final FileChannel _outputChannel;

        public Task(long chunkStartLocation, int size, FileChannel inputChannel, int chunkNumber, FileChannel outputChannel) {
            _chunkStartLocation = chunkStartLocation;
            _size = size;
            _inputChannel = inputChannel;
            _chunkNumber = chunkNumber;
            _outputChannel = outputChannel;
        }

        @Override
        public void run() {
            try {

                // allocate memory
                ByteBuffer inputBuffer = ByteBuffer.allocate(_size);

                // read file chunk to RAM
                _inputChannel.read(inputBuffer, _chunkStartLocation);

                // chunk to String
                String stringChunk = new String(inputBuffer.array(), StandardCharsets.UTF_8);

                ByteBuffer outputBuffer = ByteBuffer.wrap(stringChunk.toUpperCase().getBytes(StandardCharsets.UTF_8));

                _outputChannel.write(outputBuffer);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}