package it.francescozanoni.concurrency.prodcons;

// http://www2.cs.uregina.ca/~nova/Java_Study/Examples9/

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class Producer implements Runnable 
{
   private static Random generator = new Random();
   private ArrayBlockingQueue<Integer> queue;

   public Producer( ArrayBlockingQueue<Integer> queue )
   {
      this.queue = queue;
   }

   public void run()
   {
      int sum = 0;

      for ( int count = 1; count <= 10; count++ ) {  
         try {
            Thread.sleep( generator.nextInt( 3000 ) );   
            queue.put( count );
            sum += count;
            System.out.printf( "\t%2d\n", sum );
         } catch ( InterruptedException exception ) {
            exception.printStackTrace();
         }
      }

      System.out.printf( "\n%s\n%s\n", "Producer done producing.", 
         "Terminating Producer." );
   }
}
