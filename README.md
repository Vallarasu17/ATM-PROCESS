# ATM-PROCESS
# 1.load cash Module 
# How to Use the Program
1. Compile and run the loadCash class. This class represents the Load Cash module of the ATM.

2. The program will read the cash denomination details from the MoneyDenomination.txt file if it exists. The file should contain data in the format: <denomination>,<quantity>,<value>. For example: 2000,20,40000. If the file does not exist or is empty, the program will start with an empty cash denomination.
  
3. The program will allow you to load additional cash denominations into the ATM. You need to provide the denomination, quantity, and total value for each denomination.

4. After loading cash denominations, the program will save the updated cash denomination details to the MoneyDenomination.txt file.

5. Finally, the program will display the currency denomination details, showing the denomination, quantity, and total value for each denomination. 

# 2.CustomerDetails Module 
This Java program lets you retrieve and display customer details from a file. The customerDetails class provides methods to read customer information from a text file and display it in a structured format. 

# How to Use the Program

Ensure that you have the customer_details.txt file in the specified location.

Compile the customerDetails class using the Java compiler.

Run the program by executing the main method.

The program will read the customer details from the file and display them in a tabular format.

If the file is found and the customer details are successfully read, you will see the customer information displayed on the console.

If there is an issue reading the file or if the file is not found, an error message will be displayed.

# Data File

The program uses the customer_details.txt file to read the customer details. Ensure that this file is present in the specified location and contains customer information in the format <account number>,<account holder name>,<PIN>,<account balance>.

# 3.ATM PROCESS MODULE

This Java program simulates an Automated Teller Machine (ATM) process, allowing users to perform various banking operations. The program is designed to manage customer accounts, check account balances, withdraw money, and display ATM cash details.

# Overview
The ATMProcess package contains three classes:

atm functions: This class handles all ATM-related functions, including loading customer details, displaying the main menu, checking balances, withdrawing money, and checking the ATM cash details.

customer details: This class manages customer account details, reads customer information from a file, and displays customer details in a tabular format.

loadCash: This class handles the ATM's cash-loading process, allowing the user to load money denominations into the ATM.

# How to Use the Program
Ensure that you have the necessary data files: customer_details.txt containing customer information and MoneyDenomination.txt containing ATM cash details.

Compile all three classes: atmFunctions, customerDetails, and loadCash.

Run the program by executing the main method in the atmFunctions class.

The program will prompt you to enter your account number and PIN. If the provided credentials are valid, the main menu will be displayed.

Choose an option from the main menu to perform the desired operation:

Option 1: Check Balance
Option 2: Withdraw Money
Option 3: Check ATM Balance
Depending on the chosen option, the program will perform the corresponding operation and display the results.

# Data Files
customer_details.txt: This file contains customer account information in the format <account number>,<account holder name>,<PIN>,<account balance>. Ensure that the file exists and is correctly formatted.

MoneyDenomination.txt: This file contains ATM cash details, including denominations and quantities in the format <denomination>,<quantity>. Make sure this file exists and is properly maintained.

