package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import others.School;
import users.Admin;
import users.Donator;
import users.Teacher;

public class Controller implements Initializable {

    public void initialize (URL location, ResourceBundle resources) {
    	    	
    }
    
    // navigator method
    public void navigate (String page, String title) throws Exception {
    	Main.root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
        Main.scene = new Scene(Main.root);
    	Main.window.setTitle(title);
        Main.window.setScene(Main.scene);
        Main.window.show();
    }
    
    // navigates user to login page, and logout user from application
    public void login() throws Exception {
    	navigate("login", "Log In");
    	Main.currentUser = null;
    }
    
    // navigates user to respective user type sign up page
    public void signUp() throws Exception {
    	RadioButton admin = (RadioButton) Main.scene.lookup("#admin");
    	RadioButton donator = (RadioButton) Main.scene.lookup("#donator");
    	RadioButton teacher = (RadioButton) Main.scene.lookup("#teacher");
    	if (admin.isSelected()) {
    		adminSignup();
    	}
    	else if (donator.isSelected()) {
    		donatorSignup();
    	}
    	else if (teacher.isSelected()) {
    		teacherSignup();
    	}
    }
    
    // navigates user to admin sign up page
    public void adminSignup() throws Exception{
    	navigate("adminSignup","Admin Sign Up");		
	}
    
    // navigates user to donator sign up page
    public void donatorSignup() throws Exception{
    	navigate("donatorSignup","Donator Sign Up");		
	}
    
    // navigates user to teacher sign up page
    public void teacherSignup() throws Exception{
    	navigate("teacherSignup","Teacher Sign Up");		
	}

    // navigates user to respective user type home page
    public void home() throws Exception {
    	RadioButton admin = (RadioButton) Main.scene.lookup("#admin");
    	RadioButton donator = (RadioButton) Main.scene.lookup("#donator");
    	RadioButton teacher = (RadioButton) Main.scene.lookup("#teacher");
    	TextField username = (TextField) Main.scene.lookup("#username");
    	TextField password = (TextField) Main.scene.lookup("#password");
    	if (admin.isSelected() && validAdmin(username.getText(),password.getText())) {
        	adminHome();
    	}
    	else if (donator.isSelected() && validDonator(username.getText(),password.getText())) {
    		donatorHome();
    	}
    	else if (teacher.isSelected() && validTeacher(username.getText(),password.getText())) {
    		teacherHome();
    	}
    	else {
    		System.out.println("Invalid username/password");
    	}
    }

    // confirms that user has already signed up through verifying username & password
	public boolean validAdmin(String username, String password) {
    	if (Main.admin != null)
	    	if (username.equals(Main.admin.getUsername()) && password.equals(Main.admin.getPassword())) {
	    		Main.currentUser = Main.admin;
	    		return true;
	    	}
    	return false;
    }
    
    // confirms that user has already signed up through verifying username & password
    public boolean validDonator (String username, String password) {
    	for (int i = 0; i < Main.donators.size(); ++i) 
			if (username.equals(Main.donators.get(i).getUsername()) && password.equals(Main.donators.get(i).getPassword())) {
	    		Main.currentUser = Main.donators.get(i);
				return true;
			}
    	return false;
    }
    
    // confirms that user has already signed up through verifying username & password
    public boolean validTeacher (String username, String password) {
    	for (int i = 0; i < Main.donators.size(); ++i) 
    		if (Main.donators.get(i) instanceof Teacher)
	    		if (username.equals(Main.donators.get(i).getUsername()) && password.equals(Main.donators.get(i).getPassword())) {
		    		Main.currentUser = Main.donators.get(i);
	    			return true;
	    		}
    	return false;
    }
    
    // navigates to admin home page
    public void adminHome() throws Exception {
    	if (Main.window.getTitle().equals("Admin Sign Up"))
    		registerAdmin();
    	if (Main.admin != null) {
    		navigate("adminHome","Configure School");
        	((Label) Main.scene.lookup("#num")).setText(String.valueOf(Admin.schools.size()));
    	}
    }

