package gui;

import java.net.URL;
import java.util.ResourceBundle;

import features.Donation;
import features.Money;
import features.Stationery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import users.Donator;

public class StationeryController implements Initializable {

	@FXML
	 ComboBox<String> packcombo;
	 ObservableList<String> packs = FXCollections.observableArrayList("Regular Package","Premium Package");	
	
	@FXML
	ComboBox<String> qntcombo;
	ObservableList<String> qnt = FXCollections.observableArrayList("1","2","3","4","5");	
	
	 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		packcombo.setItems(packs);
		qntcombo.setItems(qnt);
	}
	
	private Controller controller = new Controller();
	
	// navigates donator to home page after storing donation info
	public void homePage() throws Exception {
		String pack = packcombo.getValue();
		int qnt = Integer.parseInt(qntcombo.getValue());
		DatePicker pickup = (DatePicker) Main.scene.lookup("#pickup");
	
		Donation donation = new Stationery(pack,qnt,pickup);
		donation.assign(((Donator) Main.currentUser));
		donation.assign(Main.selectedSchool);
		((Donator) Main.currentUser).assign(donation);
		Main.selectedSchool.assign(donation);
		Main.selectedSchool = null;
		controller.donatorHome();
		System.out.println("Donator: " + donation.getDonator().getUserId() + "\nSchool: " + donation.getSchool().getName() + "\nPackage: " + ((Stationery) donation).getPack());
	}

}
