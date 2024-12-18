package BrainWaveMatrix;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ATMInterface {
    private static final int DEFAULT_BALANCE = 5000;
    private static final int DEFAULT_PIN = 1234;
    private static final int MAX_ATTEMPTS = 3;

    private static int balance = DEFAULT_BALANCE;
    private static List<String> transactionHistory = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the ATM!");
        
        if (!authenticateUser()) {
            System.out.println("Your account is locked due to multiple incorrect attempts.");
            return;
        }

        showMenu();
    }

    private static boolean authenticateUser() {
        int attempts = 0;
        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Enter your PIN: ");
            int enteredPin = scanner.nextInt();
            if (enteredPin == DEFAULT_PIN) {
                return true;
            }
            attempts++;
            System.out.println("Incorrect PIN. Attempts left: " + (MAX_ATTEMPTS - attempts));
        }
        return false;
    }

    private static void showMenu() {
        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Transaction History");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    depositMoney();
                    break;
                case 3:
                    withdrawMoney();
                    break;
                case 4:
                    showTransactionHistory();
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void checkBalance() {
        System.out.println("Your current balance is: Rs. " + balance);
    }

    private static void depositMoney() {
        System.out.print("Enter the amount to deposit: Rs. ");
        int depositAmount = scanner.nextInt();
        if (depositAmount > 0) {
            balance += depositAmount;
            System.out.println("Successfully deposited Rs. " + depositAmount);
            System.out.println("Your updated balance is: Rs. " + balance);
            transactionHistory.add("Deposited: Rs. " + depositAmount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    private static void withdrawMoney() {
        System.out.print("Enter the amount to withdraw: Rs. ");
        int withdrawAmount = scanner.nextInt();
        if (withdrawAmount > 0 && withdrawAmount <= balance) {
            balance -= withdrawAmount;
            System.out.println("Successfully withdrawn Rs. " + withdrawAmount);
            System.out.println("Your updated balance is: Rs. " + balance);
            transactionHistory.add("Withdrew: Rs. " + withdrawAmount);
        } else if (withdrawAmount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            System.out.println("Invalid withdrawal amount.");
        }
    }

    private static void showTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            System.out.println("Transaction History:");
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }
}

