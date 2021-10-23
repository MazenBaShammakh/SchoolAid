package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

public class TypeController implements Initializable {

	 @FXML
	 ComboBox<String> donationcombo;
	 ObservableList<String> types = FXCollections.observableArrayList("Stationery","Money");
 	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		donationcombo.setItems(types);
	}
	
	private Controller controller = new Controller();
	
	// navigates donator to chosen donation type page
    public void donation() throws Exception {
    	if(donationcombo.getValue()  == "Stationery") {
    		controller.navigate("donateStationery", "Stationery Donation");
    	}
    	else if (donationcombo.getValue() == "Money") {
    		controller.navigate("donateMoney","Money Donation");
    	}
    }
    

}
