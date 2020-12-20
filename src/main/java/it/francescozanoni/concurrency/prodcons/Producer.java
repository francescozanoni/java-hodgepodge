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
      int sum = 0;

      for ( int count = 1; count <= 10; count++ ) {  
         try {
            queue.put( count );
            sum += count;
            System.out.printf( "\t%2d\n", sum );
         } catch ( InterruptedException exception ) {
            exception.printStackTrace();
         }
      }
      
      try {
            // Poison value.
      // https://mkyong.com/java/java-blockingqueue-examples
      queue.put(9999);
         } catch ( InterruptedException exception ) {
            exception.printStackTrace();
         }

      System.out.printf( "\n%s\n%s\n", "Producer done producing.", "Terminating Producer." );
   }
}
