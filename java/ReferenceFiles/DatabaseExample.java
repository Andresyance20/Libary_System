package ReferenceFiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import org.springframework.jdbc.core.JdbcTemplate;

public class DatabaseExample
{	
	// Typically, the data for these fields is stored in a properties file
	// I just hard-coded the information here for simplicity
	// You may need to change the Strings to match what your database credentials are
	
	// Where my database is (server + database name + schema)
	private static final String DATABASE_URL =
			"jdbc:postgresql://marigold.csse.uwplatt.edu:5432/rognsvoogr?CurrentSchema=lib_db_project";
	
	// What user I am connecting as
	private static final String DATABASE_USER = "rognsvoogr";
	
	// Password for the user
	private static final String DATABASE_PASSWORD = "PLT157933324";
	
	// What driver I am using
	// Since out database is postgres we will use the postgres driver
	// For other databases (SQLServer, Oracle, MySQL) another driver would be used
	private static final String DATABASE_DRIVER = "org.postgresql.Driver";


	// Class for the driver manager to manage the JDBC connection and interface with the driver
	private DriverManagerDataSource dataSource;
	
	// Class to provide methods to execute commands on the database
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	// Constructor to initialize the data source and namedParameterJdbcTemplate
	public DatabaseExample()
	{
		dataSource = new DriverManagerDataSource();
		
        dataSource.setUrl(DATABASE_URL);
        dataSource.setUsername(DATABASE_USER);
        dataSource.setPassword(DATABASE_PASSWORD);
        dataSource.setDriverClassName(DATABASE_DRIVER);

        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public int new_insert()
	{
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "INSERT INTO departments (department_id, department_name, location_id) VALUES (14, 'Test2 Department', 2400)";

		int rows = jdbcTemplate.update(sql);
		if (rows > 0) {
			System.out.println("A new row has been inserted.");
		}
		return rows;
	}

	public int insert(Employees employee)
	{
		// Template string for the query
		// This can hold variables, to be replaced
		String queryTemplate = "INSERT INTO employees "
				+ "("+ Employees.FIRST_NAME_COLUMN_NAME + ", " + Employees.LAST_NAME_COLUMN_NAME + ") "
				+ "VALUES(:firstName, :lastName)";

		// The variables (strings) should also be constants stored somewhere

		// Map of variable names and the values to replace with
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    parameters.addValue("firstName", employee.getFirstName());
	    parameters.addValue("lastName", employee.getLastName());
	    
	    // Empty list of maps to hold a mapping for column names and their values, which is held by the key holder (see next line)
	    List<Map<String, Object>> keyList = new ArrayList<>();
	    
	    // Special object to hold any values from the inserted row
	    // Typically used for columns we did not provide, such as the "id" column
	    KeyHolder keyHolder = new GeneratedKeyHolder(keyList);
	    
	    // Statement to insert the row
	    int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);
	    
	    // Can get the keys returned
	    Map<String, Object> keyMap = keyHolder.getKeys();
	    	    
	    int id = (int)keyMap.get(Employees.EMPLOYEE_ID_COLUMN_NAME);

		// Add the generated columns to the employee object
		employee.setEmployeeId(id);

	    return rowsAffected;
	}

	/**
	 * Method to select entries in the employees table by their id
	 * @param id The id of the employees to select
	 */
	public void selectById(int id)
	{
		// create an object to extract the data, this implements ResultSetExtractor<ResultSet>
		SimpleExtractor extractor = new SimpleExtractor();
		
		// create the template for the query
		// These strings are typically stored in a .sql file
		String queryTemplate = "SELECT * FROM employees WHERE employee_id = :id";

		// create a mapping of any variable substitutions
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    parameters.addValue("id", id);
		 
	    // execute the query and return the result set -> the extractData method in the extractor will be called
	    // Think of the result set as the table of data returned, it also includes other meta-data
	    List<Employees> employeesList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
	    
	    System.out.println(employeesList);
	}

	/**
	 * Updates the employees row
	 *
	 * @return Returns the number of rows updated
	 */
	public int update(String oldFirstName, String newFirstName)
	{
		String queryTemplate = "UPDATE employees SET " + Employees.FIRST_NAME_COLUMN_NAME + " = :newFirstName"
				+ " WHERE "+ Employees.FIRST_NAME_COLUMN_NAME +" = :oldFirstName";

	    MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("oldFirstName", oldFirstName);
	    parameters.addValue("newFirstName", newFirstName);
		
		 
	    int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters);
	    return rowsAffected;
	}
	
}

