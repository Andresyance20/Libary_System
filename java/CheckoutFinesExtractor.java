import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ReferenceFiles.Employees;
import org.springframework.jdbc.core.ResultSetExtractor;

public class CheckoutFinesExtractor implements ResultSetExtractor<List<CheckoutFines>>
{
    @Override
    public List<CheckoutFines> extractData(ResultSet rs) throws SQLException
    {
        // create a list of ReferenceFiles.CheckoutFines objects to store
        // each row in the result set will represent CheckoutFines
        List<CheckoutFines> CheckoutFinesList = new ArrayList<>();

        // the results start prior to the first row
        // .next() moves to the next row
        // .next() returns false when it moves past the last row
        while(rs.next())
        {
            // a builder would be nice here, but I didn't want to complicate the design anymore
            // used bean-style instead
            CheckoutFines cf = new CheckoutFines();

            Integer fineId;
            Integer checkoutId;
            Integer memberId;
            boolean checkoutOverdue;
            double fineAmount;

            fineId = rs.getInt(CheckoutFines.FINE_ID_COLUMN_NAME);

            // '0' is returned if an integer value was null
            // need to check if the primitive was null since primitives cannot hold null values in Java
            // this distinguishes between a '0' value and a '0' null
            //.wasNull() checks if the last value read was null
            if(rs.wasNull())
            {
                // represent the null in some way different than an actual value
                // could use wrapper classes here (Integer vs int)
                // Integer can hold null, int cannot
                fineId = null;
            }

            checkoutId = rs.getInt(CheckoutFines.CHECKOUT_ID_COLUMN_NAME);
            memberId = rs.getInt(CheckoutFines.MEMBER_ID_COLUMN_NAME);
            checkoutOverdue = rs.getBoolean(CheckoutFines.CHECKOUT_OVERDUE_COLUMN_NAME);
            fineAmount = rs.getDouble(CheckoutFines.FINE_AMOUNT_COLUMN_NAME);

            // Set all values for the ReferenceFiles.CheckoutFines
            cf.setFineId(fineId);
            cf.setCheckoutId(checkoutId);
            cf.setMemberId(memberId);
            cf.setCheckoutOverdue(checkoutOverdue);
            cf.setFineAmount(fineAmount);

            // Add the ReferenceFiles.CheckoutFines to the list
            CheckoutFinesList.add(cf);
        }
        return CheckoutFinesList;
    }
}
