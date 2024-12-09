package com.yohannes;

public class BankAccount {

        private int accountId;
        private String accountHolderName;
        private double accountBalance;
        private double interestRate;

        public BankAccount(int accountId, String accountHolderName, double accountBalance, double interestRate) {
            this.accountId = accountId;
            this.accountHolderName = accountHolderName;
            this.accountBalance = accountBalance;
            this.interestRate = interestRate;
        }

        public int getAccountId() {
            return accountId;
        }

        public String getAccountHolderName() {
            return accountHolderName;
        }

        public double getAccountBalance() {
            return accountBalance;
        }

        public double getInterestRate() {
            return interestRate;
        }

        public void deposit(double amount) {
            if (amount <= 0) {
                throw new IllegalArgumentException("Deposit amount must be positive.");
            }
            accountBalance += amount;
        }

        public void withdraw(double amount) {
            if (amount <= 0) {
                throw new IllegalArgumentException("Withdrawal amount must be positive.");
            }
            if (amount > accountBalance) {
                throw new IllegalArgumentException("Insufficient balance.");
            }
            accountBalance -= amount;
        }

        public double calculateInterest() {
            return accountBalance * (interestRate / 100);
        }
    }


