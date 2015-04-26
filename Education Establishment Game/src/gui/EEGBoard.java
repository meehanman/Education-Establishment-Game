package gui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 * GUI for Education Establishment Game
 * (Just testing and class could be moved to Game)
 * 
 * @author Dean
 *
 * @see https://www.youtube.com/watch?v=wOUmUcVbO2s (Totorial on JavaFX)
 */

public class EEGBoard extends Application{

	public static void main(String[] args){
		
		//Launch JavaFX Board
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//Return the XML file
		Parent root = FXMLLoader.load(getClass().getResource("MainBoard.fxml"));
		Scene scene = new Scene(root,1200,800);
		
		primaryStage.setResizable(true);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Education Establishment Game");		
		primaryStage.show();
	}
	
	

}
