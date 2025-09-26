package com.expense_tracker.dao;
import com.expense_tracker.util.DatabaseConnection;
import com.expense_tracker.Model.Expense;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class ExpenseDAO{
    private static final String FILTERING_DATA_BY_CATEGORY = "SELECT * FROM expense WHERE category=?";
    private static final String DELETE_EXPENSE_BY_ID="DELETE FROM expense WHERE id=?";
    private static final String UPDATE_EXPENSE_BY_ID="UPDATE expense SET category=?,amount=?,description=?,date=? WHERE id=?";
    private static final String INSERT_EXPENSE="INSERT INTO expense(category,amount,description,date) VALUES(?,?,?,?)";
    private Expense getAsExpense(ResultSet rs) throws SQLException {
        return new Expense(
            rs.getInt("id"),
            rs.getString("category"),
            rs.getInt("amount"),
            rs.getString("description"),
            rs.getDate("date").toLocalDate()
        );
    }
    public List<Expense> getCategory(String curr) throws SQLException {
        List<Expense> expenses = new ArrayList<>();
        DatabaseConnection db = new DatabaseConnection();
        try (Connection cn = db.getDbConnection();
            PreparedStatement stmt = cn.prepareStatement(FILTERING_DATA_BY_CATEGORY)) {
            stmt.setString(1, curr);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                expenses.add(getAsExpense(rs));
            }
        }
        return expenses;
    }
    public void deleteExpense(int id) throws SQLException{
        DatabaseConnection db =new DatabaseConnection();
        try(Connection cn =db.getDbConnection();
            PreparedStatement stmt = cn.prepareStatement(DELETE_EXPENSE_BY_ID)){
            stmt.setInt(1,id);
            stmt.executeUpdate();
        }
    }
    public void addExpense(Expense expense) throws SQLException{
              DatabaseConnection db = new DatabaseConnection();
                            try(
                                Connection cn = db.getDbConnection();
                                PreparedStatement stmt =cn.prepareStatement(INSERT_EXPENSE)
                            ){
                stmt.setString(1,expense.getCategory());
                stmt.setInt(2,expense.getAmount());
                stmt.setString(3,expense.getDescription());
                stmt.setDate(4,java.sql.Date.valueOf(expense.getDate()));
                stmt.executeUpdate();
              }
    }
    public void updateExpense(Expense expense) throws SQLException{
           DatabaseConnection db =new DatabaseConnection();
           try(
            Connection cn = db.getDbConnection();
            PreparedStatement stmt =cn.prepareStatement(UPDATE_EXPENSE_BY_ID)
           ){
            stmt.setString(1,expense.getCategory());
            stmt.setInt(2,expense.getAmount());
            stmt.setString(3,expense.getDescription());
            stmt.setDate(4,java.sql.Date.valueOf(expense.getDate()));
            stmt.setInt(5,expense.getId());
            stmt.executeUpdate();
           }
    }
}