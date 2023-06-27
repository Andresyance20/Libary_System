import java.util.Objects;

// Class to hold data for input/output of an insertion/query
// Referred to as a model
public class Holds
{
    // String constants to hold the column names of the database
    // Useful for referencing when inserting/querying
    public static final String BOOK_ID_COLUMN_NAME = "book_id";
    public static final String MEMBER_ID_COLUMN_NAME = "member_id";

    // Using wrapper classes instead of int / boolean primitives to hold null values if necessary
    private Integer bookId;
    private Integer memberId;

    /* Getters and Setters */
    public Integer getBookId() { return bookId; }
    public void setBookId(Integer bi) { bookId = bi; }

    public Integer getMemberId() { return memberId; }
    public void setMemberId(Integer mi) { memberId = mi; }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Holds h = (Holds) o;
        return Objects.equals(bookId, h.bookId) &&
                Objects.equals(memberId, h.memberId);
    }

    @Override
    public int hashCode() { return Objects.hash(bookId, memberId); }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("holds{");
        sb.append("book_id=").append(bookId);
        sb.append(", member_id='").append(memberId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
