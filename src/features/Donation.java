package features;

import java.util.Date;

import others.School;
import users.Donator;

public abstract class Donation {
	
	protected String donationId;
	protected String type;
	protected Date dateDonated;
	protected Donator donator; 
	protected School school;
	
	public Donation (String type) {
		this.type = type;
		dateDonated = new Date();
	}
	
	public String getDonationId() {
		return donationId;
	}

	public String getType() {
		return type;
	}

	public Date getDateDonated() {
		return dateDonated;
	}

	public Donator getDonator() {
		return donator;
	}

	public School getSchool() {
		return school;
	}
	
	public abstract String generateDonationId();
	
	public abstract void updateSchoolNeeds();
		
	public void assign(Donator donator) {
		this.donator= donator; 
	}
	
	public void assign(School school) {
		this.school = school;
	}
	
}
