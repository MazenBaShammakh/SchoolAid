package features;

import javafx.scene.control.DatePicker;

public class Stationery extends Donation {
	
	private String pack;
	private int quantity;
	private DatePicker pickup;
	
	public Stationery (String pack, int quantity, DatePicker pickup) {
		super("Stationery");
		this.pack = pack;
		this.quantity = quantity;
		this.pickup = pickup;
		donationId = generateDonationId();
	}
	
	public String getPack() {
		return pack;
	} 

	public int getQuantity() {
		return quantity;
	}

	public DatePicker getPickup() {
		return pickup;
	}

	@Override
	public String generateDonationId() {
		int num = (int) (Math.random() * 10000 + 1);
		return "ST" + String.valueOf(num);
	}
	
	@Override
	public void updateSchoolNeeds() {
		// access school using id
		// update its stationery needs
	}
	
}
