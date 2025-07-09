package com.wasay_a.progresstracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.wasay_a.progresstracker.connection.ConnectionManager;
import com.wasay_a.progresstracker.exception.DataAccessException;

public class BookDaoImpl implements BookDao {
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
    public List<Book> getAll() throws DataAccessException {
        List<Book> books = new ArrayList<>();
        try {
			PreparedStatement pStmt = connection.prepareStatement("SELECT id, title, isbn, authors, genres, page_count FROM all_books");

            ResultSet rs = pStmt.executeQuery();

            while(rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String isbn = rs.getString(3);
                String rawAuthors = rs.getString(4);
                String rawGenres = rs.getString(5);
                int pageCount = rs.getInt(6);

                List<Author> authors = Arrays.stream(rawAuthors.split(";"))
                                         .map(Author::new)
                                         .collect(Collectors.toList());
                List<Book.Genre> genres = Arrays.stream(rawGenres.split(";"))
                                        .map(String::trim)                            
                                        .filter(s -> !s.isEmpty())                    
                                        .map(s -> {
                                            try {
                                                // turn "Novel" → "NOVEL", "Science Fiction" → "SCIENCE_FICTION"
                                                return Book.Genre.valueOf(
                                                    s.replace(' ', '_')
                                                    .toUpperCase()
                                                );
                                            } catch (IllegalArgumentException iae) {
                                                // unknown genre—just skip it
                                                System.err.println("Warning: unknown genre '" + s + "'");
                                                return null;
                                            }
                                        })
                                        .filter(Objects::nonNull)
                                        .collect(Collectors.toList());
                Book b = new Book(id, title);
                b.setIsbn(isbn);
                b.setPageCount(pageCount);
                b.setAuthors(authors);
                b.setGenres(genres);
                books.add(b);
            }

			rs.close();
			pStmt.close();
			
            return books;

        } catch(SQLException e){
            throw new DataAccessException("Error retrieving books from database", e);
        } catch (Exception e) {
            e.printStackTrace();
        }

		return null;
    }

    @Override
    public List<Book> getUserBooks(int userId) throws DataAccessException {
        List<Book> books = new ArrayList<>();
        try {
            PreparedStatement pStmt = connection.prepareStatement("SELECT b.id, b.title, ub.status, b.isbn, b.authors, b.genres, b.page_count FROM user_books ub JOIN all_books b ON ub.book_id = b.id WHERE ub.user_id = ? ORDER BY b.title");
            pStmt.setInt(1, userId);
            ResultSet rs = pStmt.executeQuery();

            while(rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String status = rs.getString(3);
                String isbn = rs.getString(4);
                String rawAuthors = rs.getString(5);
                String rawGenres = rs.getString(6);
                int pageCount = rs.getInt(7);
                
                rawAuthors = rawAuthors == null ? "" : rawAuthors;
                rawGenres = rawGenres == null ? "" : rawGenres;


                List<Author> authors = Arrays.stream(rawAuthors.split(";"))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .map(Author::new)
                    .collect(Collectors.toList());

                // parse genres (with your hyphen/space handling)
                List<Book.Genre> genres = Arrays.stream(rawGenres.split(";"))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .map(s -> {
                        String key = s.replaceAll("[ \\-]", "_")
                                      .toUpperCase();
                        try {
                            return Book.Genre.valueOf(key);
                        } catch (IllegalArgumentException e) {
                            // skip unknown
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

                // construct full Book
                Book b = new Book(id, title, status, isbn, pageCount, authors, genres);
                books.add(b);
            }

            rs.close();
            pStmt.close();

            return books;

        } catch(SQLException e){
            throw new DataAccessException("Error retrieving user books from database", e);
        } catch (Exception e) {
            e.printStackTrace();
        }

		return null;
    }

    @Override
    public boolean addBookToUser(int userId, int bookId) throws DataAccessException {
        try {
            PreparedStatement pStmt = connection.prepareStatement("INSERT IGNORE INTO user_books (user_id, book_id, status) VALUES (?, ?, ?)");
            pStmt.setInt(1, userId);
            pStmt.setInt(2, bookId);
            pStmt.setString(3, "not started");
            int rowsAffected = pStmt.executeUpdate();
            pStmt.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new DataAccessException("Error adding book to user", e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateProgress(int userId, int bookId, String status) throws DataAccessException {
        try {
            PreparedStatement pStmt = connection.prepareStatement("INSERT INTO user_books (user_id, book_id, status) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE status = VALUES(status)");
            pStmt.setInt(1, userId);
            pStmt.setInt(2, bookId);
            pStmt.setString(3, status);
            int rowsAffected = pStmt.executeUpdate();
            pStmt.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new DataAccessException("Error updating book progress", e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
