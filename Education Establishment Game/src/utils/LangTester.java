package utils;

import java.util.Locale;

public class LangTester {

	public static void main(String[] args) {
		LanguageController lang = new LanguageController(Locale.ENGLISH);
		
		System.out.println(lang.getMessage("greetings"));
	}

}
