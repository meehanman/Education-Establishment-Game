package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.Player;
import Game.Game;
import board.SpecialSquare;
import board.Square;
import board.establishment.Bar;
import board.establishment.Establishment;
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
	@FXML private Text txtMessageOutput,txtPropertyCard;
	@FXML private ImageView imgDice1,imgDice2; 	//Dice http://www.wpclipart.com/recreation/games/dice/dice.png.html
	@FXML private AnchorPane HeadNode;
	@FXML public Group BoardNode;
	@FXML public Canvas exampleCanvas;
	@FXML public Button btnNextTurn,btnBuyProperty;
	
	public String[] playerColors = {"#49bcff","#60ff92","#ff55f9","#ff6666"};
	
	public Stage stageOwner;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		//Add event listeners to all the squares
		for(int i = 0; i<game.board.Squares.length;i++){
			GetSquarePane(i).setOnMouseClicked(this::squareClicked);
			
		}
		//Button Event Handlers
		btnNextTurn.setOnMouseClicked(this::takeNextTurn);
		btnBuyProperty.setOnMouseClicked(this::buyProperty);
		
		//Mouse Click
		imgDice1.setOnMouseClicked(this::diceRoll);
		imgDice2.setOnMouseClicked(this::diceRoll);
		
		//Add all the squares to the board
		for(int i = 0; i<game.board.Squares.length;i++){
			Square square = game.board.Squares[i];
			
			if(square.getSquareType().equals("Subject")){
				Subject sub = (Subject)square;
				ChangeEstablishmentSquare(GetSquarePane(i),sub.getColor(),sub.getName(),sub.getPrice());
				
			}else if(square.getSquareType().equals("Bar")){
				Bar bar = (Bar)square;
				ChangeBarSquare(GetSquarePane(i),bar.getName(),bar.getPrice());
				
			}else if(square.getSquareType().equals("Restaurant")){
				Restaurant restaurant = (Restaurant)square;
				ChangeBarSquare(GetSquarePane(i),restaurant.getName(),restaurant.getPrice());
				
			}else if(square.getSquareType().equals("SpecialSquare")){
				SpecialSquare specialSquare = (SpecialSquare)square;
			}
		}
		
	}
	
	//Example of something for a mouse event, and not an action event
	public void diceRoll(MouseEvent t) {
		if(game.canRoll()){
			int[] diceRoll = game.rollDice();
			
			imgDice1.setImage(new Image("\\gui\\img\\dice\\"+diceRoll[0]+".png"));
			imgDice2.setImage(new Image("\\gui\\img\\dice\\"+diceRoll[1]+".png"));
			
			txtMessageOutput.setText("Rolled: "+(game.board.dice.getValue()));
			drawBoard();
		}else{
			System.out.println(game.getCurrentPlayer().getName()+", you cannot roll again.");
		}
	}
	
	/**
	 * Method to move a piece around the board
	 */
	public void movePlayerPiece(MouseEvent event){
		
		System.out.println("===========");
		game.nextTurn();
		System.out.println("===========");
		
		//TEST TODO Testing getting the property data up
		Square playerSquare = game.board.Squares[game.getCurrentPlayer().getPosition()];
		if(playerSquare.getSquareType().equals("Subject")){
			Subject sub = (Subject)playerSquare;
			
			String s=game.getCurrentPlayer().getName()+ " landed on " + sub.getName()+"\n";
			for (int i = 0; i < 5; i++) {
				s += i + ") £"+sub.getRent(i)+"\n";
			}
			
			txtPropertyCard.setText(s);

		}
		//Update the Scene
		drawBoard();
	}
	
	/**
	 *  Next Turn Button
	 *  @after Prompt user to roll dice
	 */
	public void takeNextTurn(MouseEvent event){
		if(game.canRoll()){
			txtMessageOutput.setText("You must roll the dice before giving up your go "+game.getCurrentPlayer().getName());
		}else{
			//Set the next turn in the game logic
			game.nextTurn();
			//Output message
			txtMessageOutput.setText(game.getCurrentPlayer().getName()+", it's your go!");
			//Update the Scene
			drawBoard();
		}
	}
	/**
	 * TEST BUTTON TO BUY PROPERTY
	 * @param e
	 */
	public void buyProperty(MouseEvent e){
		Square square = game.board.Squares[game.getCurrentPlayer().getPosition()];
		if(square instanceof Establishment){
			Establishment est = (Establishment)square;
			if(game.getCurrentPlayer().buy(est)){
				txtMessageOutput.setText("Baught " + est.getName());
			}else{
				txtMessageOutput.setText("Could not buy " + est.getName());
			}
		}else{
			System.out.println("User cannot buy this Square");
		}
		
		//Update the Scene
		drawBoard();
	}
	
	/**
	 * What happens when the user clicks on a square
	 * @param event
	 */
	public void squareClicked(MouseEvent event){
			
		String text = ((Text)((Pane) event.getSource()).getChildren().get(1)).getText();	
		
		Square square = game.board.Squares[getSquareIndex(text)];
		if(square instanceof Establishment){ 
			Establishment est = (Establishment)(square);
			
			String owner = est.getOwner()!=null?est.getOwner().getName():"No Owner"; 
			System.out.println("@SquareClicked():\n"+est.getName()+
								"\n"+est.getPrice()+
								"\n"+owner);
		}
		
		
	}
	/**
	 * Searches the board Squares for an item of name text and returns the location
	 * @param text
	 * @return
	 */
	private int getSquareIndex(String text){
		int i=0;
		for(Square square : game.board.Squares){
			if(square.getName()==text){
				return i;
			}else{
				i++;
			}
		}
		return -1;
			
	}

	
	/**
	 * Sets up the values for Establishment Squares (Pane,Text,Text)
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
	 * Sets up the values for Bar and Restraunt Squares (Text,Text)
	 * 
	 * @param SquareName Square Object
	 * @param Title Title of Subject e.g. Ollies
	 * @param Price Price of Subject e.g. 400
	 */
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
	public Pane GetSquarePane(int i){
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
		
		//System.out.println("GetSquarePane(i):"+rowLocation + " " + squareLocation);
		
		//Returns the squareLocation Pane Child of the rowLocation pane child of the headNode 
		return (Pane) ((Pane)(BoardNode.getChildren().get(rowLocation))).getChildren().get(squareLocation);
		
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * DRAW SCENE 
	 * Method used to keep track of players and properties 
	 * and draw these onto the screen
	 */
	public void drawBoard(){
		
		for(int i=0; i<game.board.Squares.length;i++){
			//SET UP VARIABLES
			Square square = game.board.Squares[i];
			Pane squarePane = GetSquarePane(i);
			Player owner = null;
			Canvas canvas = null;
			GraphicsContext gc = null;
			
			//Finding if there is an owner
			if(square instanceof Establishment){ 
				if(((Establishment)(square)).getOwner()!=null){
					owner = ((Establishment)(square)).getOwner();
				}
			}
			
			//Set Canvas and GraphicsContext
			for(Node node : squarePane.getChildren()){
				if(node.getClass().getSimpleName().equals("Canvas")){		
					canvas = (Canvas)node;
					gc = canvas.getGraphicsContext2D();
				}
			}
			//if there is a canvas then
			if(canvas!=null){
				//1. Clearing Canvas
				gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
				//2. Draw Player Owned Land and Properties
				if(owner!=null){
					//Draw any Land
					gc.setFill(Color.web(playerColors[game.getPlayerIndex(owner)]));
					gc.fillRoundRect(0,150,100,20, 20, 20);
					//TODO Draw Houses
				}
				//3. Draw Player Location
				for(Player p : game.players){
					if(p.getPosition()==i){
						//Draws the players images to the locations they are
						Image img = new Image("gui/"+p.getToken().getIconFileLocation());
						//TODO need to work on moving them around so more than one can fit on screen
						gc.setFill(Color.web(playerColors[game.getPlayerIndex(p)]));
						gc.fillOval(30, 75, 40, 40);
						gc.drawImage(img, 30, 75, 40, 40);
						
					}
				}
			}
		}
			
	}

	
	


}
