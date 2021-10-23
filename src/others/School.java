package others;

import java.util.ArrayList;
import java.util.Date;

import features.Application;
import features.Donation;

public class School {
		
	private String schoolId;
	private String name;
	private String location;
	private double money;
	private int teachers;
	private int stationery;
	private boolean anyDonation;
	private Date dateRegistered;
	private ArrayList<Application> applications = new ArrayList<Application>();
	private ArrayList<Donation> donations = new ArrayList<Donation>();
	
	public School (String nm, String loc, double amt, int tr, int sn, boolean ad) {
		schoolId = generateSchoolId();
		name = nm;
		location = loc;
		money = amt;
		teachers = tr;
		stationery = sn;
		anyDonation = ad;
		dateRegistered = new Date();
	}

	public String getName() {
		return name;
	}

	public String getLocation() {
		return location;
	}
	
	public double getMoney() {
		return money;
	}

	public int getTeachers() {
		return teachers;
	}

	public int getStationery() {
		return stationery;
	}

	public boolean acceptAnyDonation() {
		return anyDonation;
	}

	public ArrayList<Application> getApplications() {
		return applications;
	}

	public ArrayList<Donation> getDonations() {
		return donations;
	}

	public Date getDateRegistered() {
		return dateRegistered;
	}
	
	public String getSchoolId() {
		return schoolId;
	}
	
	public String generateSchoolId() {
		int num = (int) (Math.random() * 10000 + 1);
		return "SC" + String.valueOf(num);
	}
	
	public void assign(Application application) {
		applications.add(application);
	}
	
	public void assign(Donation donation) {
		donations.add(donation);
	}

	
}
