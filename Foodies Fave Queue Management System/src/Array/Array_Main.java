package Array;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;


public class Array_Main {
    // Create a Scanner object to read user input
    public static Scanner scan = new Scanner(System.in);
    // Arrays to store customer names and queue information
    private static String[] queue1 = new String[2];
    private static String[] queue2 = new String[3];
    private static String[] queue3 = new String[5];

    // Stock information
    private static final int MAX_BURGERS_STOCK = 50;
    private static int BURGERS_STOCK = MAX_BURGERS_STOCK;
    private static final int BURGERS_PER_CUSTOMER = 5;
    private static final String DATABASE_NAME = "Database/DataBase_array.txt";

    public static void main(String[] args) {
        System.out.println();

        while (true) {
            displayMenu();

            boolean loopControl = true;
            while (loopControl) {
                loopControl = false;
                System.out.print("Enter your choice: ");
                String tempInput = scan.next().toUpperCase();
                if (tempInput.equals("101") || tempInput.equals("VEQ")) {
                    viewAllEmptyQueues();
                } else if (tempInput.equals("102") || tempInput.equals("ACQ")) {
                    addCustomerToQueue();
                } else if (tempInput.equals("103") || tempInput.equals("RCQ")) {
                    removeCustomerFromQueue();
                } else if (tempInput.equals("104") || tempInput.equals("PCQ")) {
                    removeServedCustomer();
                } else if (tempInput.equals("105") || tempInput.equals("VCS")) {
                    customersSortedInAlphabetical();
                } else if (tempInput.equals("106") || tempInput.equals("SPD")) {
                    saveToFile();
                } else if (tempInput.equals("107") || tempInput.equals("LPD")) {
                    ReadFromFile();
                } else if (tempInput.equals("108") || tempInput.equals("STK")) {
                    viewRemainingBurgersStock();
                } else if (tempInput.equals("109") || tempInput.equals("AFS")) {
                    addBurgersToStock();
                } else if (tempInput.equals("999") || tempInput.equals("EXT")) {
                    //Terminate the program
                    System.exit(0);
                } else {
                    //Not breaking Input validating loop
                    loopControl = true;
                    System.out.println("please enter correct option !!!");
                }
            }

            System.out.println();
        }

    }

    private static void addBurgersToStock() {
        if (BURGERS_STOCK == MAX_BURGERS_STOCK) {
            System.out.println("Burger stock already full !!");
        } else {
            outerloop:
            while (true) {
                System.out.print("Enter the number of burgers to add to stock [Current stock - " + BURGERS_STOCK + " Maximum stock - " + MAX_BURGERS_STOCK + "]: ");
                try {
                    int newStock = scan.nextInt();

                    if ((BURGERS_STOCK + newStock) > MAX_BURGERS_STOCK) {
                        System.out.println("Can't add this stock. You can currently add a maximum of " + (MAX_BURGERS_STOCK - BURGERS_STOCK) + " burgers.");
                        while (true) {
                            System.out.print("Would you like to add? [Y/N]: ");
                            String option = scan.next().toUpperCase();
                            if (option.equals("Y")) {
                                BURGERS_STOCK = BURGERS_STOCK + (MAX_BURGERS_STOCK - BURGERS_STOCK);
                                break;
                            } else if (option.equals("N")) {
                                continue outerloop;
                            } else {
                                System.out.println("Wrong input! Enter again.");
                            }
                        }
                    } else {
                        BURGERS_STOCK = BURGERS_STOCK + newStock;
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a valid number.");
                    // Clear the invalid input from the scanner
                    scan.next();
                }
            }
            System.out.println("Remaining burgers stock: " + BURGERS_STOCK);
        }
    }


    private static void viewRemainingBurgersStock() {
        System.out.println("Remaining burgers Stock: " + BURGERS_STOCK);
    }

    private static void ReadFromFile() {
        try {
            FileReader fileReader = new FileReader(DATABASE_NAME);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            int count = 1;
            while ((line = bufferedReader.readLine()) != null) {
                // Get each line of the file
                if (count==1){
                    BURGERS_STOCK = Integer.parseInt(line);
                    count++;
                }else {
                    String[] saved_names = line.split(",");
                    for (int i = 0; i < queue1.length; i++) {
                        queue1[i] = saved_names[i];
                    }

                    for (int i = 0; i < queue2.length; i++) {
                        queue2[i] = saved_names[i + queue1.length];
                    }

                    for (int i = 0; i < queue3.length; i++) {
                        queue3[i] = saved_names[i + queue1.length + queue2.length];
                    }
                }
            }
            // Close the buffered reader
            bufferedReader.close();
            System.out.println("Successfully update the system using data!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveToFile() {
        try {
            FileWriter fileWriter = new FileWriter(DATABASE_NAME);
            // Write the data to the file
            fileWriter.write(BURGERS_STOCK+ "\n");
            String temp_data = "";
            temp_data=nameMergeForSave(temp_data,queue1);
            temp_data=nameMergeForSave(temp_data,queue2);
            temp_data=nameMergeForSave(temp_data,queue3);
            fileWriter.write(temp_data);
            fileWriter.close();
            System.out.println("Data has been saved to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String nameMergeForSave(String tempData, String[] queueType) {
        for (String name:queueType){
            tempData=tempData + name+",";
        }
        return tempData;
    }


    private static void removeServedCustomer() {
    }

    private static void customersSortedInAlphabetical() {
    }

    private static void removeCustomerFromQueue() {
    }

    private static void addCustomerToQueue() {
    }

    private static void viewAllEmptyQueues() {
    }


    private static void displayMenu() {
        System.out.println("Foodies Fave Queue Management System\n");
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