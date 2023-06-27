import java.util.Objects;

// Class to hold data for input/output of an insertion/query
// Referred to as a model
public class MemberInfo
{
    // String constants to hold the column names of the database
    // Useful for referencing when inserting/querying
    public static final String MEMBER_ID_COLUMN_NAME = "member_id";
    public static final String FIRST_NAME_COLUMN_NAME = "fname";
    public static final String LAST_NAME_COLUMN_NAME = "lname";
    public static final String EMAIL_COLUMN_NAME = "email";
    public static final String PHONE_NUMBER_COLUMN_NAME = "phone_number";

    // Using wrapper classes instead of int / boolean primitives to hold null values if necessary
    private Integer memberId;
    private String fName;
    private String lName;
    private String email;
    private String phoneNumber;

    /* Getters and Setters */
    public Integer getMemberId() { return memberId; }
    public void setMemberId(Integer mi) { memberId = mi; }

    public String getFName() { return fName; }
    public void setFName(String fn) { fName = fn; }

    public String getLName() { return lName; }
    public void setLName(String ln) { lName = ln; }

    public String getEmail() { return email; }
    public void setEmail(String e) { email = e; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String pn) { phoneNumber = pn; }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberInfo mi = (MemberInfo) o;
        return Objects.equals(memberId, mi.memberId) &&
                Objects.equals(fName, mi.fName) &&
                Objects.equals(lName, mi.lName) &&
                Objects.equals(email, mi.email) &&
                Objects.equals(phoneNumber, mi.phoneNumber);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(memberId, fName, lName, email, phoneNumber);
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("member_info{");
        sb.append("member_id=").append(memberId);
        sb.append(", fname='").append(fName).append('\'');
        sb.append(", lname='").append(lName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", phone_number='").append(phoneNumber).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
