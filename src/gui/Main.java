package gui;

import java.util.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import others.School;
import users.*;

public class Main extends Application {

	public static Admin admin;
	public static ArrayList<Donator> donators = new ArrayList<Donator>();
	public static User currentUser;
	public static School selectedSchool;
	
	public static void main(String [] args) {
		launch(args);
	}
	
	public static Stage window = new Stage();
	public static Scene scene;
	public static Parent root;
	
	@Override
	public void start(Stage arg0) throws Exception {
		root = FXMLLoader.load(getClass().getResource("login.fxml"));
        scene = new Scene(root);
    	window.setTitle("Log In");
        window.setScene(scene);
        window.show();
	}
}
