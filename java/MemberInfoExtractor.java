import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

public class MemberInfoExtractor implements ResultSetExtractor<List<MemberInfo>>
{
    @Override
    public List<MemberInfo> extractData(ResultSet rs) throws SQLException
    {
        // create a list of ReferenceFiles.MemberInfo objects to store
        // each row in the result set will represent MemberInfo
        List<MemberInfo> MemberInfoList = new ArrayList<>();

        // read/process the results as necessary

        // the results start prior to the first row
        // .next() moves to the next row
        // .next() returns false when it moves past the last row
        while(rs.next())
        {
            // a builder would be nice here, but I didn't want to complicate the design anymore
            // used bean-style instead
            MemberInfo mi = new MemberInfo();

            Integer memberId;
            String fName;
            String lName;
            String email;
            String phoneNumber;

            memberId = rs.getInt(MemberInfo.MEMBER_ID_COLUMN_NAME);

            // '0' is returned if an integer value was null
            // need to check if the primitive was null since primitives cannot hold null values in Java
            // this distinguishes between a '0' value and a '0' null
            //.wasNull() checks if the last value read was null
            if (rs.wasNull()) {
                // represent the null in some way different than an actual value
                // could use wrapper classes here (Integer vs int)
                // Integer can hold null, int cannot
                memberId = null;
            }

            // Set all values for the ReferenceFiles.MemberInfo
            fName = rs.getString(MemberInfo.FIRST_NAME_COLUMN_NAME);
            lName = rs.getString(MemberInfo.LAST_NAME_COLUMN_NAME);
            email = rs.getString(MemberInfo.EMAIL_COLUMN_NAME);
            phoneNumber = rs.getString(MemberInfo.PHONE_NUMBER_COLUMN_NAME);

            // Set all values for the ReferenceFiles.MemberInfo
            mi.setMemberId(memberId);
            mi.setFName(fName);
            mi.setLName(lName);
            mi.setEmail(email);
            mi.setPhoneNumber(phoneNumber);

            // Add the ReferenceFiles.MemberInfo to the list
            MemberInfoList.add(mi);
        }

        return MemberInfoList;
    }
}
