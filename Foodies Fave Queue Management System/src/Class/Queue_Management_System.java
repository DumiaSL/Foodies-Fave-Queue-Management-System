package Class;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Queue_Management_System {
    // Create a Scanner object to read user input
    public static Scanner scan = new Scanner(System.in);
    // Arrays to store customer names and queue information
    private static FoodQueue[] queue1 = new FoodQueue[2];
    private static FoodQueue[] queue2 = new FoodQueue[3];
    private static FoodQueue[] queue3 = new FoodQueue[5];

    // Stock information
    private static final int MAX_BURGERS_STOCK = 50;
    private static final int MAX_PRICE_EACH_BURGER = 650;
    private static int BURGERS_STOCK = MAX_BURGERS_STOCK;
    private static int[] NUMBER_OF_BURGERS_SOLD = new int[3];
    private static final String DATABASE_NAME = "Database/DataBase_class.txt";

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
                    case "110", "IFQ" -> checkIncomeEachQueue();
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

    private static void checkIncomeEachQueue() {
        System.out.println("Cashier 1: " + NUMBER_OF_BURGERS_SOLD[0] * 650 + " sold burgers: " + NUMBER_OF_BURGERS_SOLD[0]);
        System.out.println("Cashier 2: " + NUMBER_OF_BURGERS_SOLD[1] * 650 + " sold burgers: " + NUMBER_OF_BURGERS_SOLD[1]);
        System.out.println("Cashier 3: " + NUMBER_OF_BURGERS_SOLD[2] * 650 + " sold burgers: " + NUMBER_OF_BURGERS_SOLD[2]);
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
                } else {
                    System.out.print("O");
                }
            } else {
                System.out.print(" ");
            }
            System.out.print("    ");
            if (count < queue2.length) {
                if (queue2[count] == null) {
                    System.out.print("X");
                } else {
                    System.out.print("O");
                }
            } else {
                System.out.print(" ");
            }
            System.out.print("    ");
            if (count < queue3.length) {
                if (queue3[count] == null) {
                    System.out.print("X");
                } else {
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
            int tempCount = 0;
            while ((line = bufferedReader.readLine()) != null) {
                // Get each line of the file
                if (count == 1) {
                    BURGERS_STOCK = Integer.parseInt(line);
                } else if (count < 4) {
                    if (line.equals("null")) {
                        queue1[tempCount] = null;
                    } else {
                        String[] temp = line.split(",");
                        queue1[tempCount] = new FoodQueue(temp[0], temp[1], Integer.parseInt(temp[2]));
                    }
                } else if (count < 7) {
                    if (count == 4) tempCount = 0;
                    if (line.equals("null")) {
                        queue2[tempCount] = null;
                    } else {
                        String[] temp = line.split(",");
                        queue2[tempCount] = new FoodQueue(temp[0], temp[1], Integer.parseInt(temp[2]));
                    }
                } else {
                    if (count == 7) tempCount = 0;
                    if (line.equals("null")) {
                        queue3[tempCount] = null;
                    } else {
                        String[] temp = line.split(",");
                        queue3[tempCount] = new FoodQueue(temp[0], temp[1], Integer.parseInt(temp[2]));
                    }
                }
                if (count != 1) tempCount++;
                count++;
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
            queueWrite(fileWriter, queue1);
            queueWrite(fileWriter, queue2);
            queueWrite(fileWriter, queue3);
            fileWriter.close();
            System.out.println("Data has been saved to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void queueWrite(FileWriter fileWriter, FoodQueue[] queueType) throws IOException {
        for (FoodQueue order : queueType) {
            if (order != null) {
                fileWriter.write(order.getCustomer().getFirstName() + "," + order.getCustomer().getLastName() + "," + order.getNoOfBurgers() + "\n");
            } else {
                fileWriter.write("null" + "\n");
            }
        }
    }

    private static void removeServedCustomer() {
        int cashierNumber;
        while (true) {
            System.out.print("Enter relevant cashier want to served [1,2,3]: ");
            try {
                cashierNumber = scan.nextInt();
                if (cashierNumber == 1 || cashierNumber == 2 || cashierNumber == 3) {
                    break;
                }
                System.out.println("Please enter correct cashier number");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                // Clear the invalid input from the scanner
                scan.next();
            }
        }

        if (cashierNumber == 1) {
            servedCashier(queue1, cashierNumber);
        } else if (cashierNumber == 2) {
            servedCashier(queue2, cashierNumber);
        } else {
            servedCashier(queue3, cashierNumber);

        }
    }

    private static void servedCashier(FoodQueue[] queueType, int cashierNumber) {
        boolean checkFlag = false;
        //checking order is queue
        for (FoodQueue order : queueType) {
            if (order != null) {
                checkFlag = true;
                break;
            }
        }
        if (checkFlag) {
            FoodQueue tempOrder = null;
            // Remove the first element
            tempOrder = queueType[0];
            for (int i = 0; i < queueType.length - 1; i++) {
                queueType[i] = queueType[i + 1];
            }

            // Set the last element to null or an empty string
            queueType[queueType.length - 1] = null;
            BURGERS_STOCK = BURGERS_STOCK - tempOrder.getNoOfBurgers();
            NUMBER_OF_BURGERS_SOLD[cashierNumber - 1] += tempOrder.getNoOfBurgers();
            System.out.println("Order complete: " + tempOrder.getCustomer().getFirstName() + " " + tempOrder.getCustomer().getLastName() + " - " + tempOrder.getNoOfBurgers() + " burgers");
            if (BURGERS_STOCK <= 10) {
                System.out.println("Low burger stock!!!");
            }
        } else {
            System.out.println("No orders in this Cashier");
        }
    }

    private static void customersSortedInAlphabetical() {
        // Sort the list
        bubbleSort(queue1, "Queue 1");
        bubbleSort(queue2, "Queue 2");
        bubbleSort(queue3, "Queue 3");
    }

    public static void bubbleSort(FoodQueue[] arr, String queueType) {
        FoodQueue[] tempArr = arr.clone();
        for (int i = 0; i < tempArr.length - 1; i++) {
            boolean checkFlag = false;
            for (int j = 0; j < tempArr.length - i - 1; j++) {
                if (tempArr[j + 1] != null && (tempArr[j].getCustomer().getFirstName() + " " + tempArr[j].getCustomer().getLastName()).compareToIgnoreCase(tempArr[j + 1].getCustomer().getFirstName() + " " + tempArr[j + 1].getCustomer().getLastName()) > 0) {
                    // Swap elements if they are in the wrong order
                    FoodQueue temp = tempArr[j];
                    tempArr[j] = tempArr[j + 1];
                    tempArr[j + 1] = temp;
                    checkFlag = true;
                }
            }
            if (!checkFlag) {
                break;
            }
        }
        printSortQueue(queueType, tempArr);
    }

    private static void printSortQueue(String queueType, FoodQueue[] tempArr) {
        System.out.print(queueType + " : ");
        int count = 0;
        // Print the sorted list
        for (FoodQueue order : tempArr) {
            if (order == null) {
                System.out.print("Not Occupied");
            } else {
                System.out.print(order.getCustomer().getFirstName() + " " + order.getCustomer().getLastName());
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
        while (true) {
            System.out.print("Enter the name of the customer to remove: ");
            removeName = scan.next();
            if (!removeName.isEmpty()) {
                break;
            } else {
                System.out.println("Enter correct name!!");
            }
        }

        int cashierNumber;
        while (true) {
            System.out.print("Enter relevant cashier want to served [1,2,3]: ");
            try {
                cashierNumber = scan.nextInt();
                if (cashierNumber == 1 || cashierNumber == 2 || cashierNumber == 3) {
                    break;
                }
                System.out.println("Please enter correct cashier number");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                // Clear the invalid input from the scanner
                scan.next();
            }
        }

        if (cashierNumber == 1) {
            removeCustomer(queue1, removeName);
        } else if (cashierNumber == 2) {
            removeCustomer(queue2, removeName);
        } else {
            removeCustomer(queue3, removeName);
        }
    }

    private static void removeCustomer(FoodQueue[] queueType, String removeName) {
        boolean checkFlag = false;
        for (int index = 0; index < queueType.length - 1; index++) {
            if (((queueType[index] != null) && ((queueType[index].getCustomer().getFirstName().equals(removeName)) || (queueType[index].getCustomer().getFirstName() + " " + queueType[index].getCustomer().getLastName()).equals(removeName))) || checkFlag) {
                checkFlag = true;
                queueType[index] = queueType[index + 1];
            }
        }
        if (checkFlag) {
            queueType[queueType.length - 1] = null;
            System.out.println("Successfully removed order : " + removeName);
        } else {
            System.out.println("Any customer not found in this cashier");
        }
    }

    private static void addCustomerToQueue() {
        if (checkAvailable()) {
            String firstName = getValidateName("First");
            String secondName = getValidateName("Second");

            int noBurgers;

            while (true) {
                System.out.print("Enter Number of burgers : ");
                try {
                    noBurgers = scan.nextInt();
                    if (noBurgers <= MAX_BURGERS_STOCK) {
                        break;
                    } else {
                        System.out.println("Please enter <" + MAX_BURGERS_STOCK + " orders");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a valid number.");
                    // Clear the invalid input from the scanner
                    scan.next();
                }
            }

            int freeQueue1 = getFreesLots(queue1);
            int freeQueue2 = getFreesLots(queue2);
            int freeQueue3 = getFreesLots(queue3);

            int cashierNumber = 0;
            int maxNumber = Integer.MIN_VALUE;
            Integer[] freeQueueArray = {freeQueue1, freeQueue2, freeQueue3};

            for (int i = 0; i < freeQueueArray.length; i++) {
                if (freeQueueArray[i] > maxNumber) {
                    maxNumber = freeQueueArray[i];
                    cashierNumber = i + 1;
                }
            }

            if (cashierNumber == 1) {
                if (setName(queue1, firstName, secondName, noBurgers, " cashier 1"))
                    System.out.println("Sorry! No slots in Cashier 1");
            } else if (cashierNumber == 2) {
                if (setName(queue2, firstName, secondName, noBurgers, " cashier 2"))
                    System.out.println("Sorry! No slots in Cashier 2");
            } else {
                if (setName(queue3, firstName, secondName, noBurgers, " cashier 3"))
                    System.out.println("Sorry! No slots in Cashier 3");
            }
        } else {
            System.out.println("All Cashiers not available!!");
        }
    }

    private static String getValidateName(String nameType) {
        String userName;
        while (true) {
            System.out.print("Enter Customer " + nameType + " name : ");
            userName = scan.next();
            if (!userName.isEmpty()) {
                break;
            } else {
                System.out.println("Wrong !Enter " + nameType + " name correctly");
            }
        }
        return userName;
    }

    private static int getFreesLots(FoodQueue[] queueType) {
        int count = 0;
        for (FoodQueue order : queueType) {
            if (order == null) {
                count++;
            }
        }
        System.out.println(count);
        return count;
    }

    private static boolean setName(FoodQueue[] queueType, String firstName, String secondName, int noBurgers, String cashierType) {
        for (int index = 0; index < queueType.length; index++) {
            if (queueType[index] == null) {
                FoodQueue temp = new FoodQueue(firstName, secondName, noBurgers);
                queueType[index] = temp;
                System.out.println("Customer order - " + cashierType + " slot - " + (index + 1));
                return false;
            }
        }
        return true;
    }

    private static boolean checkAvailable() {
        boolean avaliable;
        avaliable = checkEachQueue(queue1, "Cashier Queue 1") | checkEachQueue(queue2, "Cashier Queue 2") | checkEachQueue(queue3, "Cashier Queue 3");
        return avaliable;
    }

    private static boolean checkEachQueue(FoodQueue[] queueType, String queueName) {
        int count = 0;
        for (FoodQueue order : queueType) {
            if (order == null) {
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

    private static void checkQueueEmpty(FoodQueue[] queueType, String queueName) {
        boolean queueEmpty = false;
        for (FoodQueue order : queueType) {
            System.out.println(order);
            if (order != null) {
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
        System.out.println("110 or IFQ: Income of each cashier");
        System.out.println("999 or EXT: Exit the Program");
    }
}