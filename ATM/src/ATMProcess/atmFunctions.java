package ATMProcess;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner; 
import java.io.FileWriter;
import java.io.IOException;

public class atmFunctions { 
	
	private static HashMap<Integer,Integer> accountDetails = new HashMap<>();
    private static HashMap<Integer,Double> accountBalance=new HashMap<>();
    private static HashMap<Integer,String> accountHolder = new HashMap<>();
    private static HashMap<Integer, Integer> denominations = new HashMap<>();
    private static double atmBalance = 100000.0;
    public static void main(String args[]){
        readCustomerDetailsFromFile("customer_details.txt");
        readDenominationsFromFile("MoneyDenomination.txt");
        Scanner sc=new Scanner(System.in);
        
        while(true){
            System.out.println("Please enter your account number:");
            int accNumber=sc.nextInt();
            System.out.println("Please enter your pin:");
            int pin=sc.nextInt();
            
            if(validateUser(accNumber,pin)){
                displayMenu();
                int option=sc.nextInt();
                
                switch(option){
                    case 1:
                        checkBalance(accNumber);
                        break;
                    case 2:
                        withdrawMoney(accNumber);
                        break;
                    case 4:
                        checkATMBalance();
                        break;
                    default:
                        System.out.println("Invalid option.Please try again.");
                }
            }
            else{
                System.out.println("Invalid Account Number or pin. Please try again.");
            }
        }
    }
    private static boolean validateUser(int accNumber,int pin){
        return accountDetails.containsKey(accNumber) && accountDetails.get(accNumber)==pin;
        }
        
    private static void readCustomerDetailsFromFile(String fileName){
        try{
            File file=new File(fileName);
            Scanner sc=new Scanner(file);
            while(sc.hasNextLine()){
                String line=sc.nextLine();
                String[] parts=line.split(",");
                String accNumber=parts[0];
                String accHolder=parts[1];
                String pin = parts[2];
                String accBalance = parts[3];
                accountDetails.put(Integer.parseInt(accNumber),Integer.parseInt(pin));
                accountBalance.put(Integer.parseInt(accNumber),Double.parseDouble(accBalance));
                accountHolder.put(Integer.parseInt(accNumber),accHolder);
            }
            sc.close();
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
    
    private static void readDenominationsFromFile(String fileName) {
        try {
            File file = new File(fileName);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(",");
                int denomination = Integer.parseInt(parts[0].trim());
                int quantity = Integer.parseInt(parts[1].trim());
                denominations.put(denomination, quantity);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void displayMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Check Balance");
        System.out.println("2. Withdraw Money");
        System.out.println("3. Check ATM Balance");
        System.out.println("Please enter your choice:");
    }
    
    private static void checkBalance(int accountNumber) {
        double balance = accountBalance.get(accountNumber);
        System.out.println("Your account balance is: " + balance );
    }
    
    private static void withdrawMoney(int accountNumber) {
    	Scanner sc = new Scanner(System.in);
        double accBalance = accountBalance.get(accountNumber);
        System.out.println("Please enter the amount to be withdrawn:");
        double amountToWithdraw = sc.nextDouble();

        if (amountToWithdraw < 100 || amountToWithdraw > 10000) {
            System.out.println("Invalid withdrawal amount. The amount must be between 100₹ and 10,000₹.");
            return;
        }

        if (amountToWithdraw > atmBalance) {
            System.out.println("ATM does not have sufficient money to vend. Please try a lower amount.");
            return;
        }

        if (amountToWithdraw > accBalance) {
            System.out.println("Your account balance is lower than the requested withdrawal amount.");
            return;
        }

        int[] denominations = getDenominations(amountToWithdraw);

        accBalance -= amountToWithdraw;
        atmBalance -= amountToWithdraw;

        saveBalancesToFile("customer_details.txt");

        System.out.println("Amount withdrawn successfully. Please take the cash:");
        System.out.println("Denominations:");
        System.out.println("2000: " + denominations[0]);
        System.out.println("500: " + denominations[1]);
        System.out.println("100: " + denominations[2]);
    }


    private static int[] getDenominations(double amount) {
        int[] denominations = new int[3];
        if (amount <= 5000) {
            denominations[0] = (int) (amount / 2000);
            amount %= 2000;
            denominations[1] = (int) (amount / 500);
            amount %= 500;
            denominations[2] = (int) Math.min(amount / 100, 15);
        } else {
            denominations[0] = (int) (amount / 2000) * 2;
            amount %= 2000;
            denominations[1] = (int) (amount / 500) * 2;
            amount %= 500;
            denominations[2] = (int) Math.min(amount / 100, 10);
        }
        return denominations;
    }

    private static void saveBalancesToFile(String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName);
            for (int accNumber : accountBalance.keySet()) {
                writer.write(accNumber + ","+ accountHolder.get(accNumber) + ","+ accountDetails.get(accNumber) + "," + accountBalance.get(accNumber) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void checkATMBalance() {
    	 System.out.println("Denomination\tNumber\tValue");
    	    for (int denomination : denominations.keySet()) {
    	        int quantity = denominations.get(denomination);
    	        System.out.println(denomination + "\t\t" + quantity + "\t" + (denomination * quantity));
    	    }

    	    System.out.println("\nTotal Amount available in the ATM = " + atmBalance );
    }

}
