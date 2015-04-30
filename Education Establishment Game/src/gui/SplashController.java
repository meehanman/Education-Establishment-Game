package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 
 * JAVAFX -> Code for #events from SplashController.fxml
 * 
 * @author Dean
 *
 * @see https://www.youtube.com/watch?v=wOUmUcVbO2s (Totorial on JavaFX)
 */
public class SplashController implements Initializable{
	
	@FXML Text loadText;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//http://stackoverflow.com/questions/18781704/is-there-a-way-to-hide-a-stage-after-a-speficied-time
		PauseTransition pause = new PauseTransition(Duration.seconds(5));
		pause.setOnFinished(e -> {change();});
		pause.play();
		
		//Run Textbox Animation using Threading
		(new Thread() {
			  public void run() {
					try {
						loadText.setText("Putting on Fancy Hats!");
						Thread.sleep(1000);
						loadText.setText(".Putting on Fancy Hats!.");
						Thread.sleep(1000);
						loadText.setText("..Putting on Fancy Hats!..");
						Thread.sleep(1000);
						loadText.setText("...Putting on Fancy Hats!...");
						Thread.sleep(1000);
					} catch (InterruptedException e) {}
			  }
			 }).start();
	}
	
	public void change(){
		try {
			Stage stage = new Stage();
			stage.setTitle("Education Establishment Game");	
		    Pane myPane = null;
		       
		    myPane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
			
		    Scene scene = new Scene(myPane);
		    stage.setScene(scene);

		    stage.show();
		    
		    ((Stage) loadText.getScene().getWindow()).close();
		    
		    
		      
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}