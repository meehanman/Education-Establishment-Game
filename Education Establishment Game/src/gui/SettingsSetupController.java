package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.Player;
import utils.Settings;
/**
 * 
 * JAVAFX -> Code for #events from MainMenu.fxml
 * 
 * @author Dean
 *
 * @see https://www.youtube.com/watch?v=wOUmUcVbO2s (Totorial on JavaFX)
 */
public class SettingsSetupController implements Initializable{
	
	public String[] counter;
	
	@FXML Button btnPlayGame,btnBack;
	
	@FXML Slider sliderMoney;
	@FXML Text txtMoney;
	@FXML CheckBox chkTimedGame;
	
	
	int imageone=0,imagetwo=1,imagethree=2,imagefour=3; //Keeps the locations of what image is being shown
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		txtMoney.setText("£"+Settings.StartingMoney);
		// Handle CheckBox event.
		chkTimedGame.setOnAction((event) -> {
			if(chkTimedGame.isSelected()){
				Settings.TimeMinutes = 60;
			}else{
				Settings.TimeMinutes = -1;
			}
		});
		
		sliderMoney.valueProperty().addListener(new ChangeListener<Number>() {
		    @Override
		    public void changed(ObservableValue<? extends Number> observable,
		            Number oldValue, Number newValue) {

				Settings.StartingMoney = newValue.intValue();
				txtMoney.setText("£"+Settings.StartingMoney);
		    }
		});
	}

	/**
	 * Button that starts the game.
	 * Sets up game Object and navagates to the page
	 * @param e
	 */
	public void playGame(ActionEvent e){
		
		
		//Set the starting money
		for(Player p : MainController.game.players){
			p.setBalance(Settings.StartingMoney);
//!*!*!*!*!
//!*!*!*!*!
		}
		
		
		
		try {
			Stage stage = new Stage();
			stage.setTitle("Education Establishment Game");	
		    Pane myPane = null;
		       
		    myPane = FXMLLoader.load(getClass().getResource("MainBoard.fxml"));
		    
		    Scene scene = new Scene(myPane);
		    stage.setScene(scene);

		    stage.show();
		    
		    ((Stage) sliderMoney.getScene().getWindow()).close();
		      
		} catch (IOException io) {
			System.out.println("playGame(): IOError: "+io.toString());
		} catch (NullPointerException np){
			System.out.println("playGame(): NPError: "+np.toString());

		}
		
		System.out.println("GameSetup(): "+"Method Finished");

	}
	
	/**
	 * Button that navagates back to the mainmenu
	 * @param e
	 */
	public void navBack(ActionEvent e){
		
		try {
			Stage stage = new Stage();
			stage.setTitle("Education Establishment Game");	
		    Pane myPane = null;
		       
		    myPane = FXMLLoader.load(getClass().getResource("GameSetup.fxml"));
			
		    Scene scene = new Scene(myPane);
		    stage.setScene(scene);

		    stage.show();
		    
		    ((Stage) btnBack.getScene().getWindow()).close();
		    
		    
		      
		} catch (IOException io) {
			System.out.println(io.getCause());
		}
	}

	

}