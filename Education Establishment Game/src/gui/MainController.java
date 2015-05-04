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
import board.Card;
import board.SpecialSquare;
import board.SpecialSquare.Type;
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
public class MainController implements Initializable{public MainController() {
}
	public static Game game;
	//Create variables with names the same as ID's in the fxml 
	//as these can be used then again in our functions	
	@FXML Text txtMessageOutput,txtPropertyCard;
	@FXML ImageView imgDice1,imgDice2,   imgTopBarSelector,imgTopBar;
	@FXML Group grpDice;
	@FXML AnchorPane HeadNode;
	@FXML Group BoardNode;
	@FXML Group grptopBarID,gpID1,gpID2,gpID3,gpID4; //The Top Bar Identification modules (ie Player Token, Color, Name, Money)
	@FXML Canvas exampleCanvas;
	@FXML Button btnNextTurn,btnBuyProperty,btnMortgageProperty;
	//Manage Screen and Manage Property
	@FXML Group GrpManagePopover, GrpManagePropertyPopover;
	@FXML Text ManageText, ManagePropertyText; 
	@FXML Button ManageBuy, ManageTrade, ManageManage, ManageClose;
	@FXML Button btnBackManageProperty, btnBuyHouseManageProperty, btnSellHouseManageProperty, btnMortgageManageProperty;
	@FXML Group grpAllDeeds;
	//BarDeed
	@FXML Pane PneBarDeed;
	@FXML Text titleDeedBarName, titleDeedBarRent,titleDeedBar2,titleDeedBar3,titleDeedBar4,titleDeedBarMortgage; 
	//Restaurant
	@FXML Pane PneRestaurantDeed;
	@FXML ImageView RestaurantDeedImage;
	@FXML Text RestaurantDeedName, RestaurantDeedMortgage;
	//TitleDeed
	@FXML Pane PneTitleDeed,TITLEDEEDCOLOR;
	@FXML Text titleDeedRent, titleDeedHouse1, titleDeedHouse2, titleDeedHouse3, titleDeedHouse4, titleDeedHouse5;
	@FXML Text titleDeedPropertyName, titleDeedMortgage, titleDeedHouseCost, titleDeedHotelCost;
	//Board Buttons (that are on the board)
	@FXML Button btnBoardManage, btnBoardTrade, btnBoardEndTurn;
	//SpecialCardPopup
	@FXML Pane pneSpecialCard;
	@FXML Text SpecialCardTitle, SpecialCardText;
	
	
	
	public String[] playerColors = {"#49bcff","#60ff92","#ff55f9","#ff6666"};
	
	public Stage stageOwner;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		PneBarDeed.setVisible(true);
		
		
		//Add event listeners to all the squares
		for(int i = 0; i<game.board.Squares.length;i++){
			GetSquarePane(i).setOnMouseClicked(this::squareClicked);
			
		}
		//Button Event Handlers
		btnNextTurn.setOnMouseClicked(this::endTurn);
		ManageBuy.setOnMouseClicked(this::buyProperty);
		btnMortgageProperty.setOnMouseClicked(this::mortgageProperty);
		
		//New Button Events
		ManageBuy.setOnMouseClicked(this::buyProperty);
		ManageTrade.setOnMouseClicked(this::stub);
		ManageManage.setOnMouseClicked(this::ManageProperty);
		ManageClose.setOnMouseClicked(this::hideManagePopover);
		
		//Handlers for OnScreenButtons (When no popup's are present)
		btnBoardManage.setOnMouseClicked(this::stub);
		btnBoardTrade.setOnMouseClicked(this::stub);
		btnBoardEndTurn.setOnMouseClicked(this::endTurn);
		
		//Handlers for Manage Property
		btnBackManageProperty.setOnMouseClicked(this::closeManagePropertyUI);
		btnBuyHouseManageProperty.setOnMouseClicked(this::stub);
		btnSellHouseManageProperty.setOnMouseClicked(this::stub);
		btnMortgageManageProperty.setOnMouseClicked(this::stub);
		
		//Handler to remove InfoCards
		pneSpecialCard.setOnMouseClicked(this::hideSpecialCard);
		
