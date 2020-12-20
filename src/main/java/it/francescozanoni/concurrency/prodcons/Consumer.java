package it.francescozanoni.concurrency.prodcons;

// http://www2.cs.uregina.ca/~nova/Java_Study/Examples9/
import java.util.concurrent.ArrayBlockingQueue;

public class Consumer implements Runnable 
{ 
   private ArrayBlockingQueue<Integer> queue;

   public Consumer( ArrayBlockingQueue<Integer> queue )
   {
      this.queue = queue;
   }

   public void run()
   {
      int sum = 0;

      while ( true ) {
         try {
            int value = queue.take();
             // Poison value.
             // https://mkyong.com/java/java-blockingqueue-examples
             if (value == 9999) {
                break;
             }
            sum += value;
            System.out.printf( "\t\t\t%2d\n", sum );
         } catch ( InterruptedException exception ) {
            exception.printStackTrace();
         }
      }

      System.out.printf( "\n%s %d.\n%s\n", "Consumer read values totaling", sum, "Terminating Consumer." );
   }
}

