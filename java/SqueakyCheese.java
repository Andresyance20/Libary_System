import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import ReferenceFiles.Books;
//import ReferenceFiles.BooksExtractor;
import ReferenceFiles.Employees;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class SqueakyCheese {
    // Typically, the data for these fields is stored in a properties file
    // I just hard-coded the information here for simplicity
    // You may need to change the Strings to match what your database credentials are

    // Where my database is (server + database name + schema)
    private static final String DATABASE_URL = "jdbc:postgresql://marigold.csse.uwplatt.edu:5432/rognsvoogr?CurrentSchema=lib_db_project";

    // What user I am connecting as
    private static final String DATABASE_USER = "rognsvoogr";

    // Password for the user
    private static final String DATABASE_PASSWORD = "PLT157933324";

    // What driver I am using
    // Since out database is postgres we will use the postgres driver
    // For other databases (SQLServer, Oracle, MySQL) another driver would be used
    private static final String DATABASE_DRIVER = "org.postgresql.Driver";


    // Class for the driver manager to manage the JDBC connection and interface with the driver
    private DriverManagerDataSource dataSource;

    // Class to provide methods to execute commands on the database
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    // Constructor to initialize the data source and namedParameterJdbcTemplate
    public SqueakyCheese() {
        dataSource = new DriverManagerDataSource();

        dataSource.setUrl(DATABASE_URL);
        dataSource.setUsername(DATABASE_USER);
        dataSource.setPassword(DATABASE_PASSWORD);
        dataSource.setDriverClassName(DATABASE_DRIVER);

        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public int addBookToDatabase(String isbn, Integer authorId, String title, Integer genreId, String bookLocation, Integer totalCopies) {
        try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {
            conn.setAutoCommit(false);
            String getMaxBookId = "SELECT MAX(book_id) FROM book_info";
            try (PreparedStatement pstmt1 = conn.prepareStatement(getMaxBookId)) {
                ResultSet rs = pstmt1.executeQuery();
                int bookId = 1;        // Default value if no rows in table
                if (rs.next()) {
                    bookId = rs.getInt(1) + 1;    // Increment by 1 to generate new ID
                    System.out.println(bookId);
                }
                // Insert book information
                String insertBookInfo =
                        "INSERT INTO book_info (book_id, isbn, author_id, title, genre_id, book_location, available_copies, total_copies) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement pstmt2 = conn.prepareStatement(insertBookInfo)) {
                    pstmt2.setInt(1, bookId);
                    pstmt2.setString(2, isbn);
                    pstmt2.setInt(3, authorId);
                    pstmt2.setString(4, title);
                    pstmt2.setInt(5, genreId);
                    pstmt2.setString(6, bookLocation);
                    pstmt2.setInt(7, totalCopies);
                    pstmt2.setInt(8, totalCopies);
                    int affectedRows = pstmt2.executeUpdate();
                    if (affectedRows == 0) {
                        throw new
                                SQLException("Insert book info failed, no rows affected.");
                    }
                }
            }
            conn.commit();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    // this verifies the username and password are correct, and returns if the password matches.
    public boolean login(String username, String password) {
        String query = "SELECT * FROM member_logins WHERE username = ? AND password = ?";
        try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String dbPassword = rs.getString("password");
                System.out.println("Password Verification SUCCESS");
                return dbPassword.equals(password);
            }
            return false; // if the login does not equal password, return false
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false; // if an exception occurs, automatically return false
        }

    }

    // this gets the "isadmin" to return true or false based on the account.
    public boolean admin(String username, String password) {
        String query = "SELECT isAdmin FROM member_logins WHERE username = ? AND password = ?";
        try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Boolean dbAdmin = rs.getBoolean("isAdmin");
                System.out.println("Admin Processing SUCCESS");
                return dbAdmin.booleanValue();
            }
            return false; // if the login does not equal password, return false
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false; // if an exception occurs, automatically return false
        }
    }
}
