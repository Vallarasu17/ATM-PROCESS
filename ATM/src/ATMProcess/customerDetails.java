package ATMProcess;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class customerDetails {
	private int AccNumber;
    private String AccHolder;
    private int pin;
    private double AccBalance;

    public customerDetails(int AccNumber, String AccHolder, int pin, double AccBalance) {
        this.AccNumber = AccNumber;
        this.AccHolder = AccHolder;
        this.pin = pin;
        this.AccBalance = AccBalance;
    }

    public static void main(String args[]) {
        String FileName = "C:\\Users\\Vallarasu S\\eclipse-workspace\\ATM\\customer_details.txt";
        customerDetails[] customers = readCustomerDetailsFromFile(FileName);

        if (customers != null) {
            displayDetails(customers);
        } else {
            System.out.println("Error reading from File.");
        }
    }

    public static customerDetails[] readCustomerDetailsFromFile(String FileName) {
    customerDetails[] customers = null;
    try {
        File file = new File(FileName);
        Scanner sc = new Scanner(file);
        int numberOfCustomers = 0;
        while (sc.hasNextLine()) {
            sc.nextLine();
            numberOfCustomers++;
        }
        sc.close();

        sc = new Scanner(file);
        customers = new customerDetails[numberOfCustomers];
        int i = 0;

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] parts = line.split(",");
            String AccNumber = parts[0].trim();
            String AccHolder = parts[1].trim();
            String pin = parts[2].trim();
            String AccBalance = parts[3].trim();
            customers[i] = new customerDetails(Integer.parseInt(AccNumber),AccHolder,Integer.parseInt(pin),Double.parseDouble(AccBalance));
            i++;
        }
        sc.close();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    return customers;
}



    public static void displayDetails(customerDetails[] customers) {
        System.out.println("Customer Details:");
        System.out.println("Acc No\t\tAcc Holder\t\tPin Number\tAcc Balance");
        for (customerDetails customer : customers) {
            System.out.format("%-8d\t%-16s\t%-12d\t\t%.2f%n", customer.AccNumber, customer.AccHolder, customer.pin, customer.AccBalance);
        }
    }

}
