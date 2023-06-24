package Class;

import java.io.Serializable;

public class FoodQueue implements Serializable {
    Customer customer;
    int noOfBurgers;


    public FoodQueue(String fistName, String secondName, int noOfBurgers) {
        this.customer = new Customer(fistName,secondName);
        this.noOfBurgers = noOfBurgers;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getNoOfBurgers() {
        return noOfBurgers;
    }

    public void setNoOfBurgers(int noOfBurgers) {
        this.noOfBurgers = noOfBurgers;
    }

    @Override
    public String toString() {
        return "FoodQueue{" +
                "customer=" + customer +
                ", noOfBurgers=" + noOfBurgers +
                '}';
    }
}
