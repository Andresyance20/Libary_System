import java.sql.Date;
import java.util.Objects;

// Class to hold data for input/output of an insertion/query
// Referred to as a model
public class Checkouts
{
    // String constants to hold the column names of the database
    // Useful for referencing when inserting/querying
    public static final String CHECKOUT_ID_COLUMN_NAME = "checkout_id";
    public static final String BOOK_ID_COLUMN_NAME = "book_id";
    public static final String MEMBER_ID_COLUMN_NAME = "member_id";
    public static final String CHECKOUT_DATE_COLUMN_NAME = "checkout_date";
    public static final String RETURN_DATE_COLUMN_NAME = "return_date";
    public static final String CHECKOUT_OVERDUE_COLUMN_NAME = "checkout_overdue";

    // Using wrapper classes instead of int / boolean primitives to hold null values if necessary
    private Integer checkoutId;
    private Integer bookId;
    private Integer memberId;
    private Date checkoutDate;
    private Date returnDate;
    private boolean checkoutOverdue;

    /* Getters and Setters */
    public Integer getCheckoutId() { return checkoutId; }
    public void setCheckoutId(Integer ci) { checkoutId = ci; }

    public Integer getBookId() { return bookId; }
    public void setBookId(Integer bi) { bookId = bi; }

    public Integer getMemberId() { return memberId; }
    public void setMemberId(Integer mi) { memberId = mi; }

    public Date getCheckoutDate() { return checkoutDate; }
    public void setCheckoutDate(Date cd) { checkoutDate = cd; }

    public Date getReturnDate() { return returnDate; }
    public void setReturnDate(Date rd) { returnDate = rd; }

    public boolean getCheckoutOverdue() { return checkoutOverdue; }
    public void setCheckoutOverdue(boolean co) { checkoutOverdue = co; }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Checkouts c = (Checkouts) o;
        return Objects.equals(checkoutId, c.checkoutId) &&
                Objects.equals(bookId, c.bookId) &&
                Objects.equals(memberId, c.memberId) &&
                Objects.equals(checkoutDate, c.checkoutDate) &&
                Objects.equals(returnDate, c.returnDate) &&
                Objects.equals(checkoutOverdue, c.checkoutOverdue);
    }

    @Override
    public int hashCode() { return Objects.hash(checkoutId, bookId, memberId, checkoutDate, returnDate, checkoutOverdue ); }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("checkouts{");
        sb.append("checkout_id=").append(checkoutId);
        sb.append(", book_id='").append(bookId).append('\'');
        sb.append(", member_id='").append(memberId).append('\'');
        sb.append(", checkout_date='").append(checkoutDate).append('\'');
        sb.append(", return_date='").append(returnDate).append('\'');
        sb.append(", checkout_overdue='").append(checkoutOverdue).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