    // register new admin from sign up page
    public void registerAdmin() {
    	TextField us = (TextField) Main.scene.lookup("#user");
    	PasswordField pas = (PasswordField) Main.scene.lookup("#password");
    	TextField em = (TextField) Main.scene.lookup("#email");
    	TextField ngo = (TextField) Main.scene.lookup("#ngo");
    	
    	if (!us.getText().trim().isEmpty() && !em.getText().trim().isEmpty() && !pas.getText().trim().isEmpty() && !ngo.getText().trim().isEmpty()) {
    		Main.admin = new Admin(us.getText(), em.getText(), pas.getText(), ngo.getText());
    		Main.currentUser = Main.admin;
    	}
    }
    
    // navigates admin to existing schools page
    public void existingSchoool() throws Exception{
    	navigate("existingSchool","Exisitng Schools");
    }
    
    // navigates admin to add new school page
    public void newShool() throws Exception{
    	navigate("addschool","New Schools");
    }
  
    // register new school to Admin.schools
    public void addSchool() {
    	TextField name = (TextField) Main.scene.lookup("#name");
    	TextField stationary = (TextField) Main.scene.lookup("#stat");
    	TextField money = (TextField) Main.scene.lookup("#mn");
    	TextField teach = (TextField) Main.scene.lookup("#tc");
    	TextField address = (TextField) Main.scene.lookup("#ad");
    	
    	int stat = Integer.parseInt(stationary.getText());
    	double mon = Integer.parseInt(money.getText());
    	int tea = Integer.parseInt(teach.getText());    	
    	
    	if (!name.getText().trim().isEmpty() && !stationary.getText().trim().isEmpty() && !money.getText().trim().isEmpty() && !address.getText().trim().isEmpty()){
    		((Admin) Main.currentUser).addSchool(new School(name.getText(),address.getText(),mon,tea,stat,true)); 	
    	}
    	name.setText("");
    	stationary.setText("");
    	money.setText("");
    	teach.setText("");
    	address.setText("");       
    }
    
