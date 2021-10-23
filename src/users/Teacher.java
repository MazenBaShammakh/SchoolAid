package users;

import java.util.ArrayList;
import java.util.Date;

import features.Application;

public class Teacher extends Donator{
	private int experience;
	private String degree;
	private ArrayList<String> subjects;
	private ArrayList<Application> applications = new ArrayList<Application>();
	
	public Teacher(String un,String ps,String em, String fn, String ln, Date dob, char g, String add, String tel, int ex, String deg, ArrayList<String> sub){
		super(un, ps, em, fn, ln, dob, g, add, tel);
		experience = ex;
		degree = deg;
		subjects = sub;
		dateEnrolled = new Date();
		userId = generateUserId();
	}
	
	public int getExperience() {
		return experience;
	}

	public String getDegree() {
		return degree;
	}

	public ArrayList<String> getSubjects() {
		return subjects;
	}

	public ArrayList<Application> getApplications() {
		return applications;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public void setSubjects(ArrayList<String> subjects) {
		this.subjects = subjects;
	}

	public void assign(Application application) {
		applications.add(application);
	}
	
	@Override
	protected String generateUserId() {
		int num = (int)(Math.random()*10000)+1;
		return "TR"+ String.valueOf(num);
	}

}
