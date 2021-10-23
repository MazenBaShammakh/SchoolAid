package users;

import java.util.*;

import others.School;

public class Admin extends User{
	private String NGO_name;
	public static ArrayList<School> schools = new ArrayList<School>();
	
	public Admin(String username, String password, String email, String NGO_name) {
		super(username, password, email);
		this.userId = generateUserId();
		this.NGO_name = NGO_name;
		this.dateEnrolled = new Date();
	}

	public String getNGO_name() {
		return NGO_name;
	}

	public void setNGO_name(String nGO_name) {
		NGO_name = nGO_name;
	}

	public void addSchool(School school) {
		schools.add(school);
	}

	
	@Override
	protected String generateUserId() {
		int num = (int)(Math.random()*10000)+1;
		return "AD"+ String.valueOf(num);
	}

}
