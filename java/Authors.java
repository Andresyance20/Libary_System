import java.util.Objects;

// Class to hold data for input/output of an insertion/query
// Referred to as a model
public class Authors
{
    // String constants to hold the column names of the database
    // Useful for referencing when inserting/querying
    public static final String AUTHOR_ID_COLUMN_NAME = "author_id";
    public static final String AUTHOR_FIRST_NAME_COLUMN_NAME = "author_fname";
    public static final String AUTHOR_LAST_NAME_COLUMN_NAME = "author_lname";

    // Using wrapper classes instead of int / boolean primitives to hold null values if necessary
    private Integer authorId;
    private String authorFName;
    private String authorLName;

    /* Getters and Setters */
    public Integer getAuthorId() { return authorId; }
    public void setAuthorId(Integer ai) { authorId = ai; }

    public String getAuthorFName() { return authorFName; }
    public void setAuthorFName(String afn) { authorFName = afn; }

    public String getAuthorFLName() { return authorLName; }
    public void setAuthorLName(String aln) { authorLName = aln; }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authors a = (Authors) o;
        return Objects.equals(authorId, a.authorId) &&
                Objects.equals(authorFName, a.authorFName) &&
                Objects.equals(authorLName, a.authorLName);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(authorId, authorFName, authorLName);
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("author{");
        sb.append("author_id=").append(authorId);
        sb.append(", author_fname='").append(authorFName).append('\'');
        sb.append(", author_lname='").append(authorLName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}