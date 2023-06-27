import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Book {
    private Integer id;
    private String isbn;
    private Integer authorId;
    private String authorFname;
    private String authorLname;
    private String title;
    private Integer genreId;
    private String genre;
    private String bookLocation;
    private Integer availableCopies;
    private Integer totalCopies;

    public Book(Integer id, String isbn, Integer authorId, String authorFname, String authorLname, String title, Integer genreId, String genre, String bookLocation, Integer totalCopies, Integer avialableCopies)
    {
        this.id = id;
        this.isbn = isbn;
        this.authorId = authorId;
        this.authorFname = authorFname;
        this.authorLname = authorLname;
        this.title = title;
        this.genreId = genreId;
        this.genre = genre;
        this.bookLocation = bookLocation;
        this.availableCopies = avialableCopies;
        this.totalCopies = totalCopies;
    }

    public Integer getId() { return id; }

    public String getIsbn() { return isbn; }

    public Integer getAuthorId() { return authorId; }

    public String getAuthorFname() { return authorFname; }

    public String getAuthorLname() { return authorLname; }

    public String getTitle() { return title; }

    public Integer getGenreId() { return genreId; }

    public String getGenre() { return genre; }

    public String getBookLocation() { return bookLocation; }

    public Integer getAvailableCopies() { return availableCopies; }

    public Integer getTotalCopies() { return totalCopies; }
}
