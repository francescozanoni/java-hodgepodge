package it.francescozanoni.concurrency.prodcons;

// http://www2.cs.uregina.ca/~nova/Java_Study/Examples9/
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class Consumer implements Runnable 
{ 
   private static Random generator = new Random();
   private ArrayBlockingQueue<Integer> sharedLocation; // reference to shared object

   // constructor
   public Consumer( ArrayBlockingQueue<Integer> shared )
   {
      sharedLocation = shared;
   } // end Consumer constructor

   // read sharedLocation's value four times and sum the values
   public void run()
   {
      int sum = 0;

      for ( int count = 1; count <= 10; count++ ) 
      {
         // sleep 0 to 3 seconds, read value from buffer and add to sum
         try 
         {
            Thread.sleep( generator.nextInt( 3000 ) );    
            sum += sharedLocation.take();
            System.out.printf( "\t\t\t%2d\n", sum );
         } // end try
         // if sleeping thread interrupted, print stack trace
         catch ( InterruptedException exception ) 
         {
            exception.printStackTrace();
         } // end catch
      } // end for

      System.out.printf( "\n%s %d.\n%s\n", 
         "Consumer read values totaling", sum, "Terminating Consumer." );
   } // end method run
} // end class Consumer

