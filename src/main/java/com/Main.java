package com.expense_tracker;

import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.expense_tracker.util.DatabaseConnection; 
import com.expense_tracker.GUI.IntroFrame;

public class Main {
    public static void main(String[] args) {

        DatabaseConnection db = new DatabaseConnection();
        try {
            Connection cn = db.getDbConnection();
            System.out.println("Connection Successful");
        } catch (SQLException e) {
            System.out.println("Connection Failed: " + e.getMessage());
        }

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new IntroFrame().setVisible(true);
        });
    }
}
