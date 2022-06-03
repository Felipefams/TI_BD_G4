package model;

public class Login {
	protected String email;
	protected String userPassword;

	public Login(){

	}

	public Login(String email, String userPassword){
		this.email = email;
		this.userPassword = userPassword;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

}
