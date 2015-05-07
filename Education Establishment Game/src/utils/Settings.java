package utils;

import gui.GameSetupController;

import java.util.Locale;

/**
 * 
 * SETTINGS FOR EEG Game
 * 
 * @author Dean
 *
 */
public class Settings {
	public static int StartingMoney;
	public static int PlayUntilNPlayers;
	public static int TimeMinutes;
	public static int GetOutOfJailAmount;
	public static Locale language;
	
	public static void defaultSettings(){
		Settings.StartingMoney = 1500;
		Settings.PlayUntilNPlayers = 1;
		Settings.TimeMinutes = -1;
		Settings.GetOutOfJailAmount = 50;
		Settings.language = Locale.ENGLISH;
	}
}
