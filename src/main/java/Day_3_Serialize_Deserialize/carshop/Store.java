package Day_3_Serialize_Deserialize.carshop;

import Day_3_Serialize_Deserialize.datamanager.DataManager;
import Day_3_Serialize_Deserialize.models.Car;
import Day_3_Serialize_Deserialize.models.Client;
import Day_3_Serialize_Deserialize.models.Order;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.util.*;


/**
 * Created by sa on 08.02.17.
 */
public class Store {
    private static HashMap<Order, Client> contractList = new HashMap<Order, Client>(256);
    private HashSet<Car> cars = new HashSet<Car>(32);
    private HashSet<Client> clients = new HashSet<Client>(256);
    private static final String FILE_CONTRACTS = "fileContracts.txt";
    private static final String FILE_CARS = "fileCars.txt";
    private static final String FILE_CLIENTS = "fileClients.txt";

    private static Logger logger = Logger.getLogger(Store.class);

    static {
        // BasicConfigurator.configure();
        DOMConfigurator.configure("src/main/resources/log4j.xml");
    }

    public void createCar(int price, String model,
                          String regNumber) {
        Car car = new Car(price, model, regNumber);
        cars.add(car);
    }

    public void save() {
        DataManager.serialize(cars, FILE_CARS);
        DataManager.serialize(clients, FILE_CLIENTS);
        DataManager.serialize(contractList, FILE_CONTRACTS);
    }

    public void recover() {
        ArrayList<Car> list = new ArrayList<Car>();
        DataManager.deserialize(FILE_CARS, list);
        for (Car car :
                list) {
            cars.add(car);
        }

        ArrayList<Client> listClient = new ArrayList<Client>();
        DataManager.deserialize(FILE_CLIENTS, listClient);
        for (Client client :
                listClient) {
            clients.add(client);
        }

        ArrayList<Order> contractListOne = new ArrayList<Order>();
        ArrayList<Client> contractListTwo = new ArrayList<Client>();
        DataManager.deserialize(FILE_CONTRACTS, contractList);
    }

    public Order getFirstOrder() {
        for (Order order :
                contractList.keySet()) {
            return order;
        }
        return null;

    }

    public void sellCar(String model,
                        String firstName,
                        String lastName,
                        String phoneNumber) throws CarNotFoundException {
        Client client = new Client(firstName,
                lastName, phoneNumber);
        clients.add(client);

        Car tmpCar = null;
        for (Car car :
                cars) {
            if (car.getModel().equals(model)) {
                tmpCar = car;
                break;
            }
        }
        if (tmpCar != null) {
            Random random = new Random();
            Order order = new Order(tmpCar,
                    tmpCar.getPrice() * 2,
                    random.nextLong(), (short) 80
            );
            contractList.put(order, client);
            cars.remove(tmpCar);
        } else {
            CarNotFoundException ex = null;
            logger.error("Car with model " + model + " not found", ex);
            throw ex;
        }
    }

    public List<Order> getOrders() {
        List<Order> orders = new ArrayList<Order>();

        for (Order order :
                contractList.keySet()) {
            orders.add(order);
            logger.info(order.toString());
        }
        return orders;
    }

    public List<Car> getFreeCars() {
        List<Car> list = new ArrayList<Car>();
        for (Car car :
                cars) {
            list.add(car);
        }
        return list;
    }

    public static Map<Order, Client> getContractList() {
        return contractList;
    }


}
