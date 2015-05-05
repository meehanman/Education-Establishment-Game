package board;

import utils.Dice;
import board.SpecialSquare.Type;
import board.establishment.Bar;
import board.establishment.Restaurant;
import board.establishment.Subject;

/**
 * 
 * The board where everything else arises
 * Contains the cards and Squares/Tiles for the board
 * 
 * @author Dean
 *
 * TODO Needs lots of work 
 */
public class Board {
	
	public Square[] Squares;
	public CardDeck ComunityCheckCardsChest;
	public CardDeck ChanceCardsDeck;
	public Dice dice = new Dice();
	
	/**
	 * Initilised a default board for the game including cards, dice and squares
	 */
	public Board(){
		//Draw the Squares
		initialiseSquares();
		initialiseComunityChestCards();
		initialiseChanceCards();
	}
	
	private void initialiseComunityChestCards(){
		Card[] ComunityCards = {
				new Card("Academia","Your invited to dinner at Maggie May's! \nMove to Maggie May's",new Effect(0,28,false,0,true,false)),
				new Card("Academia","After lecture drink at the speakeasy! \nMove to Speakeasy",new Effect(0,5,false,0,true,false)),
				new Card("Academia","Need fuel for all those research topics - get a Boojum! \nMove to Boojum",new Effect(0,12,false,0,true,false)),
				new Card("Academia","All these classes are making you Cuckoo!\nMove to Cuckoo",new Effect(0,35,false,0,true,false)),
				new Card("Academia","Quiet pint in Ryan's at the end of a long day\nMove to Ryans",new Effect(0,25,false,0,true,false)),
				new Card("Academia","Get yourself down to the Bot for a pub quiz!\nMove to Bot",new Effect(0,15,false,0,true,false)),
				new Card("Academia","We can't say whats happened. But you've been sent to Detention!\nMove to Detention",new Effect(0,30,false,0,true,false)),
				new Card("Academia","Help those on probation get back into the swing of things. \n Recieve £50",new Effect(50,10,false,0,true,false)),
				new Card("Academia","Union Action - Go on Strike!\n Move to Strike Day",new Effect(0,20,false,0,true,false)),
				new Card("Academia","Win debate. \n Receieve\n Recieve £100",new Effect(100,0,false,0,false,false)),
				new Card("Academia","Successfully defend your thesis. \n Receieve \n Recieve £70",new Effect(70,0,false,0,false,false)),
				new Card("Academia","Achieve tenure! Bonus \n Receieve \n Recieve £200",new Effect(200,0,false,0,false,false)),
				new Card("Academia","Reasearch breakthrough! Additional funding! \n Receieve \n Recieve £50",new Effect(50,0,false,0,false,false)),
				new Card("Academia","Tech Demo Mishap. \n Pay £40 per school",new Effect(-40,0,false,0,false,true)),
				new Card("Academia","Living for the research grant \n Receieve £500",new Effect(500,0,false,0,true,false)),
				new Card("Academia","Help a cop at their recruitment drive on campus - they will remember that!  \n Awarded an Out Of Jail Free Card",new Effect(0,0,true,0,false,false)),
				new Card("Academia","Your reasearch paper contains plagurised content! \n Pay\n Pay £100",new Effect(-100,0,false,0,false,false)),
				new Card("Academia","Labs are damaged in an accident \n Pay £30 per school",new Effect(-30,0,false,0,false,true)),
				new Card("Academia","Funding boost accross your premises\n Recieve £20 per school",new Effect(20,0,false,0,false,true)),
				new Card("Academia","Your campuse voted top!\n Recieve £100",new Effect(100,0,false,0,false,false))
		};
		
		ComunityCheckCardsChest = new CardDeck(ComunityCards);
	}
	
	private void initialiseChanceCards(){
		Card[] ChanceCards = {
			new Card("Student Experience","Student union attracts tourism.\n Recieve £50",new Effect(50,0,false,0,false,false)),
			new Card("Student Experience","Campus has become a landmark!\n Recieve £100",new Effect(100,0,false,0,false,false)),
			new Card("Student Experience","Student Party Gets out of hand!\n Pay £80",new Effect(80,0,false,0,false,false)),
			new Card("Student Experience","Student Elections, provide materials\n Pay £50",new Effect(50,0,false,0,false,false)),
			new Card("Student Experience","Student strike after fee rise!\n Pay £50",new Effect(-50,0,false,0,false,false)),
			new Card("Student Experience","Low campus ratings!\n Pay £30 per school",new Effect(-30,0,false,0,false,true)),
			new Card("Student Experience","High ratings!\n Recieve £20 per school",new Effect(20,0,false,0,false,true)),
			new Card("Student Experience","Word of your top class facilities spread!\n Recieve £20 per school",new Effect(20,0,false,0,false,true)),
			new Card("Student Experience","Societies attract more students!\n Recieve £150",new Effect(150,0,false,0,false,false)),
			new Card("Student Experience","Party at the Bot!\n Pay £50",new Effect(-50,15,false,0,true,false)),
			new Card("Student Experience","Pub crawl! Get to the speakeasy!\n Pay £50",new Effect(-50,5,false,0,true,false)),
			new Card("Student Experience","Fresher's events attract crowds!\n Recieve £100",new Effect(100,0,false,0,false,false)),
			new Card("Student Experience","RAG week raises record figures!\n Recieve £150",new Effect(150,0,false,0,false,false)),
			new Card("Student Experience","Students protect funding cuts\n Pay £20 per school",new Effect(-20,0,false,0,false,true)),
			new Card("Student Experience","Host inter-varsities!\n Pay £100",new Effect(-100,0,false,0,false,false)),
			new Card("Student Experience","Hold Campus Cup Competition!\n Pay £100",new Effect(-100,0,false,0,false,false)),
			new Card("Student Experience","Society lose students on mystery tour!\n Pay £150",new Effect(-150,0,false,0,false,false)),
			new Card("Student Experience","Society win competition and Achieve recognition!\n Pay £200",new Effect(-200,0,false,0,false,false))
		};
		
		ChanceCardsDeck = new CardDeck(ChanceCards);
	}
	
