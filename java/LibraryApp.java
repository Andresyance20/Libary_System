import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.stage.Stage;


import java.sql.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class LibraryApp extends Application {

    SqueakyCheese database = new SqueakyCheese();

    private Stage primaryStage;
    private Stage secondaryStage;
    private Stage teritiaryStage;
    private BorderPane mainLayout;

    private static final String DATABASE_URL = "jdbc:postgresql://marigold.csse.uwplatt.edu:5432/rognsvoogr?CurrentSchema=lib_db_project";
    private static final String DATABASE_USER = "rognsvoogr";
    private static final String DATABASE_PASSWORD = "PLT157933324";
    private static final String DATABASE_DRIVER = "org.postgresql.Driver";

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Login");

        // Create the login form
        GridPane loginForm = new GridPane();
        loginForm.setPadding(new Insets(10));
        loginForm.setHgap(10);
        loginForm.setVgap(10);


        // Add form fields
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");

        // Load the image
        Image logo_image = new Image(getClass().getResourceAsStream("/images/SqueakyCheeseLogo.png"));
        //Image backgroundImage = new Image("images/R.jpg)");

        String imagePath = getClass().getResource("/images/R.jpg").toExternalForm();
        loginForm.setStyle("-fx-background-image: url('" + imagePath + "');");



        // Create the image view
        ImageView imageView = new ImageView(logo_image);
        imageView.setFitWidth(250);
        imageView.setFitHeight(150);

        // Add form fields to the grid
        loginForm.add(imageView, 0, 0, 2 ,1); // Add the image to the top row
        loginForm.add(usernameLabel, 0, 1);
        loginForm.add(usernameField, 1, 1);
        loginForm.add(passwordLabel, 0, 2);
        loginForm.add(passwordField, 1, 2);
        loginForm.add(loginButton, 1, 3);
        //loginForm.setBackground(new Background(new BackgroundFill(Color.YELLOW,null,null)));

        // Create the login scene
        Scene loginScene = new Scene(loginForm, 280, 270);


        // Add event handler for the login button
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            SqueakyCheese database = new SqueakyCheese();
            boolean loginans = database.login(username, password);
            boolean adminans = database.admin(username, password);

            if(loginans && adminans) {
                usernameField.clear();
                passwordField.clear();
                System.out.println("Main Admin Program Launched!");
                launchAdminMainApp();
            } else if(loginans && !adminans) {
                usernameField.clear();
                passwordField.clear();
                System.out.println("User Program Launched!");
                launchUserMainApp();
            } else {
                // Do something when the login fails
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Authentication Failed");
                alert.setHeaderText(null);
                alert.setContentText("Invalid username or password.");
                alert.showAndWait();
            }
        });

        // Show the login screen
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    // Method to launch the main application
    public void launchAdminMainApp() {

        primaryStage.close();
        secondaryStage = new Stage();
        secondaryStage.setTitle("Library Admin Access");

        String imagePath = getClass().getResource("/images/L.jpg").toExternalForm();

        // Create the main layout for the secondary stage





        // Create the menu bar
        MenuBar menuBar = new MenuBar();

        // Create the "Home" menu button
        Menu homeMenu = new Menu("Home");
        MenuItem goHome = new MenuItem("Go to Home");
        goHome.setOnAction(event -> {
            // Clear all information on the screen
            mainLayout.setCenter(null);
            // Home Menu Information
            Label infoLabel = new Label("Welcome to the Squeaky Cheese LMS Admin View!\n\nNavigate with the menu options at the top of the screen.");
            infoLabel.setFont(Font.font("Arial",20));
            mainLayout.setCenter(infoLabel);

            // Load Image

        });
        homeMenu.getItems().add(goHome);
        // *** ON CLICK SHOULD BRING USER HOME ***

        // Create the "Users" menu and menu items
        Menu usersMenu = new Menu("Users");
        MenuItem addUserMenuItem = new MenuItem("Add User"); // This is the section to add a new user to the database.
        addUserMenuItem.setOnAction(event -> {
            mainLayout.setCenter(null);

            // Create the form fields
            Label titleInfo = new Label("Add a new user to the database:");
            Label f_nameLabel  = new Label("First Name:");
            TextField f_nameField = new TextField();
            Label l_nameLabel = new Label("Last Name:");
            TextField l_nameField = new TextField();
            Label emailLabel = new Label("Email:");
            TextField emailField = new TextField();
            Label phoneLabel = new Label("Phone:");
            TextField phoneField = new TextField();
            Label usernameLabel = new Label("Username:");
            TextField usernameField = new TextField();
            Label passwordLabel = new Label("Password:");
            TextField passwordField = new TextField();
            Label isAdminLabel = new Label("Is user an Admin?:");
            CheckBox isAdminCheckBox = new CheckBox(" ");
            //boolean isAdminBool = isAdminCheckBox.isSelected();

            Button submitButton = new Button("Submit");

            // Create the form layout
            GridPane addUser = new GridPane();
            addUser.setPadding(new Insets(10));
            addUser.setHgap(10);
            addUser.setVgap(10);
            // Add the form fields to the grid pane
            addUser.add(titleInfo, 0, 0, 2, 1);
            addUser.add(f_nameLabel, 0, 1);
            addUser.add(f_nameField, 1, 1);
            addUser.add(l_nameLabel, 0, 2);
            addUser.add(l_nameField, 1, 2);
            addUser.add(emailLabel, 0, 3);
            addUser.add(emailField, 1, 3);
            addUser.add(phoneLabel, 0, 4);
            addUser.add(phoneField, 1, 4);
            addUser.add(usernameLabel, 0, 5);
            addUser.add(usernameField, 1, 5);
            addUser.add(passwordLabel, 0, 6);
            addUser.add(passwordField, 1, 6);
            addUser.add(isAdminLabel, 0, 7);
            addUser.add(isAdminCheckBox, 1, 7);

            // Add the submit button
            addUser.add(submitButton, 1, 8);

            // Submit button actions
            submitButton.setOnAction(addUserEvent -> {

                // Validate first name
                String firstName = f_nameField.getText();
                if (!firstName.matches("[a-zA-Z]+")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid Input");
                    alert.setHeaderText(null);
                    alert.setContentText("First name can only contain alphabetic characters.");
                    alert.showAndWait();
                    f_nameField.clear();
                    return; // Stop execution and show error message
                }

                // Validate last name
                String lastName = l_nameField.getText();
                if (!lastName.matches("[a-zA-Z]+")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid Input");
                    alert.setHeaderText(null);
                    alert.setContentText("Last name can only contain alphabetic characters.");
                    alert.showAndWait();
                    l_nameField.clear();
                    return; // Stop execution and show error message
                }

                // Validate email
                String email = emailField.getText();
                if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid Input");
                    alert.setHeaderText(null);
                    alert.setContentText("Email is not in a valid format.");
                    alert.showAndWait();
                    emailField.clear();
                    return; // Stop execution and show error message
                }

                // Validate phone number
                String phone = phoneField.getText();
                if (!phone.matches("\\d{3}-?\\d{3}-?\\d{4}")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid Input");
                    alert.setHeaderText(null);
                    alert.setContentText("Phone number can only contain digits and optional dashes.");
                    alert.showAndWait();
                    phoneField.clear();
                    return; // Stop execution and show error message
                }

                // If all input is valid, proceed with database insertion
                try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {
                    conn.setAutoCommit(false);
                    String getMaxMemberId = "SELECT MAX(member_id) FROM member_info";
                    try (PreparedStatement pstmt1 = conn.prepareStatement(getMaxMemberId)) {
                        ResultSet rs = pstmt1.executeQuery();
                        int memberId = 1; // Default value if no rows in table
                        if (rs.next()) {
                            memberId = rs.getInt(1) + 1; // Increment by 1 to generate new ID
                            System.out.println(memberId);
                        }
                        // Insert member information
                        String insertMemberInfo = "INSERT INTO member_info (member_id, fname, lname, email, phone_number) VALUES (?, ?, ?, ?, ?)";
                        try (PreparedStatement pstmt2 = conn.prepareStatement(insertMemberInfo)) {
                            pstmt2.setInt(1,memberId);
                            pstmt2.setString(2, f_nameField.getText());
                            pstmt2.setString(3, l_nameField.getText());
                            pstmt2.setString(4, emailField.getText());
                            pstmt2.setString(5, phoneField.getText());
                            int affectedRows = pstmt2.executeUpdate();
                            if (affectedRows == 0) {
                                throw new SQLException("Insert member info failed, no rows affected.");
                            }
                            // Insert member login information
                            String insertMemberLogin = "INSERT INTO member_logins (member_id, username, password, isadmin) VALUES (?, ?, ?, ?)";
                            try (PreparedStatement pstmt3 = conn.prepareStatement(insertMemberLogin)) {
                                pstmt3.setInt(1, memberId);
                                pstmt3.setString(2, usernameField.getText());
                                pstmt3.setString(3, passwordField.getText());
                                pstmt3.setBoolean(4, isAdminCheckBox.isSelected());
                                pstmt3.executeUpdate();
                            }

                        }
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success!");
                        alert.setHeaderText(null);
                        alert.setContentText("Record insertion complete.");
                        alert.showAndWait();
                    }
                    conn.commit();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                // Clear the form fields
            f_nameField.clear();
            l_nameField.clear();
            emailField.clear();
            phoneField.clear();
            usernameField.clear();
            passwordField.clear();
            isAdminCheckBox.setSelected(false);

            });

            // Show the form on the main screen
            mainLayout.setCenter(addUser);


        });
        MenuItem viewUsersMenuItem = new MenuItem("Search Users"); // This is the section for searching for users in the database
        viewUsersMenuItem.setOnAction(event ->{
            mainLayout.setCenter(null);

            // Create the form fields
            Label titleInfo = new Label("Search for a user:");
            TextField searchKeywordField = new TextField();
            Button searchButton = new Button("Search");

            GridPane Search = new GridPane();
            Search.setPadding(new Insets(10));
            Search.setHgap(10);
            Search.setVgap(10);
            Search.add(titleInfo,0,0,2,1);
            Search.add(searchKeywordField, 0, 1);
            Search.add(searchButton, 0, 2);

            searchButton.setOnAction(searchEvent -> {
                String searchKeyword = searchKeywordField.getText(); // Replace with the keyword to search for

                String searchQuery = "SELECT * FROM member_info INNER JOIN member_logins ON member_info.member_id = member_logins.member_id WHERE CAST(member_info.member_id AS TEXT) = ? OR fname LIKE ? OR lname LIKE ? OR email LIKE ? OR phone_number LIKE ?";
                try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
                     PreparedStatement pstmt = conn.prepareStatement(searchQuery)) {
                    pstmt.setString(1, "%" + searchKeyword + "%");
                    pstmt.setString(2, "%" + searchKeyword + "%");
                    pstmt.setString(3, "%" + searchKeyword + "%");
                    pstmt.setString(4, "%" + searchKeyword + "%");
                    pstmt.setString(5, "%" + searchKeyword + "%");
                    ResultSet rs = pstmt.executeQuery();

                    List<Member> searchResults = new ArrayList<>();
                    while (rs.next()) {
                        int memberId = rs.getInt("member_id");
                        String fname = rs.getString("fname");
                        String lname = rs.getString("lname");
                        String email = rs.getString("email");
                        String phoneNumber = rs.getString("phone_number");
                        String username = rs.getString("username");
                        String password = rs.getString("password");
                        searchResults.add(new Member(memberId, fname, lname, email, phoneNumber, username, password));
                    }

                    TableView<Member> tableView = new TableView<>();
                    TableColumn<Member, Integer> idColumn = new TableColumn<>("ID");
                    TableColumn<Member, String> firstNameColumn = new TableColumn<>("First Name");
                    TableColumn<Member, String> lastNameColumn = new TableColumn<>("Last Name");
                    TableColumn<Member, String> emailColumn = new TableColumn<>("Email");
                    TableColumn<Member, String> phoneColumn = new TableColumn<>("Phone");
                    TableColumn<Member, String> usernameColumn = new TableColumn<>("Username");
                    TableColumn<Member, String> passwordColumn = new TableColumn<>("Password");

                    idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
                    firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
                    lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
                    emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
                    phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
                    usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
                    passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

                    tableView.getColumns().addAll(idColumn, firstNameColumn, lastNameColumn, emailColumn, phoneColumn, usernameColumn, passwordColumn);

                    tableView.getItems().setAll(searchResults);
                    Search.add(tableView, 0, 3);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });

            // show the Search screen
            mainLayout.setCenter(Search);
        });




        MenuItem removeUserMenuItem = new MenuItem("Remove User"); // This is the section for deleting users from the database
        removeUserMenuItem.setOnAction(event -> {
            mainLayout.setCenter(null);

            // Create the form fields
            Label titleInfo = new Label("Search for a user to delete from the database:");
            TextField searchKeywordField = new TextField();
            Button searchButton = new Button("Search");
            Button deleteButton = new Button ("Delete");
            Label del_text = new Label("ID to delete:");
            TextField id_for_del = new TextField();

            GridPane Delete = new GridPane();
            Delete.setPadding(new Insets(10));
            Delete.setHgap(10);
            Delete.setVgap(10);
            Delete.add(titleInfo,0,0,3,1);
            Delete.add(searchKeywordField, 0, 1);
            Delete.add(searchButton, 0, 2);
            Delete.add(deleteButton,0,3);
            Delete.add(del_text,1,3);
            Delete.add(id_for_del,2,3);

            searchButton.setOnAction(searchEvent -> {
                String searchKeyword = searchKeywordField.getText(); // Replace with the keyword to search for

                String searchQuery = "SELECT * FROM member_info INNER JOIN member_logins ON member_info.member_id = member_logins.member_id WHERE CAST(member_info.member_id AS TEXT) = ? OR fname LIKE ? OR lname LIKE ? OR email LIKE ? OR phone_number LIKE ?";
                try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
                     PreparedStatement pstmt = conn.prepareStatement(searchQuery)) {
                    pstmt.setString(1, "%" + searchKeyword + "%");
                    pstmt.setString(2, "%" + searchKeyword + "%");
                    pstmt.setString(3, "%" + searchKeyword + "%");
                    pstmt.setString(4, "%" + searchKeyword + "%");
                    pstmt.setString(5, "%" + searchKeyword + "%");
                    ResultSet rs = pstmt.executeQuery();

                    List<Member> searchResults = new ArrayList<>();
                    while (rs.next()) {
                        int memberId = rs.getInt("member_id");
                        String fname = rs.getString("fname");
                        String lname = rs.getString("lname");
                        String email = rs.getString("email");
                        String phoneNumber = rs.getString("phone_number");
                        String username = rs.getString("username");
                        String password = rs.getString("password");
                        searchResults.add(new Member(memberId, fname, lname, email, phoneNumber, username, password));
                    }

                    TableView<Member> tableView = new TableView<>();
                    TableColumn<Member, Integer> idColumn = new TableColumn<>("ID");
                    TableColumn<Member, String> firstNameColumn = new TableColumn<>("First Name");
                    TableColumn<Member, String> lastNameColumn = new TableColumn<>("Last Name");
                    TableColumn<Member, String> emailColumn = new TableColumn<>("Email");
                    TableColumn<Member, String> phoneColumn = new TableColumn<>("Phone");
                    TableColumn<Member, String> usernameColumn = new TableColumn<>("Username");
                    TableColumn<Member, String> passwordColumn = new TableColumn<>("Password");

                    idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
                    firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
                    lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
                    emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
                    phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
                    usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
                    passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

                    tableView.getColumns().addAll(idColumn, firstNameColumn, lastNameColumn, emailColumn, phoneColumn, usernameColumn, passwordColumn);

                    tableView.getItems().setAll(searchResults);
                    Delete.add(tableView, 0, 4,3,1);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }



            });

            deleteButton.setOnAction(delEvent -> {
                String hold_id = id_for_del.getText();
                int id_to_del = Integer.valueOf(hold_id);


                String findQuery = "SELECT * FROM member_info WHERE member_id = ?";
                try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
                     PreparedStatement pstmt = conn.prepareStatement(findQuery)) {
                    pstmt.setInt(1, id_to_del);
                    ResultSet rs = pstmt.executeQuery();
                    if (rs.next()) {
                        int id = rs.getInt("member_id");
                        String firstName = rs.getString("fname");
                        String lastName = rs.getString("lname");
                        String email = rs.getString("email");
                        String phoneNumber = rs.getString("phone_number");

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Warning");
                        alert.setHeaderText(null);
                        alert.setContentText("Are you sure you want to proceed?\nThis action cannot be undone.\n\n ID: "+ id_to_del +"\n First Name: "+ firstName +"\n Last Name: "+ lastName);
                        Optional<ButtonType> result = alert.showAndWait();


                        if (result.isPresent() && result.get() == ButtonType.OK) {
                            String deleteLoginsQuery = "DELETE FROM member_logins WHERE member_id = ?";
                            String deleteInfoQuery = "DELETE FROM member_info WHERE member_id = ?";
                            try (Connection DEL_conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
                                 PreparedStatement DEL_pstmt1 = DEL_conn.prepareStatement(deleteLoginsQuery);
                                 PreparedStatement DEL_pstmt2 = DEL_conn.prepareStatement(deleteInfoQuery)) {
                                DEL_pstmt1.setInt(1, id_to_del);
                                DEL_pstmt2.setInt(1, id_to_del);
                                int rowsAffected1 = DEL_pstmt1.executeUpdate();
                                int rowsAffected2 = DEL_pstmt2.executeUpdate();
                                if (rowsAffected1 > 0 && rowsAffected2 > 0) {
                                    System.out.println("Deletion success");
                                }
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            System.out.println("Deletion canceled");
                        }

                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });

            // show the Search screen
            mainLayout.setCenter(Delete);

        });


        usersMenu.getItems().addAll(addUserMenuItem, viewUsersMenuItem, removeUserMenuItem);




        // Create the "Books" menu and menu items
        Menu booksMenu = new Menu("Books");
        MenuItem addBookMenuItem = new MenuItem("Add Book");
        addBookMenuItem.setOnAction(event -> {
            mainLayout.setCenter(null);

            // Create the form fields
            Label titleInfo = new Label("Add a book to the database:");
            Label isbnLabel = new Label("ISBN:");
            TextField isbnField = new TextField();
            Label authorIdLabel = new Label("Author ID:");
            TextField authorIdField = new TextField();
            Label titleLabel = new Label("Title:");
            TextField titleField = new TextField();
            Label genreIdLabel = new Label("Genre ID:");
            TextField genreIdField = new TextField();
            Label bookLocationLabel = new Label("Book Location:");
            TextField bookLocationField = new TextField();
            Label totalCopiesLabel = new Label("Total Copies");
            TextField totalCopiesField = new TextField();
            Button submitButton = new Button("Submit");

            // Create the form layout
            GridPane addBookForm = new GridPane();
            addBookForm.setPadding(new Insets(10));
            addBookForm.setHgap(10);
            addBookForm.setVgap(10);
            addBookForm.add(titleInfo,0,0);
            addBookForm.add(isbnLabel, 0, 1);
            addBookForm.add(isbnField, 1, 1);
            addBookForm.add(authorIdLabel, 0, 2);
            addBookForm.add(authorIdField, 1, 2);
            addBookForm.add(titleLabel, 0, 3);
            addBookForm.add(titleField, 1, 3);
            addBookForm.add(genreIdLabel, 0, 4);
            addBookForm.add(genreIdField, 1, 4);
            addBookForm.add(bookLocationLabel, 0, 5);
            addBookForm.add(bookLocationField, 1, 5);
            addBookForm.add(totalCopiesLabel,0,6);
            addBookForm.add(totalCopiesField,1,6);
            addBookForm.add(submitButton, 1, 7);

            // Add event handler for the "Submit" button
            submitButton.setOnAction(addBooksEvent -> {

                // Get the book details from the form fields
                String isbn = isbnField.getText();
                String title = titleField.getText();
                Integer authorId = null;
                Integer genreId = null;
                String bookLocation = bookLocationField.getText();
                Integer totalCopies = null;
                try {
                    authorId = Integer.parseInt(authorIdField.getText());
                    genreId = Integer.parseInt(genreIdField.getText());
                    totalCopies = Integer.parseInt(totalCopiesField.getText());
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter valid numbers for Author ID, Genre ID, and Total Copies.");
                    alert.showAndWait();
                    return;
                }

                // Add the book to the database
                database.addBookToDatabase(isbn, authorId, title, genreId, bookLocation, totalCopies);

                // Clear the form fields
                isbnField.clear();
                authorIdField.clear();
                titleField.clear();
                genreIdField.clear();
                bookLocationField.clear();
                totalCopiesField.clear();
            });

            // Show the form on the main screen
            mainLayout.setCenter(addBookForm);

        });

        MenuItem viewBooksMenuItem = new MenuItem("Search Books");
        viewBooksMenuItem.setOnAction(event -> {
            mainLayout.setCenter(null);

            // Create the form field
            Label titleInfo = new Label("Search for a book:");
            TextField searchBookKeywordField = new TextField();
            Button searchButton = new Button("Search");

            GridPane Search = new GridPane();
            Search.setPadding(new Insets(10));
            Search.setHgap(10);
            Search.setVgap(10);
            Search.add(titleInfo,0,0,2,1);
            Search.add(searchBookKeywordField, 0, 1);
            Search.add(searchButton, 0, 2);

            searchButton.setOnAction(searchEvent -> {
                String searchKeyword = searchBookKeywordField.getText(); // Replace with the keyword to search for

                String searchQuery = "SELECT * FROM book_info INNER JOIN authors ON book_info.author_id = authors.author_id INNER JOIN genres ON book_info.genre_id = genres.genre_id WHERE CAST(book_info.book_id AS TEXT) = ? OR isbn LIKE ? OR CAST(book_info.author_id AS TEXT) = ? OR title LIKE ? OR CAST(book_info.genre_id AS TEXT) = ? OR book_location LIKE ?";
                try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
                     PreparedStatement pstmt = conn.prepareStatement(searchQuery)) {
                    pstmt.setString(1, "%" + searchKeyword + "%");
                    pstmt.setString(2, "%" + searchKeyword + "%");
                    pstmt.setString(3, "%" + searchKeyword + "%");
                    pstmt.setString(4, "%" + searchKeyword + "%");
                    pstmt.setString(5, "%" + searchKeyword + "%");
                    pstmt.setString(6, "%" + searchKeyword + "%");
                    ResultSet rs = pstmt.executeQuery();

                    List<Book> searchResults = new ArrayList<>();
                    while (rs.next()) {
                        Integer bookId = rs.getInt("book_id");
                        String isbn = rs.getString("isbn");
                        Integer authorId = rs.getInt("author_id");
                        String authorFname = rs.getString("author_fname");
                        String authorLname = rs.getString("author_lname");
                        String title = rs.getString("title");
                        Integer genreId = rs.getInt("genre_id");
                        String genre = rs.getString("genre_name");
                        String bookLocation = rs.getString("book_location");
                        Integer totalCopies = rs.getInt("total_copies");
                        Integer availableCopies = rs.getInt("available_copies");
                        searchResults.add(new Book(bookId, isbn, authorId, authorFname, authorLname, title, genreId, genre, bookLocation, totalCopies, availableCopies));
                    }

                    TableView<Book> tableView = new TableView<>();
                    TableColumn<Book, Integer> idColumn = new TableColumn<>("ID");
                    TableColumn<Book, String> isbnColumn = new TableColumn<>("ISBN");
                    TableColumn<Book, Integer> authorIdColumn = new TableColumn<>("Author ID");
                    TableColumn<Book, String> authorFnameColumn = new TableColumn<>("Author First Name");
                    TableColumn<Book, String> authorLnameColumn = new TableColumn<>("Author Last Name");
                    TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
                    TableColumn<Book, Integer> genreIdColumn = new TableColumn<>("Genre ID");
                    TableColumn<Book, String> genreColumn = new TableColumn<>("Genre Name");
                    TableColumn<Book, String> bookLocationColumn = new TableColumn<>("Book Location");
                    TableColumn<Book, Integer> totalCopiesColumn = new TableColumn<>("Total Copies");
                    TableColumn<Book, Integer> availableCopiesColumn = new TableColumn<>("Available Copies");

                    idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
                    isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
                    authorIdColumn.setCellValueFactory(new PropertyValueFactory<>("authorId"));
                    authorFnameColumn.setCellValueFactory(new PropertyValueFactory<>("authorFname"));
                    authorLnameColumn.setCellValueFactory(new PropertyValueFactory<>("authorLname"));
                    titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
                    genreIdColumn.setCellValueFactory(new PropertyValueFactory<>("genreId"));
                    genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
                    bookLocationColumn.setCellValueFactory(new PropertyValueFactory<>("bookLocation"));
                    totalCopiesColumn.setCellValueFactory(new PropertyValueFactory<>("totalCopies"));
                    availableCopiesColumn.setCellValueFactory(new PropertyValueFactory<>("availableCopies"));

                    tableView.getColumns().addAll(idColumn, isbnColumn, authorIdColumn, authorFnameColumn, authorLnameColumn, titleColumn, genreIdColumn, genreColumn, bookLocationColumn, totalCopiesColumn, availableCopiesColumn);

                    tableView.getItems().setAll(searchResults);
                    Search.add(tableView, 0, 3);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });

            // show the Search screen
            mainLayout.setCenter(Search);
        });

        MenuItem removeBookMenuItem = new MenuItem("Remove Book");
        removeBookMenuItem.setOnAction(event -> {
            mainLayout.setCenter(null);


            Label titleInfo = new Label("Search for a book to delete from the database:");
            TextField searchKeywordField = new TextField();
            Button searchButton = new Button("Search");
            Button deleteButton = new Button("Delete");
            Label del_text = new Label("ID to delete:");
            TextField id_for_del = new TextField();

            GridPane Delete = new GridPane();
            Delete.setPadding(new Insets(10));
            Delete.setHgap(10);
            Delete.setVgap(10);
            Delete.add(titleInfo, 0, 0, 3, 1);
            Delete.add(searchKeywordField, 0, 1);
            Delete.add(searchButton, 0, 2);
            Delete.add(deleteButton, 0, 3);
            Delete.add(del_text, 1, 3);
            Delete.add(id_for_del, 2, 3);



            searchButton.setOnAction(searchEvent -> {
                String searchKeyword = searchKeywordField.getText(); // Replace with the keyword to search for

                String searchQuery = "SELECT * FROM book_info INNER JOIN authors ON book_info.author_id = authors.author_id INNER JOIN genres ON book_info.genre_id = genres.genre_id WHERE CAST(book_info.book_id AS TEXT) = ? OR isbn LIKE ? OR CAST(book_info.author_id AS TEXT) = ? OR title LIKE ? OR CAST(book_info.genre_id AS TEXT) = ? OR book_location LIKE ?";
                try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
                     PreparedStatement pstmt = conn.prepareStatement(searchQuery)) {
                    pstmt.setString(1, "%" + searchKeyword + "%");
                    pstmt.setString(2, "%" + searchKeyword + "%");
                    pstmt.setString(3, "%" + searchKeyword + "%");
                    pstmt.setString(4, "%" + searchKeyword + "%");
                    pstmt.setString(5, "%" + searchKeyword + "%");
                    pstmt.setString(6, "%" + searchKeyword + "%");
                    ResultSet rs = pstmt.executeQuery();

                    List<Book> searchResults = new ArrayList<>();
                    while (rs.next()) {
                        Integer bookId = rs.getInt("book_id");
                        String isbn = rs.getString("isbn");
                        Integer authorId = rs.getInt("author_id");
                        String authorFname = rs.getString("author_fname");
                        String authorLname = rs.getString("author_lname");
                        String title = rs.getString("title");
                        Integer genreId = rs.getInt("genre_id");
                        String genre = rs.getString("genre_name");
                        String bookLocation = rs.getString("book_location");
                        Integer totalCopies = rs.getInt("total_copies");
                        Integer availableCopies = rs.getInt("available_copies");
                        searchResults.add(new Book(bookId, isbn, authorId, authorFname, authorLname, title, genreId, genre, bookLocation, totalCopies, availableCopies));
                    }

                    TableView<Book> tableView = new TableView<>();
                    TableColumn<Book, Integer> idColumn = new TableColumn<>("ID");
                    TableColumn<Book, String> isbnColumn = new TableColumn<>("ISBN");
                    TableColumn<Book, Integer> authorIdColumn = new TableColumn<>("Author ID");
                    TableColumn<Book, String> authorFnameColumn = new TableColumn<>("Author First Name");
                    TableColumn<Book, String> authorLnameColumn = new TableColumn<>("Author Last Name");
                    TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
                    TableColumn<Book, Integer> genreIdColumn = new TableColumn<>("Genre ID");
                    TableColumn<Book, String> genreColumn = new TableColumn<>("Genre Name");
                    TableColumn<Book, String> bookLocationColumn = new TableColumn<>("Book Location");
                    TableColumn<Book, Integer> totalCopiesColumn = new TableColumn<>("Total Copies");
                    TableColumn<Book, Integer> availableCopiesColumn = new TableColumn<>("Available Copies");

                    idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
                    isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
                    authorIdColumn.setCellValueFactory(new PropertyValueFactory<>("authorId"));
                    authorFnameColumn.setCellValueFactory(new PropertyValueFactory<>("authorFname"));
                    authorLnameColumn.setCellValueFactory(new PropertyValueFactory<>("authorLname"));
                    titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
                    genreIdColumn.setCellValueFactory(new PropertyValueFactory<>("genreId"));
                    genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
                    bookLocationColumn.setCellValueFactory(new PropertyValueFactory<>("bookLocation"));
                    totalCopiesColumn.setCellValueFactory(new PropertyValueFactory<>("totalCopies"));
                    availableCopiesColumn.setCellValueFactory(new PropertyValueFactory<>("availableCopies"));

                    tableView.getColumns().addAll(idColumn, isbnColumn, authorIdColumn, authorFnameColumn, authorLnameColumn, titleColumn, genreIdColumn, genreColumn, bookLocationColumn, totalCopiesColumn, availableCopiesColumn);

                    tableView.getItems().setAll(searchResults);
                    Delete.add(tableView, 0, 4, 3, 1);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });

            deleteButton.setOnAction(delEvent -> {
                String hold_id = id_for_del.getText();
                int id_to_del = Integer.valueOf(hold_id);

                String findQuery = "SELECT * FROM book_info WHERE book_id = ?";
                try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
                     PreparedStatement pstmt = conn.prepareStatement(findQuery)) {
                    pstmt.setInt(1, id_to_del);
                    ResultSet rs = pstmt.executeQuery();
                    if (rs.next()) {
                        Integer bookId = rs.getInt("book_id");
                        String isbn = rs.getString("isbn");
                        Integer authorId = rs.getInt("author_id");
                        String title = rs.getString("title");
                        Integer genreId = rs.getInt("genre_id");
                        String bookLocation = rs.getString("book_location");
                        Integer totalCopies = rs.getInt("total_copies");
                        Integer availableCopies = rs.getInt("available_copies");

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Warning");
                        alert.setHeaderText(null);
                        alert.setContentText("Are you sure you want to proceed?\nThis action cannot be undone.\n\n ID: " + id_to_del + "\n ISBN: " + isbn + "\n Title: " + title);
                        Optional<ButtonType> result = alert.showAndWait();

                        if (result.isPresent() && result.get() == ButtonType.OK) {
                            String deleteInfoQuery = "DELETE FROM book_info WHERE book_id = ?";
                            try (Connection DEL_conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
                                 PreparedStatement DEL_pstmt = DEL_conn.prepareStatement(deleteInfoQuery)) {
                                DEL_pstmt.setInt(1, id_to_del);
                                int rowsAffected = DEL_pstmt.executeUpdate();
                                if (rowsAffected > 0) {
                                    System.out.println("Deletion success");
                                }
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            System.out.println("Deletion canceled");
                        }

                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });

            // show the Search screen
            mainLayout.setCenter(Delete);
        });

        booksMenu.getItems().addAll(addBookMenuItem, viewBooksMenuItem, removeBookMenuItem);


        MenuItem checkOutBookMenuItem = new MenuItem("Check-out Book");
        checkOutBookMenuItem.setOnAction(event -> {
            mainLayout.setCenter(null);
            // Create the form field
            Label titleIn = new Label("Search Book to check-Out:");
            TextField searchBookKey = new TextField();
            Button searchButton = new Button("Search");


            Label memberSearchLabel = new Label("Search for member:");
            TextField memberSearchKeywordField = new TextField();
            Button memberSearchButton = new Button("Search");

            //select buttom
            Button selectButton = new Button("Check-Out Book");

            //drawing
            GridPane Search = new GridPane();
            Search.setPadding(new Insets(10));
            Search.setHgap(10);
            Search.setVgap(10);


            // create the search book section
            Search.add(titleIn, 0, 0, 2, 1);
            Search.add(searchBookKey, 0, 1);
            Search.add(searchButton, 1, 1);

            // create the search member section
            Search.add(memberSearchLabel, 0, 2, 1, 1);
            Search.add(memberSearchKeywordField, 0, 3);
            Search.add(memberSearchButton, 1, 3);

            Search.add(selectButton, 0, 4); // Add the new button to the GridPane

            searchButton.setOnAction(searchEvent -> {
                String searchKeyword = searchBookKey.getText(); // Replace with the keyword to search for

                String searchQuery = "SELECT * FROM book_info INNER JOIN authors ON book_info.author_id = authors.author_id INNER JOIN genres ON book_info.genre_id = genres.genre_id WHERE CAST(book_info.book_id AS TEXT) = ? OR isbn LIKE ? OR CAST(book_info.author_id AS TEXT) = ? OR title LIKE ? OR CAST(book_info.genre_id AS TEXT) = ? OR book_location LIKE ?";
                try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
                     PreparedStatement pstmt = conn.prepareStatement(searchQuery)) {
                    pstmt.setString(1, "%" + searchKeyword + "%");
                    pstmt.setString(2, "%" + searchKeyword + "%");
                    pstmt.setString(3, "%" + searchKeyword + "%");
                    pstmt.setString(4, "%" + searchKeyword + "%");
                    pstmt.setString(5, "%" + searchKeyword + "%");
                    pstmt.setString(6, "%" + searchKeyword + "%");
                    ResultSet rs = pstmt.executeQuery();

                    List<Book> searchResults = new ArrayList<>();
                    while (rs.next()) {
                        Integer bookId = rs.getInt("book_id");
                        String isbn = rs.getString("isbn");
                        Integer authorId = rs.getInt("author_id");
                        String authorFname = rs.getString("author_fname");
                        String authorLname = rs.getString("author_lname");
                        String title = rs.getString("title");
                        Integer genreId = rs.getInt("genre_id");
                        String genre = rs.getString("genre_name");
                        String bookLocation = rs.getString("book_location");
                        Integer totalCopies = rs.getInt("total_copies");
                        Integer availableCopies = rs.getInt("available_copies");
                        searchResults.add(new Book(bookId, isbn, authorId, authorFname, authorLname, title, genreId, genre, bookLocation, totalCopies, availableCopies));
                    }

                    TableView<Book> tableView = new TableView<>();
                    TableColumn<Book, Integer> idColumn = new TableColumn<>("ID");
                    TableColumn<Book, String> isbnColumn = new TableColumn<>("ISBN");
                    TableColumn<Book, Integer> authorIdColumn = new TableColumn<>("Author ID");
                    TableColumn<Book, String> authorFnameColumn = new TableColumn<>("Author First Name");
                    TableColumn<Book, String> authorLnameColumn = new TableColumn<>("Author Last Name");
                    TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
                    TableColumn<Book, Integer> genreIdColumn = new TableColumn<>("Genre ID");
                    TableColumn<Book, String> genreColumn = new TableColumn<>("Genre Name");
                    TableColumn<Book, String> bookLocationColumn = new TableColumn<>("Book Location");
                    TableColumn<Book, Integer> totalCopiesColumn = new TableColumn<>("Total Copies");
                    TableColumn<Book, Integer> availableCopiesColumn = new TableColumn<>("Available Copies");

                    idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
                    isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
                    authorIdColumn.setCellValueFactory(new PropertyValueFactory<>("authorId"));
                    authorFnameColumn.setCellValueFactory(new PropertyValueFactory<>("authorFname"));
                    authorLnameColumn.setCellValueFactory(new PropertyValueFactory<>("authorLname"));
                    titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
                    genreIdColumn.setCellValueFactory(new PropertyValueFactory<>("genreId"));
                    genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
                    bookLocationColumn.setCellValueFactory(new PropertyValueFactory<>("bookLocation"));
                    totalCopiesColumn.setCellValueFactory(new PropertyValueFactory<>("totalCopies"));
                    availableCopiesColumn.setCellValueFactory(new PropertyValueFactory<>("availableCopies"));





                    memberSearchButton.setOnAction(MsearchEvent -> {
                        String MsearchKeyword = memberSearchKeywordField.getText(); // Replace with the keyword to search for

                        String MsearchQuery = "SELECT * FROM member_info INNER JOIN member_logins ON member_info.member_id = member_logins.member_id WHERE CAST(member_info.member_id AS TEXT) = ? OR fname LIKE ? OR lname LIKE ? OR email LIKE ? OR phone_number LIKE ?";
                        try (Connection Mconn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
                             PreparedStatement Mpstmt = Mconn.prepareStatement(MsearchQuery)) {
                            Mpstmt.setString(1, "%" + MsearchKeyword + "%");
                            Mpstmt.setString(2, "%" + MsearchKeyword + "%");
                            Mpstmt.setString(3, "%" + MsearchKeyword + "%");
                            Mpstmt.setString(4, "%" + MsearchKeyword + "%");
                            Mpstmt.setString(5, "%" + MsearchKeyword + "%");
                            ResultSet Mrs = Mpstmt.executeQuery();

                            List<Member> MsearchResults = new ArrayList<>();
                            while (Mrs.next()) {
                                int memberId = Mrs.getInt("member_id");
                                String fname = Mrs.getString("fname");
                                String lname = Mrs.getString("lname");
                                String email = Mrs.getString("email");
                                String phoneNumber = Mrs.getString("phone_number");
                                String username = Mrs.getString("username");
                                String password = Mrs.getString("password");
                                MsearchResults.add(new Member(memberId, fname, lname, email, phoneNumber, username, password));
                            }

                            TableView<Member> MtableView = new TableView<>();
                            TableColumn<Member, Integer> IdColumn = new TableColumn<>("ID");
                            TableColumn<Member, String> firstNameColumn = new TableColumn<>("First Name");
                            TableColumn<Member, String> lastNameColumn = new TableColumn<>("Last Name");
                            TableColumn<Member, String> emailColumn = new TableColumn<>("Email");
                            TableColumn<Member, String> phoneColumn = new TableColumn<>("Phone");
                            TableColumn<Member, String> usernameColumn = new TableColumn<>("Username");
                            TableColumn<Member, String> passwordColumn = new TableColumn<>("Password");

                            IdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
                            firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
                            lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
                            emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
                            phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
                            usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
                            passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

                            MtableView.getColumns().addAll(IdColumn, firstNameColumn, lastNameColumn, emailColumn, phoneColumn, usernameColumn, passwordColumn);
                            MtableView.getItems().setAll(MsearchResults);

                            Search.add(MtableView, 0, 6);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    });
                    selectButton.setOnAction(selectEvent -> {
                        String SelectSearchQuery = "UPDATE books SET available_copies = available_copies - 1 WHERE id = ?";
                        String updateCheckoutDateQuery = "UPDATE checkouts SET checkout_date = CURRENT_DATE WHERE book_id = ?";
                        Book selectedBook = tableView.getSelectionModel().getSelectedItem();
                        if (selectedBook == null) {
                            // Display an error message if no book is selected
                            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a book to check-out.");
                            alert.showAndWait();
                        } else {
                            // Display a success message and update the database to check out the book
                            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Your book has been checked-out.");
                            alert.showAndWait();
                            // Update the database to have - 1  the available copies from book table.
                            try (Connection Sconn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);) {
                                PreparedStatement Spstmt = Sconn.prepareStatement(SelectSearchQuery);
                                Spstmt.setInt(1, selectedBook.getId()); // Replace 'selectedBook.getBookId()' with the actual ID of the selected book
                                Spstmt.executeUpdate();

                                // update checkout table to current date
                                PreparedStatement Cpstmt = Sconn.prepareStatement(updateCheckoutDateQuery);
                                Cpstmt.setInt(1, selectedBook.getId());
                                Cpstmt.executeUpdate();

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    tableView.getColumns().addAll(idColumn, isbnColumn, authorIdColumn, authorFnameColumn, authorLnameColumn, titleColumn, genreIdColumn, genreColumn, bookLocationColumn, totalCopiesColumn, availableCopiesColumn);

                    tableView.getItems().setAll(searchResults);

                    Search.add(tableView, 0, 5);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });

            // show the Search screen
            mainLayout.setCenter(Search);
        });


        Menu checkoutsMenu = new Menu("Checkouts");
        MenuItem checkInBookMenuItem = new MenuItem("Check-In Book");
        checkInBookMenuItem.setOnAction(event -> {
            mainLayout.setCenter(null);
            // Create the form field
            Label titleIn = new Label("Search Book to Check-In:");
            TextField searchBookKey = new TextField();
            Button searchButton = new Button("Search");


            Label memberSearchLabel = new Label("Search for member:");
            TextField memberSearchKeywordField = new TextField();
            Button memberSearchButton = new Button("Search");

            //select buttom
            Button selectButton = new Button("Check-In Book");

            //drawing
            GridPane Search = new GridPane();
            Search.setPadding(new Insets(10));
            Search.setHgap(10);
            Search.setVgap(10);


            // create the search book section
            Search.add(titleIn, 0, 0, 2, 1);
            Search.add(searchBookKey, 0, 1);
            Search.add(searchButton, 1, 1);

            // create the search member section
            Search.add(memberSearchLabel, 0, 2, 1, 1);
            Search.add(memberSearchKeywordField, 0, 3);
            Search.add(memberSearchButton, 1, 3);

            Search.add(selectButton, 0, 4); // Add the new button to the GridPane

            searchButton.setOnAction(searchEvent -> {
                String searchKeyword = searchBookKey.getText(); // Replace with the keyword to search for

                String searchQuery = "SELECT * FROM book_info INNER JOIN authors ON book_info.author_id = authors.author_id INNER JOIN genres ON book_info.genre_id = genres.genre_id WHERE CAST(book_info.book_id AS TEXT) = ? OR isbn LIKE ? OR CAST(book_info.author_id AS TEXT) = ? OR title LIKE ? OR CAST(book_info.genre_id AS TEXT) = ? OR book_location LIKE ?";
                try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
                     PreparedStatement pstmt = conn.prepareStatement(searchQuery)) {
                    pstmt.setString(1, "%" + searchKeyword + "%");
                    pstmt.setString(2, "%" + searchKeyword + "%");
                    pstmt.setString(3, "%" + searchKeyword + "%");
                    pstmt.setString(4, "%" + searchKeyword + "%");
                    pstmt.setString(5, "%" + searchKeyword + "%");
                    pstmt.setString(6, "%" + searchKeyword + "%");
                    ResultSet rs = pstmt.executeQuery();

                    List<Book> searchResults = new ArrayList<>();
                    while (rs.next()) {
                        Integer bookId = rs.getInt("book_id");
                        String isbn = rs.getString("isbn");
                        Integer authorId = rs.getInt("author_id");
                        String authorFname = rs.getString("author_fname");
                        String authorLname = rs.getString("author_lname");
                        String title = rs.getString("title");
                        Integer genreId = rs.getInt("genre_id");
                        String genre = rs.getString("genre_name");
                        String bookLocation = rs.getString("book_location");
                        Integer totalCopies = rs.getInt("total_copies");
                        Integer availableCopies = rs.getInt("available_copies");
                        searchResults.add(new Book(bookId, isbn, authorId, authorFname, authorLname, title, genreId, genre, bookLocation, totalCopies, availableCopies));
                    }

                    TableView<Book> tableView = new TableView<>();
                    TableColumn<Book, Integer> idColumn = new TableColumn<>("ID");
                    TableColumn<Book, String> isbnColumn = new TableColumn<>("ISBN");
                    TableColumn<Book, Integer> authorIdColumn = new TableColumn<>("Author ID");
                    TableColumn<Book, String> authorFnameColumn = new TableColumn<>("Author First Name");
                    TableColumn<Book, String> authorLnameColumn = new TableColumn<>("Author Last Name");
                    TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
                    TableColumn<Book, Integer> genreIdColumn = new TableColumn<>("Genre ID");
                    TableColumn<Book, String> genreColumn = new TableColumn<>("Genre Name");
                    TableColumn<Book, String> bookLocationColumn = new TableColumn<>("Book Location");
                    TableColumn<Book, Integer> totalCopiesColumn = new TableColumn<>("Total Copies");
                    TableColumn<Book, Integer> availableCopiesColumn = new TableColumn<>("Available Copies");

                    idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
                    isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
                    authorIdColumn.setCellValueFactory(new PropertyValueFactory<>("authorId"));
                    authorFnameColumn.setCellValueFactory(new PropertyValueFactory<>("authorFname"));
                    authorLnameColumn.setCellValueFactory(new PropertyValueFactory<>("authorLname"));
                    titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
                    genreIdColumn.setCellValueFactory(new PropertyValueFactory<>("genreId"));
                    genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
                    bookLocationColumn.setCellValueFactory(new PropertyValueFactory<>("bookLocation"));
                    totalCopiesColumn.setCellValueFactory(new PropertyValueFactory<>("totalCopies"));
                    availableCopiesColumn.setCellValueFactory(new PropertyValueFactory<>("availableCopies"));

                    memberSearchButton.setOnAction(MsearchEvent -> {
                        String MsearchKeyword = memberSearchKeywordField.getText(); // Replace with the keyword to search for

                        String MsearchQuery = "SELECT * FROM member_info INNER JOIN member_logins ON member_info.member_id = member_logins.member_id WHERE CAST(member_info.member_id AS TEXT) = ? OR fname LIKE ? OR lname LIKE ? OR email LIKE ? OR phone_number LIKE ?";
                        try (Connection Mconn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
                             PreparedStatement Mpstmt = Mconn.prepareStatement(MsearchQuery)) {
                            Mpstmt.setString(1, "%" + MsearchKeyword + "%");
                            Mpstmt.setString(2, "%" + MsearchKeyword + "%");
                            Mpstmt.setString(3, "%" + MsearchKeyword + "%");
                            Mpstmt.setString(4, "%" + MsearchKeyword + "%");
                            Mpstmt.setString(5, "%" + MsearchKeyword + "%");
                            ResultSet Mrs = Mpstmt.executeQuery();

                            List<Member> MsearchResults = new ArrayList<>();
                            while (Mrs.next()) {
                                int memberId = Mrs.getInt("member_id");
                                String fname = Mrs.getString("fname");
                                String lname = Mrs.getString("lname");
                                String email = Mrs.getString("email");
                                String phoneNumber = Mrs.getString("phone_number");
                                String username = Mrs.getString("username");
                                String password = Mrs.getString("password");
                                MsearchResults.add(new Member(memberId, fname, lname, email, phoneNumber, username, password));
                            }

                            TableView<Member> MtableView = new TableView<>();
                            TableColumn<Member, Integer> IdColumn = new TableColumn<>("ID");
                            TableColumn<Member, String> firstNameColumn = new TableColumn<>("First Name");
                            TableColumn<Member, String> lastNameColumn = new TableColumn<>("Last Name");
                            TableColumn<Member, String> emailColumn = new TableColumn<>("Email");
                            TableColumn<Member, String> phoneColumn = new TableColumn<>("Phone");
                            TableColumn<Member, String> usernameColumn = new TableColumn<>("Username");
                            TableColumn<Member, String> passwordColumn = new TableColumn<>("Password");

                            IdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
                            firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
                            lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
                            emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
                            phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
                            usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
                            passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

                            MtableView.getColumns().addAll(IdColumn, firstNameColumn, lastNameColumn, emailColumn, phoneColumn, usernameColumn, passwordColumn);
                            MtableView.getItems().setAll(MsearchResults);

                            Search.add(MtableView, 0, 6);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    });

                    selectButton.setOnAction(selectEvent -> {
                        String SelectSearchQuery = "UPDATE books SET available_copies = available_copies + 1 WHERE id = ?";
                        String updateCheckoutDateQuery = "UPDATE checkouts SET checkout_return_date = CURRENT_DATE WHERE book_id = ?";
                        //Checkouts checkout = new Checkouts();
                        Book selectedBook = tableView.getSelectionModel().getSelectedItem();
                        if (selectedBook == null) {
                            // Display an error message if no book is selected
                            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a book to check-in.");
                            alert.showAndWait();
                        } else {
                            // Display a success message and update the database to check in the book
                            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Your book has been checked-in.");
                            alert.showAndWait();
                            // Update the database to have - 1  the available copies from book table.
                            try (Connection Sconn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);) {
                                PreparedStatement Spstmt = Sconn.prepareStatement(SelectSearchQuery);
                                Spstmt.setInt(1, selectedBook.getId()); // Replace 'selectedBook.getBookId()' with the actual ID of the selected book
                                Spstmt.executeUpdate();

                                // update checkout table to current date
                                PreparedStatement Cpstmt = Sconn.prepareStatement(updateCheckoutDateQuery);
                                Cpstmt.setInt(1, selectedBook.getId());
                                Cpstmt.executeUpdate();

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    tableView.getColumns().addAll(idColumn, isbnColumn, authorIdColumn, authorFnameColumn, authorLnameColumn, titleColumn, genreIdColumn, genreColumn, bookLocationColumn, totalCopiesColumn, availableCopiesColumn);

                    tableView.getItems().setAll(searchResults);

                    Search.add(tableView, 0,5);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });

            // show the Search screen
            mainLayout.setCenter(Search);
        });


        MenuItem viewCheckoutsMenuItem = new MenuItem("View Checkouts");
        viewCheckoutsMenuItem.setOnAction(event -> {
            mainLayout.setCenter(null);
        });

        checkoutsMenu.getItems().addAll(checkOutBookMenuItem, checkInBookMenuItem, viewCheckoutsMenuItem);

        // Create the "Fines" menu and menu items
        Menu finesMenu = new Menu("Fines");
        MenuItem viewFinesMenuItem = new MenuItem("View Fines on Account");
        MenuItem createFineMenuItem = new MenuItem("Add Fine to Account");
        MenuItem removeFineMenuItem = new MenuItem("Remove Fine from Account");
        finesMenu.getItems().addAll(viewFinesMenuItem, createFineMenuItem, removeFineMenuItem);

        // Create the "Logout" menu and menu items
        Menu logOut= new Menu("Exit");
        MenuItem logOutClick = new MenuItem("Log Out");
        MenuItem sessionClick = new MenuItem("End App Session");
        logOutClick.setOnAction(event -> {
            primaryStage.show();
            secondaryStage.close();
            //System.out.println("Logged out...");
        });
        sessionClick.setOnAction(event -> {
            //System.out.println("Program Exiting...");
            System.exit(0);
        });
        logOut.getItems().addAll(logOutClick, sessionClick);

        // Add menus to the menu bar
        menuBar.getMenus().addAll(homeMenu, usersMenu, booksMenu, checkoutsMenu, finesMenu, logOut);

        // Create the main layout
        mainLayout = new BorderPane();
        mainLayout.setTop(menuBar);

        mainLayout.setStyle("-fx-background-image: url('" + imagePath + "');");
        mainLayout.setPadding(new Insets(10));


        // Create the main scene and show it
        Scene mainScene = new Scene(mainLayout, 800, 600);
        secondaryStage.setScene(mainScene);
        secondaryStage.show();

        Image logo_image = new Image(getClass().getResourceAsStream("/images/SqueakyCheeseLogo.png"));



        // Create the image view
        ImageView imageView = new ImageView(logo_image);
        imageView.setFitWidth(150);
        imageView.setFitHeight(100);

        mainLayout.setBottom(imageView);
        BorderPane.setAlignment(imageView, Pos.BOTTOM_RIGHT);

        Label welcomeLabel = new Label("Welcome to the Squeaky Cheese LMS!\n\nUsing this program you can:\n- Add, remove, and edit book information\n- Search for books based on filters\n- View, add, remove and edit users\n- View, Check-In, and Check-Out books\n- Add, remove and view fines\n\nHave a great day!");
        welcomeLabel.setFont(Font.font("Arial",20));
        mainLayout.setCenter(welcomeLabel);


    }

    /*
    ***********************************
    ABOVE IS ADMIN VIEW
    BELOW IS USER VIEW
    ***********************************
     */


    public void launchUserMainApp() {

        primaryStage.close();
        teritiaryStage = new Stage();
        teritiaryStage.setTitle("Library User Access");

        // Create the menu bar
        MenuBar menuBar = new MenuBar();

        // Create the "Home" menu button
        Menu homeMenu = new Menu("Home");
        MenuItem goHome = new MenuItem("Go to Home");
        goHome.setOnAction(event -> {
            // Clear all information on the screen
            mainLayout.setCenter(null);
            // Home Menu Information
            Label infoLabel = new Label("Welcome to the Squeaky Cheese LMS as a User!\n\nNavigate with the menu options at the top of the screen.");
            infoLabel.setFont(Font.font("Arial",20));
            mainLayout.setCenter(infoLabel);

            // Load Image

        });
        homeMenu.getItems().add(goHome);
        // *** ON CLICK SHOULD BRING USER HOME ***


        // Create the "Search Books" menu and menu items
        Menu searchBooksMenu = new Menu("Search Books");
        MenuItem addTitleMenuItem = new MenuItem("Search by Title");
        addTitleMenuItem.setOnAction(event -> {
            mainLayout.setCenter(null);

            // Create the form field
            Label titleInfo = new Label("Search for a book by title:");
            TextField searchBookKeywordField = new TextField();
            Button searchButton = new Button("Search");

            GridPane Search = new GridPane();
            Search.setPadding(new Insets(10));
            Search.setHgap(10);
            Search.setVgap(10);
            Search.add(titleInfo,0,0,2,1);
            Search.add(searchBookKeywordField, 0, 1);
            Search.add(searchButton, 0, 2);

            searchButton.setOnAction(searchEvent -> {
                String searchKeyword = searchBookKeywordField.getText(); // Replace with the keyword to search for

                String searchQuery = "SELECT * FROM book_info JOIN authors ON book_info.author_id = authors.author_id JOIN genres ON book_info.genre_id = genres.genre_id WHERE title LIKE ?";
                try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
                     PreparedStatement pstmt = conn.prepareStatement(searchQuery)) {
                    pstmt.setString(1, "%" + searchKeyword + "%");
                    ResultSet rs = pstmt.executeQuery();

                    List<Book> searchResults = new ArrayList<>();
                    while (rs.next()) {
                        Integer bookId = rs.getInt("book_id");
                        String isbn = rs.getString("isbn");
                        Integer authorId = rs.getInt("author_id");
                        String authorFname = rs.getString("author_fname");
                        String authorLname = rs.getString("author_lname");
                        String title = rs.getString("title");
                        Integer genreId = rs.getInt("genre_id");
                        String genre = rs.getString("genre_name");
                        String bookLocation = rs.getString("book_location");
                        Integer totalCopies = rs.getInt("total_copies");
                        Integer availableCopies = rs.getInt("available_copies");
                        searchResults.add(new Book(bookId, isbn, authorId, authorFname, authorLname, title, genreId, genre, bookLocation, totalCopies, availableCopies));
                    }

                    TableView<Book> tableView = new TableView<>();
                    TableColumn<Book, Integer> idColumn = new TableColumn<>("ID");
                    TableColumn<Book, String> isbnColumn = new TableColumn<>("ISBN");
                    TableColumn<Book, Integer> authorIdColumn = new TableColumn<>("Author ID");
                    TableColumn<Book, String> authorFnameColumn = new TableColumn<>("Author First Name");
                    TableColumn<Book, String> authorLnameColumn = new TableColumn<>("Author Last Name");
                    TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
                    TableColumn<Book, Integer> genreIdColumn = new TableColumn<>("Genre ID");
                    TableColumn<Book, String> genreColumn = new TableColumn<>("Genre Name");
                    TableColumn<Book, String> bookLocationColumn = new TableColumn<>("Book Location");
                    TableColumn<Book, Integer> totalCopiesColumn = new TableColumn<>("Total Copies");
                    TableColumn<Book, Integer> availableCopiesColumn = new TableColumn<>("Available Copies");

                    idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
                    isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
                    authorIdColumn.setCellValueFactory(new PropertyValueFactory<>("authorId"));
                    authorFnameColumn.setCellValueFactory(new PropertyValueFactory<>("authorFname"));
                    authorLnameColumn.setCellValueFactory(new PropertyValueFactory<>("authorLname"));
                    titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
                    genreIdColumn.setCellValueFactory(new PropertyValueFactory<>("genreId"));
                    genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
                    bookLocationColumn.setCellValueFactory(new PropertyValueFactory<>("bookLocation"));
                    totalCopiesColumn.setCellValueFactory(new PropertyValueFactory<>("totalCopies"));
                    availableCopiesColumn.setCellValueFactory(new PropertyValueFactory<>("availableCopies"));

                    tableView.getColumns().addAll(idColumn, isbnColumn, authorIdColumn, authorFnameColumn, authorLnameColumn, titleColumn, genreIdColumn, genreColumn, bookLocationColumn, totalCopiesColumn, availableCopiesColumn);

                    tableView.getItems().setAll(searchResults);
                    Search.add(tableView, 0, 3);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });

            // show the Search screen
            mainLayout.setCenter(Search);
        });

        MenuItem addAuthorMenuItem = new MenuItem("Search by Author");
        addAuthorMenuItem.setOnAction(event -> {
            mainLayout.setCenter(null);

            // Create the form field
            Label titleInfo = new Label("Search for a book by author:");
            TextField searchBookKeywordField = new TextField();
            Button searchButton = new Button("Search");

            GridPane Search = new GridPane();
            Search.setPadding(new Insets(10));
            Search.setHgap(10);
            Search.setVgap(10);
            Search.add(titleInfo,0,0,2,1);
            Search.add(searchBookKeywordField, 0, 1);
            Search.add(searchButton, 0, 2);

            searchButton.setOnAction(searchEvent -> {
                String searchKeyword = searchBookKeywordField.getText(); // Replace with the keyword to search for

                String searchQuery = "SELECT * FROM book_info JOIN authors ON book_info.author_id = authors.author_id JOIN genres ON book_info.genre_id = genres.genre_id WHERE authors.author_fname LIKE ? OR authors.author_lname LIKE ?";
                try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
                     PreparedStatement pstmt = conn.prepareStatement(searchQuery)) {
                    pstmt.setString(1, "%" + searchKeyword + "%");
                    pstmt.setString(2, "%" + searchKeyword + "%");
                    ResultSet rs = pstmt.executeQuery();

                    List<Book> searchResults = new ArrayList<>();
                    while (rs.next()) {
                        Integer bookId = rs.getInt("book_id");
                        String isbn = rs.getString("isbn");
                        Integer authorId = rs.getInt("author_id");
                        String authorFname = rs.getString("author_fname");
                        String authorLname = rs.getString("author_lname");
                        String title = rs.getString("title");
                        Integer genreId = rs.getInt("genre_id");
                        String genre = rs.getString("genre_name");
                        String bookLocation = rs.getString("book_location");
                        Integer totalCopies = rs.getInt("total_copies");
                        Integer availableCopies = rs.getInt("available_copies");
                        searchResults.add(new Book(bookId, isbn, authorId, authorFname, authorLname, title, genreId, genre, bookLocation, totalCopies, availableCopies));
                    }

                    TableView<Book> tableView = new TableView<>();
                    TableColumn<Book, Integer> idColumn = new TableColumn<>("ID");
                    TableColumn<Book, String> isbnColumn = new TableColumn<>("ISBN");
                    TableColumn<Book, Integer> authorIdColumn = new TableColumn<>("Author ID");
                    TableColumn<Book, String> authorFnameColumn = new TableColumn<>("Author First Name");
                    TableColumn<Book, String> authorLnameColumn = new TableColumn<>("Author Last Name");
                    TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
                    TableColumn<Book, Integer> genreIdColumn = new TableColumn<>("Genre ID");
                    TableColumn<Book, String> genreColumn = new TableColumn<>("Genre Name");
                    TableColumn<Book, String> bookLocationColumn = new TableColumn<>("Book Location");
                    TableColumn<Book, Integer> totalCopiesColumn = new TableColumn<>("Total Copies");
                    TableColumn<Book, Integer> availableCopiesColumn = new TableColumn<>("Available Copies");

                    idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
                    isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
                    authorIdColumn.setCellValueFactory(new PropertyValueFactory<>("authorId"));
                    authorFnameColumn.setCellValueFactory(new PropertyValueFactory<>("authorFname"));
                    authorLnameColumn.setCellValueFactory(new PropertyValueFactory<>("authorLname"));
                    titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
                    genreIdColumn.setCellValueFactory(new PropertyValueFactory<>("genreId"));
                    genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
                    bookLocationColumn.setCellValueFactory(new PropertyValueFactory<>("bookLocation"));
                    totalCopiesColumn.setCellValueFactory(new PropertyValueFactory<>("totalCopies"));
                    availableCopiesColumn.setCellValueFactory(new PropertyValueFactory<>("availableCopies"));

                    tableView.getColumns().addAll(idColumn, isbnColumn, authorIdColumn, authorFnameColumn, authorLnameColumn, titleColumn, genreIdColumn, genreColumn, bookLocationColumn, totalCopiesColumn, availableCopiesColumn);

                    tableView.getItems().setAll(searchResults);
                    Search.add(tableView, 0, 3);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });

            // show the Search screen
            mainLayout.setCenter(Search);
        });

        MenuItem addISBNMenuItem = new MenuItem("Search by ISBN");
        addISBNMenuItem.setOnAction(event -> {
            mainLayout.setCenter(null);

            // Create the form field
            Label titleInfo = new Label("Search for a book by ISBN:");
            TextField searchBookKeywordField = new TextField();
            Button searchButton = new Button("Search");

            GridPane Search = new GridPane();
            Search.setPadding(new Insets(10));
            Search.setHgap(10);
            Search.setVgap(10);
            Search.add(titleInfo,0,0,2,1);
            Search.add(searchBookKeywordField, 0, 1);
            Search.add(searchButton, 0, 2);

            searchButton.setOnAction(searchEvent -> {
                String searchKeyword = searchBookKeywordField.getText(); // Replace with the keyword to search for

                String searchQuery = "SELECT * FROM book_info JOIN authors ON book_info.author_id = authors.author_id JOIN genres ON book_info.genre_id = genres.genre_id WHERE isbn LIKE ?";
                try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
                     PreparedStatement pstmt = conn.prepareStatement(searchQuery)) {
                    pstmt.setString(1, "%" + searchKeyword + "%");
                    ResultSet rs = pstmt.executeQuery();

                    List<Book> searchResults = new ArrayList<>();
                    while (rs.next()) {
                        Integer bookId = rs.getInt("book_id");
                        String isbn = rs.getString("isbn");
                        Integer authorId = rs.getInt("author_id");
                        String authorFname = rs.getString("author_fname");
                        String authorLname = rs.getString("author_lname");
                        String title = rs.getString("title");
                        Integer genreId = rs.getInt("genre_id");
                        String genre = rs.getString("genre_name");
                        String bookLocation = rs.getString("book_location");
                        Integer totalCopies = rs.getInt("total_copies");
                        Integer availableCopies = rs.getInt("available_copies");
                        searchResults.add(new Book(bookId, isbn, authorId, authorFname, authorLname, title, genreId, genre, bookLocation, totalCopies, availableCopies));
                    }

                    TableView<Book> tableView = new TableView<>();
                    TableColumn<Book, Integer> idColumn = new TableColumn<>("ID");
                    TableColumn<Book, String> isbnColumn = new TableColumn<>("ISBN");
                    TableColumn<Book, Integer> authorIdColumn = new TableColumn<>("Author ID");
                    TableColumn<Book, String> authorFnameColumn = new TableColumn<>("Author First Name");
                    TableColumn<Book, String> authorLnameColumn = new TableColumn<>("Author Last Name");
                    TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
                    TableColumn<Book, Integer> genreIdColumn = new TableColumn<>("Genre ID");
                    TableColumn<Book, String> genreColumn = new TableColumn<>("Genre Name");
                    TableColumn<Book, String> bookLocationColumn = new TableColumn<>("Book Location");
                    TableColumn<Book, Integer> totalCopiesColumn = new TableColumn<>("Total Copies");
                    TableColumn<Book, Integer> availableCopiesColumn = new TableColumn<>("Available Copies");

                    idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
                    isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
                    authorIdColumn.setCellValueFactory(new PropertyValueFactory<>("authorId"));
                    authorFnameColumn.setCellValueFactory(new PropertyValueFactory<>("authorFname"));
                    authorLnameColumn.setCellValueFactory(new PropertyValueFactory<>("authorLname"));
                    titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
                    genreIdColumn.setCellValueFactory(new PropertyValueFactory<>("genreId"));
                    genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
                    bookLocationColumn.setCellValueFactory(new PropertyValueFactory<>("bookLocation"));
                    totalCopiesColumn.setCellValueFactory(new PropertyValueFactory<>("totalCopies"));
                    availableCopiesColumn.setCellValueFactory(new PropertyValueFactory<>("availableCopies"));

                    tableView.getColumns().addAll(idColumn, isbnColumn, authorIdColumn, authorFnameColumn, authorLnameColumn, titleColumn, genreIdColumn, genreColumn, bookLocationColumn, totalCopiesColumn, availableCopiesColumn);

                    tableView.getItems().setAll(searchResults);
                    Search.add(tableView, 0, 3);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });

            // show the Search screen
            mainLayout.setCenter(Search);
        });

        MenuItem addGenreMenuItem = new MenuItem("Search by Genre");
        addGenreMenuItem.setOnAction(event -> {
            mainLayout.setCenter(null);

            // Create the form field
            Label titleInfo = new Label("Search for a book by genre:");
            TextField searchBookKeywordField = new TextField();
            Button searchButton = new Button("Search");

            GridPane Search = new GridPane();
            Search.setPadding(new Insets(10));
            Search.setHgap(10);
            Search.setVgap(10);
            Search.add(titleInfo,0,0,2,1);
            Search.add(searchBookKeywordField, 0, 1);
            Search.add(searchButton, 0, 2);

            searchButton.setOnAction(searchEvent -> {
                String searchKeyword = searchBookKeywordField.getText(); // Replace with the keyword to search for

                String searchQuery = "SELECT * FROM book_info JOIN authors ON book_info.author_id = authors.author_id JOIN genres ON book_info.genre_id = genres.genre_id WHERE genres.genre_name LIKE ?";
                try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
                     PreparedStatement pstmt = conn.prepareStatement(searchQuery)) {
                    pstmt.setString(1, "%" + searchKeyword + "%");
                    ResultSet rs = pstmt.executeQuery();

                    List<Book> searchResults = new ArrayList<>();
                    while (rs.next()) {
                        Integer bookId = rs.getInt("book_id");
                        String isbn = rs.getString("isbn");
                        Integer authorId = rs.getInt("author_id");
                        String authorFname = rs.getString("author_fname");
                        String authorLname = rs.getString("author_lname");
                        String title = rs.getString("title");
                        Integer genreId = rs.getInt("genre_id");
                        String genre = rs.getString("genre_name");
                        String bookLocation = rs.getString("book_location");
                        Integer totalCopies = rs.getInt("total_copies");
                        Integer availableCopies = rs.getInt("available_copies");
                        searchResults.add(new Book(bookId, isbn, authorId, authorFname, authorLname, title, genreId, genre, bookLocation, totalCopies, availableCopies));
                    }

                    TableView<Book> tableView = new TableView<>();
                    TableColumn<Book, Integer> idColumn = new TableColumn<>("ID");
                    TableColumn<Book, String> isbnColumn = new TableColumn<>("ISBN");
                    TableColumn<Book, Integer> authorIdColumn = new TableColumn<>("Author ID");
                    TableColumn<Book, String> authorFnameColumn = new TableColumn<>("Author First Name");
                    TableColumn<Book, String> authorLnameColumn = new TableColumn<>("Author Last Name");
                    TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
                    TableColumn<Book, Integer> genreIdColumn = new TableColumn<>("Genre ID");
                    TableColumn<Book, String> genreColumn = new TableColumn<>("Genre Name");
                    TableColumn<Book, String> bookLocationColumn = new TableColumn<>("Book Location");
                    TableColumn<Book, Integer> totalCopiesColumn = new TableColumn<>("Total Copies");
                    TableColumn<Book, Integer> availableCopiesColumn = new TableColumn<>("Available Copies");

                    idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
                    isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
                    authorIdColumn.setCellValueFactory(new PropertyValueFactory<>("authorId"));
                    authorFnameColumn.setCellValueFactory(new PropertyValueFactory<>("authorFname"));
                    authorLnameColumn.setCellValueFactory(new PropertyValueFactory<>("authorLname"));
                    titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
                    genreIdColumn.setCellValueFactory(new PropertyValueFactory<>("genreId"));
                    genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
                    bookLocationColumn.setCellValueFactory(new PropertyValueFactory<>("bookLocation"));
                    totalCopiesColumn.setCellValueFactory(new PropertyValueFactory<>("totalCopies"));
                    availableCopiesColumn.setCellValueFactory(new PropertyValueFactory<>("availableCopies"));

                    tableView.getColumns().addAll(idColumn, isbnColumn, authorIdColumn, authorFnameColumn, authorLnameColumn, titleColumn, genreIdColumn, genreColumn, bookLocationColumn, totalCopiesColumn, availableCopiesColumn);

                    tableView.getItems().setAll(searchResults);
                    Search.add(tableView, 0, 3);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });

            // show the Search screen
            mainLayout.setCenter(Search);
        });

        searchBooksMenu.getItems().addAll(addTitleMenuItem, addAuthorMenuItem, addISBNMenuItem, addGenreMenuItem);


        // Create the "Checkouts" menu and menu items
        Menu checkoutsMenu = new Menu("Checkouts");
        MenuItem checkoutBookMenuItem = new MenuItem("Check-Out Book");
        checkoutBookMenuItem.setOnAction(event -> {
            mainLayout.setCenter(null);
            // Create the form field
            Label titleIn = new Label("Search book to Check-Out:");
            TextField searchBookKey = new TextField();
            Button searchButton = new Button("Search");
            Button selectButton = new Button("Select Book"); // New button
            GridPane Search = new GridPane();
            Search.setPadding(new Insets(10));
            Search.setHgap(10);
            Search.setVgap(10);
            Search.add(titleIn,0,0,2,1);
            Search.add(searchBookKey, 0, 1);
            Search.add(searchButton, 0, 2);
            Search.add(selectButton, 1, 2); // Add the new button to the GridPane

            searchButton.setOnAction(searchEvent -> {
                String searchKeyword = searchBookKey.getText(); // Replace with the keyword to search for

                String searchQuery = "SELECT * FROM book_info INNER JOIN authors ON book_info.author_id = authors.author_id INNER JOIN genres ON book_info.genre_id = genres.genre_id WHERE CAST(book_info.book_id AS TEXT) = ? OR isbn LIKE ? OR CAST(book_info.author_id AS TEXT) = ? OR title LIKE ? OR CAST(book_info.genre_id AS TEXT) = ? OR book_location LIKE ?";
                try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
                     PreparedStatement pstmt = conn.prepareStatement(searchQuery)) {
                    pstmt.setString(1, "%" + searchKeyword + "%");
                    pstmt.setString(2, "%" + searchKeyword + "%");
                    pstmt.setString(3, "%" + searchKeyword + "%");
                    pstmt.setString(4, "%" + searchKeyword + "%");
                    pstmt.setString(5, "%" + searchKeyword + "%");
                    pstmt.setString(6, "%" + searchKeyword + "%");
                    ResultSet rs = pstmt.executeQuery();

                    List<Book> searchResults = new ArrayList<>();
                    while (rs.next()) {
                        Integer bookId = rs.getInt("book_id");
                        String isbn = rs.getString("isbn");
                        Integer authorId = rs.getInt("author_id");
                        String authorFname = rs.getString("author_fname");
                        String authorLname = rs.getString("author_lname");
                        String title = rs.getString("title");
                        Integer genreId = rs.getInt("genre_id");
                        String genre = rs.getString("genre_name");
                        String bookLocation = rs.getString("book_location");
                        Integer totalCopies = rs.getInt("total_copies");
                        Integer availableCopies = rs.getInt("available_copies");
                        searchResults.add(new Book(bookId, isbn, authorId, authorFname, authorLname, title, genreId, genre, bookLocation, totalCopies, availableCopies));
                    }

                    TableView<Book> tableView = new TableView<>();
                    TableColumn<Book, Integer> idColumn = new TableColumn<>("ID");
                    TableColumn<Book, String> isbnColumn = new TableColumn<>("ISBN");
                    TableColumn<Book, Integer> authorIdColumn = new TableColumn<>("Author ID");
                    TableColumn<Book, String> authorFnameColumn = new TableColumn<>("Author First Name");
                    TableColumn<Book, String> authorLnameColumn = new TableColumn<>("Author Last Name");
                    TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
                    TableColumn<Book, Integer> genreIdColumn = new TableColumn<>("Genre ID");
                    TableColumn<Book, String> genreColumn = new TableColumn<>("Genre Name");
                    TableColumn<Book, String> bookLocationColumn = new TableColumn<>("Book Location");
                    TableColumn<Book, Integer> totalCopiesColumn = new TableColumn<>("Total Copies");
                    TableColumn<Book, Integer> availableCopiesColumn = new TableColumn<>("Available Copies");

                    idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
                    isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
                    authorIdColumn.setCellValueFactory(new PropertyValueFactory<>("authorId"));
                    authorFnameColumn.setCellValueFactory(new PropertyValueFactory<>("authorFname"));
                    authorLnameColumn.setCellValueFactory(new PropertyValueFactory<>("authorLname"));
                    titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
                    genreIdColumn.setCellValueFactory(new PropertyValueFactory<>("genreId"));
                    genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
                    bookLocationColumn.setCellValueFactory(new PropertyValueFactory<>("bookLocation"));
                    totalCopiesColumn.setCellValueFactory(new PropertyValueFactory<>("totalCopies"));
                    availableCopiesColumn.setCellValueFactory(new PropertyValueFactory<>("availableCopies"));

                    selectButton.setOnAction(selectEvent -> {
                        String SelectSearchQuery = "UPDATE books SET available_copies = available_copies - 1 WHERE id = ?";
                        String updateCheckoutDateQuery = "UPDATE checkouts SET checkout_date = CURRENT_DATE WHERE book_id = ?";
                        //Checkouts checkout = new Checkouts();
                        Book selectedBook = tableView.getSelectionModel().getSelectedItem();
                        if (selectedBook == null) {
                            // Display an error message if no book is selected
                            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a book to check-out.");
                            alert.showAndWait();
                        } else {
                            // Display a success message and update the database to check out the book
                            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Your book has been checked-out.");
                            alert.showAndWait();
                            // Update the database to have - 1  the available copies from book table.
                            try (Connection Sconn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);) {
                                PreparedStatement Spstmt = Sconn.prepareStatement(SelectSearchQuery);
                                Spstmt.setInt(1, selectedBook.getId()); // Replace 'selectedBook.getBookId()' with the actual ID of the selected book
                                Spstmt.executeUpdate();

                                // update checkout table to current date
                                PreparedStatement Cpstmt = Sconn.prepareStatement(updateCheckoutDateQuery);
                                Cpstmt.setInt(1, selectedBook.getId());
                                Cpstmt.executeUpdate();

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    tableView.getColumns().addAll(idColumn, isbnColumn, authorIdColumn, authorFnameColumn, authorLnameColumn, titleColumn, genreIdColumn, genreColumn, bookLocationColumn, totalCopiesColumn, availableCopiesColumn);

                    tableView.getItems().setAll(searchResults);
                    Search.add(tableView, 0, 3);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });

            // show the Search screen
            mainLayout.setCenter(Search);
        });



        MenuItem checkinBookMenuItem = new MenuItem("Check-In Book");
        checkinBookMenuItem.setOnAction(event -> {
            mainLayout.setCenter(null);
            // Create the form field
            Label titleIn = new Label("Search book to Check-In:");
            TextField searchBookKey = new TextField();
            Button searchButton = new Button("Search");
            Button selectButton = new Button("Select Book"); // New button
            GridPane Search = new GridPane();
            Search.setPadding(new Insets(10));
            Search.setHgap(10);
            Search.setVgap(10);
            Search.add(titleIn,0,0,2,1);
            Search.add(searchBookKey, 0, 1);
            Search.add(searchButton, 0, 2);
            Search.add(selectButton, 1, 2); // Add the new button to the GridPane

            searchButton.setOnAction(searchEvent -> {
                String searchKeyword = searchBookKey.getText(); // Replace with the keyword to search for

                String searchQuery = "SELECT * FROM book_info INNER JOIN authors ON book_info.author_id = authors.author_id INNER JOIN genres ON book_info.genre_id = genres.genre_id WHERE CAST(book_info.book_id AS TEXT) = ? OR isbn LIKE ? OR CAST(book_info.author_id AS TEXT) = ? OR title LIKE ? OR CAST(book_info.genre_id AS TEXT) = ? OR book_location LIKE ?";
                try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
                     PreparedStatement pstmt = conn.prepareStatement(searchQuery)) {
                    pstmt.setString(1, "%" + searchKeyword + "%");
                    pstmt.setString(2, "%" + searchKeyword + "%");
                    pstmt.setString(3, "%" + searchKeyword + "%");
                    pstmt.setString(4, "%" + searchKeyword + "%");
                    pstmt.setString(5, "%" + searchKeyword + "%");
                    pstmt.setString(6, "%" + searchKeyword + "%");
                    ResultSet rs = pstmt.executeQuery();

                    List<Book> searchResults = new ArrayList<>();
                    while (rs.next()) {
                        Integer bookId = rs.getInt("book_id");
                        String isbn = rs.getString("isbn");
                        Integer authorId = rs.getInt("author_id");
                        String authorFname = rs.getString("author_fname");
                        String authorLname = rs.getString("author_lname");
                        String title = rs.getString("title");
                        Integer genreId = rs.getInt("genre_id");
                        String genre = rs.getString("genre_name");
                        String bookLocation = rs.getString("book_location");
                        Integer totalCopies = rs.getInt("total_copies");
                        Integer availableCopies = rs.getInt("available_copies");
                        searchResults.add(new Book(bookId, isbn, authorId, authorFname, authorLname, title, genreId, genre, bookLocation, totalCopies, availableCopies));
                    }

                    TableView<Book> tableView = new TableView<>();
                    TableColumn<Book, Integer> idColumn = new TableColumn<>("ID");
                    TableColumn<Book, String> isbnColumn = new TableColumn<>("ISBN");
                    TableColumn<Book, Integer> authorIdColumn = new TableColumn<>("Author ID");
                    TableColumn<Book, String> authorFnameColumn = new TableColumn<>("Author First Name");
                    TableColumn<Book, String> authorLnameColumn = new TableColumn<>("Author Last Name");
                    TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
                    TableColumn<Book, Integer> genreIdColumn = new TableColumn<>("Genre ID");
                    TableColumn<Book, String> genreColumn = new TableColumn<>("Genre Name");
                    TableColumn<Book, String> bookLocationColumn = new TableColumn<>("Book Location");
                    TableColumn<Book, Integer> totalCopiesColumn = new TableColumn<>("Total Copies");
                    TableColumn<Book, Integer> availableCopiesColumn = new TableColumn<>("Available Copies");

                    idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
                    isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
                    authorIdColumn.setCellValueFactory(new PropertyValueFactory<>("authorId"));
                    authorFnameColumn.setCellValueFactory(new PropertyValueFactory<>("authorFname"));
                    authorLnameColumn.setCellValueFactory(new PropertyValueFactory<>("authorLname"));
                    titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
                    genreIdColumn.setCellValueFactory(new PropertyValueFactory<>("genreId"));
                    genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
                    bookLocationColumn.setCellValueFactory(new PropertyValueFactory<>("bookLocation"));
                    totalCopiesColumn.setCellValueFactory(new PropertyValueFactory<>("totalCopies"));
                    availableCopiesColumn.setCellValueFactory(new PropertyValueFactory<>("availableCopies"));

                    selectButton.setOnAction(selectEvent -> {
                        String SelectSearchQuery = "UPDATE books SET available_copies = available_copies + 1 WHERE id = ?";
                        String updateCheckoutDateQuery = "UPDATE checkouts SET checkout_return_date = CURRENT_DATE WHERE book_id = ?";
                        //Checkouts checkout = new Checkouts();
                        Book selectedBook = tableView.getSelectionModel().getSelectedItem();
                        if (selectedBook == null) {
                            // Display an error message if no book is selected
                            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a book to check-out.");
                            alert.showAndWait();
                        } else {
                            // Display a success message and update the database to check in the book
                            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Your book has been Checked in!");
                            alert.showAndWait();
                            // Update the database to have - 1  the available copies from book table.
                            try (Connection Sconn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);) {
                                PreparedStatement Spstmt = Sconn.prepareStatement(SelectSearchQuery);
                                Spstmt.setInt(1, selectedBook.getId()); // Replace 'selectedBook.getBookId()' with the actual ID of the selected book
                                Spstmt.executeUpdate();

                                // update checkout table to current date
                                PreparedStatement Cpstmt = Sconn.prepareStatement(updateCheckoutDateQuery);
                                Cpstmt.setInt(1, selectedBook.getId());
                                Cpstmt.executeUpdate();

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    tableView.getColumns().addAll(idColumn, isbnColumn, authorIdColumn, authorFnameColumn, authorLnameColumn, titleColumn, genreIdColumn, genreColumn, bookLocationColumn, totalCopiesColumn, availableCopiesColumn);

                    tableView.getItems().setAll(searchResults);
                    Search.add(tableView, 0, 3);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });

            // show the Search screen
            mainLayout.setCenter(Search);
        });
        MenuItem viewCheckoutsMenuItem = new MenuItem("View Checkouts");
        checkoutsMenu.getItems().addAll(checkoutBookMenuItem,checkinBookMenuItem, viewCheckoutsMenuItem);

        // Create the "Fines" menu and menu items
        Menu finesMenu = new Menu("Fines");
        MenuItem viewFinesMenuItem = new MenuItem("View Fines on Account");
        MenuItem payFinesMenuItem = new MenuItem("Pay Fines");
        finesMenu.getItems().addAll(viewFinesMenuItem, payFinesMenuItem);

        // Create the "Logout" menu and menu items
        Menu logOut= new Menu("Exit");
        MenuItem logOutClick = new MenuItem("Log Out");
        MenuItem sessionClick = new MenuItem("End App Session");
        logOutClick.setOnAction(event -> {
            primaryStage.show();
            teritiaryStage.close();
            //System.out.println("Logged out...");
        });
        sessionClick.setOnAction(event -> {
            //System.out.println("Program Exiting...");
            System.exit(0);
        });
        logOut.getItems().addAll(logOutClick, sessionClick);

        // Add menus to the menu bar
        menuBar.getMenus().addAll(homeMenu, searchBooksMenu, checkoutsMenu, finesMenu, logOut);

        // Create the main layout
        mainLayout = new BorderPane();
        mainLayout.setTop(menuBar);

        // Create the main scene and show it
        Scene mainScene = new Scene(mainLayout, 800, 600);
        teritiaryStage.setScene(mainScene);
        teritiaryStage.show();

        Image logo_image = new Image(getClass().getResourceAsStream("/images/SqueakyCheeseLogo.png"));

        // Create the image view
        ImageView imageView = new ImageView(logo_image);
        imageView.setFitWidth(150);
        imageView.setFitHeight(100);

        mainLayout.setBottom(imageView);
        BorderPane.setAlignment(imageView, Pos.BOTTOM_RIGHT);

        Label welcomeLabel = new Label("Welcome to the Squeaky Cheese LMS!\n\n- You can search for books with filters,\n- Check-in and check-out books,\n- Check for fines\n\nHave a great day!");
        welcomeLabel.setFont(Font.font("Arial",20));
        mainLayout.setCenter(welcomeLabel);

    }

    /*
    // Method to show the add book form in a new window
    private void showAddBookForm() {
        // Create the form
        GridPane addBookForm = new GridPane();
        addBookForm.setPadding(new Insets(10));
        addBookForm.setHgap(5);
        addBookForm.setVgap(5);

        // Add form fields
        Label titleLabel = new Label("Title:");
        TextField titleField = new TextField();
        Label authorLabel = new Label("Author:");
        TextField authorField = new TextField();
        Label isbnLabel = new Label("ISBN:");
        TextField isbnField = new TextField();
        Label publisherLabel = new Label("Publisher:");
        TextField publisherField = new TextField();
        Button submitButton = new Button("Submit");

        // Add form fields to the grid
        addBookForm.add(titleLabel, 0, 0);
        addBookForm.add(titleField, 1, 0);
        addBookForm.add(authorLabel, 0, 1);
        addBookForm.add(authorField, 1, 1);
        addBookForm.add(isbnLabel, 0, 2);
        addBookForm.add(isbnField, 1, 2);
        addBookForm.add(publisherLabel, 0, 3);
        addBookForm.add(publisherField, 1, 3);
        addBookForm.add(submitButton, 1, 4);

        // Create the add book scene
        Scene addBookScene = new Scene(addBookForm, 280, 200);

        // Create the add book stage
        Stage addBookStage = new Stage();
        addBookStage.setTitle("Add Book");
        addBookStage.setScene(addBookScene);
        addBookStage.show();

        // Add event handler for the submit button
        submitButton.setOnAction(e -> {
            // Code to add book to database
            // ...
            // Close the add book stage
            addBookStage.close();
        });
    }
*/

    public static void main(String[] args) {
        launch(args);
    }
}
