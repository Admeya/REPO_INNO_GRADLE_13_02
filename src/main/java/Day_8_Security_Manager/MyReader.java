package Day_8_Security_Manager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Ирина on 15.02.2017.
 */
public class MyReader {
    private String fileName;

    public MyReader(String fileName) {
        this.fileName = fileName;
    }

    public int reader() {
        int rr = 0;
        try (InputStream is = new FileInputStream(fileName)) {
            rr = is.read();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rr;
    }
}
