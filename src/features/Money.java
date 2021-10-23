package features;

public class Money extends Donation {
	
	private double amount;

	public Money (double amount) {
		super("Money");
		this.amount = amount;
		donationId = generateDonationId();
	}
	
	// getters & setters
	public double getAmount() {
		return amount;
	}


	// methods
	@Override
	public String generateDonationId() {
		int num = (int) (Math.random() * 10000 + 1);
		return "MN" + String.valueOf(num);
	}

	@Override
	public void updateSchoolNeeds() {
		// access school using id
		// update its financial needs
	}

}
