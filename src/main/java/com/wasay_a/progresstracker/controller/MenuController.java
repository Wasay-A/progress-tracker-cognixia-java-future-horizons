package com.wasay_a.progresstracker.controller;

import com.wasay_a.progresstracker.dao.*;
import com.wasay_a.progresstracker.exception.AuthenticationException;
import com.wasay_a.progresstracker.exception.DataAccessException;
import com.wasay_a.progresstracker.view.Menu;

import java.sql.SQLException;
import java.util.List;

public class MenuController {
    private final UserDao   userDao;
    private final BookDao   bookDao;
    private final Menu view;

    public MenuController(UserDao u, BookDao b, Menu v) {
        userDao = u;
        bookDao = b;
        view    = v;
    }

    public void start() {
        int userId = handleLoginRegister();
        if (userId < 0) {
            view.showMessage("Goodbye!");
            view.close();
            return;
        }
        runTrackerLoop(userId);
        view.close();
    }

    private int handleLoginRegister() {
        while (true) {
            String choice = view.promptMainOption();
            try {
                if (choice.equals("1")) {
                    String u = view.readUsername();
                    String p = view.readPassword();
                    userDao.establishConnection();
                    int id = userDao.login(u, p);
                    view.showMessage("Welcome, " + u + "!");
                    return id;
                }
                else if (choice.equals("2")) {
                    String u = view.readUsername();
                    String p = view.readPassword();
                    userDao.establishConnection();
                    userDao.register(u, p);
                    view.showMessage("Registration successful! You can now log in.");
                }
                else if (choice.equals("3")) {
                    return -1;
                }
                else {
                    view.showMessage("Invalid choice, please enter 1, 2, or 3.");
                }
            } catch (AuthenticationException ae) {
                view.showMessage("Error: " + ae.getMessage());
            } catch (DataAccessException de) {
                view.showMessage("Database error: " + de.getMessage());
                return -1;
            } catch (SQLException | ClassNotFoundException dbEx) {
                view.showMessage("DB connection error: " + dbEx.getMessage());
                return -1;
            }
        }
    }

    private void runTrackerLoop(int userId) {
        boolean exit = false;
        while (!exit) {
            String cmd = view.promptTrackerOption();
            try {
                bookDao.establishConnection();
                switch (cmd) {
                    case "1":  // View all books
                        List<Book> all = bookDao.getAll();
                        view.displayBooks(all);
                        break;
                    case "2":  // View user progress
                        List<Book> mine = bookDao.getUserBooks(userId);
                        view.displayUserBooks(mine);
                        break;
                    case "3":  // Update progress
                        int bid = view.promptBookId();
                        String status = view.promptStatus();

                        List<String> allowed = List.of("not started", "in progress", "completed");
                        if (!allowed.contains(status.toLowerCase())) {
                            view.showMessage("Invalid status. Please enter exactly one of: "
                                + String.join(", ", allowed));
                            break;  // back to menu
                        }

                        try {
                            bookDao.addBookToUser(userId, bid);
                            boolean updated = bookDao.updateProgress(userId, bid, status);
                            view.showMessage(updated
                                ? "Progress updated successfully."
                                : "Failed to update progress. Please check the book ID.");
                        } catch (DataAccessException dae) {
                            view.showMessage("Database error: " + dae.getMessage());
                        }
                        break;
                    case "4":
                        exit = true;
                        break;
                    default:
                        view.showMessage("Invalid input. Please enter 1-4.");
                }
            } catch (DataAccessException dae) {
                view.showMessage("Database error: " + dae.getMessage());
            } catch (SQLException | ClassNotFoundException ex) {
                view.showMessage("DB connection error: " + ex.getMessage());
                exit = true;
            }
        }
        // cleanup
        try { 
            bookDao.closeConnection(); 
        } catch (SQLException | DataAccessException ignore) { }
        try { 
            userDao.closeConnection(); 
        } catch (SQLException | DataAccessException ignore) { }
        view.showMessage("\nGoodbye!");
    }
}
