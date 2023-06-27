import java.util.Objects;

// Class to hold data for input/output of an insertion/query
// Referred to as a model
public class BookInfo {
    // String constants to hold the column names of the database
    // Useful for referencing when inserting/querying
    public static final String BOOK_ID_COLUMN_NAME = "book_id";
    public static final String ISBN_COLUMN_NAME = "isbn";
    public static final String AUTHOR_ID_COLUMN_NAME = "author_id";
    public static final String TITLE_COLUMN_NAME = "title";
    public static final String GENRE_ID_COLUMN_NAME = "genre_id";
    public static final String AVAILABLE_COPIES_COLUMN_NAME = "available_copies";
    public static final String TOTAL_COPIES_COLUMN_NAME = "total_copies";

    // Using wrapper classes instead of int / boolean primitives to hold null values if necessary
    private Integer bookId;
    private String isbn;
    private Integer authorId;
    private String title;
    private Integer genreId;
    private Integer availableCopies;
    private Integer totalCopies;

    /* Getters and Setters */
    public Integer getBookId() { return bookId; }
    public void setBookId(Integer bi) { bookId = bi; }

    public String getISBN() { return isbn; }
    public void setISBN(String i) { isbn = i; }

    public Integer getAuthorId() { return authorId; }
    public void setAuthorId(Integer ai) { authorId = ai; }

    public String getTitle() { return title; }
    public void setTitle(String t) { title = t; }

    public Integer getGenreId() { return genreId; }
    public void setGenreId(Integer gi) { genreId = gi; }

    public Integer getAvailableCopies() { return availableCopies; }
    public void setAvailableCopies(Integer ac) { availableCopies = ac; }

    public Integer getTotalCopies() { return totalCopies; }
    public void setTotalCopies(Integer tc) { totalCopies = tc; }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookInfo bi = (BookInfo) o;
        return Objects.equals(bookId, bi.bookId) &&
                Objects.equals(isbn, bi.isbn) &&
                Objects.equals(authorId, bi.authorId) &&
                Objects.equals(title, bi.title) &&
                Objects.equals(genreId, bi.genreId) &&
                Objects.equals(availableCopies, bi.availableCopies) &&
                Objects.equals(totalCopies, bi.totalCopies);
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("book_info{");
        sb.append("book_id=").append(bookId);
        sb.append(", isbn='").append(isbn).append('\'');
        sb.append(", author_id='").append(authorId).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", genre_id='").append(genreId).append('\'');
        sb.append(", availableCopies='").append(availableCopies).append('\'');
        sb.append(", totalCopies='").append(totalCopies).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
