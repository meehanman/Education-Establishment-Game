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
				new Card("CC Card","This is some Comunity Description",new Effect(200,0,false,0)),
				new Card("CC Card","This is some Comunity Description",new Effect(200,0,false,0)),
				new Card("CC Card","This is some Comunity Description",new Effect(200,0,false,0)),
				new Card("CC Card","This is some Comunity Description",new Effect(200,0,false,0)),
				new Card("CC Card","This is some Comunity Description",new Effect(200,0,false,0)),
				new Card("CC Card","This is some Comunity Description",new Effect(200,0,false,0)),
				new Card("CC Card","This is some Comunity Description",new Effect(200,0,false,0)),
				new Card("CC Card","This is some Comunity Description",new Effect(200,0,false,0)),
				new Card("CC Card","This is some Comunity Description",new Effect(200,0,false,0)),
				new Card("CC Card","This is some Comunity Description",new Effect(200,0,false,0)),
				new Card("CC Card","This is some Comunity Description",new Effect(200,0,false,0)),
				new Card("CC Card","This is some Comunity Description",new Effect(200,0,false,0)),
				new Card("CC Card","This is some Comunity Description",new Effect(200,0,false,0)),
				new Card("CC Card","This is some Comunity Description",new Effect(200,0,false,0)),
				new Card("CC Card","This is some Comunity Description",new Effect(200,0,false,0)),
				new Card("CC Card","This is some Comunity Description",new Effect(200,0,false,0)),
				new Card("CC Card","This is some Comunity Description",new Effect(200,0,false,0)),
				new Card("CC Card","This is some Comunity Description",new Effect(200,0,false,0)),
				new Card("CC Card","This is some Comunity Description",new Effect(200,0,false,0))
		};
		
		ComunityCheckCardsChest = new CardDeck(ComunityCards);
	}
	
	private void initialiseChanceCards(){
		Card[] ChanceCards = {
			new Card("C Card","This is some Chance Description",new Effect(-250,0,false,0)),
			new Card("C Card","This is some Chance Description",new Effect(-250,0,false,0)),
			new Card("C Card","This is some Chance Description",new Effect(-250,0,false,0)),
			new Card("C Card","This is some Chance Description",new Effect(-250,0,false,0)),
			new Card("C Card","This is some Chance Description",new Effect(-250,0,false,0)),
			new Card("C Card","This is some Chance Description",new Effect(-250,0,false,0)),
			new Card("C Card","This is some Chance Description",new Effect(-250,0,false,0)),
			new Card("C Card","This is some Chance Description",new Effect(-250,0,false,0)),
			new Card("C Card","This is some Chance Description",new Effect(-250,0,false,0)),
			new Card("C Card","This is some Chance Description",new Effect(-250,0,false,0)),
			new Card("C Card","This is some Chance Description",new Effect(-250,0,false,0)),
			new Card("C Card","This is some Chance Description",new Effect(-250,0,false,0)),
			new Card("C Card","This is some Chance Description",new Effect(-250,0,false,0)),
			new Card("C Card","This is some Chance Description",new Effect(-250,0,false,0)),
			new Card("C Card","This is some Chance Description",new Effect(-250,0,false,0)),
			new Card("C Card","This is some Chance Description",new Effect(-250,0,false,0)),
			new Card("C Card","This is some Chance Description",new Effect(-250,0,false,0)),
			new Card("C Card","This is some Chance Description",new Effect(-250,0,false,0)),
			new Card("C Card","This is some Chance Description",new Effect(-250,0,false,0))
		};
		
		ChanceCardsDeck = new CardDeck(ChanceCards);
	}
	
	private void initialiseSquares(){
		
		//Default Effects
		Effect PassGo = new Effect(200, 0, false,0);
		Effect IncomeTax = new Effect(-200,0,false,0);
		Effect SuperTax = new Effect(-100,0,false,0);
		Effect VisitingJail = new Effect(0,0,false,0);
		Effect GotoJail = new Effect(0,0,false,0);
		Effect FreeParking = new Effect(0,0,false,0); //TODO
		//Card Pickups
		Effect CommunityChest = new Effect(0, 0, false,1);
		Effect ChanceCard = new Effect(0, 0, false,2);
		 
		Squares = new Square[40];
		
		//Row 1
		Squares[0] = new SpecialSquare(Type.Go, PassGo);
		Squares[1] = new Subject("Philosophy", "brown", 60, new int[] {2,10,30,90,160,250},50);
		Squares[2] = new SpecialSquare(Type.ComunityChest, CommunityChest);
		Squares[3] = new Subject("Politics", "brown", 60, new int[] {4,20,60,180,320,450},50);
		Squares[4] = new SpecialSquare(Type.IncomeTax, IncomeTax);
		Squares[5] = new Bar("Speakeasy",200);
		Squares[6] = new Subject("History", "lightblue", 100, new int[] {6,30,90,270,400,550},50);
		Squares[7] = new SpecialSquare(Type.ChanceCard, ChanceCard);
		Squares[8] = new Subject("Anthropology", "lightblue", 100, new int[] {6,30,90,270,400,550},50);
		Squares[9] = new Subject("Theology", "lightblue", 120, new int[] {8,40,100,300,450},50);

		
		//Row 2
		Squares[10] = new SpecialSquare(Type.Jail, VisitingJail);
		Squares[11] = new Subject("Archaeology", "pink", 60, new int[] {10,50,150,450,625,750},100);
		Squares[12] = new Restaurant("Boojum",150);
		Squares[13] = new Subject("Geography", "pink", 60, new int[] {10,50,150,450,625,750},100);
		Squares[14] = new Subject("Environmental Engineering", "pink", 60, new int[] {12,60,180,500,700,900},100);
		Squares[15] = new Bar("Botanic Inn",200);
		Squares[16] = new Subject("Zoology", "orange", 100, new int[] {14,70,200,550,750,950},100);
		Squares[17] = new SpecialSquare(Type.ComunityChest, CommunityChest);
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
		Squares[33] = new SpecialSquare(Type.ComunityChest, CommunityChest);
		Squares[34] = new Subject("Human Genetics", "green", 120, new int[] {28,150,450,1000,1200,1400},200);
		Squares[35] = new Bar("Cuckoo",200);
		Squares[36] = new SpecialSquare(Type.ChanceCard, ChanceCard);
		Squares[37] = new Subject("Electrical and Electronic Engineering", "darkblue", 100, new int[] {35,175,500,1100,1300,1500},200);
		Squares[38] = new SpecialSquare(Type.SuperTax, SuperTax);
		Squares[39] = new Subject("Computer Science", "darkblue", 120, new int[] {50,200,600,1400,1700,2000},200);
		
	}
	
	
}
