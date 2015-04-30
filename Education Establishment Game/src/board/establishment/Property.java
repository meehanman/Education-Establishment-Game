package board.establishment;

import utils.Player;
import javafx.scene.paint.Color;

public class Property {
	
	private String name;
	private Color color;
	private double price;
	private Player owner;
	private double mortgageValue;
	private boolean mortgaged;
	private boolean forSale;
	private double[] rent;
	private int rentFactor;
	private double noOfHouses;
	private double houseCost;
	private boolean hotel;
	private boolean canBuildOn;
	
	public Property(String name, Color color, double price, double mortgageValue,
			double[] rent, double houseCost, boolean canBuildOn ){
		this.name = name;
		this.color = color;
		this.price = price;
		this.mortgageValue = mortgageValue;
		this.rent = rent;
		this.houseCost = houseCost;
		this.canBuildOn = canBuildOn;
		this.mortgaged = false;
		this.forSale = true;
		this.rentFactor = 0;
	}
	
	//modified methods
	
	public double getRent(){
		return rent[rentFactor];
	}
	
	//generated getters and setters

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public double getMortgageValue() {
		return mortgageValue;
	}

	public void setMortgageValue(double mortgageValue) {
		this.mortgageValue = mortgageValue;
	}

	public boolean isMortgaged() {
		return mortgaged;
	}

	public void setMortgaged(boolean mortgaged) {
		this.mortgaged = mortgaged;
	}

	public boolean isForSale() {
		return forSale;
	}

	public void setForSale(boolean forSale) {
		this.forSale = forSale;
	}

	public int getRentFactor() {
		return rentFactor;
	}

	public void setRentFactor(int rentFactor) {
		this.rentFactor = rentFactor;
	}

	public double getNoOfHouses() {
		return noOfHouses;
	}

	public void setNoOfHouses(double noOfHouses) {
		this.noOfHouses = noOfHouses;
	}

	public double getHouseCost() {
		return houseCost;
	}

	public void setHouseCost(double houseCost) {
		this.houseCost = houseCost;
	}

	public boolean isHotel() {
		return hotel;
	}

	public void setHotel(boolean hotel) {
		this.hotel = hotel;
	}

	public boolean isCanBuildOn() {
		return canBuildOn;
	}

	public void setCanBuildOn(boolean canBuildOn) {
		this.canBuildOn = canBuildOn;
	}

	public void setRent(double[] rent) {
		this.rent = rent;
	}
	
	
	
	
	
}
