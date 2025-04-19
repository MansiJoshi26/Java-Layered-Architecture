package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import  connect.DBconnect;
import modal.ModalUser;

public class UserDao {
	public boolean registerNewPlayer(String username, String password) throws SQLException {
		
		boolean isRegistered=false;
		
		 String checkUserQuery = "SELECT COUNT(*) FROM users WHERE username = ?";
		    try (Connection con = DBconnect.getconnect(); PreparedStatement pstCheck = con.prepareStatement(checkUserQuery)) {
		     pstCheck.setString(1, username);
		     ResultSet rs = pstCheck.executeQuery();
		        rs.next(); 
		        if (rs.getInt(1) > 0) {
		            
		            System.out.println("Username already exists. Please choose a different username.");
		            return false;
		        }
		    }
		    
		    
		    //inserting a new player    
		    
		String query="insert into users(username,password) value(?,?)";
		try(Connection con1=DBconnect.getconnect();PreparedStatement pst=con1.prepareStatement(query)){
		pst.setString(1, username);	
		pst.setString(2, password);
		int rowAffected=(int) pst.executeLargeUpdate();
		if(rowAffected>0) {
			isRegistered=true;
			
		}
		}
		catch(SQLException e) {
			
			System.out.println("not registered:"+e.getMessage());
		}
		
	return isRegistered;
	}
	
//=====================================================================================================
public void setUsername(String loggedInUser, String newUserName) throws SQLException {
		
		
		try(Connection con=DBconnect.getconnect();PreparedStatement pst=con.prepareStatement("update users set username=? where username=?")){
			
			pst.executeUpdate("update users set username='"+newUserName+"' where username='"+loggedInUser+"'");
			
			
				}
			}
	public void setPass(String loggedInUser,String newpassword) throws SQLException {
		
try(Connection con=DBconnect.getconnect();PreparedStatement pst=con.prepareStatement("update users set username=? where username=?")){
			
			pst.executeUpdate("update users set password='"+newpassword+"' where password='"+loggedInUser+"'");
			
			
				}
	}
//=================================================================================================
public  boolean authentication(String userName,String password) throws SQLException {
		
		String query="select * from users where username=? and password=?";
		try(Connection con=DBconnect.getconnect();PreparedStatement pst=con.prepareStatement(query)){
			pst.setString(1, userName);
			pst.setString(2, password);
			ResultSet rs=pst.executeQuery();
			
			return rs.next();
			}
		catch(SQLException e1) {
		System.out.println("Databases Error:"+e1.getMessage());
		return false;
		
	}
}
//=============================================================================================================	

public void setPassword(String loggedInUser, String newPassword) throws SQLException {
    String query = "UPDATE users SET password=? WHERE username=?";
    try (Connection con = DBconnect.getconnect(); PreparedStatement pst = con.prepareStatement(query)) {
        pst.setString(1, newPassword);
        pst.setString(2, loggedInUser);
        pst.executeUpdate();
    }
}
}

