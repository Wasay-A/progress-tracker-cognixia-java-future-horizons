# Progress Tracker - Cognixia Java Future Horizons

This program is a simple console-based Java application to track each user’s reading progress across a curated list of books. This program was built using JDBC, MVC + DAO patterns, and MySQL for persistence.

This was a capstone project given by Cognixia upon the completion of a 4 week training program consisting of Linux, Java, SQL, JDBC, and Gen AI. Thank you Cognixia team!
Credit and code attribution given to Cognixia team for providing various examples of code usage through notes and assignments!



## Prerequisites

1. **Java 11+** installed  
2. **MySQL** running locally (or adjust JDBC URL)  
3. **Maven** installed  



## Features

- **User authentication**  
  - Register with a unique username & password  
  - Login to your personal tracker  

- **View all books**  
  - See title, ISBN, page count, author(s), genre(s)  

- **Personal progress tracker**  
  - View your own “not started” / “in progress” / “completed” status per book  
  - Update your status on any book
 


## Tech Stack

- **Language:** Java 11  
- **Database:** MySQL
- **Build & Dependency Management:** Maven  
- **Patterns:** MVC for UI flow, DAO for data access  
- **JDBC Driver:** `mysql-connector-java`  



## Getting Started

### 1. Clone the repo

In bash:
git clone https://github.com/<your-username>/progress-tracker-cognixia-java-future-horizons.git

For further assistance on cloning a repo on github, please refer to: https://docs.github.com/en/repositories/creating-and-managing-repositories/cloning-a-repository

### 2. Open in Visual Studio Code

### 3. Log into MySQLWorkbench and run the provided 'books_progress_tracker_db.sql' SQL script

### 4. Configure your credentials

Edit src/main/resources/config.properties:
url=jdbc:mysql://localhost:3306/books_progress_tracker_db
username=YourMySQLUsername
password=YourMySQLPassword

Edit ConnectionManager.java:
private static final String URL = "jdbc:mysql://localhost:3306/books_progress_tracker_db";
private static final String USERNAME = "YourMySQLUsername";
private static final String PASSWORD = "YourMySQLPassword";

### 5. Build and run
