package users;

import java.time.LocalDate;
import java.util.*;

import features.Donation;

public class Donator extends User{
	
	protected String firstName;
	protected String lastName;
	protected Date DOB;
	protected char gender;
	protected String address;
	protected String telNo;
	protected ArrayList<Donation> donations = new ArrayList<Donation>();

	public Donator(String un,String ps,String em, String fn, String ln, Date dob, char g, String add, String tel){
		super(un, ps, em);
		firstName = fn;
		lastName = ln;
		DOB = dob;
		gender = g;
		address = add;
		telNo = tel;
		this.dateEnrolled = new Date();
		this.userId = generateUserId();
	}
	
	public String getName() {
		return firstName + " " + lastName;
	}

	public int getAge() {
		return LocalDate.now().getYear() - DOB.getYear();
	}

	public char getGender() {
		return gender;
	}

	public String getAddress() {
		return address;
	}

	public String getTelNo() {
		return telNo;
	}

	
	public ArrayList<Donation> getDonations() {
		return donations;
	}
	
	public void assign(Donation donation) {
		donations.add(donation);
	}
	
	@Override
	protected String generateUserId() {
		int num = (int)(Math.random()*10000)+1;
		return "DN"+ String.valueOf(num);
	}
	
}
