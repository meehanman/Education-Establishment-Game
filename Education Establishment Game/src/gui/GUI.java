package gui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * 
 * GUI for Education Establishment Game
 * (Just testing and class could be moved to Game)
 * 
 * @author Dean
 *
 * @see https://www.youtube.com/watch?v=wOUmUcVbO2s (Totorial on JavaFX)
 */

public class GUI extends Application{

	public static void main(String[] args){
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
