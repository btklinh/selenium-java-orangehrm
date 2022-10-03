package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		// Class.forName(JDBC_DRIVER);
		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/orangehrm_mysql", "root", "");
		// Create statement/query
		Statement stmt = con.createStatement();

		String s = "SELECT job_title FROM ohrm_job_title";

		// Execute statement/query
		ResultSet rs = stmt.executeQuery(s);
		List<String> listJob = new ArrayList<String>();
		while (rs.next()) {
			String jobtitle = rs.getString("job_title");
			listJob.add(jobtitle);
			System.out.println(jobtitle);
		}

		con.close();
		System.out.print("Query executed");
		for (String a : listJob) {
			System.out.println(a);
		}
	}
}
