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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import others.School;
import users.Admin;
import users.Donator;
import users.Teacher;

public class SchoolController implements Initializable {

	@FXML
	private VBox schoolbox;
	
	@FXML
	private HBox teachbox;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (Main.currentUser instanceof Admin)
			for (School s: Admin.schools){
				Label id = new Label(s.getSchoolId());
				Label name = new Label(s.getName());
				Label date = new Label(s.getDateRegistered().toString());
				HBox hbox = new HBox(id,name,date);
				hbox.setAlignment(Pos.CENTER);
				hbox.setSpacing(20);
	        	schoolbox.getChildren().add(hbox);
			}
		else {
			for (School s: Admin.schools){
				Label id = new Label(s.getSchoolId());
				Label name = new Label(s.getName());
				Label loc = new Label(s.getLocation());
				Label stat = new Label(String.valueOf(s.getStationery()) + " Stationery");
				Label mon = new Label(String.valueOf(s.getMoney()) + " RM");
				Label tea = new Label(String.valueOf(s.getTeachers()) + " Teachers");
				HBox hbox = new HBox(id, name, loc, stat, mon, tea);
				hbox.setAlignment(Pos.CENTER);
				hbox.setSpacing(20);
	        	schoolbox.getChildren().add(hbox);
			}
			if (Main.currentUser instanceof Teacher) {
				Button teach = new Button("Teach");
				teach.setOnAction(e -> {
					try {
						applyToTeach();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				});
				teach.getStyleClass().add("smallbtn");
				teachbox.getChildren().add(teach);
			}
		}
	}
	
	private Controller controller = new Controller();
	
	public void homePage() throws Exception {
		if (Main.currentUser instanceof Donator)
			controller.donatorHome();
		else if (Main.currentUser instanceof Admin) {
			controller.adminHome();
		}
	}
	
	// navigate donator to donation type page
    public void donateType() throws Exception {
		TextField schoolid = (TextField) Main.scene.lookup("#schoolid");
    	if (!schoolid.getText().trim().isEmpty())
    		for (School s: Admin.schools)  {
        		if (schoolid.getText().equals(s.getSchoolId())) {
        			Main.selectedSchool = s;
        			controller.donateType();
        			break;
        		}
    		}
    }
	
    // teacher apply to school and application is stored
    public void applyToTeach() throws Exception {
		TextField schoolid = (TextField) Main.scene.lookup("#schoolid");
    	if (!schoolid.getText().trim().isEmpty())
    		for (School s: Admin.schools)  {
        		if (schoolid.getText().equals(s.getSchoolId())) {
        			Main.selectedSchool = s;
        			Teacher teacher = ((Teacher) Main.currentUser);
        			Application app = new Application();
        			app.assign(Main.selectedSchool);
        			app.assign(teacher);
        			teacher.assign(app);
        			Main.selectedSchool.assign(app);
        			Main.selectedSchool = null;
        			System.out.println("Teacher: " + app.getTeacher().getUserId() + "\nSchool: " + app.getSchool().getName() + "\nDate Applied: " + app.getDateApplied());
        			controller.teacherHome();
        			break;
        		}
    		}
    }
    
    // update school list once key is released
	public void updateSchools() {
		TextField schoolid = (TextField) Main.scene.lookup("#schoolid");
		schoolbox.getChildren().clear();
		for (School s: Admin.schools) {
			if (s.getSchoolId().startsWith(schoolid.getText())) {
				if (Main.currentUser instanceof Admin) {
						Label id = new Label(s.getSchoolId());
						Label name = new Label(s.getName());
						Label date = new Label(s.getDateRegistered().toString());
						HBox hbox = new HBox(id,name,date);
						hbox.setAlignment(Pos.CENTER);
						hbox.setSpacing(20);
			        	schoolbox.getChildren().add(hbox);
				}
				else {
						Label id = new Label(s.getSchoolId());
						Label name = new Label(s.getName());
						Label loc = new Label(s.getLocation());
						Label stat = new Label(String.valueOf(s.getStationery()) + " Stationery");
						Label mon = new Label(String.valueOf(s.getMoney()) + " RM");
						Label tea = new Label(String.valueOf(s.getTeachers()) + " Teachers");
						HBox hbox = new HBox(id, name, loc, stat, mon, tea);
						hbox.setAlignment(Pos.CENTER);
						hbox.setSpacing(20);
			        	schoolbox.getChildren().add(hbox);
				}
			}
        }		
	}

	// remove school from school list
	public void removeSchool() {
		TextField schoolid = (TextField) Main.scene.lookup("#schoolid");
		int index = -1;
		int i = 0;
		for (School s: Admin.schools) {
			if (s.getSchoolId().equals(schoolid.getText())) {
				index = i;
			}
			i++;
        }
		if (index != -1) {
			Admin.schools.remove(index);
			schoolid.setText("");
			updateSchools();
		}
	}
	
	// view school details
	public void viewSchool() {
		TextField schoolid = (TextField) Main.scene.lookup("#schoolid");
		int index = -1;
		int i = 0;
		for (School s: Admin.schools) {
			if (s.getSchoolId().equals(schoolid.getText())) {
		    	schoolbox.getChildren().clear();
				index = i;
			}
			i++;
        }
		if (index != -1) {
			School s = Admin.schools.get(index);
			Label id = new Label(s.getSchoolId());
			Label name = new Label(s.getName());
//			Label date = new Label(s.getDateRegistered().toString());
			Label loc = new Label(s.getLocation());
			Label stat = new Label(String.valueOf(s.getStationery()) + " Stationery");
			Label mon = new Label(String.valueOf(s.getMoney()) + " RM");
			Label tea = new Label(String.valueOf(s.getTeachers()) + " Teachers");
			HBox hbox = new HBox(id, name, loc, stat, mon, tea);
			hbox.setAlignment(Pos.CENTER);
			hbox.setSpacing(20);
        	schoolbox.getChildren().add(hbox);
		}
	}

}
