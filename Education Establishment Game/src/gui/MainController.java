package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
	@FXML private AnchorPane HeadNode;
	@FXML private Group BoardNode;
	
	public Stage stageOwner;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	/**
	 * When the main button is clicked,
	 * sets the text of the textArea
	 * @param e
	 */
	int i = 0;
	public void buttonClicked(ActionEvent e){
		System.out.println("Yo! " + i);
		Button btn = (Button)e.getSource();
		btn.setText("Yo! " + i);
		i++;
		
		
		ChangeEstablishmentSquare(GetSquarePane(BoardNode,i),"red",i+" <- Yo",i);
	}
	
	//Example of something for a mouse event, and not an action event
	public void squarePress(MouseEvent t) {
		Rectangle r = (Rectangle) t.getSource();
		r.setFill(Color.RED);
	}
	
	
	
	
	
	/**
	 * 
	 * @param SquareName Square Object
	 * @param color Square Colour in Lowercase
	 * @param Title Title of Subject e.g. Computer Science
	 * @param Price Price of Subject e.g. 400
	 */
	public static void ChangeEstablishmentSquare(Pane SquareName, String color, String Title, int Price){
		
		//Get(0) is the Upper Pane
		((Pane)SquareName.getChildren().get(0)).getStyleClass().add(color);
		//The title Text Box
		((Text)SquareName.getChildren().get(1)).setText(Title);
		//The Money Text Box
		((Text)SquareName.getChildren().get(2)).setText("£"+Price);
	}
	
	/**
	 * From an Integer, it will return the Square Pane
	 * 
	 * @param HeadNode The HeadNode as Method is static
	 * @param i The location you want to find 0-39
	 * @return The Square Pane
	 * 
	 * @Important 
	 */
	public static Pane GetSquarePane(Group BoardNode, int i){
		//There are 4 Row Panes that hold Square panes within them
		//Each Row Pane starts with a large Square
		//Total of 40 Panes
		int rowLocation = 0, squareLocation = 0;
		if(i>9){ //If i is more than nine, then there are at least two digits
			rowLocation = Integer.parseInt(Integer.toString(i).substring(0, 1));
			squareLocation = Integer.parseInt(Integer.toString(i).substring(1, 2));
		}else{
			squareLocation = Integer.parseInt(Integer.toString(i).substring(0, 1));
		}
		
		System.out.println(rowLocation + " " + squareLocation);
		
		//Returns the squareLocation Pane Child of the rowLocation pane child of the headNode 
		return (Pane) ((Pane)(BoardNode.getChildren().get(rowLocation))).getChildren().get(squareLocation);
		
	}


}
