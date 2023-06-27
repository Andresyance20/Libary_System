import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ReferenceFiles.Employees;
import org.springframework.jdbc.core.ResultSetExtractor;

public class AuthorsExtractor implements ResultSetExtractor<List<Authors>>
{
    @Override
    public List<Authors> extractData(ResultSet rs) throws SQLException
    {
        // create a list of ReferenceFiles.Authors objects to store
        // each row in the result set will represent  Employee
        List<Authors> AuthorsList = new ArrayList<>();

        // read/process the results as necessary

        // the results start prior to the first row
        // .next() moves to the next row
        // .next() returns false when it moves past the last row
        while(rs.next())
        {
            Authors a = new Authors();

            Integer author_id;
            String authorFName;
            String authorLName;

            author_id =rs.getInt(Authors.AUTHOR_ID_COLUMN_NAME);

            // '0' is returned if an integer value was null
            // need to check if the primitive was null since primitives cannot hold null values in Java
            // this distinguishes between a '0' value and a '0' null
            //.wasNull() checks if the last value read was null
            if(rs.wasNull())
            {
                // represent the null in some way different than an actual value
                // could use wrapper classes here (Integer vs int)
                // Integer can hold null, int cannot
                author_id = null;
            }

            authorFName = rs.getString(Authors.AUTHOR_FIRST_NAME_COLUMN_NAME);
            authorLName = rs.getString(Authors.AUTHOR_LAST_NAME_COLUMN_NAME);

            // Set all values for the ReferenceFiles.Authors
            a.setAuthorId(author_id);
            a.setAuthorFName(authorFName);
            a.setAuthorLName(authorLName);

            // Add the ReferenceFiles.Authors to the list
            AuthorsList.add(a);
        }
        return AuthorsList;
    }
}
