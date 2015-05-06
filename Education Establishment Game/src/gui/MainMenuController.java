package gui;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.LanguageController;
import utils.Settings;

/**
 * 
 * JAVAFX -> Code for #events from MainMenu.fxml
 * 
 * @author Dean
 *
 * @see https://www.youtube.com/watch?v=wOUmUcVbO2s (Totorial on JavaFX)
 */
public class MainMenuController implements Initializable{
	
	@FXML Button btnPlayGame,btnExit;
	@FXML ImageView img_en, img_sp, img_fr, img_de;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//Set Language Event Handlers
		img_en.setOnMouseClicked(this::setEN);
		img_fr.setOnMouseClicked(this::setFR);
		img_de.setOnMouseClicked(this::setDE);
		img_sp.setOnMouseClicked(this::setSP);
		
	}
	
	//Language Settings
	public void setEN(MouseEvent e){
		Settings.language = Locale.ENGLISH;
	}
	public void setFR(MouseEvent e){
		Settings.language = Locale.FRENCH;
	}
	public void setDE(MouseEvent e){
		Settings.language = Locale.GERMAN;
	}
	public void setSP(MouseEvent e){
		Settings.language = new Locale("es", "ES");
	}
	/**
	 * Set the local for buttons on screen
	 */
	public void updateLocaleValues(){
		btnPlayGame.setText(LanguageController.getMessage("PlayGame"));
		btnExit.setText(LanguageController.getMessage("Exit"));
	}
	
	
	public void playGame(ActionEvent e){
		try {
			Stage stage = new Stage();
			stage.setTitle("Education Establishment Game");	
		    Pane myPane = null;
		       
		    myPane = FXMLLoader.load(getClass().getResource("GameSetup.fxml"));
			
		    Scene scene = new Scene(myPane);
		    stage.setScene(scene);

		    stage.show();
		    
		    ((Stage) btnPlayGame.getScene().getWindow()).close();
		    
		    
		      
		} catch (IOException io) {}
	}
	
	public void exitGame(ActionEvent e){
		System.exit(0);
	}
	

}