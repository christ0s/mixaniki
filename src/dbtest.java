
import javax.swing.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;



public class dbtest {
	 static Statement stmt = null;
	 static ResultSet rs = null;
	 private static Connection DBconnection;
	 public static Connection connect(){
	
	  try
	  {
	  Class.forName("com.mysql.jdbc.Driver").newInstance();
	  String url = "jdbc:mysql://83.212.101.143:3306/mydb";
	  String dbusername = "root";
	  String dbpassword = "";

	  System.out.println("Connecting database...");
	  DBconnection = DriverManager.getConnection(url, dbusername, dbpassword);
	  JOptionPane.showMessageDialog(null,"Database connected!");
     
	  }
	  catch (Exception e)
	  {
	  e.printStackTrace();
	  }
		

	  try { if (stmt != null) stmt.close(); } catch (SQLException e) {
		  e.printStackTrace(); }
	  try { if (DBconnection != null) DBconnection.close(); } catch (SQLException e) {
		  e.printStackTrace(); }
	 return null;
	}
  }