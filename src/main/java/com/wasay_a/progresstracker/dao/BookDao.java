package com.wasay_a.progresstracker.dao;

import java.sql.SQLException;
import java.util.List;

import com.wasay_a.progresstracker.exception.DataAccessException;

public interface BookDao {
	public void establishConnection() throws ClassNotFoundException, SQLException, DataAccessException;
	
	public void closeConnection() throws SQLException, DataAccessException;

    public List<Book> getAll() throws DataAccessException;

    public List<Book> getUserBooks(int userId) throws DataAccessException;

    public boolean addBookToUser(int userId, int bookId) throws DataAccessException;

    public boolean updateProgress(int userId, int bookId, String status) throws DataAccessException;
}
