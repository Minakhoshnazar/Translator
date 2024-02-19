package Translator.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Translator {

	private static final String Properties = null;
	private Map<String, String> dictionary = new HashMap<>();
	
	public boolean isEmpty() {
		return dictionary.isEmpty();
	}

   public String Translator(String word) {
	if (word.contains(" ")) {
		String[] words = word.split(" ");
		String Translation = "";
		for (String w : words) {
			if (w.equals("[,.;!]+")) {
	          Translation += w + (" ");
	         
	        }
			else {
				Translation += Translator(w).split(",")[0] + (" ");
			}
			
		}
	    	
          return Translation.trim().toLowerCase();
		}
	else {
	   if (dictionary.containsKey(word))
		return dictionary.get(word);
	}
	return word;
	
	}
	

	public void AddTranslation(String sourceWord, String destWord) {
		if (dictionary.containsKey(sourceWord))
			dictionary.put(sourceWord, dictionary.get(sourceWord).toLowerCase() + ", " + destWord);
		else
	
		    dictionary.put(sourceWord, destWord);
		
	}

	public void load(String fileName) throws FileNotFoundException, IOException {
		java.util.Properties propfile = new java.util.Properties();
		propfile.load(new FileReader(fileName));
		for (Object word : propfile.keySet()) {
			AddTranslation(word.toString(), propfile.get(word).toString());	
		}
		
	}


	
	
	
}

	

