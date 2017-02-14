package Day_6_UnitTests_Mockito;

import Day_3_Serialize_Deserialize.carshop.CarNotFoundException;
import Day_3_Serialize_Deserialize.carshop.Store;
import Day_3_Serialize_Deserialize.models.Car;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by Ирина on 13.02.2017.
 */
@SuppressWarnings("ALL")
class StoreTest {
    static Store store;

    @BeforeAll
    @Deprecated
    public static void initStore() {
        store = new Store();
        assertNotNull(store);
    }

    @Test
    void createCar() {
        store = new Store();

        Car car = new Car(100, "Lada", "ABC");
        store.createCar(100, "Lada", "ABC");

        assertNotNull(store.getFreeCars());

        assertTrue(store.getFreeCars().size() > 0);

        store.getFreeCars().stream().forEach((car1) -> {
            assertEquals(car1.getPrice(), 100);
            assertEquals(car1.getModel(), "Lada");
            assertEquals(car1.getRegNum(), "ABC");
        });
    }

    @Test
    void save() {

    }

    @org.junit.jupiter.api.Test
    void recover() {

    }

    @org.junit.jupiter.api.Test
    void getFirstOrder() {
        Store store = new Store();
        store.getOrders();
    }

    @org.junit.jupiter.api.Test
    void sellCar() throws CarNotFoundException {
        Store store = new Store();

        assertThrows(CarNotFoundException.class, () ->
                store.sellCar("kia Rio", "Jhon", "Konor", "123"));

        store.createCar(100, "kia Rio", "ABC");
        store.sellCar("kia Rio", "Jhon", "Konor", "123");

        assertTrue(store.getFreeCars().size() == 0);

        assertTrue(store.getOrders().stream().filter((order) -> order.getCar().getModel().equals("Lada")
                && order.getCar().getPrice() == 100
                && order.getCar().getRegNum().equals("ABC")).count() > 0);

        assertTrue(Store.getContractList().size() == 1);

        assertTrue(Store.getContractList().values().stream().filter(
                (client) -> client.getFirstName().equals("Jhon")
                            && client.getLastName().equals("Konor")
                            &&client.getPhoneNumber().equals("123")).count() == 1);

    }

    @org.junit.jupiter.api.Test
    void getOrders() {

    }

    @org.junit.jupiter.api.Test
    void getFreeCars() {

    }

    @Test
    void getContractList() {
        Store store = new Store();
        assertNotNull(store.getContractList());

        assertTrue(store.getContractList().size() == 0);
    }

}