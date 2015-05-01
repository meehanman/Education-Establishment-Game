package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Game.Game;
import board.SpecialSquare;
import board.Square;
import board.establishment.Bar;
import board.establishment.Restaurant;
import board.establishment.Subject;

/**
 * 
 * JAVAFX -> Code for #events from MainBoard.fxml
 * 
 * @author Dean
 *
 * @see https://www.youtube.com/watch?v=wOUmUcVbO2s (Totorial on JavaFX)
 */
public class MainController implements Initializable{
	
	public static Game game;
	//Create variables with names the same as ID's in the fxml 
	//as these can be used then again in our functions	
	@FXML private Text txtMessageOutput;
	@FXML private ImageView imgDice; 	//Dice http://www.wpclipart.com/recreation/games/dice/dice.png.html
	@FXML private AnchorPane HeadNode;
	@FXML public Group BoardNode;
	
	public Stage stageOwner;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//Add event listeners to all the squares
		for(int i = 0; i<game.board.Squares.length;i++){
			GetSquarePane(BoardNode, i).setOnMouseClicked(this::squareClicked);
		}

		
		
		//Add all the squares to the board
		for(int i = 0; i<game.board.Squares.length;i++){
			Square square = game.board.Squares[i];
			
			if(square.getSquareType().equals("Subject")){
				Subject sub = (Subject)square;
				ChangeEstablishmentSquare(GetSquarePane(BoardNode,i),sub.getColor(),sub.getName(),sub.getPrice());
				
			}else if(square.getSquareType().equals("Bar")){
				Bar bar = (Bar)square;
				ChangeBarSquare(GetSquarePane(BoardNode,i),bar.getName(),bar.getPrice());
				
			}else if(square.getSquareType().equals("Restaurant")){
				Restaurant restaurant = (Restaurant)square;
				ChangeBarSquare(GetSquarePane(BoardNode,i),restaurant.getName(),restaurant.getPrice());
				
			}else if(square.getSquareType().equals("SpecialSquare")){
				SpecialSquare specialSquare = (SpecialSquare)square;
			}
		}
		
	}
	
	//Example of something for a mouse event, and not an action event
	public void diceRoll(MouseEvent t) {
		game.board.dice.roll();
		
		txtMessageOutput.setText("Rolled: "+(game.board.dice.getValue()+game.board.dice.getValue()));
	}
	
	public void squareClicked(MouseEvent event){
		
		String text = ((Text)((Pane) event.getSource()).getChildren().get(1)).getText();
		System.out.println(text);
		
		int i=0;
		for(Square square : game.board.Squares){
			if(square.getName()==text){
				txtMessageOutput.setText(square.getName()+" ("+i+")");
				break;
			}else{
				i++;
			}
		}
			
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
	public static void ChangeBarSquare(Pane SquareName, String Title, int Price){
		
		//Get(0) Title
		((Text)SquareName.getChildren().get(0)).setText(Title);
		//The Money Text Box
		((Text)SquareName.getChildren().get(1)).setText("£"+Price);
			
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
