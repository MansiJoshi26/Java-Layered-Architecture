package idiomdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import connect.DBconnect;

public class IdiomDao {
    
    
    public String[] getRandomIdiom(int randomNo) throws SQLException {
        String query = "SELECT idiom, definition FROM idioms WHERE random_no=?";
        
        try (Connection con = DBconnect.getconnect(); PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, randomNo);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                return new String[] {
                    rs.getString("idiom"),
                    rs.getString("definition")
                };
            }
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
        
        return null;
    }

    // Method to add a new idiom to the database
    public boolean addNewIdiom(String idiom, String definition) throws SQLException {
        Random random = new Random();
        int randomNo1;
        
        while (true) {
            randomNo1 = random.nextInt(100);
            String query1 = "SELECT COUNT(*) FROM idioms WHERE random_no=?";
            
            try (Connection con1 = DBconnect.getconnect(); PreparedStatement pst1 = con1.prepareStatement(query1)) {
                pst1.setInt(1, randomNo1);
                ResultSet rs = pst1.executeQuery();
                
                if (rs.next() && rs.getInt(1) == 0) {
                    break;
                }
            } catch (SQLException e) {
                System.out.println("Error checking random No: " + e.getMessage());
                return false;
            }
        }
        
        String query = "INSERT INTO idioms(idiom, definition, random_no) VALUES(?,?,?)";
        try (Connection con = DBconnect.getconnect(); PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, idiom);
            pst.setString(2, definition);
            pst.setInt(3, randomNo1);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error in adding a new idiom: " + e.getMessage());
            return false;
        }
    }

    
    public boolean deleteIdiomByIdiom(String idiom) throws SQLException {
        String deleteQuery = "DELETE FROM idioms WHERE idiom = ?";
        try (Connection con = DBconnect.getconnect(); PreparedStatement pst = con.prepareStatement(deleteQuery)) {
            pst.setString(1, idiom);
            int rowsAffected = pst.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Idiom deleted successfully.");
                return true;
            } else {
                System.out.println("Idiom not found in the database.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error in deleting the idiom: " + e.getMessage());
            return false;
        }
    }
}