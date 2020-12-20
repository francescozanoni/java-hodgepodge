package it.francescozanoni.concurrency.prodcons;

// http://www2.cs.uregina.ca/~nova/Java_Study/Examples9/

import java.util.concurrent.ArrayBlockingQueue;

public class Producer implements Runnable 
{
   private ArrayBlockingQueue<Integer> queue;

   public Producer( ArrayBlockingQueue<Integer> queue )
   {
      this.queue = queue;
   }

   public void run()
   {
      // 9999 is the poison value.
      // https://mkyong.com/java/java-blockingqueue-examples
      int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 9999};

      for ( int count : values ) {  
         try {
            queue.put( count );
            System.out.printf( "\t%2d\n", count );
         } catch ( InterruptedException exception ) {
            exception.printStackTrace();
         }
      }

      System.out.println( "Producer done producing." );
   }
}
