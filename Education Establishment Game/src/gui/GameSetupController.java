package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.Piece;
import utils.Player;
import Game.Game;
/**
 * 
 * JAVAFX -> Code for #events from MainMenu.fxml
 * 
 * @author Dean
 *
 * @see https://www.youtube.com/watch?v=wOUmUcVbO2s (Totorial on JavaFX)
 */
public class GameSetupController implements Initializable{
	
	public String[] counter;
	
	@FXML Button btnPlayGame,btnBack;
	@FXML Group grp1,grp2,grp3,grp4; //Groups for current Players
	@FXML TextField textFieldP1,textFieldP2,textFieldP3,textFieldP4;
	@FXML Group grpAddPlayer3, grpAddPlayer4; //Add more player buttons
	@FXML ImageView imgCounterP1,imgCounterP2,imgCounterP3,imgCounterP4;
	@FXML Text msgError;
	
	int imageone=0,imagetwo=1,imagethree=2,imagefour=3; //Keeps the locations of what image is being shown
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		counter = Piece.possibleIcons;
		
		imgCounterP1.setImage(new Image("\\gui\\img\\pieces\\"+counter[imageone]+".png"));
		imgCounterP2.setImage(new Image("\\gui\\img\\pieces\\"+counter[imagetwo]+".png"));
		imgCounterP3.setImage(new Image("\\gui\\img\\pieces\\"+counter[imagethree]+".png"));
		imgCounterP4.setImage(new Image("\\gui\\img\\pieces\\"+counter[imagefour]+".png"));
	}
	
	
	/**
	 * Button that starts the game.
	 * Sets up game Object and navagates to the page
	 * @param e
	 */
	public void playGame(ActionEvent e){
		
		ArrayList<Player> players = new ArrayList<Player>();
		
		//Add first two players
		if(!textFieldP1.getText().isEmpty() && !textFieldP2.getText().isEmpty()){
			players.add(new Player(textFieldP1.getText(),imageone));
			players.add(new Player(textFieldP2.getText(),imagetwo));
		}else{
			msgError.setText("Error: Player 1 and Player 2 must have names!");
			return;
		}
		
		//Add if there are any more
		if(!textFieldP3.getText().isEmpty()){
			players.add(new Player(textFieldP3.getText(),imagethree));
		}
		if(!textFieldP4.getText().isEmpty()){
			players.add(new Player(textFieldP4.getText(),imagefour));
		}
			
		MainController.game = new Game(players);
		
		try {
			Stage stage = new Stage();
			stage.setTitle("Education Establishment Game");	
		    Pane myPane = null;
		       
		    myPane = FXMLLoader.load(getClass().getResource("MainBoard.fxml"));
		    
		    Scene scene = new Scene(myPane);
		    stage.setScene(scene);

		    stage.show();
		    
		    ((Stage) grpAddPlayer3.getScene().getWindow()).close();
		      
		} catch (IOException io) {
			System.out.println("playGame(): IOError: "+io.toString());
		} catch (NullPointerException np){
			System.out.println("playGame(): NPError: "+np.toString());

		}
		
		System.out.println("playGame(): "+"Method Finished");

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
		       
		    myPane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
			
		    Scene scene = new Scene(myPane);
		    stage.setScene(scene);

		    stage.show();
		    
		    ((Stage) btnBack.getScene().getWindow()).close();
		    
		    
		      
		} catch (IOException io) {
			System.out.println(io.getCause());
		}
	}
	
	/**
	 * Code used to change the counters in the game
	 * @param e
	 */
	public void ChangePiece(MouseEvent e){
		ImageView img = (ImageView)(e.getSource());
		
		try{
		if(img.getId().equals("imgCounterP1")){
			
			if(imageone==counter.length-1){imageone=0;}else{imageone++;}
			img.setImage(new Image("\\gui\\img\\pieces\\"+counter[imageone]+".png"));
			
		}else if(img.getId().equals("imgCounterP2")){
			
			if(imagetwo==counter.length-1){imagetwo=0;}else{imagetwo++;}
			img.setImage(new Image("\\gui\\img\\pieces\\"+counter[imagetwo]+".png"));
			
		}else if(img.getId().equals("imgCounterP3")){
			
			if(imagethree==counter.length-1){imagethree=0;}else{imagethree++;}
			img.setImage(new Image("\\gui\\img\\pieces\\"+counter[imagethree]+".png"));
			
		}else if(img.getId().equals("imgCounterP4")){
			if(imagefour==counter.length-1){imagefour=0;}else{imagefour++;}
			img.setImage(new Image("\\gui\\img\\pieces\\"+counter[imagefour]+".png"));
			
		}
		}catch(NullPointerException np){
			System.out.println("ChangePiece(): "+"Looks like there fella, there's a problems with some of your Player counter Images");
		}
		
	}
	
	public void removeElement(MouseEvent e){
		if(e.getSource().equals(grpAddPlayer3)){
			System.out.println("Remove grpAddPlayer4");
			grpAddPlayer3.setLayoutX(-10000);
			grpAddPlayer3.setDisable(true);
		}
		
		if(e.getSource().equals(grpAddPlayer4)){
			if(grpAddPlayer3.isDisable()){
				grpAddPlayer4.setLayoutX(-10000);
			}else{
				grpAddPlayer3.setLayoutX(-10000);
				grpAddPlayer3.setDisable(true);
			}
			System.out.println("Remove grpAddPlayer4");
		}
		
	}
	

}