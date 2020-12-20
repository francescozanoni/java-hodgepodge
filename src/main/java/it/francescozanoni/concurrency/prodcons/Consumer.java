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
      while ( true ) {
         try {
            int value = queue.take();
             // Poison value.
             // https://mkyong.com/java/java-blockingqueue-examples
             if (value == 9999) {
                break;
             }
            System.out.printf( "\t\t\t%2d\n", value );
         } catch ( InterruptedException exception ) {
            exception.printStackTrace();
         }
      }

      System.out.println( "Consumer done consuming." );
   }
}

