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
                switch (tempInput) {
                    case "100", "VFQ" -> viewAllQueues();
                    case "101", "VEQ" -> viewAllEmptyQueues();
                    case "102", "ACQ" -> addCustomerToQueue();
                    case "103", "RCQ" -> removeCustomerFromQueue();
                    case "104", "PCQ" -> removeServedCustomer();
                    case "105", "VCS" -> customersSortedInAlphabetical();
                    case "106", "SPD" -> saveToFile();
                    case "107", "LPD" -> ReadFromFile();
                    case "108", "STK" -> viewRemainingBurgersStock();
                    case "109", "AFS" -> addBurgersToStock();
                    case "999", "EXT" ->
                        //Terminate the program
                            System.exit(0);
                    default -> {
                        //Not breaking Input validating loop
                        loopControl = true;
                        System.out.println("please enter correct option !!!");
                    }
                }
            }
            System.out.println();
        }
    }

    private static void viewAllQueues() {
        System.out.println("   *****************");
        System.out.println("   *    Cashiers   *");
        System.out.println("   *****************");

        int maxLines = Math.max(queue1.length, Math.max(queue2.length, queue3.length));
        for (int count = 0; count < maxLines; count++) {
            System.out.print("      ");
            if (count < queue1.length) {
                if (queue1[count] == null) {
                    System.out.print("X");
                }else {
                    System.out.print("O");
                }
            } else {
                System.out.print(" ");
            }
            System.out.print("    ");
            if (count < queue2.length) {
                if (queue2[count] == null) {
                    System.out.print("X");
                }else {
                    System.out.print("O");
                }
            } else {
                System.out.print(" ");
            }
            System.out.print("    ");
            if (count < queue3.length) {
                if (queue3[count] == null) {
                    System.out.print("X");
                }else {
                    System.out.print("O");
                }
            }
            System.out.println();
        }
        System.out.println("   *****************");
        System.out.println(" X – Not Occupied O – Occupied");
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
                if (count == 1) {
                    BURGERS_STOCK = Integer.parseInt(line);
                    count++;
                } else {
                    String[] saved_names = line.split(",");
                    for (int i = 0; i < queue1.length; i++) {
                        if (saved_names[i].equals("null")){
                            queue1[i] = null;
                        }else {
                            queue1[i] = saved_names[i];
                        }
                    }

                    for (int i = 0; i < queue2.length; i++) {
                        if (saved_names[i + queue1.length].equals("null")){
                            queue2[i] = null;
                        }else {
                            queue2[i] = saved_names[i + queue1.length];
                        }
                    }

                    for (int i = 0; i < queue3.length; i++) {
                        if (saved_names[i + queue1.length + queue2.length].equals("null")){
                            queue3[i] = null;
                        }else {
                            queue3[i] = saved_names[i + queue1.length + queue2.length];
                        }
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
            fileWriter.write(BURGERS_STOCK + "\n");
            String temp_data = "";
            temp_data = String.join(",",queue1)+","+String.join(",",queue2)+","+String.join(",",queue3);
            fileWriter.write(temp_data);
            fileWriter.close();
            System.out.println("Data has been saved to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void removeServedCustomer() {
        int cashierNumber;
        while (true) {
            System.out.print("Enter relevant cashier want to served [1,2,3]: ");
            try {
                cashierNumber = scan.nextInt();
                if (cashierNumber == 1 || cashierNumber == 2 || cashierNumber == 3){
                    break;
                }
                System.out.println("Please enter correct cashier number");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                // Clear the invalid input from the scanner
                scan.next();
            }
        }

        if (cashierNumber == 1){
            servedCashier(queue1);
        } else if (cashierNumber == 2){
            servedCashier(queue2);
        }else {
            servedCashier(queue3);
        }
    }

    private static void servedCashier(String[] queueType) {
        boolean checkFlag = false;
        for (String name:queueType){
            if (!(name==null)){
                checkFlag=true;
                break;
            }
        }
        if (checkFlag){
            // Remove the first element
            for (int i = 0; i < queueType.length - 1; i++) {
                queueType[i] = queueType[i + 1];
            }

            // Set the last element to null or an empty string
            queueType[queueType.length - 1] = null;
            BURGERS_STOCK = BURGERS_STOCK - BURGERS_PER_CUSTOMER;
            if (BURGERS_STOCK <=10){
                System.out.println("Low burger stock!!!");
            }
        }else {
            System.out.println("No customer in this Cashier");
        }
    }

    private static void customersSortedInAlphabetical() {
        // Sort the list
        bubbleSort(queue1, "Queue 1");
        bubbleSort(queue2, "Queue 2");
        bubbleSort(queue3, "Queue 3");
    }

    public static void bubbleSort(String[] arr, String queueType) {
        String[] tempArr = arr.clone();
        for (int i = 0; i < tempArr.length - 1; i++) {
            boolean checkFlag = false;
            for (int j = 0; j < tempArr.length - i - 1; j++) {
                if (tempArr[j + 1] != null && tempArr[j].compareToIgnoreCase(tempArr[j + 1]) > 0) {
                    // Swap elements if they are in the wrong order
                    String temp = tempArr[j];
                    tempArr[j] = tempArr[j + 1];
                    tempArr[j + 1] = temp;
                    checkFlag = true;
                }
            }
            if (!checkFlag){
                break;
            }
        }
        printSortQueue(queueType, tempArr);
    }

    private static void printSortQueue(String queueType, String[] tempArr) {
        System.out.print(queueType + " : ");
        int count = 0;
        // Print the sorted list
        for (String str : tempArr) {
            if (str == null) {
                System.out.print("Not Occupied");
            } else {
                System.out.print(str);
            }
            if (!(count == (tempArr.length - 1))) {
                System.out.print(", ");
            }
            count++;
        }
        System.out.println();
    }

    private static void removeCustomerFromQueue() {
        String removeName;
        while (true){
            System.out.print("Enter the name of the customer to remove: ");
            removeName = scan.next();
            if (!removeName.isEmpty()){
                break;
            }else {
                System.out.println("Enter correct name!!");
            }
        }

        int cashierNumber;
        while (true) {
            System.out.print("Enter relevant cashier want to served [1,2,3]: ");
            try {
                cashierNumber = scan.nextInt();
                if (cashierNumber == 1 || cashierNumber == 2 || cashierNumber == 3){
                    break;
                }
                System.out.println("Please enter correct cashier number");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                // Clear the invalid input from the scanner
                scan.next();
            }
        }

        if (cashierNumber == 1){
            removeCustomer(queue1,removeName);
        } else if (cashierNumber == 2){
            removeCustomer(queue2,removeName);
        }else {
            removeCustomer(queue3,removeName);
        }
    }

    private static void removeCustomer(String[] queueType, String removeName) {
        boolean checkFlag = false;
        for (int index=0;index<queueType.length-1; index++){
            if ( ((queueType[index]!=null) && queueType[index].equals(removeName)) || checkFlag) {
                checkFlag = true;
                queueType[index]=queueType[index+1];
            }
        }
        if (checkFlag){
            queueType[queueType.length - 1] = null;
            System.out.println("Successfully removed customer "+ removeName);
        }else {
            System.out.println("Any customer not found in this cashier");
        }
    }

    private static void addCustomerToQueue() {
        if (checkAvailable()){
            System.out.print("Enter Customer name : ");
            String userName = scan.next();
            int cashierNumber;
            while (true) {
                System.out.print("Enter relevant cashier [1,2,3]: ");
                try {
                    cashierNumber = scan.nextInt();
                    if (cashierNumber == 1 || cashierNumber == 2 || cashierNumber == 3){
                        break;
                    }
                    System.out.println("Please enter correct cashier number");
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a valid number.");
                    // Clear the invalid input from the scanner
                    scan.next();
                }
            }

            if (cashierNumber == 1){
                if (setName(queue1,userName)) System.out.println("Sorry! No slots in Cashier 1");
            } else if (cashierNumber == 2){
                if (setName(queue2,userName)) System.out.println("Sorry! No slots in Cashier 2");
            }else {
                if (setName(queue3,userName)) System.out.println("Sorry! No slots in Cashier 3");
            }
        }else {
            System.out.println("All Cashiers not available!!");
        }
    }

    private static boolean setName(String[] queueType, String userName) {
        for (int index=0;index< queueType.length;index++){
            if (queueType[index]==null){
                queueType[index]=userName;
                return false;
            }
        }
        return true;
    }

    private static boolean checkAvailable() {
        boolean avaliable;
        avaliable = checkEachQueue(queue1,"Cashier Queue 1")|checkEachQueue(queue2,"Cashier Queue 2")|checkEachQueue(queue3,"Cashier Queue 3");
        return avaliable;
    }

    private static boolean checkEachQueue(String[] queueType, String queueName) {
        for (String name:queueType){
            if (name==null){
                System.out.println(queueName + " available");
                return true;
            }
        }
        return false;
    }

    private static void viewAllEmptyQueues() {
        checkQueueEmpty(queue1, "Queue 1");
        checkQueueEmpty(queue2, "Queue 2");
        checkQueueEmpty(queue3, "Queue 3");
    }

    private static void checkQueueEmpty(String[] queueType, String queueName) {
        boolean queueEmpty = false;
        for (String name : queueType) {
            if (name != null) {
                queueEmpty = true;
                break;
            }
        }
        if (!queueEmpty) {
            System.out.println(queueName + " all slots free.");
        }
    }

    private static void displayMenu() {
        System.out.println("Foodies Fave Queue Management System\n");
        System.out.println("Menu:");
        System.out.println("100 or VFQ: View all Queues");
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