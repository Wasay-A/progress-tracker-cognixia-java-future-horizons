package com.wasay_a.progresstracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wasay_a.progresstracker.connection.ConnectionManager;

public class BookDaoImpl implements BookDao {
    private Connection connection = null;

	@Override
	public void establishConnection() throws ClassNotFoundException, SQLException {
		
		if(connection == null) {
			connection = ConnectionManager.getConnection();
		}
	}
	
	@Override
	public void closeConnection() throws SQLException {
		connection.close();
	}


    @Override
    public List<Book> getAll() {
        try {
			PreparedStatement pStmt = connection.prepareStatement("SELECT * FROM ALL_BOOKS");

            ResultSet rs = pStmt.executeQuery();

            List<Book> books = new ArrayList<>();

            while(rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);

                Book b = new Book(id, title);
                books.add(b);
            }

			rs.close();
			pStmt.close();
			
            return books;

        } catch(SQLException e){
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

		return null;
    }

    @Override
    public String getProgress(Book book) {
        return "none";
    }

    @Override
    public boolean update(Book book) {
        return false;
    }
}
