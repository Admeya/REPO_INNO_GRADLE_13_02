package Day_2_Threads;

public class DataManagerSimple {
    private static boolean ready = false;
    
    
    public static void sendData(){
        while(!ready){
            System.out.println("Waiting for data");           
        }
        System.out.println("Data sent!");
    }
    
    public static void repareData(){
        System.out.println("Data prepared");
        ready = true;
    }
    
}
