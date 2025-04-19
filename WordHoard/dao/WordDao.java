package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import connect.DBconnect;

public class WordDao {
public String[] getRandomDefine(int randomNo) throws SQLException {
		
		
		
		String query="select word , definition from words where random_no=?";
		
		
		try(Connection con=DBconnect.getconnect();PreparedStatement pst=con.prepareStatement(query)){
			
			pst.setInt(1, randomNo);
			ResultSet rs=pst.executeQuery();
			
			
			if(rs.next()) {
				
				return new String[] {
						
						rs.getString("word"),
						rs.getString("definition")
				};
			}
			
		}
		
		catch(SQLException e) 
			{
				System.out.println("Database Error: "+e.getMessage());
			}
			
		
		return null;
		
}	
public boolean addNewWord(String word,String definition, int randomNo) throws SQLException {
	
	
	
	Random random=new Random();
	int randomNo1;
	while(true) {
		
	randomNo1=random.nextInt(100);
	String query1="select count(*) from words where random_no=?";
	
	
	try(Connection con1=DBconnect.getconnect();PreparedStatement pst1=con1.prepareStatement(query1)){
		
		pst1.setInt(1,randomNo1);
		
		ResultSet rs=pst1.executeQuery();
		
		
		if(rs.next()&&rs.getInt(1)==0) {
			
			

			break;
		}
		
	}
		catch(SQLException e) {
			
			
			System.out.println("error checking random No");
			return false;
		}
		
	}		
	
	
	String query="insert into words(word,definition,random_no) values(?,?,?)";
	try(Connection con=DBconnect.getconnect();PreparedStatement pst=con.prepareStatement(query)){
		pst.setString(1, word);
		pst.setString(2, definition);
		pst.setInt(3, randomNo1);

		
		return pst.executeUpdate() > 0;
		
	}
	catch(SQLException e) {
		
		
		System.out.println("error in adding a new word"+e.getMessage());
	return false;
	
	
	}
}	

public boolean deleteWordByWord(String word) throws SQLException {
    String deleteQuery = "DELETE FROM words WHERE word = ?";
    try (Connection con = DBconnect.getconnect(); PreparedStatement pst = con.prepareStatement(deleteQuery)) {
        pst.setString(1, word);
        
        
        int rowsAffected = pst.executeUpdate();
        
        
        if (rowsAffected > 0) {
        	
        	
            System.out.println("Word deleted successfully.");
            return true;
        
        
        } 
        
        
        else {
            System.out.println("Word not found in the database.");
            return false;
        }
        
    } 
    
 
    
    catch (SQLException e) {
        System.out.println("Error in deleting the word: " + e.getMessage());
        return false;
    }

}
}
