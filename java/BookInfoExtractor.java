import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ReferenceFiles.Employees;
import org.springframework.jdbc.core.ResultSetExtractor;

public class BookInfoExtractor implements ResultSetExtractor<List<BookInfo>>
{
    @Override
    public List<BookInfo> extractData(ResultSet rs) throws SQLException
    {
        // create a list of ReferenceFiles.Employees objects to store
        // each row in the result set will represent  Employee
        List<BookInfo> BookInfoList = new ArrayList<>();

        // read/process the results as necessary

        // the results start prior to the first row
        // .next() moves to the next row
        // .next() returns false when it moves past the last row
        while(rs.next())
        {
            // a builder would be nice here, but I didn't want to complicate the design anymore
            // used bean-style instead
            BookInfo bi = new BookInfo();

            Integer bookId;
            String isbn;
            Integer authorId;
            String title;
            Integer genreId;
            Integer availableCopies;
            Integer totalCopies;

            bookId = rs.getInt(BookInfo.BOOK_ID_COLUMN_NAME);

            // '0' is returned if an integer value was null
            // need to check if the primitive was null since primitives cannot hold null values in Java
            // this distinguishes between a '0' value and a '0' null
            //.wasNull() checks if the last value read was null
            if(rs.wasNull())
            {
                // represent the null in some way different than an actual value
                // could use wrapper classes here (Integer vs int)
                // Integer can hold null, int cannot
                bookId = null;
            }

            isbn = rs.getString(BookInfo.ISBN_COLUMN_NAME);
            authorId = rs.getInt(BookInfo.AUTHOR_ID_COLUMN_NAME);

            if(rs.wasNull())
            {
                authorId = null;
            }

            title = rs.getString(BookInfo.TITLE_COLUMN_NAME);
            genreId = rs.getInt(BookInfo.GENRE_ID_COLUMN_NAME);

            if(rs.wasNull())
            {
                genreId = null;
            }

            availableCopies = rs.getInt(BookInfo.AVAILABLE_COPIES_COLUMN_NAME);
            totalCopies = rs.getInt(BookInfo.TOTAL_COPIES_COLUMN_NAME);

            // Set all values for the ReferenceFiles.BookInfo
            bi.setBookId(bookId);
            bi.setISBN(isbn);
            bi.setAuthorId(authorId);
            bi.setTitle(title);
            bi.setGenreId(genreId);
            bi.setAvailableCopies(availableCopies);
            bi.setTotalCopies(totalCopies);

            // Add the ReferenceFiles.BookInfo to the list
            BookInfoList.add(bi);
        }
        return BookInfoList;
    }
}
