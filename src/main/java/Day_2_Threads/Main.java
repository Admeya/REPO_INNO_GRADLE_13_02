package Day_2_Threads;

import java.util.Random;

public class Main {
    public static volatile int count = 0; 
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
           synchronized(Locker){
                count++;
                System.out.println(count); 
           }
               
        }
    };
    
    for (int i = 0; i < 100; i++) {
        Thread thread = new Thread(myRun);
        thread.start();
    }
               

    }

}
