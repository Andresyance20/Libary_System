import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Member {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String username;
    private String password;

    public Member(int id, String firstName, String lastName, String email, String phoneNumber, String username, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
    }


    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
