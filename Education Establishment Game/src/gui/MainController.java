package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

/**
 * 
 * JAVAFX -> Code for #events from MainBoard.fxml
 * 
 * @author Dean
 *
 * @see https://www.youtube.com/watch?v=wOUmUcVbO2s (Totorial on JavaFX)
 */
public class MainController implements Initializable{
	
	//Create variables with names the same as ID's in the fxml 
	//as these can be used then again in our functions
	@FXML
	private TextArea textArea;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	/**
	 * When the main button is clicked,
	 * sets the text of the textArea
	 * @param e
	 */
	public void buttonClicked(ActionEvent e){
		System.out.println("Yo!");
	}

}
