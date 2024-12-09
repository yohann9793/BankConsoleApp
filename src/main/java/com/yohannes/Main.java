package com.yohannes;

import java.util.Scanner;

public class Main {
        public static void main(String[] args) {
                DatabaseConnection dbManager = new DatabaseConnection();
                Scanner scanner = new Scanner(System.in);

                while (true) {
                        System.out.println("=  Manage Bank Account  =");
                        System.out.println("1. Create Account");
                        System.out.println("2. Deposit");
                        System.out.println("3. Withdraw");
                        System.out.println("4. Calculate Interest");
                        System.out.println("5. Exit");
                        System.out.print("Choose an option: ");
                        int choice = scanner.nextInt();

                        try {
                                switch (choice) {
                                        case 1:
                                                System.out.print("Enter account holder name: ");
                                                scanner.nextLine(); // Consume newline
                                                String name = scanner.nextLine();
                                                System.out.print("Enter initial balance: ");
                                                double balance = scanner.nextDouble();
                                                System.out.print("Enter interest rate: ");
                                                double interestRate = scanner.nextDouble();
                                                BankAccount newAccount = new BankAccount(0, name, balance, interestRate);
                                                dbManager.addAccount(newAccount);
                                                System.out.println("Account created successfully.");
                                                break;

                                        case 2:
                                                System.out.print("Enter account ID: ");
                                                int depositAccountId = scanner.nextInt();
                                                System.out.print("Enter deposit amount: ");
                                                double depositAmount = scanner.nextDouble();
                                                BankAccount depositAccount = dbManager.getAccount(depositAccountId);
                                                if (depositAccount != null) {
                                                        depositAccount.deposit(depositAmount);
                                                        dbManager.updateAccountBalance(depositAccountId, depositAccount.getAccountBalance());
                                                        System.out.println("Deposit successful.");
                                                } else {
                                                        System.out.println("Account not found.");
                                                }
                                                break;

                                        case 3:
                                                System.out.print("Enter account ID: ");
                                                int withdrawAccountId = scanner.nextInt();
                                                System.out.print("Enter withdrawal amount: ");
                                                double withdrawAmount = scanner.nextDouble();
                                                BankAccount withdrawAccount = dbManager.getAccount(withdrawAccountId);
                                                if (withdrawAccount != null) {
                                                        withdrawAccount.withdraw(withdrawAmount);
                                                        dbManager.updateAccountBalance(withdrawAccountId, withdrawAccount.getAccountBalance());
                                                        System.out.println("Withdrawal successful.");
                                                } else {
                                                        System.out.println("Account not found.");
                                                }
                                                break;

                                        case 4:
                                                System.out.print("Enter account ID: ");
                                                int interestAccountId = scanner.nextInt();
                                                BankAccount interestAccount = dbManager.getAccount(interestAccountId);
                                                if (interestAccount != null) {
                                                        double interest = interestAccount.calculateInterest();
                                                        System.out.println("Calculated Interest: " + interest);
                                                } else {
                                                        System.out.println("Account not found.");
                                                }
                                                break;

                                        case 5:
                                                System.out.println("Goodbye!");
                                                scanner.close();
                                                return;

                                        default:
                                                System.out.println("Invalid choice. Please try again.");
                                                break;
                                }
                        } catch (Exception e) {
                                System.out.println("Error: " + e.getMessage());
                        }
                }
        }
}