    // navigates user to donator home page
    public void donatorHome() throws Exception {
    	if (Main.window.getTitle().equals("Donator Sign Up"))
    		registerDonator();
    	navigate("donatorHome","Donator Home");
    	((Label) Main.scene.lookup("#num")).setText(String.valueOf(((Donator)Main.currentUser).getDonations().size()));
    	if (Main.currentUser instanceof Teacher) {
    		Button btn = new Button("Teacher Mode");
    		btn.setOnAction(e -> {
    			try {
					teacherHome();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			});
    		btn.getStyleClass().add("smallbtn");
    		btn.setMinWidth(Control.USE_PREF_SIZE);
    		((GridPane) Main.scene.lookup("#teacher")).getChildren().add(btn);
    	}
    }
    
    // register new donator from sign up page
    public void registerDonator() {
    	TextField fName = (TextField) Main.scene.lookup("#fname");
    	TextField lName = (TextField) Main.scene.lookup("#lname");
    	TextField user = (TextField) Main.scene.lookup("#uname");
    	TextField email = (TextField) Main.scene.lookup("#email");
    	TextField address = (TextField) Main.scene.lookup("#address");
    	TextField telNo = (TextField) Main.scene.lookup("#tel");
    	TextField ps = (TextField) Main.scene.lookup("#pas");
    	RadioButton male = (RadioButton) Main.scene.lookup("#m1");
    	RadioButton fm = (RadioButton) Main.scene.lookup("#fm");
    	DatePicker dt = (DatePicker) Main.scene.lookup("#dt");
    	
    	//Take first letter of Gender chosen
    	String g;
    	if(fm.isSelected()){
    		g = fm.getText();
    	}
    	else{
    		g = male.getText();
    	}
    	char sex = g.charAt(0);
      // Convert DatePicker(LocalDate) to Date 
    	java.sql.Date date = java.sql.Date.valueOf(dt.getValue());
    	if (!user.getText().trim().isEmpty() && !email.getText().trim().isEmpty() && !ps.getText().trim().isEmpty() && !address.getText().trim().isEmpty()&&!fName.getText().trim().isEmpty()&&!lName.getText().trim().isEmpty()&&dt.getValue()!=null &&!telNo.getText().trim().isEmpty()){
    		Main.donators.add(new Donator(fName.getText(),lName.getText(),user.getText(),email.getText(),ps.getText(),date, sex,address.getText(),telNo.getText()));
         	Main.currentUser = Main.donators.get(Main.donators.size() - 1);
         	System.out.println("Welecome Don "+user.getText()+" ["+ps.getText()+" ]");
    	}
    }

    // navigates donator to select school to donate to page
    public void selectSchool() throws Exception {
    	navigate("selectSchool","Select School");
    }
     
    // navigates donator to type of donation page
    public void donateType() throws Exception {
    	navigate("donateType","Donation Type");
    }
       
    // navigates donator to existing donation page
    public void existingDonation() throws Exception {
    	navigate("existingDonation","My Donations");
    }
    
    // navigates user to teacher home page
    public void teacherHome() throws Exception {
    	if (Main.window.getTitle().equals("Teacher Sign Up"))
    		registerTeacher();
    	navigate("teacherHome","Teacher Home");
    	((Label) Main.scene.lookup("#num")).setText(String.valueOf(((Teacher)Main.currentUser).getApplications().size()));
    }
    
    // register new teacher from sign up page
    public void registerTeacher(){
    	TextField fName = (TextField) Main.scene.lookup("#fn");
    	TextField lName = (TextField) Main.scene.lookup("#ln");
    	TextField user = (TextField) Main.scene.lookup("#un");
    	TextField email = (TextField) Main.scene.lookup("#em");
    	TextField address = (TextField) Main.scene.lookup("#ad");
    	TextField telNo = (TextField) Main.scene.lookup("#tN");
    	TextField ps = (TextField) Main.scene.lookup("#ps");
    	TextField exp = (TextField) Main.scene.lookup("#Ex");
    	TextField degree = (TextField) Main.scene.lookup("#De");
    	RadioButton male = (RadioButton) Main.scene.lookup("#ml");
    	RadioButton fm = (RadioButton) Main.scene.lookup("#fm");
    	DatePicker dt = (DatePicker) Main.scene.lookup("#dt");
    	CheckBox math = (CheckBox) Main.scene.lookup("#M");
    	CheckBox bio = (CheckBox) Main.scene.lookup("#B");
    	CheckBox phy = (CheckBox) Main.scene.lookup("#P");
    	CheckBox eng = (CheckBox) Main.scene.lookup("#E");
    	
    	//Take first letter of Gender chosen
    	String g;
    	if(fm.isSelected()){
    		g = fm.getText();
    	}else{
    		g = male.getText();
    	}
    	char sex = g.charAt(0);
    	
    	//convert from LocalDate to Date java.sql.
    	java.sql.Date date = java.sql.Date.valueOf(dt.getValue());
        //experience as integer  	
    	int exp1 = Integer.parseInt(exp.getText());
    	//Subjects from checkBox
    	ArrayList<String> subjects = new ArrayList<String>();
    	if(math.isSelected()){
    		subjects.add("Math");
    	}if(bio.isSelected()){
    		subjects.add("Biology");
    	}if(phy.isSelected()){
    		subjects.add("Physics");
    	}if(eng.isSelected()){
    		subjects.add("English");
    	}
    	
    	if (!user.getText().trim().isEmpty() && !email.getText().trim().isEmpty() && !ps.getText().trim().isEmpty() && !address.getText().trim().isEmpty()&&!fName.getText().trim().isEmpty()&&!lName.getText().trim().isEmpty()&&dt.getValue()!=null &&!telNo.getText().trim().isEmpty() && !exp.getText().trim().isEmpty() && !degree.getText().trim().isEmpty()){
    		Main.donators.add(new Teacher(fName.getText(),lName.getText(),user.getText(),email.getText(),ps.getText(),date, sex,address.getText(),telNo.getText(),exp1,degree.getText(),subjects));
         	Main.currentUser = (Main.donators.get(Main.donators.size()-1));
         	System.out.println("Welecome Teach "+user.getText()+" ["+ps.getText()+" ]");
    	}
    }
    
    // navigates teacher to update profile page
    public void updateProfile() throws Exception{
    	navigate("updateProfile","Update Profile");
    }
    
   // navigates teacher to existing applications page
    public void existingApplication() throws Exception {
    	navigate("existingApplication","Existing Application");
    }
    
}