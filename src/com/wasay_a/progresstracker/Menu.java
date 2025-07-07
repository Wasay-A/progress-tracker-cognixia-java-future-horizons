package com.wasay_a.progresstracker;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.wasay_a.progresstracker.dao.Book;
import com.wasay_a.progresstracker.dao.BookDao;
import com.wasay_a.progresstracker.dao.BookDaoImpl;

public class Menu {
    // single scanner we will use through our program.
	private static Scanner sc;

	public static void mainMenu() {
		
		// once we enter menu, can initialize scanner
		sc = new Scanner(System.in);

		System.out.println("Welcome to the books progress tracker!");
		
		// used to exit loop
		boolean exit = false;
		
		while(!exit) {
			
			System.out.println("\nWhat would you like to do?");
			System.out.println("1. View books");
			System.out.println("2. View tracker");
			System.out.println("3. Update tracker");
			System.out.println("4. Exit");
			
			int input = sc.nextInt();
			sc.nextLine(); // will stop issue with an infinite loop w/ scanner (new line character can get stuck in buffer)
			
			switch (input) {
			
			case 1:
                BookDao bookDao = new BookDaoImpl();
		
                try {
                    bookDao.establishConnection();
                    
                    
                } catch (ClassNotFoundException | SQLException e1) {
                    
                    System.out.println("\nCould not connect to the Database, application cannot run at this time.");
                }
                
                List<Book> books = bookDao.getAll();
                System.out.println(books);
				break;
			case 2:
				System.out.println("TODO: view tracker");
				break;
			case 3:
				System.out.println("TODO: update tracker");
				break;
			case 4:
				exit = true;
				break;
			default:
				System.out.println("\nPlease enter an option listed (number 1 - 4)");
				break;
			}
			
		}
		
		System.out.println("\n\nGoodBye!");
		
		// once we exit, will close the scanner
		sc.close();
	}
}
