package Day_4_Threads_locks_semaphore.Lection_notifications;

public class DataManagerWait {
    public static final Object lock = new Object();
    public static boolean ready = false;

    public static void sendData() {
        synchronized (lock) {
            try {
                while (!ready)
                    lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Data send");
        }
    }

    public static void prepareData() {
        synchronized (lock) {
            ready = true;
            System.out.println("Data prepared");
            lock.notify();
        }
    }

}
