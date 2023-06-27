import java.util.Objects;

// Class to hold data for input/output of an insertion/query
// Referred to as a model
public class CheckoutFines
{
    // String constants to hold the column names of the database
    // Useful for referencing when inserting/querying
    public static final String FINE_ID_COLUMN_NAME = "fine_id";
    public static final String CHECKOUT_ID_COLUMN_NAME = "checkout_id";
    public static final String MEMBER_ID_COLUMN_NAME = "member_id";
    public static final String CHECKOUT_OVERDUE_COLUMN_NAME = "checkout_overdue";
    public static final String FINE_AMOUNT_COLUMN_NAME = "fine_amount";

    // Using wrapper classes instead of int / boolean primitives to hold null values if necessary
    private Integer fineId;
    private Integer checkoutId;
    private Integer memberId;
    private boolean checkoutOverdue;
    private double fineAmount;

    /* Getters and Setters */
    public Integer getFineId() { return fineId; }
    public void setFineId(Integer fi) { fineId = fi; }

    public Integer getCheckoutId() { return checkoutId; }
    public void setCheckoutId(Integer ci) { checkoutId = ci; }

    public Integer getMemberId() { return memberId; }
    public void setMemberId(Integer mi) { memberId = mi; }

    public boolean getCheckoutOverdue() { return checkoutOverdue; }
    public void setCheckoutOverdue(boolean co) { checkoutOverdue = co; }

    public double getFineAmount() { return fineAmount; }
    public void setFineAmount(double fa) { fineAmount = fa; }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CheckoutFines cf = (CheckoutFines) o;
        return Objects.equals(fineId, cf.fineId) &&
                Objects.equals(checkoutId, cf.checkoutId) &&
                Objects.equals(memberId, cf.memberId) &&
                Objects.equals(checkoutOverdue, cf.checkoutOverdue) &&
                Objects.equals(fineAmount, cf.fineAmount);
    }

    @Override
    public int hashCode() { return Objects.hash(fineId, checkoutId, memberId, checkoutOverdue, fineAmount); }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("checkout_fines{");
        sb.append("fine_id=").append(fineId);
        sb.append(", checkout_id='").append(checkoutId).append('\'');
        sb.append(", member_id='").append(memberId).append('\'');
        sb.append(", checkout_overdue='").append(checkoutOverdue).append('\'');
        sb.append(", fine_amount='").append(fineAmount).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
