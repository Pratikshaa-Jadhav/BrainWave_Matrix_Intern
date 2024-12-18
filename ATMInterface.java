package BrainWaveMatrix;
import java.util.Scanner;

public class ATMInterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int balance = 5000; // Default balance
        int pin = 1234;     // Default PIN
        int attempts = 0;
        boolean isAuthenticated = false;

        System.out.println("Welcome to the ATM!");

        // PIN Authentication
        while (attempts < 3 && !isAuthenticated) {
            System.out.print("Enter your PIN: ");
            int enteredPin = scanner.nextInt();

            if (enteredPin == pin) {
                isAuthenticated = true;
            } else {
                attempts++;
                System.out.println("Incorrect PIN. Attempts left: " + (3 - attempts));
            }
        }

        if (!isAuthenticated) {
            System.out.println("Your account is locked due to multiple incorrect attempts.");
            return;
        }

        // ATM Menu
        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Your current balance is: Rs. " + balance);
                    break;

                case 2:
                    System.out.print("Enter the amount to deposit: Rs. ");
                    int depositAmount = scanner.nextInt();
                    if (depositAmount > 0) {
                        balance += depositAmount;
                        System.out.println("Successfully deposited Rs. " + depositAmount);
                        System.out.println("Your updated balance is: Rs. " + balance);
                    } else {
                        System.out.println("Invalid deposit amount.");
                    }
                    break;

                case 3:
                    System.out.print("Enter the amount to withdraw: Rs. ");
                    int withdrawAmount = scanner.nextInt();
                    if (withdrawAmount > 0 && withdrawAmount <= balance) {
                        balance -= withdrawAmount;
                        System.out.println("Successfully withdrawn Rs. " + withdrawAmount);
                        System.out.println("Your updated balance is: Rs. " + balance);
                    } else if (withdrawAmount > balance) {
                        System.out.println("Insufficient balance.");
                    } else {
                        System.out.println("Invalid withdrawal amount.");
                    }
                    break;

                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
