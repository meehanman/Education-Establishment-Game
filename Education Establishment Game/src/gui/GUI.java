package gui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.Settings;

/**
 * 
 * GUI for Education Establishment Game
 * 
 * @author Dean
 */

public class GUI extends Application{
	
	public static void main(String[] args){
		
		//Set Settings
		Settings.defaultSettings();
		
		launch();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//Return the XML file
		Parent root = FXMLLoader.load(getClass().getResource("Splash.fxml"));
		//Scene scene = new Scene(root,1200,800);
		Scene scene = new Scene(root,800,400);
		
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.initStyle(StageStyle.UNDECORATED);	
		primaryStage.show();
	}	

}
