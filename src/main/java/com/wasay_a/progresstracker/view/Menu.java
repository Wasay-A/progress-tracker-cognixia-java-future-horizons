package com.wasay_a.progresstracker.view;

import com.wasay_a.progresstracker.dao.Book;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final Scanner sc = new Scanner(System.in);

    public String promptMainOption() {
        System.out.println("\nWelcome to the books progress tracker! Please choose an option (1-3):");
        System.out.println("1) Login");
        System.out.println("2) Register");
        System.out.println("3) Exit");
        System.out.print("> ");
        return sc.nextLine().trim();
    }

    public String readUsername() {
        System.out.print("Username: ");
        return sc.nextLine().trim();
    }

    public String readPassword() {
        System.out.print("Password: ");
        return sc.nextLine().trim();
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }

    public String promptTrackerOption() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("1. View books");
        System.out.println("2. View books progress tracker");
        System.out.println("3. Update books progress tracker");
        System.out.println("4. Exit");
        System.out.print("\nPlease enter a number (1 - 4): ");
        return sc.nextLine().trim();
    }

    public void displayBooks(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("No books available at the moment.");
        } else {
            System.out.println("\nBooks available:");
            books.forEach(b -> {
                System.out.println(b);
                System.out.println();
            });
        }
    }

    public void displayUserBooks(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("You have no books in your progress tracker.");
        } else {
            System.out.println("\nYour books progress tracker:");
            books.forEach(System.out::println);
        }
    }

    public int promptBookId() {
        System.out.print("Enter the book ID to update progress: ");
        int id = Integer.parseInt(sc.nextLine().trim());
        return id;
    }

    public String promptStatus() {
        System.out.print("Enter new status ('not started', 'in progress', 'completed'): ");
        return sc.nextLine().trim();
    }

    public void close() {
        sc.close();
    }
}
