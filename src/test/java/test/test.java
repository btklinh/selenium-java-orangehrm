package test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;

import commons.AbstractTest;
import pageObjects.JobTitlesPageObject;
import pageObjects.LoginPageObject;
import pageObjects.PayGradesPageObject;
import pageObjects.PimPageObject;
import pageObjects.UserPageObject;

public class test extends AbstractTest {
	String username, password;
	PimPageObject pimPage;
	LoginPageObject loginPage;
	PayGradesPageObject payGradesPage;
	JobTitlesPageObject jobTitlesPage;
	UserPageObject userPage;
	WebDriver driver;

//	public static void main(String[] args) throws SQLException, ClassNotFoundException {
////		String JDBC_DRIVER = "com.mysql.jdbc.Driver";
////		Class.forName(JDBC_DRIVER);
//		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/orangehrm_mysql", "root", "");
//		
//		// Create statement/query
//		Statement stmt = con.createStatement();
//
//		String s = "SELECT * from ohrm_job_title";
//			
//
//		// Execute statement/query
//		ResultSet rs = stmt.executeQuery(s);
//		List<String> jobList = new ArrayList<String>();
//		
//		while (rs.next()) {
//			String jobtitle = rs.getString("job_title");
//			jobList.add(jobtitle);
//		}
//
//		con.close();
//		System.out.println(jobList);
//		System.out.print("Query executed");
//
//	}

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
////		String JDBC_DRIVER = "com.mysql.jdbc.Driver";
////		Class.forName(JDBC_DRIVER);
//		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/orangehrm_mysql", "root", "");
//
//		// Create statement/query
//		Statement stmt = con.createStatement();
//
//		String s = "SELECT em.employee_id,em.emp_lastname,em.emp_middle_name,em.emp_firstname, jt.job_title, sub.name AS 'Sub Unit', emSta.name AS 'Employment Status'\r\n" + "FROM hs_hr_employee AS em\r\n"
//				+ "INNER JOIN ohrm_job_title AS jt ON em.job_title_code=jt.id\r\n" + "INNER JOIN ohrm_subunit AS sub ON em.work_station=sub.id\r\n" + "INNER JOIN ohrm_employment_status AS emSta ON em.emp_status=emSta.id\r\n;";
//
//		// Execute statement/query
//		ResultSet rs = stmt.executeQuery(s);
//		List<String> jobList = new ArrayList<String>();
//		List<String> employeeID = new ArrayList<String>();
//		List<String> empLastName = new ArrayList<String>();
//		List<String> empSubUnit = new ArrayList<String>();
//		while (rs.next()) {
//			String jobtitle = rs.getString("job_title");
//			String empid = rs.getString("employee_id");
//			String emplastname = rs.getString("emp_lastname");
//			String empsubunit = rs.getString("Sub Unit");
//			employeeID.add(empid);
//			empLastName.add(emplastname);
//			empSubUnit.add(empsubunit);
//			jobList.add(jobtitle);
//		}
//
//		con.close();
//		System.out.println(employeeID);
//		System.out.println(empLastName);
//		System.out.println(empSubUnit);
//		System.out.println(jobList);
//		System.out.print("Query executed");
		List<String> ar1 = new ArrayList<String>();
		ar1.add("test1");
		ar1.add("test2");
		ar1.add("test4");
		ar1.add("test3");
		List<String> ar2 = new ArrayList<String>();
		ar2.add("test3 ");
		ar2.add("test1");
		ar2.add("test2");
		ar2.add("test4");
		Collections.sort(ar1);
		if (equals(ar1, ar2))
			System.out.println("Same");
		else
			System.out.println("Different");

	}
//Neesu
	public static boolean equals(List<String> ar1, List<String> ar2) {
		boolean result = true;
		if (ar1.size() != ar2.size())
			result = false;
		for (int i = 0; i < ar1.size(); i++) {
			int occur = Collections.frequency(ar2, ar1.get(i));
			if (!ar2.contains(ar1.get(i)) || (occur > 1)) {
				result = false;
			}
		}

		return result;

	}
}
