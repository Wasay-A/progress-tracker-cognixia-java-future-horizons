package com.wasay_a.progresstracker.dao;

import java.sql.SQLException;
import java.util.List;

public interface BookDao {
	public void establishConnection() throws ClassNotFoundException, SQLException;
	
	public void closeConnection() throws SQLException;

    public List<Book> getAll();

    public String getProgress(Book book);

    public boolean update(Book book);
}
