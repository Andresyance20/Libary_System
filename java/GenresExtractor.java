import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ReferenceFiles.Employees;
import org.springframework.jdbc.core.ResultSetExtractor;

public class GenresExtractor implements ResultSetExtractor<List<Genres>>
{
    @Override
    public List<Genres> extractData(ResultSet rs) throws SQLException
    {
        // create a list of ReferenceFiles.Employees objects to store
        // each row in the result set will represent  Employee
        List<Genres> GenresList = new ArrayList<>();

        // read/process the results as necessary

        // the results start prior to the first row
        // .next() moves to the next row
        // .next() returns false when it moves past the last row
        while(rs.next()) {
            // a builder would be nice here, but I didn't want to complicate the design anymore
            // used bean-style instead
            Genres g = new Genres();

            Integer genreId;
            String genreName;

            genreId = rs.getInt(Genres.GENRE_ID_COLUMN_NAME);

            // '0' is returned if an integer value was null
            // need to check if the primitive was null since primitives cannot hold null values in Java
            // this distinguishes between a '0' value and a '0' null
            //.wasNull() checks if the last value read was null
            if (rs.wasNull()) {
                // represent the null in some way different than an actual value
                // could use wrapper classes here (Integer vs int)
                // Integer can hold null, int cannot
                genreId = null;
            }

            genreName = rs.getString(Genres.GENRE_NAME_COLUMN_NAME);

            // Set all values for the ReferenceFiles.Genres
            g.setGenreId(genreId);
            g.setGenreName(genreName);

            // Add the ReferenceFiles.Genres to the list
            GenresList.add(g);
        }
        return GenresList;
    }
}
