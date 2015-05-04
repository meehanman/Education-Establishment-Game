package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
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
	boolean tradeActive = false;
	private Square selectedSquare;
	public String[] playerColors = {"#49bcff","#60ff92","#ff55f9","#ff6666"};
	
	Trade tradeowner, othertrader;
	//Create variables with names the same as ID's in the fxml 
	//as these can be used then again in our functions	
	@FXML ImageView imgDice1,imgDice2, imgTopBarSelector,imgTopBar;
	@FXML Group grpDice;
	@FXML AnchorPane HeadNode;
	@FXML Group BoardNode;
	@FXML Group grptopBarID,gpID1,gpID2,gpID3,gpID4; //The Top Bar Identification modules (ie Player Token, Color, Name, Money)
	@FXML Canvas exampleCanvas;
	//Manage Screen and Manage Property
	@FXML Group GrpManagePopover, GrpManagePropertyPopover;
	@FXML Text ManageText, ManagePropertyText, txtManagePropertyOwnedBy; 
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
	@FXML ImageView specialCardImage;
	//Other
	@FXML Group grpPopupMessage, grpSpecialCard;
	@FXML Text PopUpTitle, PopUpMessage;
	@FXML ImageView imgYourRoll;
		
	@FXML Pane pneTrade;
	@FXML Text txtUsernameTradeLeft, txtPropertiesTradeLeft, txtUsernameTradeRight, txtPropertiesTradeRight, txtStopTrade;
	@FXML TextField txtTradeCashLeft, txtTradeCashRight;
	@FXML Button btnLockTradeLeft, btnLockTradeRight;
	@FXML ImageView imgLeftTrader, imgRightTrader;
	@FXML Rectangle grayOut;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		PneBarDeed.setVisible(true);
		
		
		//Add event listeners to all the squares
		for(int i = 0; i<game.board.Squares.length;i++){
			if(game.board.Squares[i] instanceof Establishment){
				if(game.board.Squares[i].getSquareType().equals("Subject")){
					GetSquarePane(i).setOnMouseClicked(this::SubjectSquareClicked);
				}else{
					GetSquarePane(i).setOnMouseClicked(this::OtherSquareClicked);
				}
			}
			
			
		}
		//Button Event Handlers
		//btnBoardEndTurn.setOnMouseClicked(this::endTurn);
		ManageBuy.setOnMouseClicked(this::buyProperty);
		
		//New Button Events
		ManageBuy.setOnMouseClicked(this::buyProperty);
		ManageTrade.setOnMouseClicked(this::startTrade);
		ManageManage.setOnMouseClicked(this::ManageProperty);
		ManageClose.setOnMouseClicked(this::hideManagePopover);
		
		//Handlers for OnScreenButtons (When no popup's are present)
		btnBoardManage.setOnMouseClicked(this::ManageProperty);
		btnBoardTrade.setOnMouseClicked(this::startTrade);
		btnBoardEndTurn.setOnMouseClicked(this::endTurn);
		
		//Handlers for Manage Property
		btnBackManageProperty.setOnMouseClicked(this::closeManagePropertyUI);
		btnBuyHouseManageProperty.setOnMouseClicked(this::btnbuyHouse);
		btnSellHouseManageProperty.setOnMouseClicked(this::btnsellHouse);
		btnMortgageManageProperty.setOnMouseClicked(this::mortgageProperty);
		
		//Trade Locks
		btnLockTradeLeft.setOnMouseClicked(this::lockTrade);
		btnLockTradeRight.setOnMouseClicked(this::lockTrade);
		//Handler to remove InfoCards
		pneSpecialCard.setOnMouseClicked(this::hideSpecialCard);
		grpSpecialCard.setOnMouseClicked(this::hideSpecialCard);
		grpPopupMessage.setOnMouseClicked(this::hideAlert);
		grayOut.setOnMouseClicked(this::hideAlert);
		
		//Event handlers for the top bar
		for(int i = 0; i < game.players.size(); i++){
			grptopBarID.getChildren().get(i).setOnMouseClicked(this::playerClick);
		}
		
		
		//Other
		txtStopTrade.setOnMouseClicked(this::stopTrade);
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
			
			btnBoardEndTurn.setVisible(true);
						
			//Check what's been rolled and do UI changes
			selectedSquare = getCurrentLandedSquare();
			if(selectedSquare instanceof Establishment){
				showManagePopover();
			}else{
				SpecialSquare specialSquare = (SpecialSquare)selectedSquare;
				if(specialSquare.getType()==Type.ChanceCard){
					//Display Card UI
					Card pickedUpCard = game.board.ChanceCardsDeck.showLastCard();
					showSpecialCard(pickedUpCard.getTitle(),pickedUpCard.getDescription(),true);
				}else if(specialSquare.getType()==Type.CommunityChest){
					//Display Card UI
					Card pickedUpCard = game.board.ComunityCheckCardsChest.showLastCard();
					showSpecialCard(pickedUpCard.getTitle(),pickedUpCard.getDescription(),false);
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
		btnBoardEndTurn.setVisible(false);
		hideManagePopover();
		if(game.canRoll()){
			showAlert("Don't Rock and Roll", "You must roll the dice before giving up your go "+game.getCurrentPlayer().getName());
			imgYourRoll.setVisible(true);
		}else{
			imgYourRoll.setVisible(false);
			//Set the next turn in the game logic
			game.nextTurn();
			//Update the Scene
			drawBoard();
		}
	}
	/**
	 * BUY PROPERTY
	 * @param e
	 */
	public void buyProperty(MouseEvent e){
		if(getCurrentLandedSquare().equals(selectedSquare))
		if(selectedSquare instanceof Establishment){
			Establishment est = (Establishment)selectedSquare;
			if(game.getCurrentPlayer().buy(est)){
				hideManagePopover();
				showAlert("Property Bought", "Congratulations on your new property "+est.getName());
				
			}else{
				showAlert("Your poor!","Could not buy " + est.getName());
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
		Square square = selectedSquare;
		if(square instanceof Establishment){
			Establishment est = (Establishment)square;
			//If it has an owner, and the owner is the current player
			if(est.hasOwner() && est.getOwner().equals(game.getCurrentPlayer())){
				if(est.isMortgaged()){
					//If the user was able to unMortgage the establishment
					if(est.unMortgage()){
						btnMortgageManageProperty.setText("Mortgage");
						System.out.println(est.getOwner().getName() + " unMortgaged their property!");
					}else{
						btnMortgageManageProperty.setText("UnMortgage");
						System.out.println(est.getOwner().getName() + " Doesn't have enough to unmortgage their property!");
					}
					System.out.println(est.getOwner().getName() + " has £"+est.getOwner().getBalance());
				}else{
					est.Mortgage();
					System.out.println(est.getOwner().getName() + " Mortgaged their property!");
					System.out.println(est.getOwner().getName() + " has £"+est.getOwner().getBalance());
				}
				btnMortgageManageProperty.setVisible(true);
			}else{
				btnMortgageManageProperty.setVisible(false);
			}
		}
		drawBoard();
	}
	
	/**
	 * What happens when the user clicks on a square
	 * @param event
	 */
	public void SubjectSquareClicked(MouseEvent event){

		if(!tradeActive){
			selectedSquare = game.board.Squares[getSquareIndex(((Text)((Pane) event.getSource()).getChildren().get(1)).getText())];
			showManagePropertyPopover(true);
		}else{
			Establishment est = (Establishment)game.board.Squares[getSquareIndex(((Text)((Pane) event.getSource()).getChildren().get(1)).getText())];
			//If this property is either of the traders properties
			if(tradeowner.trader==est.getOwner()){
				tradeowner.est.add(est);
			}else if(othertrader.trader==est.getOwner()){
				othertrader.est.add(est);
			}
			setTradeInformation();
		}
	}
	/**
	 * What happens when the user clicks on a square
	 * @param event
	 */
	public void OtherSquareClicked(MouseEvent event){


		
		if(!tradeActive){
			selectedSquare = game.board.Squares[getSquareIndex(((Text)((Pane) event.getSource()).getChildren().get(0)).getText())];
			showManagePropertyPopover(true);
		}else{
			Establishment est = (Establishment)game.board.Squares[getSquareIndex(((Text)((Pane) event.getSource()).getChildren().get(0)).getText())];
			//If this property is either of the traders properties
			if(tradeowner.trader==est.getOwner()){
				tradeowner.est.add(est);
			}else if(othertrader.trader==est.getOwner()){
				othertrader.est.add(est);
			}
			setTradeInformation();
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
			if(square.getName().equals(text)){
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
		if(!tradeActive){
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
				
				//Update the dice Sign
				imgYourRoll.setVisible(game.canRoll());
				
				//Update if the Buy Property Button should be Active
				if(selectedSquare instanceof Establishment){
					Establishment landedEstablishment = (Establishment)(selectedSquare);
						//Set the button property depending on if the landedSquare has an owner
						if(landedEstablishment.hasOwner()){
							ManageBuy.setVisible(false);
							btnMortgageManageProperty.setVisible(true);
							if(landedEstablishment.isMortgaged()){
								btnMortgageManageProperty.setText("UnMortgage");
							}else{
								btnMortgageManageProperty.setText("Mortgage");
							}
							
							if(landedEstablishment.isMortgaged()){
								btnMortgageManageProperty.setText("UnMortgage");
							}else{
								btnMortgageManageProperty.setText("Mortgage");
							}
						}else{
							ManageBuy.setVisible(true);
							btnMortgageManageProperty.setVisible(false);
						}
				}else{
					//If not an establishment then disable these buttons
					ManageBuy.setVisible(false);
					btnMortgageManageProperty.setVisible(false);
				}
				
				//Update Property Information
				if(GrpManagePopover.isVisible() || GrpManagePropertyPopover.isVisible()){
					updateTitleDeed();
				}
				
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
							if(square.getSquareType().equals("Subject")){
								Subject sub = ((Subject)square);
								System.out.println("sub.getHouses(): "+sub.getHouses());
								for(int h = 0; h<sub.getHouses(); h++){
									
									if(sub.isFacility()){
										System.out.println("FACILITY!!");
										gc.setFill(Color.DARKCYAN);
										gc.fillRect(30, 10, 40, 20);
									}else{
										gc.setFill(Color.DARKRED);
										gc.fillRect((100/4)*h, 10, 20, 20);	
									}
								}
							}
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
		}else{
			//If there is a trade on, this is the updater
			startTrade();
			setTradeInformation();
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
	public void updateTitleDeed(){
		
		System.out.println("updateTitleDeed(): Called for "+selectedSquare.getName());
				
		showDeeds(true);
		System.out.println(selectedSquare.getSquareType());
		
		
		if(selectedSquare.getSquareType().equals("Subject")){
			Subject sub = (Subject)selectedSquare;
			
			System.out.println("Title Deed for Subject Updated!!"+sub.getName());
						
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
		}else if(selectedSquare.getSquareType().equals("Bar")){
			Bar bar = (Bar)selectedSquare;
			
			System.out.println("Title Deed for Bar Updated!!"+bar.getName());
			
			titleDeedBarName.setText(bar.getName());
			titleDeedBarRent.setText("Rent £"+bar.getBaseRent());
			titleDeedBar2.setText("£"+bar.getBaseRent()*2);
			titleDeedBar3.setText("£"+bar.getBaseRent()*3);
			titleDeedBar4.setText("£"+bar.getBaseRent()*4);
			
			titleDeedBarMortgage.setText("Mortgage Value £"+bar.getMortgageValue());
			
			//Update what Deed to show
			PneTitleDeed.setVisible(true);
			PneBarDeed.setVisible(false);
			PneRestaurantDeed.setVisible(false);
		}else if(selectedSquare.getSquareType().equals("Restaurant")){
			Restaurant restaurant = (Restaurant)selectedSquare;
			
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
			PneTitleDeed.setVisible(true);
			PneBarDeed.setVisible(false);
			PneRestaurantDeed.setVisible(false);
		}else{
			showDeeds(false);
			return;
		}
		showDeeds(true);
		

	}
	public void showDeeds(boolean b){
		grpAllDeeds.setVisible(b);
	}
	
	/**
	 * Shows the Manage Popover
	 * @param b
	 */
	public void showManagePopover(){
				
		if(!(selectedSquare instanceof Establishment)){
			return;
		}
		
		Establishment establishment = (Establishment)selectedSquare;
		
		//Update ManagePopover
		ManageText.setText("Purchase '"+establishment.getName()+"' for £"+establishment.getPrice());
		
		if(establishment.hasOwner()){
			txtManagePropertyOwnedBy.setText("Owned by"+establishment.getOwner().getName());
		}else{
			txtManagePropertyOwnedBy.setText("Not Owned");
		}
		
		
		//Disable the buy button if the current user is the owner
		if((establishment.hasOwner() && game.getCurrentPlayer().equals(establishment.getOwner())) || (getCurrentLandedSquare()!=establishment)){
			ManageBuy.setVisible(false);
		}else{
			ManageBuy.setVisible(true);
		}
		
		
		updateTitleDeed();

		//Show the popover
		GrpManagePopover.setVisible(true);
		showDeeds(true);
		
	}
	/**
	 * Hides the Manage Popover
	 */
	public void hideManagePopover(){
		//Set Visibility for Deeds
		showDeeds(false);
		
		//Show the popover
		GrpManagePopover.setVisible(false);
	}
	/**
	 * Show the Manage Property Popover
	 * @param b
	 */
	public void showManagePropertyPopover(Boolean b){
		System.out.println("showManagePropertyPopover(): "+ b);
		Establishment est = (Establishment)selectedSquare;
		//Update Text
		if(est.getOwner()==null){
			ManagePropertyText.setText("Not Owned");
			if(getCurrentLandedSquare().equals(selectedSquare)){
				ManageBuy.setVisible(true);
			}else{
				ManageBuy.setVisible(false);
			}
		}else{
			ManagePropertyText.setText("Owned by "+est.getOwner().getName());
			ManageBuy.setVisible(false);
		}

		
		//Show the popover
		GrpManagePropertyPopover.setVisible(b);
		hideManagePopover();

		
		//Reset Position for Deeds
		grpAllDeeds.setLayoutX(550);grpAllDeeds.setLayoutY(20);
		updateTitleDeed();
		showDeeds(true);
	}
	/**
	 * Mouse Event to shows the ManageProperty Popover
	 * @param e
	 */
	public void ManageProperty(MouseEvent e){
		Square square = getCurrentLandedSquare();
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
		showManagePopover();
	}
	/**
	 * Open UI for Chance Card etc.
	 * @param title
	 * @param text
	 * @param chance If a chance card or not
	 */
	public void showSpecialCard(String title, String text,boolean chance){
		pneSpecialCard.setVisible(true);
		
		if(chance){
			specialCardImage.setImage(new Image("\\gui\\img\\chance.png"));
		}else{
			specialCardImage.setImage(new Image("\\gui\\img\\chest.png"));
		}
		
		
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
	 * Message overlay
	 * @param title
	 * @param message
	 */
	public void showAlert(String title, String message){
		System.out.println("Alert Shown: "+message);
		grpPopupMessage.setVisible(true);
		PopUpTitle.setText(title);
		PopUpMessage.setText(message);
		grpPopupMessage.toFront();
	}
	public void hideAlert(){
		grpPopupMessage.setVisible(false);
	}
	public void hideAlert(MouseEvent e){
		hideAlert();
	}
	
	/**
	 * Button to buy a house
	 * @param e
	 */
	public void btnbuyHouse(MouseEvent event){
		if(game.upgrade((Subject) selectedSquare)){
			showAlert("Upgrade","You've a new building in your school!");
		}else{
			showAlert("Upgrade","Something went wrong!");
		}
		drawBoard();
	}
	/**
	 * Button to sell a house
	 * @param e
	 */
	public void btnsellHouse(MouseEvent event){
		if(((Subject) selectedSquare).sellHouse()){
			showAlert("Upgrade","Things looking bad? We sold that building for you!");
		}else{
			showAlert("Upgrade","Something went wrong!");
		}
		drawBoard();
	}
	
	
	
	////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////
////////////////////HELPER METHODS////////////////////////
//////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////
	public Square getCurrentLandedSquare(){
		return game.board.Squares[game.getCurrentPlayer().getPosition()];
	}
	public boolean canBuySquare(){
		return getCurrentLandedSquare().equals(selectedSquare);
	}	
	/**
	 * TODO DELETE
	 * @param event
	 */
	public void stub(MouseEvent event){System.out.println("ONLY A STUB METHOD, METHOD STILL TO BE COMPLETED!");}
	
	public void testButton(MouseEvent event){
		 ((Subject)(game.board.Squares[6])).changeOwner(game.getCurrentPlayer());
		 ((Subject)(game.board.Squares[8])).changeOwner(game.getCurrentPlayer());
		 ((Subject)(game.board.Squares[9])).changeOwner(game.getCurrentPlayer());
		 
		 ((Subject)(game.board.Squares[6])).buyHouse();
		 showAlert("Yo!", "Enjoy the wealth!");
		 drawBoard();
	}
	
	public void startTrade(){
		tradeActive = true;
		clearTradeInformation();

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
				if(owner==tradeowner.trader||owner==othertrader.trader){
					//Draw any Land
					gc.setFill(Color.web(playerColors[game.getPlayerIndex(owner)]));
					gc.fillRoundRect(0,150,100,20, 20, 20);
				}else{
					//Gray Out
					gc.setFill(Color.web("#000", .5));
					if(i%10==0){
						gc.fillRoundRect(0,0,150,150, 0, 0);
					}else{
						gc.fillRoundRect(0,0,100,150, 0, 0);
					}
					
				}
			}
		}
	}
	
	public void clearTradeInformation(){
		
		//Left Trader
		txtUsernameTradeLeft.setText("Select User Above");
		txtPropertiesTradeLeft.setText("");
		txtTradeCashLeft.setText("");
		btnLockTradeLeft.setText("Lock Trade");
		
		//Right Trader
		txtUsernameTradeRight.setText("Select User Above");
		txtPropertiesTradeRight.setText("");
		txtTradeCashRight.setText("");
		btnLockTradeRight.setText("Lock Trade");
	}
	public void lockTrade(MouseEvent e){
		
		if(((Button)(e.getSource())).getId().equals("rightLock")){
			btnLockTradeRight.setText("Locked");
			tradeowner.locked = true;
		}else{
			btnLockTradeLeft.setText("Locked");
			othertrader.locked = true;
		}
					
		if(tradeowner.locked && othertrader.locked){
			tradeInformationConfirmed();
		}
	}
	/**
	 * Final Confirmation of Trade (Sends off trade)
	 */
	public void tradeInformationConfirmed(){
		
		System.out.println("tradeInformationConfirmed(): ");
		
		if(txtTradeCashLeft.getText().isEmpty()){txtTradeCashLeft.setText("0");}
		if(txtTradeCashRight.getText().isEmpty()){txtTradeCashRight.setText("0");}
		
		System.out.println("AMount:  $$ "+txtTradeCashLeft.getText()+" + "+txtTradeCashRight.getText());
		
		game.trade(tradeowner.trader, tradeowner.est, Integer.parseInt(txtTradeCashLeft.getText()), 
			othertrader.trader, othertrader.est, Integer.parseInt(txtTradeCashRight.getText()));
		stopTrade();
		showAlert("Trade", "Trade Complete");
			
	}
	public void setTradeInformation(){
		//Left Trader
		txtUsernameTradeLeft.setText(tradeowner.trader.getName());
		imgLeftTrader.setImage(new Image("\\gui\\"+tradeowner.trader.getToken().getIconFileLocation()));
		String o = "";
		for(Establishment est : tradeowner.est){o+= est.getName()+"\n";}
		txtPropertiesTradeLeft.setText(o);
		
		//Right Trader
		txtUsernameTradeRight.setText(othertrader.trader.getName());
		imgRightTrader.setImage(new Image("\\gui\\"+othertrader.trader.getToken().getIconFileLocation()));
		o = "";
		for(Establishment est : othertrader.est){o+= est.getName()+"\n";}
			txtPropertiesTradeRight.setText(o);
	}
	/**
	 * Starts the trade cycle
	 * @param e
	 */
	public void startTrade(MouseEvent e){
		pneTrade.setVisible(true);
		tradeowner = new Trade(game.getCurrentPlayer());
		othertrader = new Trade(null);
		tradeActive = true;
	}
	public void stopTrade(MouseEvent e){
		stopTrade();
	}
	public void stopTrade(){
		pneTrade.setVisible(false);
		tradeowner = null; othertrader = null;
		tradeActive = false;
		clearTradeInformation();
		drawBoard();
	}
	//Sets the player of trader (if trade is active)
	public void playerClick(MouseEvent e){
		if(tradeActive){
			String name = ((Text)((Group)(e.getSource())).getChildren().get(0)).getText();
			for(int i = 0; i<game.players.size(); i++){
				if(game.players.get(i).getName().equals(name) && !game.getCurrentPlayer().getName().equals(name)){
					othertrader.trader = game.players.get(i);
					System.out.println("playerClick(): othertrader"+othertrader.trader.getName()+" added!");
					drawBoard();
					return;
				}else{
					System.out.println("playerClick():"+game.players.get(i).getName()+" "+name);
				}
			}
		}else{
			System.out.println("Trade not Active!");
		}
	}
	

	//Trade Object Shh!
	class Trade{
		public int Amount;
		public ArrayList<Establishment> est;
		public Player trader;
		public boolean locked;
		public Trade(Player p){
			Amount=0;
			est=new ArrayList<Establishment>();
			trader=p;
		}
	}

}
