import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

public class CheckoutsExtractor implements ResultSetExtractor<List<Checkouts>>
{
    @Override
    public List<Checkouts> extractData(ResultSet rs) throws SQLException
    {
        // create a list of Checkouts objects to store
        // each row in the result set will represent Checkout
        List<Checkouts> CheckoutsList = new ArrayList<>();

        // read/process the results as necessary

        // the results start prior to the first row
        // .next() moves to the next row
        // .next() returns false when it moves past the last row
        while(rs.next())
        {
            // a builder would be nice here, but I didn't want to complicate the design anymore
            // used bean-style instead
            Checkouts checkout = new Checkouts();

            Integer checkoutId;
            Integer bookId;
            Integer memberId;
            Date checkoutDate;
            Date returnDate;
            boolean checkoutOverdue;

            checkoutId = rs.getInt(Checkouts.CHECKOUT_ID_COLUMN_NAME);

            // '0' is returned if an integer value was null
            // need to check if the primitive was null since primitives cannot hold null values in Java
            // this distinguishes between a '0' value and a '0' null
            //.wasNull() checks if the last value read was null
            if(rs.wasNull())
            {
                // represent the null in some way different than an actual value
                // could use wrapper classes here (Integer vs int)
                // Integer can hold null, int cannot
                checkoutId = null;
            }

            bookId = rs.getInt(Checkouts.BOOK_ID_COLUMN_NAME);
            if(rs.wasNull())
            {
                bookId = null;
            }

            memberId = rs.getInt(Checkouts.MEMBER_ID_COLUMN_NAME);
            if(rs.wasNull())
            {
                memberId = null;
            }

            checkoutDate = rs.getDate(Checkouts.CHECKOUT_DATE_COLUMN_NAME);
            returnDate = rs.getDate(Checkouts.RETURN_DATE_COLUMN_NAME);
            checkoutOverdue = rs.getBoolean(Checkouts.CHECKOUT_OVERDUE_COLUMN_NAME);

            // Set all values for the ReferenceFiles.Checkouts
            checkout.setCheckoutId(checkoutId);
            checkout.setBookId(bookId);
            checkout.setMemberId(memberId);
            checkout.setCheckoutDate(checkoutDate);
            checkout.setReturnDate(returnDate);
            checkout.setCheckoutOverdue(checkoutOverdue);

            // Add the ReferenceFiles.Checkouts to the list
            CheckoutsList.add(checkout);
        }

        return CheckoutsList;
    }

}
