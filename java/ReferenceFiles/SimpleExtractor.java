package ReferenceFiles;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

public class SimpleExtractor implements ResultSetExtractor<List<Employees>>
{
	@Override
	public List<Employees> extractData(ResultSet rs) throws SQLException
	{
		// create a list of ReferenceFiles.Employees objects to store
		// each row in the result set will represent  Employee
		List<Employees> EmployeesList = new ArrayList<>();

		// read/process the results as necessary

		// the results start prior to the first row
		// .next() moves to the next row
		// .next() returns false when it moves past the last row
		while(rs.next())
		{
			// a builder would be nice here, but I didn't want to complicate the design anymore
			// used bean-style instead
			Employees employee = new Employees();

			Integer id;
			String firstName;
			String lastName;
			String email;
			// TODO More columns here

			id = rs.getInt(Employees.EMPLOYEE_ID_COLUMN_NAME);

			// '0' is returned if an integer value was null
			// need to check if the primitive was null since primitives cannot hold null values in Java
			// this distinguishes between a '0' value and a '0' null
			//.wasNull() checks if the last value read was null
			if(rs.wasNull())
			{
				// represent the null in some way different than an actual value
				// could use wrapper classes here (Integer vs int)
				// Integer can hold null, int cannot
				id = null;
			}

			firstName = rs.getString(Employees.FIRST_NAME_COLUMN_NAME);
			lastName = rs.getString(Employees.LAST_NAME_COLUMN_NAME);
			email = rs.getString(Employees.EMAIL_COLUMN_NAME);
			// TODO MORE

			// Set all values for the ReferenceFiles.Employees
			employee.setEmployeeId(id);
			employee.setFirstName(firstName);
			employee.setLastName(lastName);
			employee.setEmail(email);
			// TODO MORE

			// Add the ReferenceFiles.Employees to the list
			EmployeesList.add(employee);
		}

		return EmployeesList;
	}

}
