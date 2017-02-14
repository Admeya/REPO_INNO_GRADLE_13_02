package Day_4_Threads_locks_semaphore.Lection_notifications;

import java.util.Random;


public class WaitNotifyMain { // WainNotify
    public static volatile int count = 0;
    public static Object Locker = new Object();

    public static void main(String[] args) {
        Thread myThr1 = new Thread(new Runnable() {

            @Override
            public void run() {
                Random rand = new Random();
                try {
                    Thread.sleep(rand.nextInt(1));
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                DataManagerWait.prepareData();

            }
        });

        Thread myThr2 = new Thread(new Runnable() {

            @Override
            public void run() {
                Random rand = new Random();
                try {
                    Thread.sleep(rand.nextInt(1));
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                DataManagerWait.sendData();
            }
        });

        myThr1.start();
        myThr2.start();

    }
}
