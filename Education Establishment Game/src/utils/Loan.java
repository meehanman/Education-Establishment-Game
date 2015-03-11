package utils;

public class Loan {
	//total current value of player's loan
	double amount;
	//rate in which the loan increases
	double rate;
	
	/**
	 * Getters and setters for loan values
	 */
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	
	/**
	 * Calculate the total current standing of 
	 * a player's loan. To be called on end of 
	 * loan timescale (pass go etc)
	 */
	public void calcInterest(){
		amount += amount * rate;
	}
	

}
