package com.wasay_a.progresstracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import com.wasay_a.progresstracker.connection.ConnectionManager;
import com.wasay_a.progresstracker.exception.AuthenticationException;
import com.wasay_a.progresstracker.exception.DataAccessException;

public class UserDaoImpl implements UserDao{
    private Connection connection = null;

	@Override
	public void establishConnection() throws ClassNotFoundException, SQLException, DataAccessException {
		try {
            if (connection == null) {
                connection = ConnectionManager.getConnection();
            }
        } catch (Exception e) {
            throw new DataAccessException("Failed to establish database connection", e);
        }
	}
	
	@Override
	public void closeConnection() throws SQLException, DataAccessException {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			throw new DataAccessException("Failed to close database connection", e);
		}
	}
    
    @Override
    public int login(String username, String password) throws AuthenticationException, DataAccessException {
        try {
			PreparedStatement pStmt = connection.prepareStatement("SELECT id FROM users WHERE username = ? AND password = ?");
			pStmt.setString(1, username);
			pStmt.setString(2, password);

            ResultSet rs = pStmt.executeQuery();

            if(rs.next()) {
                int id = rs.getInt(1);
                rs.close();
                pStmt.close();
                return id;
            } else {
                rs.close();
                pStmt.close();
                throw new AuthenticationException("Invalid username or password. Please try again.");
            }

        } catch(SQLException e){
            throw new DataAccessException("Error during user login", e);
        } 
    }

                
    @Override
    public boolean register(String username, String password) throws AuthenticationException, DataAccessException {
        try {
            PreparedStatement pStmt = connection.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
            pStmt.setString(1, username);
            pStmt.setString(2, password);
            int rowsAffected = pStmt.executeUpdate();
            pStmt.close();
            if (rowsAffected == 0) {
                throw new AuthenticationException("Registration failed. Please try again.");
            }
            return rowsAffected > 0;
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new AuthenticationException("Username already exists. Please choose a different username.");
        } catch (SQLException e) {
            throw new DataAccessException("Database error during user registration", e);
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return false;
    }
}
