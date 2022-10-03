package test;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class databaseTestConnection {

	public void database() {
		final String JDBC_DRIVER = "com.mysql.jdc.Driver";
		final String DB_URL = "jdbc:mysql://127.0.0.1:3306/orangehrm_mysql";
		final String user = "btklinh";
		final String password = "";

		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(JDBC_DRIVER);
			try {
				conn = DriverManager.getConnection(DB_URL, user, password);
				stmt = conn.createStatement();
				String sql = "SELECT * FROM 'ohrm_job_title'";
				ResultSet executeQuery = stmt.executeQuery(sql);
				
				while (executeQuery.next()) {
					String jobTitle = executeQuery.getString("job_title");
					System.out.println(jobTitle);
				}
				executeQuery.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}