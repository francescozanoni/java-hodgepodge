package it.francescozanoni.concurrency.prodcons;

// http://www2.cs.uregina.ca/~nova/Java_Study/Examples9/

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ArrayBlockingQueue;

public class Main
{
   public static void main( String[] args )
   {
      ExecutorService executor = Executors.newFixedThreadPool( 2 );

      ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(3);

      try {
         executor.execute( new Producer( queue ) );
         executor.execute( new Consumer( queue ) );
      } catch ( Exception exception ) {
         exception.printStackTrace();
      }

      executor.shutdown();
   }
}
