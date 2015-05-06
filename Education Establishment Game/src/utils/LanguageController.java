package utils;

import java.util.ResourceBundle;

public class LanguageController {
	
	private static ResourceBundle messages = ResourceBundle.getBundle("\\resources\\MessagesBundle", Settings.language);
	
	public static String getMessage(String text){
		return messages.getString(text);
	}
	
}
