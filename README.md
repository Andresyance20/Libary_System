Project Overview
The Library System Management project is a desktop application designed to manage a library's resources. This system allows for the login of both administrator users and regular users, enabling them to check out and check in books, sort, and search for books by genre. The application uses Java and JavaFX for the user interface and PostgreSQL for the database management, incorporating around 12 medium and advanced SQL queries.

Features
User Authentication:

Administrator Login
Regular User Login
Book Management:

Check Out Books
Check In Books
Search Books by Genre
Sort Books
Advanced SQL Queries:

The system includes around 12 medium to advanced SQL queries for efficient data management and retrieval.
Technologies Used
Programming Language: Java
User Interface: JavaFX
Database: PostgreSQL
Getting Started
Prerequisites
Java Development Kit (JDK) 8 or higher
JavaFX SDK
PostgreSQL
Installation
Clone the Repository:


git clone https://github.com/yourusername/library-system-management.git
cd library-system-management
Set Up PostgreSQL Database:

Install PostgreSQL and create a new database.
Execute the SQL script provided in the database folder to set up the necessary tables and data.
sql

psql -U username -d your_database -f database/setup.sql
Configure Database Connection:

Update the database connection settings in the src/main/resources/db.properties file.
properties

db.url=jdbc:postgresql://localhost:5432/your_database
db.username=your_username
db.password=your_password
Build and Run the Application:

Use your preferred IDE (like IntelliJ IDEA or Eclipse) to open the project.
Build and run the application.
Project Structure
css

Database Schema
Users Table:

Stores information about administrators and regular users.
Books Table:

Stores information about books, including title, author, genre, and availability status.
SQL Queries
Medium and Advanced Queries:
Query to fetch all books by genre.
Query to sort books by title, author, or publication date.
Query to check out a book.
Query to check in a book.
Query to list all books currently checked out by a user.
...
Usage
Login:

Admin and regular users can log in using their credentials.
Book Checkout:

Users can select a book to check out from the available list.
Book Check-In:

Users can return a book they have checked out.
Search and Sort Books:

Users can search for books by genre and sort them based on different criteria.
Contributing
Fork the repository.
Create a new branch (git checkout -b feature/your-feature).
Commit your changes (git commit -am 'Add some feature').
Push to the branch (git push origin feature/your-feature).
Create a new Pull Request.
License
This project is licensed under the MIT License - see the LICENSE file for details.
