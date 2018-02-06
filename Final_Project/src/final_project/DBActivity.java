/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package final_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 *
 * @author alexis cruz
 */
public class DBActivity {
    private static String connectionUrl;
	private static Connection con;
	public void connectDB(String pwd) throws SQLException {
		connectionUrl = "jdbc:sqlserver://s16988308.onlinehome-server.com:1433;databaseName=CUNY_DB;user=cst3613;password=" + pwd;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e2) {
		}
		con = DriverManager.getConnection(connectionUrl);
	}
	public void disConnectDB() throws SQLException {
		con.close();
	}
	public ObservableList<MyClassDashBoard.stClass> getMyClasses(String ssn) throws SQLException {
		final ObservableList<MyClassDashBoard.stClass> data = FXCollections.observableArrayList();
		PreparedStatement stmt = con.prepareStatement("SELECT e.courseId, c.title, grade FROM dbo.Enrollment e JOIN dbo.Course c ON c.courseID = e.courseId WHERE e.ssn = ?");
		stmt.setString(1, ssn);
		ResultSet rset = stmt.executeQuery();
		while(rset.next()) {
			data.add(new MyClassDashBoard.stClass (rset.getString(1), rset.getString(2), rset.getString(3)));
		}
		rset.close();
		return data;
	}
	public Boolean validateUser(String ssn) throws SQLException {
		Boolean isFound = false;
		PreparedStatement stmt = con.prepareStatement("SELECT ssn FROM dbo.Students WHERE ssn = ?");
		stmt.setString(1, ssn);
		ResultSet rset = stmt.executeQuery();
		while(rset.next()) {
			isFound = true;
		}
		rset.close();		
		return isFound;
	}
	public void addClass (String ssn, String courseId) throws SQLException {
		PreparedStatement stmt = con.prepareStatement("INSERT INTO dbo.Enrollment(ssn, courseId, dateRegistered, grade) VALUES (?,?, GETDATE(), 'W')");
		stmt.setString (1, ssn);
		stmt.setString (2, courseId);
		stmt.execute();
	}
	public void dropClass(String ssn, String courseId) {
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement("DELETE FROM dbo.Enrollment WHERE ssn = ? AND courseId = ?");
			stmt.setString (1, ssn);
			stmt.setString (2, courseId);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	public ArrayList<String> getCourses(String ssn) throws SQLException {
		ArrayList<String> classes = new ArrayList<String>();
		PreparedStatement stmt = con.prepareStatement("SELECT c.courseID + ',' + c.title FROM dbo.Course c WHERE c.courseID NOT IN (SELECT e.courseId FROM dbo.Enrollment e WHERE e.ssn = ?)");
		stmt.setString(1, ssn);
		ResultSet rset = stmt.executeQuery();
		while(rset.next()) {
			classes.add(rset.getString(1));
		}
		rset.close();
		return classes;
	}
	public void addUser(String ssn, String firstName, String mi,String lastName) {
		try {
			// ssn exists?
			PreparedStatement stmt = con.prepareStatement("SELECT ssn FROM dbo.Students WHERE ssn = ?");
			stmt.setString(1, ssn);
			ResultSet rset = stmt.executeQuery();
			Boolean isFound = false;
			while(rset.next()) {
				isFound = true;
			}
			rset.close();
			if (isFound) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error Dialog");
				alert.setHeaderText("Error Dialog");
				alert.setContentText("ssn already exists");
				alert.showAndWait();				
			}
			else {
				stmt = con.prepareStatement("INSERT INTO dbo.Students (ssn,firstName,mi,lastName) VALUES (?,?,?,?)");
				stmt.setString (1, ssn);
				stmt.setString (2, firstName);
				stmt.setString (3, mi);
				stmt.setString (4, lastName);
				stmt.execute();
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("New user");
				alert.setHeaderText("an Information Dialog");
				alert.setContentText("New User inserted");

				alert.showAndWait();
			}
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}
}
