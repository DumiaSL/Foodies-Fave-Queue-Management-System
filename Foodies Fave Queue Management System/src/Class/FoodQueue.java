package Class;

public class FoodQueue {
    Customer customer;
    int noOfBurgers;


    public FoodQueue(Customer customer, int noOfBurgers) {
        this.customer = customer;
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
