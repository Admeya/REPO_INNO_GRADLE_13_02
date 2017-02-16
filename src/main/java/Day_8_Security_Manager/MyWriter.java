package Day_8_Security_Manager;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Ирина on 15.02.2017.
 */
public class MyWriter {

    private String fileName;

    public MyWriter(String fileName) {
        this.fileName = fileName;
    }

    public void writer(int i) {
        try (OutputStream os = new FileOutputStream(fileName)) {
            os.write(i);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
