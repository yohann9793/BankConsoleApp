package com.yohannes;

import java.sql.*;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/bankinfo";
    private static final String USER = "root";
    private static final String PASSWORD = "Yohannes@8985";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void addAccount(BankAccount account) throws SQLException {
        String query = "INSERT INTO BankAccount (accountHolderName, accountBalance, interestRate) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, account.getAccountHolderName());
            stmt.setDouble(2, account.getAccountBalance());
            stmt.setDouble(3, account.getInterestRate());
            stmt.executeUpdate();
        }
    }

    public BankAccount getAccount(int accountId) throws SQLException {
        String query = "SELECT * FROM BankAccount WHERE accountId = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, accountId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new BankAccount(
                        rs.getInt("accountId"),
                        rs.getString("accountHolderName"),
                        rs.getDouble("accountBalance"),
                        rs.getDouble("interestRate")
                );
            } else {
                throw new SQLException("Account not found.");
            }
        }
    }

    public void updateAccountBalance(int accountId, double newBalance) throws SQLException {
        String query = "UPDATE BankAccount SET accountBalance = ? WHERE accountId = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, newBalance);
            stmt.setInt(2, accountId);
            stmt.executeUpdate();
        }
    }
}


