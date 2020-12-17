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

public class ConcurrentFileReader implements Runnable {

    private final FileChannel _channel;
    private final long _startLocation;
    private final int _size;
    private final int _sequenceNumber;

    public ConcurrentFileReader(long startLocation, int size, FileChannel channel, int sequenceNumber) {
        _startLocation = startLocation;
        _size = size;
        _channel = channel;
        _sequenceNumber = sequenceNumber;
    }

    @Override
    public void run() {
        long endLocation = _startLocation + _size;

        try {
            System.out.println("Start reading channel " + _sequenceNumber + ":\tfrom " + _startLocation + " to " + endLocation);

            // allocate memory
            ByteBuffer buffer = ByteBuffer.allocate(_size);

            // read file chunk to RAM
            _channel.read(buffer, _startLocation);

            // chunk to String
            String stringChunk = new String(buffer.array(), StandardCharsets.UTF_8);

            System.out.println("Finished reading channel " + _sequenceNumber + ":\tfrom " + _startLocation + " to " + endLocation);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {

        String inputFilePath = Utils.getAbsoluteFilePathFromBase("storage/input.txt");
        int numberOfThreads = 4;

        FileInputStream fileInputStream = new FileInputStream(inputFilePath);
        FileChannel channel = fileInputStream.getChannel();
        long remainingSize = channel.size(); // get the total number of bytes in the file
        long chunkSize = remainingSize / numberOfThreads;

        // max allocation size allowed is ~2GB
        if (chunkSize > (Integer.MAX_VALUE - 5)) {
            chunkSize = (Integer.MAX_VALUE - 5);
        }

        // thread pool
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

        long startLoc = 0; // file pointer
        int i = 0; // loop counter
        while (remainingSize >= chunkSize) {
            // launches a new thread
            executor.execute(new ConcurrentFileReader(startLoc, toIntExact(chunkSize), channel, i));
            remainingSize = remainingSize - chunkSize;
            startLoc += chunkSize;
            i++;
        }

        // load the last remaining piece
        executor.execute(new ConcurrentFileReader(startLoc, toIntExact(remainingSize), channel, i));

        // tear down
        executor.shutdown();

        // wait for all threads to finish
        while (!executor.isTerminated()) {
            // wait for infinity time
        }

        fileInputStream.close();

        System.out.println();
        System.out.println("Finished all threads");
    }

}