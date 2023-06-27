import java.util.Objects;

// Class to hold data for input/output of an insertion/query
// Referred to as a model
public class MemberLogins
{
    // String constants to hold the column names of the database
    // Useful for referencing when inserting/querying
    public static final String MEMBER_ID_COLUMN_NAME = "member_id";
    public static final String USERNAME_COLUMN_NAME = "username";
    public static final String PASSWORD_COLUMN_NAME = "password";
    public static final String ISADMIN_COLUMN_NAME = "isadmin";

    // Using wrapper classes instead of int / boolean primitives to hold null values if necessary
    private Integer memberId;
    private String username;
    private String password;
    private boolean isadmin;

    /* Getters and Setters */
    public Integer getMemberId() { return memberId; }
    public void setMemberId(Integer mi) { memberId = mi; }

    public String getUsername() { return username; }
    public void setUsername(String u) { username = u;}

    public String getPassword() { return password; }
    public void setPassword(String p) { password = p; }

    public boolean getIsadmin() { return isadmin; }
    public void setIsadmin(boolean i) { isadmin = i; }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberLogins ml = (MemberLogins) o;
        return Objects.equals(memberId, ml.memberId) &&
                Objects.equals(username, ml.username) &&
                Objects.equals(password, ml.password) &&
                Objects.equals(isadmin, ml.isadmin);
    }

    @Override
    public int hashCode() { return Objects.hash(memberId, username, password, isadmin); }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("member{");
        sb.append("member_id=").append(memberId);
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", isadmin'").append(isadmin).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
