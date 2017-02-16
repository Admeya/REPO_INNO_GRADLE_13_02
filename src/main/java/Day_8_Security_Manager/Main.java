package Day_8_Security_Manager;

/**
 * Created by Ирина on 15.02.2017.
 */
public class Main {
    public static void main(String[] args) {
        String path = "myFile.txt";
        MyWriter writer = new MyWriter(path);
        writer.writer(10);
//        MyReader reader = new MyReader(path);
//        System.out.println("reader - " + reader.reader());
    }


}
