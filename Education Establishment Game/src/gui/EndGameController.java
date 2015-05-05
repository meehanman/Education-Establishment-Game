package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.Player;

/**
 * 
 * JAVAFX -> Code for #events from SplashController.fxml
 * 
 * @author Dean
 *
 * @see https://www.youtube.com/watch?v=wOUmUcVbO2s (Totorial on JavaFX)
 */
public class EndGameController implements Initializable{
	
	@FXML Button btnExit;
	@FXML Text txtNameOutput, txtWinnerName,txtAssetsOutput;
	@FXML ImageView imgWinnerPiece;
	
	ArrayList<Player> SortedPlayers;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		btnExit.setOnMouseClicked(this::GotoMainMenu);
		SortedPlayers = new ArrayList<Player>();
		
		outputResults();
	}
	
	public void outputResults(){			
		
		txtNameOutput.setText("");
		txtAssetsOutput.setText("");
		
		MainController.game.endGame().stream()
				.sorted((p1, p2) -> Integer.compare(p2.getBalance(),p1.getBalance()))
				.forEach(  p -> {
					txtNameOutput.setText(txtNameOutput.getText()+ p.getName()+"\n");
					txtAssetsOutput.setText(txtAssetsOutput.getText()+"£"+p.getBalance()+"\n");
					SortedPlayers.add(p);
					} 
				);
					
		
		txtWinnerName.setText(SortedPlayers.get(0).getName());
		imgWinnerPiece.setImage(new Image("\\gui\\" + SortedPlayers.get(0).getToken().getIconFileLocation()));

	}
	
	public void GotoMainMenu(MouseEvent event){
		try {
			Stage stage = new Stage();
			stage.setTitle("Education Establishment Game");	
		    Pane myPane = null;
		       
		    myPane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
			
		    Scene scene = new Scene(myPane);
		    stage.setScene(scene);

		    stage.show();
		    
		    ((Stage) btnExit.getScene().getWindow()).close();
		    
		    
		      
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}