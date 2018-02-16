/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

/**
 *
 * @author NattapatN
 */
public class TestWaitThread {
     public static void main(String[] argv)throws Exception { 
      Thread thread = new MyThread();
      thread.start();
      
      if (thread.isAlive()) {
         System.out.println("Thread has not finished");
      } else {
         System.out.println("Finished");
      }
      long delayMillis = 5000; 
      thread.join(delayMillis);
      
      if (thread.isAlive()) {
         System.out.println("thread has not finished");
      } else {
         System.out.println("Finished");
      }
      thread.join();
   }
}
class MyThread extends Thread {
   boolean stop = false;
   public void run() {
      while (true) {
         if (stop) {
            return;
         }
      }
   }
}
    