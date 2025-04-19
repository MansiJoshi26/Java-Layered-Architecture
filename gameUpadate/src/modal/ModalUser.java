package modal;

public class ModalUser {

	private String  userName;
	private String userPassword;
	private int score;
	
	public ModalUser(String userName, String userPassword,int score) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
	}
	
	public ModalUser() {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
		// TODO Auto-generated constructor stub
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPasword) {
		this.userPassword = userPasword;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
	
}
