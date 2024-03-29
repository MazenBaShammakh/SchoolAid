package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import features.Donation;
import features.Money;
import features.Stationery;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import users.Donator;

public class MyDonationController implements Initializable {

	@FXML
	private VBox donationbox;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		for (Donation d: ((Donator) Main.currentUser).getDonations()) {
			Label id = new Label(d.getDonationId());
			Label type = new Label(d.getType());
			Label date = new Label(d.getDateDonated().toString());
			HBox hbox = new HBox(id,type,date);
			hbox.setAlignment(Pos.CENTER);
			hbox.setSpacing(20);
        	donationbox.getChildren().addAll(hbox);
		}
	}
	
	private Controller controller = new Controller();
	
	// navigates donator to home page
	public void homePage() throws Exception {
		controller.donatorHome();
	}
	
	// update donation list once a key is released
	public void updateDonations() {
		TextField donationid = (TextField) Main.scene.lookup("#donationid");
		donationbox.getChildren().clear();
		for (Donation d: ((Donator) Main.currentUser).getDonations()) {
			if (d.getDonationId().startsWith(donationid.getText())) {
				Label id = new Label(d.getDonationId());
				Label type = new Label(d.getType());
				Label date = new Label(d.getDateDonated().toString());
				HBox hbox = new HBox(id,type,date);
				hbox.setAlignment(Pos.CENTER);
				hbox.setSpacing(20);
	        	donationbox.getChildren().addAll(hbox);
			}
        }
	}

	// delete donation once cancel button is clicked
	public void cancelDonation() {
		TextField donationid = (TextField) Main.scene.lookup("#donationid");
		int index = -1;
		int i = 0;
		for (Donation d: ((Donator) Main.currentUser).getDonations()) {
			if (d.getDonationId().equals(donationid.getText())) {
				index = i;
			}
			i++;
        }
		if (index != -1) {
			((Donator) Main.currentUser).getDonations().remove(index);
			donationid.setText("");
			updateDonations();
		}
	}
	
	// display donation info once view button is clicked
	public void viewDonation() {
		TextField donationid = (TextField) Main.scene.lookup("#donationid");
		int index = -1;
		int i = 0;
		ArrayList<Donation> donations = ((Donator) Main.currentUser).getDonations();
		for (Donation d: donations) {
			if (d.getDonationId().equals(donationid.getText())) {
		    	donationbox.getChildren().clear();
				index = i;
			}
			i++;
        }
		if (index != -1) {
			Donation donation = donations.get(index);
			Label id = new Label(donation.getDonationId());
			Label type = new Label(donation.getType());
			Label school = new Label(donation.getSchool().getName());
//			Label date = new Label(donation.getDateDonated().toString());
			HBox hbox;
			if (donation instanceof Stationery) {
				Label pack = new Label(String.valueOf(((Stationery) donation).getQuantity()) + " " + ((Stationery) donation).getPack() + "(s)");
//				Label qnt = new Label(String.valueOf(((Stationery) donation).getQuantity()));
				hbox = new HBox(id,type,school,pack);
			}
			else {
				int amt = (int) ((Money) donation).getAmount();
				System.out.println(amt);
				Label amount = new Label(Integer.toString(amt) + " RM");
				hbox = new HBox(id,type,school,amount);
			}
			hbox.setAlignment(Pos.CENTER);
			hbox.setSpacing(20);
        	donationbox.getChildren().addAll(hbox);
		}
	}

}
