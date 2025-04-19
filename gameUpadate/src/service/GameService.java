package service;

import java.sql.SQLException;
import java.util.Random;

import dao.UserDao;
import dao.WordDao;
import idiomdao.IdiomDao;



public class GameService {

	private static UserDao userD;
	private static WordDao wordD;
	private IdiomDao idiomD;
	public  GameService() {
		this.userD= new UserDao();
		this.wordD=new WordDao();
		this.idiomD = new IdiomDao();
	}
	
	public boolean login(String userName,String password) throws SQLException {
		return userD.authentication(userName, password);
		}
		
	
	
	public boolean registerPlayer(String name,String password) throws SQLException {	
		return userD.registerNewPlayer(name, password);
	}
	
	
	
	public boolean deleteWord(String word) throws SQLException {
	    return wordD.deleteWordByWord(word);  
	}
	
	
	
	public boolean addNewWord(String word,String definition, int randomNo) throws SQLException {
		return wordD.addNewWord(word,definition,randomNo);
	}
	
	
	
	public static String[] getRandomDefine(int randomNo) throws SQLException {
		
		return wordD.getRandomDefine(randomNo);
	}
	
	
	
	public static void setUsername(String loggedInUser, String newUserName) throws SQLException {
		
		userD.setUsername(loggedInUser, newUserName);
	}
	
	
	
	public static boolean authentication(String userName,String password) throws SQLException {
		return userD.authentication(userName, password);
	}
	
	
	
	public static void setPassword(String loggedInUser, String newPassword) throws SQLException {
		userD.setPass(loggedInUser, newPassword);
	}
	
	
	public boolean addNewIdiom(String idiom, String definition) throws SQLException {
        return idiomD.addNewIdiom(idiom, definition); 
    }
	
	
	public boolean deleteIdiom(String idiom) throws SQLException {
        return idiomD.deleteIdiomByIdiom(idiom); 
    }
	
	
	public String[] getRandomIdiomDefine(int random) throws SQLException {
	        return idiomD.getRandomIdiom(random); 
	    }
		
}
