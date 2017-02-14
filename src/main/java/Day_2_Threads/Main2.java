package Day_2_Threads;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main2 {
    public static AtomicInteger count = new AtomicInteger(0); //работают быстрее, чем обычные программные инструкции
    public static Object Locker = new Object();
    
    
    public static void main(String[] args) {
       
        Runnable myRun = new Runnable() {
        
        @Override
        public void run() {
            Random rand = new Random();
//            try {
//                Thread.sleep(rand.nextInt(10000));
//            } catch (InterruptedException e) {                
//                e.printStackTrace();
//            }
           
                count.addAndGet(1);
                System.out.println(count); 
               
        }
    };
    
    for (int i = 0; i < 100; i++) {
        Thread thread = new Thread(myRun);
        thread.start();
    }
               

    }

}
