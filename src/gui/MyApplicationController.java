package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import features.Application;
import features.Donation;
import features.Money;
import features.Stationery;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import users.*;

public class MyApplicationController implements Initializable {

	@FXML
	private VBox appbox;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ArrayList<Application> apps = ((Teacher) Main.currentUser).getApplications();
		for (Application a: apps) {
			Label appid = new Label(a.getAppId());
			Label schid = new Label(a.getSchool().getSchoolId());
			Label date = new Label(a.getDateApplied().toString());
			HBox hbox = new HBox(appid,schid,date);
			hbox.setAlignment(Pos.CENTER);
			hbox.setSpacing(20);
        	appbox.getChildren().addAll(hbox);
		}
	}
	
	private Controller controller = new Controller();
	
	// navigates teacher to home page
	public void homePage() throws Exception {
		controller.teacherHome();
	}
	
	// update applications list once a key is released
	public void updateApplications() {
		TextField appid = (TextField) Main.scene.lookup("#appid");
		appbox.getChildren().clear();
		for (Application a: ((Teacher) Main.currentUser).getApplications()) {
			if (a.getAppId().startsWith(appid.getText())) {
				Label applid = new Label(a.getAppId());
				Label schid = new Label(a.getSchool().getSchoolId());
				Label date = new Label(a.getDateApplied().toString());
				HBox hbox = new HBox(applid,schid,date);
				hbox.setAlignment(Pos.CENTER);
				hbox.setSpacing(20);
	        	appbox.getChildren().addAll(hbox);
			}
        }
	}

	// cancel application once cancel button is clicked
	public void cancelApplication() {
		TextField appid = (TextField) Main.scene.lookup("#appid");
		int index = -1;
		int i = 0;
		for (Application d: ((Teacher) Main.currentUser).getApplications()) {
			if (d.getAppId().equals(appid.getText())) {
				index = i;
			}
			i++;
        }
		if (index != -1) {
			((Teacher) Main.currentUser).getApplications().remove(index);
			appid.setText("");
			updateApplications();
		}
	}
	
	// display application info once view button is clicked
	public void viewApplication() {
		TextField appid = (TextField) Main.scene.lookup("#appid");
		int index = -1;
		int i = 0;
		ArrayList<Application> apps = ((Teacher) Main.currentUser).getApplications();
		for (Application a: apps) {
			if (a.getAppId().equals(appid.getText())) {
		    	appbox.getChildren().clear();
				index = i;
				break;
			}
			i++;
        }
		if (index != -1) {
			Application app = apps.get(index);
			Label applid = new Label(app.getAppId());
			Label schid = new Label(app.getSchool().getSchoolId());
			Label date = new Label(app.getDateApplied().toString());
			Label schnm = new Label(app.getSchool().getName());
			Label schloc = new Label(app.getSchool().getLocation());
			HBox hbox = new HBox(applid,schid,schnm,schloc,date);
			hbox.setAlignment(Pos.CENTER);
			hbox.setSpacing(20);
        	appbox.getChildren().addAll(hbox);
		}
	}

}
