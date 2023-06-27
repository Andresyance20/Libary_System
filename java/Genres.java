import java.util.Objects;

// Class to hold data for input/output of an insertion/query
// Referred to as a model
public class Genres
{
    // String constants to hold the column names of the database
    // Useful for referencing when inserting/querying
    public static final String GENRE_ID_COLUMN_NAME = "genre_id";
    public static final String GENRE_NAME_COLUMN_NAME = "genre_name";

    // Using wrapper classes instead of int / boolean primitives to hold null values if necessary
    private Integer genreId;
    private String genreName;

    public Integer getGenreId() { return genreId; }
    public void setGenreId(Integer gi) { genreId = gi; }

    public String getGenreName() { return genreName; }
    public void setGenreName(String gn) { genreName = gn; }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genres g = (Genres) o;
        return Objects.equals(genreId, g.genreId) &&
                Objects.equals(genreName, g.genreName);
    }

    @Override
    public int hashCode() { return Objects.hash(genreId, genreName); }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("genres{");
        sb.append("genre_id=").append(genreId);
        sb.append(", genre_name='").append(genreName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
