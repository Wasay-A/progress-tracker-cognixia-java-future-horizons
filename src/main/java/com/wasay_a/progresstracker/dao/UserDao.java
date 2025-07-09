package com.wasay_a.progresstracker.dao;

import java.sql.SQLException;

import com.wasay_a.progresstracker.exception.AuthenticationException;
import com.wasay_a.progresstracker.exception.DataAccessException;

public interface UserDao {
    public void establishConnection() throws ClassNotFoundException, SQLException, DataAccessException;

	public void closeConnection() throws SQLException, DataAccessException;

    int login(String username, String password) throws AuthenticationException, DataAccessException;

    boolean register(String username, String password) throws AuthenticationException, DataAccessException;
}