		//DiceRoll Click
		grpDice.setOnMouseClicked(this::diceRoll);
		
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
				ChangeSpecialSquare(GetSquarePane(i),specialSquare);
			}
		}
		
		//Draw everything else
		drawBoard();		
	}
	
	//Example of something for a mouse event, and not an action event
	public void diceRoll(MouseEvent t) {
		if(game.canRoll()){
			int[] diceRoll = game.rollDice();
			
			imgDice1.setImage(new Image("\\gui\\img\\dice\\"+diceRoll[0]+".png"));
			imgDice2.setImage(new Image("\\gui\\img\\dice\\"+diceRoll[1]+".png"));
			
			txtMessageOutput.setText("Rolled: "+(game.board.dice.getValue()));
			btnNextTurn.setVisible(true);
			
			//Check what's been rolled and do UI changes
			Square landedSquare = game.board.Squares[game.getCurrentPlayer().getPosition()];
			if(landedSquare instanceof Establishment){
				showManagePopover((Establishment)game.board.Squares[game.getCurrentPlayer().getPosition()]);
			}else{
				SpecialSquare specialSquare = (SpecialSquare)landedSquare;
				if(specialSquare.getType()==Type.ChanceCard){
					//Display Card UI
					Card pickedUpCard = game.board.ChanceCardsDeck.showLastCard();
					showSpecialCard(pickedUpCard.getTitle(),pickedUpCard.getDescription());
				}else if(specialSquare.getType()==Type.CommunityChest){
					//Display Card UI
					Card pickedUpCard = game.board.ComunityCheckCardsChest.showLastCard();
					showSpecialCard(pickedUpCard.getTitle(),pickedUpCard.getDescription());
				}
			}
			
		}else{
			System.out.println(game.getCurrentPlayer().getName()+", you cannot roll again.");
		}
		drawBoard();
	}
	
	/**
	 *  Next Turn Button
	 *  @after Prompt user to roll dice
	 */
	public void endTurn(MouseEvent event){
		btnNextTurn.setVisible(false);
		hideManagePopover();
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
	 * BUY PROPERTY
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
			System.out.println("buyProperty(): User cannot buy this Square");
		}
		
		//Update the Scene
		drawBoard();
	}
	
	/**
	 * Allows a player to mortgage or un-mortgage a property they own
	 * @param e
	 */
	public void mortgageProperty(MouseEvent e){
		Square square = game.board.Squares[game.getCurrentPlayer().getPosition()];
		if(square instanceof Establishment){
			Establishment est = (Establishment)square;
			if(est.isMortgaged()){
				//If the user was able to unMortgage the establishment
				if(est.unMortgage()){
					System.out.println(est.getOwner().getName() + " unMortgaged their property!");
				}else{
					System.out.println(est.getOwner().getName() + " Doesn't have enough to unmortgage their property!");
				}
				System.out.println(est.getOwner().getName() + " has £"+est.getOwner().getBalance());
			}else{
				est.Mortgage();
				System.out.println(est.getOwner().getName() + " Mortgaged their property!");
				System.out.println(est.getOwner().getName() + " has £"+est.getOwner().getBalance());
			}
		}
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
	 * Sets up the values for Bar and Restraunt Squares (Text,Text)
	 * 
	 * @param SquareName Square Object
	 * @param Title Title of Subject e.g. Ollies
	 * @param Price Price of Subject e.g. 400
	 */
	public static void ChangeSpecialSquare(Pane SquareName, SpecialSquare Square){
		
		if(Square.getType()==Type.IncomeTax||Square.getType()==Type.SuperTax){
			//Get(0) Title
			((Text)SquareName.getChildren().get(0)).setText(Square.getName().replaceAll("(\\p{Ll})(\\p{Lu})","$1 $2"));
			//The Money Text Box
			((Text)SquareName.getChildren().get(1)).setText("Pay £"+-(Square.getEffect().getMoney()));
		}
		
			
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
		
		//Setup TopBar (Get the player index, and positions the topBarSelector depending on what this is ie 2 would be 1/2 way, 3 would be 3/4)
		imgTopBarSelector.setX((imgTopBar.getFitWidth()/4)*(game.getPlayerIndex(game.getCurrentPlayer())));

		//Update the values for each Player for the top bar
		for(int i=0;i<game.players.size();i++){
			Group group = (Group)(grptopBarID.getChildren().get(i));
			group.setOpacity(1);
			((Text)(group.getChildren().get(0))).setText(game.players.get(i).getName());
			((Text)(group.getChildren().get(1))).setText("£"+game.players.get(i).getBalance());
			((ImageView)(group.getChildren().get(3))).setImage(
					new Image("\\gui\\"+game.players.get(i).getToken().getIconFileLocation()));
		}

		//Update if the Buy Property Button should be Active
		Square landedSquare = game.board.Squares[game.getCurrentPlayer().getPosition()];
		if(landedSquare instanceof Establishment){
			Establishment landedEstablishment = (Establishment)(landedSquare);
				//Set the button property depending on if the landedSquare has an owner
				if(landedEstablishment.hasOwner()){
					ManageBuy.setVisible(false);
					btnMortgageProperty.setVisible(true);
					if(landedEstablishment.isMortgaged()){
						btnMortgageProperty.setText("UnMortgage");
					}else{
						btnMortgageProperty.setText("Mortgage");
					}
				}else{
					ManageBuy.setVisible(true);
					btnMortgageProperty.setVisible(false);
				}
		}else{
			//If not an establishment then disable these buttons
			ManageBuy.setVisible(false);
			btnMortgageProperty.setVisible(false);
		}
		
		//Update Property Information
		updateTitleDeed(game.board.Squares[game.getCurrentPlayer().getPosition()]);
		
		//Update the Board Canvas
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
						//Need to work on moving them around so more than one can fit on screen
						gc.setFill(Color.web(playerColors[game.getPlayerIndex(p)]));
						gc.fillOval(30, 75, 40, 40);
						gc.drawImage(img, 30, 75, 40, 40);
						
					}
				}
			}
		}
			
	}
	
	/**
	 * cssColors are used for the titleDeed UI and are fixed with the colors set in the CSS
	 */
	String[] cssColors = {"brown","lightblue","pink","orange","red","yellow","green","darkblue"}; //USED TO Remove CSS styles for TitleDeed
	/**
	 * Updated the titleDeed UI
	 * @param square
	 */
	public void updateTitleDeed(Square square){
		System.out.println("updateTitleDeed(): Called for "+square.getName());
		
		Square playerSquare = game.board.Squares[game.getCurrentPlayer().getPosition()];
				
		if(playerSquare.getSquareType().equals("Subject")){
			Subject sub = (Subject)playerSquare;
			
			System.out.println("Title Deed for Subject Updated!!"+sub.getName());

			
			//Update what Deed to show
			PneTitleDeed.setVisible(false);
			PneBarDeed.setVisible(true);PneBarDeed.toFront();
			PneRestaurantDeed.setVisible(false);
			
			titleDeedPropertyName.setText(sub.getName());
			for(String color : cssColors){
				TITLEDEEDCOLOR.getStyleClass().remove(color);
			}
			TITLEDEEDCOLOR.getStyleClass().add(sub.getColor());
			
			titleDeedRent.setText("RENT £"+sub.getRentInformation()[0]);
			titleDeedHouse1.setText("£"+sub.getRentInformation()[1]);
			titleDeedHouse2.setText("£"+sub.getRentInformation()[2]);
			titleDeedHouse3.setText("£"+sub.getRentInformation()[3]);
			titleDeedHouse4.setText("£"+sub.getRentInformation()[4]);
			titleDeedHouse5.setText("£"+sub.getRentInformation()[5]);
			
			titleDeedMortgage.setText("Mortgage Value £"+sub.getMortgageValue());
			titleDeedHouseCost.setText("Houses cost £"+sub.getHousePrice()+" each");
			titleDeedHotelCost.setText("Hotels, £"+sub.getHousePrice()+" each \nplus 4 houses");
			
			//Update what Deed to show
			PneTitleDeed.setVisible(true);
			PneBarDeed.setVisible(false);
			PneRestaurantDeed.setVisible(false);
		}else if(playerSquare.getSquareType().equals("Bar")){
			Bar bar = (Bar)playerSquare;
			
			System.out.println("Title Deed for Bar Updated!!"+bar.getName());
			
			titleDeedBarName.setText(bar.getName());
			titleDeedBarRent.setText("Rent £"+bar.getBaseRent());
			titleDeedBar2.setText("£"+bar.getBaseRent()*2);
			titleDeedBar3.setText("£"+bar.getBaseRent()*3);
			titleDeedBar4.setText("£"+bar.getBaseRent()*4);
			
			//Update what Deed to show
			PneTitleDeed.setVisible(false);
			PneBarDeed.setVisible(false);
			PneRestaurantDeed.setVisible(true);
			
		}else if(playerSquare.getSquareType().equals("Restaurant")){
			Restaurant restaurant = (Restaurant)playerSquare;
			
			System.out.println("Title Deed for Restaurant Updated!!"+restaurant.getName());
			
			//Find out if bar was at first half or second half to find the image by number
			int restNum = getSquareIndex(restaurant.getName())<20?0:1;
			
			//Update titleDeed
			try{
			RestaurantDeedImage.setImage(new Image("\\gui\\img\\rest"+restNum+".png"));
			}catch(IllegalArgumentException e){
				System.out.println("Cannot find your Image");
			}
			RestaurantDeedName.setText(restaurant.getName());;
			RestaurantDeedMortgage.setText("Mortgage Value £"+restaurant.getMortgageValue());
			
			//Update what Deed to show
			PneTitleDeed.setVisible(false);
			PneBarDeed.setVisible(false);
			PneRestaurantDeed.setVisible(true);
		}else if(square.getSquareType().equals("SpecialSquare")){
			
			
			
			//Update what Deed to show
			grpAllDeeds.setVisible(false);
			PneTitleDeed.setVisible(false);
			PneBarDeed.setVisible(false);
			PneRestaurantDeed.setVisible(false);
		}

	}
	
	/**
	 * Shows the Manage Popover
	 * @param b
	 */
	public void showManagePopover(Square square){
				
		if(!(square instanceof Establishment)){
			return;
		}
		
		Establishment establishment = (Establishment)square;
		
		//Update ManagePopover
		ManageText.setText("Purchase '"+establishment.getName()+"' for £"+establishment.getPrice());
		
		//Disable the buy button if the current user is the owner
		if(establishment.hasOwner() && game.getCurrentPlayer().equals(establishment.getOwner())){
			ManageBuy.setVisible(false);
		}else{
			ManageBuy.setVisible(true);
		}
		
		//Reset Position for Deeds
		grpAllDeeds.setLayoutX(550);grpAllDeeds.setLayoutY(20);
		
		//Set Visibility for Deeds
		grpAllDeeds.setVisible(true);
		
		//Show the popover
		GrpManagePopover.setVisible(true);
	}
	/**
	 * Hides the Manage Popover
	 */
	public void hideManagePopover(){
		//Set Visibility for Deeds
		grpAllDeeds.setVisible(false);
		
		//Show the popover
		GrpManagePopover.setVisible(false);
	}
	/**
	 * Show the Manage Property Popover
	 * @param b
	 */
	public void showManagePropertyPopover(Boolean b){
		System.out.println("showManagePropertyPopover(): "+ b);
		Establishment est = (Establishment)game.board.Squares[game.getCurrentPlayer().getPosition()];
		//Update Text
		if(est.getOwner()!=null){
			ManagePropertyText.setText("Not Owned");
		}else{
			ManagePropertyText.setText("Owned by "+est.getOwner().getName());
		}
		
		//Reset Position for Deeds
		grpAllDeeds.setLayoutX(550);grpAllDeeds.setLayoutY(20);
		
		//Show the popover
		GrpManagePropertyPopover.setVisible(b);
		hideManagePopover();

	}
	/**
	 * Mouse Event to shows the ManageProperty Popover
	 * @param e
	 */
	public void ManageProperty(MouseEvent e){
		Square square = game.board.Squares[game.getCurrentPlayer().getPosition()];
		if(square instanceof Establishment){
			showManagePropertyPopover(true);
		}
	}
	
	public void hideSpecialCard(MouseEvent e){
		hideSpecialCard();
	}
	public void hideManagePopover(MouseEvent e){
		hideManagePopover();
	}
	/**
	 * Closes Manage Property and Opens Manage
	 * Essentially a back button
	 * @param e
	 */
	public void closeManagePropertyUI(MouseEvent e){
		//Close Manage Property
		showManagePropertyPopover(false);
		//Open Manage
		showManagePopover(game.board.Squares[game.getCurrentPlayer().getPosition()]);
	}
	/**
	 * Open UI for Chance Card etc.
	 * @param title
	 * @param text
	 */
	public void showSpecialCard(String title, String text){
		pneSpecialCard.setVisible(true);
		SpecialCardTitle.setText(title); 
		SpecialCardText.setText(text);
	}
	/**
	 * Closes UI for Chance Card etc.
	 */
	public void hideSpecialCard(){
		pneSpecialCard.setVisible(false);
	}
	/**
	 * TODO DELETE
	 * @param event
	 */
	public void stub(MouseEvent event){System.out.println("ONLY A STUB METHOD, METHOD STILL TO BE COMPLETED!");}


}
