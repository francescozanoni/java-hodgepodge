package it.francescozanoni.concurrency.prodcons;

// http://www2.cs.uregina.ca/~nova/Java_Study/Examples9/
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class Consumer implements Runnable 
{ 
   private static Random generator = new Random();
   private ArrayBlockingQueue<Integer> queue;

   public Consumer( ArrayBlockingQueue<Integer> queue )
   {
      this.queue = queue;
   }

   public void run()
   {
      int sum = 0;

      for ( int count = 1; count <= 10; count++ ) {
         try {
            Thread.sleep( generator.nextInt( 3000 ) );    
            sum += queue.take();
            System.out.printf( "\t\t\t%2d\n", sum );
         } catch ( InterruptedException exception ) {
            exception.printStackTrace();
         }
      }

      System.out.printf( "\n%s %d.\n%s\n", 
         "Consumer read values totaling", sum, "Terminating Consumer." );
   }
}

