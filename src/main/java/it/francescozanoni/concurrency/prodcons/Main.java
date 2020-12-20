package it.francescozanoni.concurrency.prodcons;

// http://www2.cs.uregina.ca/~nova/Java_Study/Examples9/

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ArrayBlockingQueue;

public class Main
{
   public static void main( String[] args )
   {
      // create new thread pool with two threads
      ExecutorService application = Executors.newFixedThreadPool( 2 );

      // create BlockingBuffer to store ints
      ArrayBlockingQueue<Integer> sharedLocation = new ArrayBlockingQueue<Integer>(10);

      try // try to start producer and consumer
      {
         application.execute( new Producer( sharedLocation ) );
         application.execute( new Consumer( sharedLocation ) );
      } // end try
      catch ( Exception exception )
      {
         exception.printStackTrace();
      } // end catch

      application.shutdown();
   } // end main
} // end class BlockingBufferTest
