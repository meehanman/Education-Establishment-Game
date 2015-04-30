package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * 
 * JAVAFX -> Code for #events from MainMenu.fxml
 * 
 * @author Dean
 *
 * @see https://www.youtube.com/watch?v=wOUmUcVbO2s (Totorial on JavaFX)
 */
public class GameSetupController implements Initializable{
	
	@FXML Button btnPlayGame,btnExit;
	@FXML Group gp1,gp2,gp3,gp4;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	
	public void playGame(ActionEvent e){
		try {
			Stage stage = new Stage();
			stage.setTitle("Education Establishment Game");	
		    Pane myPane = null;
		       
		    myPane = FXMLLoader.load(getClass().getResource("MainBoard.fxml"));
			
		    Scene scene = new Scene(myPane);
		    stage.setScene(scene);

		    stage.show();
		    
		    ((Stage) btnPlayGame.getScene().getWindow()).close();
		    
		    
		      
		} catch (IOException io) {
			// TODO Auto-generated catch block
			io.printStackTrace();
		}
	}
	
	public void exitGame(ActionEvent e){
		System.exit(0);
	}
	

}