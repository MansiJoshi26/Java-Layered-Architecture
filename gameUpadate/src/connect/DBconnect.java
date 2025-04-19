package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnect {
	static String url="jdbc:mysql://localhost:3306/game";
	static String user="root";
	static String password="Root";
public static Connection getconnect() throws SQLException{
		
		Connection con=DriverManager.getConnection(url,user,password);
		
		return con;
}
}
