package main;
import java.io.Console;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.*;
import dao.UserDao;
import dao.WordDao;

//import game.DaoGame;
//import game.GameService;
import modal.ModalUser;
import service.GameService;
import modal.ModalWord;

public class Main {

public static void main(String args[]) {
	
	UserDao userD = null;
	WordDao wordD=new WordDao();
	GameService gameservice=new GameService();
	boolean gamerunning=true;
	
try{
	Scanner sc=new Scanner(System.in);	
		
		System.out.println("YO GUYS LETS CHECK YOUR VOCAB !!!!!!!");
		
		
		while(gamerunning) {
			
			
			 System.out.println("===========================================");
		        System.out.println("     Welcome to the Game Menu!            ");
		        System.out.println("===========================================");
		        System.out.println("Please choose an option from below:");
		        System.out.println();
		        System.out.println("1. New Player");
		        System.out.println("2. Existing Player");
		        System.out.println("3. Quit");
		        System.out.println();
		        System.out.println("===========================================");
		        System.out.println("    Make your choice wisely!              ");
		        System.out.println("===========================================");
			String choice1=sc.nextLine().trim();
			
			switch(choice1) {
			
			
			case "1":
				userD=registerNewPlayer(sc,gameservice);
				
				break;
	
			
			case "2":
				userD=login(sc,gameservice);
			break;
			
			
			case "3":
				System.out.println("thank you for playing !");
			gamerunning=false;
			break;
			
			default :
				System.out.println("please enter  a valid no.");
			}

			if(userD!=null) {

			boolean playing=true;
			while(playing) {
				 System.out.println("===============================================");
			        System.out.println("            Welcome to the Game Menu!          ");
			        System.out.println("===============================================");
			        System.out.println("\nWhat would you like to do?");
			        System.out.println("===============================================");
			        System.out.println("1. Play");
			        System.out.println("2. Reset Username or Password");
			        System.out.println("3. Enter a New Word and Definition");
			        System.out.println("4. Delete an Existing Word");
			        System.out.println("5. Add a New Idiom");
                    System.out.println("6. Delete an Existing Idiom");
                    System.out.println("7. Quit");
			    
			        System.out.println("===============================================");
			        System.out.println("Please make a selection from the above options.");
			        System.out.println("===============================================");
				
				
				String choice=sc.nextLine().trim();
				switch(choice) {
				
				
				case "1":
					playGame(sc,userD);
							break;
				
				
				case "2":
					resetCredential(sc,userD);
							break;
				
				
				case "7":
					System.out.println("thank you for play    ");
					gamerunning=false;
					playing =false;
					break;
				 case "5":
                     addNewIdiom(sc, gameservice);
                     break;
				 case "6":
                     deleteIdiom(sc, gameservice);
                     break;
				
				case "3":
					addNewWord(sc,gameservice);
					break;
					
					
				case"4":
					deleteWord(sc,gameservice);
					break;
					
					
				default :
					System.out.println("invalid choice");
					break;
				}
			}
		}
		}
				}catch(SQLException e1) {
				 System.out.println("Database error occurred: " + e1.getMessage());
				
			}
			catch(InputMismatchException e2) {
				System.out.println("invalid input \n please input valid credentials.");
				Scanner sc1=new Scanner(System.in);
				sc1.nextLine();
			}
			catch(Exception e) {
				Scanner sc1=new Scanner(System.in);
				System.out.println("Unexpected Error Occured :"+e.getMessage());
			}
			finally {
				Scanner sc1=new Scanner(System.in);
				if(sc1!=null) {
					sc1.close();
				}
				
			}	
		}
//=================================================================================================================
	public static void playGame(Scanner sc1,UserDao userD) throws SQLException{
		
		Random random=new Random();
			int randomNo=random.nextInt(100)+1;
			String [] word= service.GameService.getRandomDefine(randomNo);
		
		if(word == null) {
			
			System.out.println("insert more Words");
			return ;
		}
	String correctWord=word[0];
	String definition=word[1];
	System.out.println("=================================================\n");	
	System.out.println(" Definition: " + definition );
    System.out.println(" Your answer:");
		Scanner sc2=new Scanner(System.in);
		String userAnswer=sc2.nextLine().trim();
		System.out.println("==============================================\n");	
		if(userAnswer.equalsIgnoreCase(correctWord)) {
			
			increaseScore();
			 System.out.println("\n Congratulations!!!! You won... ");
		}
		else {
            System.out.println("\nOops! The correct word was: " + correctWord);
        }


        String[] idiom = service.GameService.getRandomDefine(randomNo);  

        if (idiom == null) {
            System.out.println("No idioms available. Please add more idioms.");
            return;
        }

        String correctIdiom = idiom[0];
        String idiomdefinition = idiom[1];
      System.out.println("=====================================================\n");
        System.out.println("Definition: " + idiomdefinition);
        System.out.println("Your answer:");
        
         userAnswer = sc2.nextLine().trim();
         System.out.println("==================================================\n");
        if (userAnswer.equalsIgnoreCase(correctIdiom)) {
            increaseScore();
            System.out.println("\nCongratulations!!!! You guessed  correctly.");
        } else {
            System.out.println("\nOops! The correct idiom was: " + correctIdiom);
        }
    }


//===============================================================================================================
private static UserDao registerNewPlayer(Scanner sc,GameService gameservice) throws SQLException{
	    System.out.println("==============================================================\n");
		System.out.println("Enter a new Username:");
        String username = sc.nextLine();
       
        Console console=System.console();
        String password=" ";
        if(console!=null) {
        System.out.println("Enter a new Password:"); 
        char[] passwordArray=console.readPassword();
         password = new String();
        }
        else {
        	System.out.println("enter password *this will be visible");
        }
        System.out.println("==============================================================\n");
        boolean success=gameservice.registerPlayer(username,password);
        
        if(success) {
        	
        	System.out.println("new player registered Successfully!");
        	
        }else {
        	
        	System.out.println("Registration fail");
        	
        }
        return null;
	}
//=================================================================================================================
public static UserDao login(Scanner sc, GameService gameService) throws SQLException {
	System.out.println("==============================================================\n");
	System.out.println("Enter username: ");
    String username = sc.nextLine();
    
    Console console=System.console();
    String password;
    if(console!=null) {
    System.out.println("Enter password: ");
    char[] passwordArray=console.readPassword();
    password = new String(passwordArray);
    }
    else {
    	System.out.println("enter password(visible)" );
    	password=sc.nextLine();
    }
    System.out.println("==============================================================\n");
    boolean success = gameService.login(username,password);
    if (success) {
        System.out.println("Login successful!");
        return new UserDao();
    } else {
        System.out.println("Login failed!");
        return null;
    }
	
}
//=============================================================================================================================
private static void addNewWord(Scanner sc,GameService gameservice)
{System.out.println("==============================================================\n");
	System.out.println("Enter a new word you want to add  :");
	String word=sc.nextLine();
	System.out.println("Enter the definition of the word  :");
	String definition=sc.nextLine().trim();
	System.out.println("==============================================================\n");
try {
	int randomNo=new Random().nextInt();
	boolean success= gameservice.addNewWord(word, definition, randomNo);
	if(success) {
		
		System.out.println("New word and definition is successfully added :");
	
	}
	else {
		
		
		System.out.println("Ooppsss!!!please try again  :");
	}
	
}
catch(SQLException e) {
	
	
	System.out.println("error in adding a new word :"+e.getMessage());
	
}
}
//============================================================================================================
private static int getScore() {
	
	return 10;
}
//==============================================================================================================
	public static int  increaseScore() {
		int score=getScore();
	
		return 10*score;
	
		}

//===============================================================================================================
public static void resetCredential(Scanner sc,UserDao loggedInUser) throws SQLException {
	 System.out.println("do you want to reset UserName OR Password");
     System.out.println("===============================================");	
	 System.out.println("          Account Reset Options               ");
     System.out.println("===============================================");
     System.out.println("\nWhat would you like to reset?");
     System.out.println("===============================================");
     System.out.println("1. Reset Username");
     System.out.println("2. Reset Password");
     System.out.println("3. Cancel");
     System.out.println("===============================================");
     System.out.println("Please select an option to proceed.");
     System.out.println("===============================================");
	String choice=sc.nextLine().trim();
	
	switch(choice) {
	
	
	case "1":
		System.out.println("Enter previous user name");
		String pre=sc.nextLine();
		System.out.println("enter new UserName  :");
		String newUserName=sc.nextLine();
		GameService.setUsername(pre, newUserName);
		System.out.println("UserName updated Successfully  !!!!");
	break;
	
	
	
	case "2":
		System.out.println("Enter previous password");
		String pre1=sc.nextLine();
			System.out.println("Enter new Password  :");
			String newPassword=sc.nextLine();
			GameService.setPassword(pre1,newPassword);
			System.out.println("password updated successfully");
	break;
	
	
	
	case "3":
		System.out.println("cancelinggggg...............");
		break;
	
	
	
	default:
		System.out.println("invalid choice please enter between 1-3");
	}
	}
//================================================================================================================
private static void deleteWord(Scanner sc, GameService gameservice) {
	 System.out.println("Enter the word you want to delete:");
       String word = sc.nextLine();

       try {
           boolean success = gameservice.deleteWord(word);
           if (success) {
               System.out.println("Word deleted successfully.");
       } else {
               System.out.println("Failed to delete the word. Try again.");
           }
       } catch (SQLException e) {
           System.out.println("Error in deleting the word: " + e.getMessage());
       }
}
// ================================================================================================================
private static void addNewIdiom(Scanner sc, GameService gameservice) {
	System.out.println("==============================================================\n");
	System.out.println("Enter a new idiom you want to add:");
    String idiom = sc.nextLine();
    System.out.println("Enter the definition of the idiom:");
    String definition = sc.nextLine().trim();
    System.out.println("==============================================================\n");
    try {
        boolean success = gameservice.addNewIdiom(idiom, definition);
        if (success) {
            System.out.println("New idiom and definition successfully added.");
        } else {
            System.out.println("Oops! Please try again.");
        }

    } catch (SQLException e) {
        System.out.println("Error in adding a new idiom: " + e.getMessage());
    }
}
//================================================================================================================
private static void deleteIdiom(Scanner sc, GameService gameservice) {
    System.out.println("Enter the idiom you want to delete:");
    String idiom = sc.nextLine();

    try {
        boolean success = gameservice.deleteIdiom(idiom);
        if (success) {
            System.out.println("Idiom deleted successfully.");
        } else {
            System.out.println("Failed to delete the idiom. Try again.");
        }
    } catch (SQLException e) {
        System.out.println("Error in deleting the idiom: " + e.getMessage());
    }
}

}

