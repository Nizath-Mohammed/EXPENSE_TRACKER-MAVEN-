package com.expense_tracker.dao;
import com.expense_tracker.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.expense_tracker.Model.Catagory;
public class CategoryDAO {
	private static final String INSERT_QUERY = "INSERT INTO categories(name) VALUES(?)";
	private static final String FETCH_ALL_CATEGORIES = "SELECT * FROM categories";
	private static final String DELETE_CATEGORY_BY_NAME = "DELETE FROM categories WHERE name=?";
	public void deleteCatagory(String name) throws SQLException{
	    DatabaseConnection db = new DatabaseConnection();
		try(Connection cn = db.getDbConnection();
		PreparedStatement stmt = cn.prepareStatement(DELETE_CATEGORY_BY_NAME);
		)
		{
			stmt.setString(1,name);
			stmt.executeUpdate();
		}

	}
	public void addCatagory(String name) throws SQLException{
		DatabaseConnection db =new DatabaseConnection();
		try(Connection cn = db.getDbConnection();
		PreparedStatement stmt = cn.prepareStatement(INSERT_QUERY)){
			stmt.setString(1,name);
			stmt.executeUpdate();
		}
	}
	private Catagory addAsCatagory(ResultSet rs) throws SQLException{
		Catagory c = new Catagory(rs.getString("name"));
		return c;
	}
	public List<Catagory> getAllCatagory() throws SQLException{
		List<Catagory> categories = new ArrayList<>();
		DatabaseConnection db = new DatabaseConnection();
		try(Connection cn = db.getDbConnection();
		PreparedStatement stmt = cn.prepareStatement(FETCH_ALL_CATEGORIES); ){
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				categories.add(addAsCatagory(rs));
			}
		}
		return categories;
	}

	// Add methods and fields as needed
}
