package users;

import java.util.*;

public abstract class  User {
	protected String userId;
	protected String username;
	protected String password;
	protected String email;
	protected Date dateEnrolled;
	
	public User (String userName, String password, String email){
		this.username = userName;
		this.password = password;
		this.email = email;
	}

	public String getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}
	
	public Date getDateEnrolled() {
		return dateEnrolled;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	protected abstract String generateUserId();
	
}
