package Day_3_Serialize_Deserialize;

import Day_3_Serialize_Deserialize.carshop.CarNotFoundException;
import Day_3_Serialize_Deserialize.carshop.Store;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


/**
 * Created by sa on 08.02.17.
 */
public class Main {
    public static final Logger logger = Logger.getLogger(Main.class);

    static {
        PropertyConfigurator.configure("src/main/resources/log4j.xml");
    }

    public static void main(String[] args) {
        logger.debug("Store created");
        Store store = new Store();
        store.createCar(500000, "kia-rio",
                "B146AA");
        try {
            store.sellCar("kia-rio",
                    "Jhon",
                    "Konner",
                    "+79126241898");

        } catch (CarNotFoundException e) {
            e.printStackTrace();
        }

        store.save();
        logger.debug("Store save");
    }
}
