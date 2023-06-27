package ReferenceFiles;

import java.sql.Date;
import java.util.Objects;

// Class to hold data for input/output of an insertion/query
// Referred to as a model
public class Employees
{
	// String constants to hold the column names of the database
	// Useful for referencing when inserting/querying
	public static final String EMPLOYEE_ID_COLUMN_NAME = "employee_id";
	public static final String FIRST_NAME_COLUMN_NAME = "first_name";
	public static final String LAST_NAME_COLUMN_NAME = "last_name";
	public static final String EMAIL_COLUMN_NAME = "email";
	public static final String PHONE_NUMBER_COLUMN_NAME = "phone_number";
	public static final String HIRE_DATE_COLUMN_NAME = "hire_date";
	public static final String JOB_ID_COLUMN_NAME = "job_id";
	public static final String SALARY_COLUMN_NAME = "salary";
	public static final String MANAGER_ID_COLUMN_NAME = "manager_id";
	public static final String DEPARTMENT_ID_COLUMN_NAME = "department_id";


	// Using wrapper classes instead of int / boolean primitives to hold null values if necessary
	private Integer employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private Date hireDate;
	private Integer jobId;
	private float salary;
	private Integer managerId ;
	private Integer departmentId ;


	/* Getters and Setters */
	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Employees employee = (Employees) o;
		return Objects.equals(employeeId, employee.employeeId) &&
				Objects.equals(firstName, employee.firstName) &&
				Objects.equals(lastName, employee.lastName) // TODO Add more columns
				;
	}

	/*
	@Override
	public int hashCode()
	{
		return Objects.hash(employeeId, firstName, lastName /* TODO add other columns * / );
	}
	*/

	@Override
	public String toString()
	{
		final StringBuilder sb = new StringBuilder("employee{");
		sb.append("id=").append(employeeId);
		sb.append(", firstName='").append(firstName).append('\'');
		sb.append(", lastName='").append(lastName).append('\'');
		sb.append(", email='").append(email).append('\'');
		// TODO MORE HERE
		sb.append('}');
		return sb.toString();
	}
}
