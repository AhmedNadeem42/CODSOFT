import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn $" + amount);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }
}

class bank {
    private BankAccount account;

    public bank(BankAccount account) {
        this.account = account;
    }

    public void displayOptions() {
        System.out.println("Options:");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
    }

    public void performTransaction(int choice, double amount) {
        switch (choice) {
            case 1:
                System.out.println("Current Balance: $" + account.getBalance());
                break;
            case 2:
                account.deposit(amount);
                break;
            case 3:
                account.withdraw(amount);
                break;
            case 4:
                System.out.println("Exiting. Thank you!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option. Please choose a valid option.");
        }
    }
}

public class ATM {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000.0);
        bank atm = new bank(userAccount);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            atm.displayOptions();
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                if (choice == 4) {
                    System.out.println("Exiting. Thank you!");
                    scanner.close();
                    System.exit(0);
                } else if (choice >= 1 && choice <= 3) {
                    System.out.print("Enter the amount: ");
                    if (scanner.hasNextDouble()) {
                        double amount = scanner.nextDouble();
                        atm.performTransaction(choice, amount);
                    } else {
                        System.out.println("Invalid amount. Please enter a valid number.");
                    }
                } else {
                    System.out.println("Invalid option. Please choose a valid option.");
                }
            } else {
                System.out.println("Invalid option. Please choose a valid option.");
                scanner.next();
            }
        }
    }
}
