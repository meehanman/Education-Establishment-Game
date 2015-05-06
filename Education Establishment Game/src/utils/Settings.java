package utils;

/**
 * 
 * SETTINGS FOR EEG Game
 * 
 * @author Dean
 *
 */
public class Settings {
	public int StartingMoney;
	public int PlayUntilNPlayers;
	public int TimeMinutes;
	public int GetOutOfJailAmount;
	public Language language;
	
	public enum Language { English, Spanish, French, German };

	public Settings(){
		StartingMoney = 1500;
		PlayUntilNPlayers = 1;
		TimeMinutes = -1;
		GetOutOfJailAmount = 50;
		language = Language.English;
	}
}
