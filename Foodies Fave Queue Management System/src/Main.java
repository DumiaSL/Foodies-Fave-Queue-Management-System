import java.util.Scanner;

public class Main {
    public static Scanner scan = new Scanner(System.in);
    // Arrays to store customer names and queue information
    private static String[] queue1 = new String[2];
    private static String[] queue2 = new String[3];
    private static String[] queue3 = new String[5];

    // Stock information
    private static final int MAX_BURGERS_STOCK = 50;
    private static final int BURGERS_PER_CUSTOMER = 5;
    public static void main(String[] args) {

        while (true){
            displayMenu();

            boolean loopControl  = true;
            //
            while (loopControl){
                loopControl = false;
                System.out.print("Enter your choice: ");
                String tempInput = scan.next().toUpperCase();
                if (tempInput.equals("101") || tempInput.equals("VEQ")){
                    viewAllEmptyQueues();
                }else if (tempInput.equals("102") || tempInput.equals("ACQ")) {
                    addCustomerToQueue();
                }else if (tempInput.equals("103") || tempInput.equals("RCQ")) {
                    removeCustomerFromQueue();
                }else if (tempInput.equals("104") || tempInput.equals("PCQ")) {

                }else if (tempInput.equals("105") || tempInput.equals("VCS")) {

                }else if (tempInput.equals("106") || tempInput.equals("SPD")) {

                }else if (tempInput.equals("107") || tempInput.equals("LPD")) {

                }else if (tempInput.equals("108") || tempInput.equals("STK")) {

                }else if (tempInput.equals("109") || tempInput.equals("AFS")) {

                }else if (tempInput.equals("999") || tempInput.equals("EXT")) {

                }else {
                    loopControl = true;
                    System.out.println("please enter correct option !!!");
                }
            }
            
            System.out.println();
        }

    }

    private static void removeCustomerFromQueue() {
    }

    private static void addCustomerToQueue() {
    }

    private static void viewAllEmptyQueues() {
    }


    private static void displayMenu() {
        System.out.println("Menu:");
        System.out.println("101 or VEQ: View all Empty Queues");
        System.out.println("102 or ACQ: Add customer to a Queue");
        System.out.println("103 or RCQ: Remove a customer from a Queue (From a specific location)");
        System.out.println("104 or PCQ: Remove a served customer");
        System.out.println("105 or VCS: View Customers Sorted in alphabetical order");
        System.out.println("106 or SPD: Store Program Data into file");
        System.out.println("107 or LPD: Load Program Data from file");
        System.out.println("108 or STK: View Remaining burgers Stock");
        System.out.println("109 or AFS: Add burgers to Stock");
        System.out.println("999 or EXT: Exit the Program");
    }
}