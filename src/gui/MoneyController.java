package gui;

import java.net.URL;
import java.util.ResourceBundle;

import features.Donation;
import features.Money;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import users.Donator;

public class MoneyController implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}	
	
	private Controller controller = new Controller();
	
	// navigates donator to home page after storing donation info
	public void homePage() throws Exception {
		TextField amountfield = (TextField) Main.scene.lookup("#amount");
		int amount = Integer.parseInt(amountfield.getText());
		Donation donation = new Money(amount);
		donation.assign(((Donator) Main.currentUser));
		donation.assign(Main.selectedSchool);
		((Donator) Main.currentUser).assign(donation);
		Main.selectedSchool.assign(donation);
		Main.selectedSchool = null;
		controller.donatorHome();
		System.out.println("Donator: " + donation.getDonator().getUserId() + "\nSchool: " + donation.getSchool().getName() + "\nMoney: " + ((Money)donation).getAmount());
	}
}
