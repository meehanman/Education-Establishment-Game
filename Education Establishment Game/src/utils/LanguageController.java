package utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageController {
	
	Locale currentLocale;
	ResourceBundle messages;
	
	public LanguageController(Locale language){
		currentLocale = language;
		messages = ResourceBundle.getBundle("\\resources\\MessagesBundle", currentLocale);
		
	}
	
	public void setLanguage(Locale language){
		currentLocale = language;
		messages = ResourceBundle.getBundle("\\resources\\MessagesBundle", currentLocale);
	}
	
	public String getMessage(String text){
		return messages.getString(text);
	}
	
}