	private void initialiseSquares(){
		
		//Default Effects
		Effect PassGo = new Effect(200, 0, false,0,false,false);
		Effect IncomeTax = new Effect(-200,0,false,0,false,false);
		Effect SuperTax = new Effect(-100,0,false,0,false,false);
		Effect VisitingJail = new Effect(0,0,false,0,false,false);
		Effect GotoJail = new Effect(0,0,false,0,false,false);
		Effect FreeParking = new Effect(0,0,false,0,false,false); //TODO
		//Card Pickups
		Effect CommunityChest = new Effect(0, 0, false,1,false,false);
		Effect ChanceCard = new Effect(0, 0, false,2,false,false);
		 
		Squares = new Square[40];
		
		//Row 1
		Squares[0] = new SpecialSquare(Type.Go, PassGo);
		Squares[1] = new Subject("Philosophy", "brown", 60, new int[] {2,10,30,90,160,250},50);
		Squares[2] = new SpecialSquare(Type.CommunityChest, CommunityChest);
		Squares[3] = new Subject("Politics", "brown", 60, new int[] {4,20,60,180,320,450},50);
		Squares[4] = new SpecialSquare(Type.IncomeTax, IncomeTax);
		Squares[5] = new Bar("Speakeasy",200);
		Squares[6] = new Subject("History", "lightblue", 100, new int[] {6,30,90,270,400,550},50);
		Squares[7] = new SpecialSquare(Type.ChanceCard, ChanceCard);
		Squares[8] = new Subject("Anthropology", "lightblue", 100, new int[] {6,30,90,270,400,550},50);
		Squares[9] = new Subject("Theology", "lightblue", 120, new int[] {8,40,100,300,450,600},50);

		
		//Row 2
		Squares[10] = new SpecialSquare(Type.Jail, VisitingJail);
		Squares[11] = new Subject("Archaeology", "pink", 60, new int[] {10,50,150,450,625,750},100);
		Squares[12] = new Restaurant("Boojum",150);
		Squares[13] = new Subject("Geography", "pink", 60, new int[] {10,50,150,450,625,750},100);
		Squares[14] = new Subject("Environmental Engineering", "pink", 60, new int[] {12,60,180,500,700,900},100);
		Squares[15] = new Bar("Botanic Inn",200);
		Squares[16] = new Subject("Zoology", "orange", 100, new int[] {14,70,200,550,750,950},100);
		Squares[17] = new SpecialSquare(Type.CommunityChest, CommunityChest);
		Squares[18] = new Subject("Microbiology", "orange", 100, new int[] {14,70,200,550,750,950},100);
		Squares[19] = new Subject("Marine Biology", "orange", 100, new int[] {16,80,220,600,800,1000},100);
		
		//Row 3
		Squares[20] = new SpecialSquare(Type.FreeParking, FreeParking);
		Squares[21] = new Subject("Medical Chemistry", "red", 100, new int[] {18,90,250,700,875,1050},150);
		Squares[22] = new SpecialSquare(Type.ChanceCard, ChanceCard);
		Squares[23] = new Subject("Chemistry", "red", 100, new int[] {18,90,250,700,875,1050},150);
		Squares[24] = new Subject("Chemical Engineering", "red", 100, new int[] {20,100,300,750,925,1100},150);
		Squares[25] = new Bar("Ryans",200);
		Squares[26] = new Subject("Maths", "yellow", 100, new int[] {22,110,330,800,975,1150},150);
		Squares[27] = new Subject("Physics", "yellow", 100, new int[] {22,110,330,800,975,1150},150);
		Squares[28] = new Restaurant("Maggie Mays",150);
		Squares[29] = new Subject("Theoretical Physics", "yellow", 120, new int[] {24,120,360,850,1025,1200},150);
		
		//Row 4
		Squares[30] = new SpecialSquare(Type.GotoJail, GotoJail);
		Squares[31] = new Subject("Dentistry", "green", 120, new int[] {26,130,390,900,1100,1275},200);
		Squares[32] = new Subject("Nursing", "green", 120, new int[] {26,130,390,900,1100,1275},200);
		Squares[33] = new SpecialSquare(Type.CommunityChest, CommunityChest);
		Squares[34] = new Subject("Human Genetics", "green", 120, new int[] {28,150,450,1000,1200,1400},200);
		Squares[35] = new Bar("Cuckoo",200);
		Squares[36] = new SpecialSquare(Type.ChanceCard, ChanceCard);
		Squares[37] = new Subject("Electrical and Electronic Engineering", "darkblue", 100, new int[] {35,175,500,1100,1300,1500},200);
		Squares[38] = new SpecialSquare(Type.SuperTax, SuperTax);
		Squares[39] = new Subject("Computer Science", "darkblue", 120, new int[] {50,200,600,1400,1700,2000},200);
		
	}
	
	
}
