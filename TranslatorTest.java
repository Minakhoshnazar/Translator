package Translator.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class TranslatorTest {

	@Test
	void EmptyTranslator() {
		Translator t = new Translator();
		assertTrue(t.isEmpty());
	}
	
	@Test
	void UnknownwordTranslator() {
		Translator t = new Translator();
		String word = "Unknown";
		String Translation = t.Translator("Unknown");
		assertEquals(word, Translation);
		
	}
	
	@Test
	void AddtranslationAndTranslate() {
		Translator t = new Translator();
		String sourceWord = "ambulanza";
		String destWord = "Krankenwagen";
		t.AddTranslation(sourceWord, destWord);
		String Translation = t.Translator(sourceWord);
		assertEquals(destWord, Translation);
		assertFalse(t.isEmpty());
		
	}
	
	@Test
	void AddPairWordstranslationAndTranslate() {
		Translator t = new Translator();
		t.AddTranslation("ambulanza", "Krankenwagen");
		t.AddTranslation("farfalla", "Schmetterling");
		assertEquals("Krankenwagen", t.Translator("ambulanza"));
		assertEquals("Schmetterling", t.Translator("farfalla"));
		assertFalse(t.isEmpty());
		
	}
	
	@Test
	void AddTwoTranslationSameWords() {
		Translator t = new Translator();
		t.AddTranslation("fare", "tun");
		t.AddTranslation("fare", "machen");
		assertEquals("tun, machen", t.Translator("fare"));
		assertFalse(t.isEmpty());
		
	}
	
	@Test
	void TranslateSentence() {
		Translator t = new Translator();
		String DestSentence = "schmetterling fliegt langsam";
		String SourceSentence = "farfalla vola lentamente";
		t.AddTranslation("farfalla", "schmetterling");
		t.AddTranslation("vola", "fliegt");
		t.AddTranslation("lentamente", "langsam");
		assertEquals(DestSentence, t.Translator(SourceSentence));
	}
	
	@Test
	void TranslateSentenceWithMultipleWords() {
		Translator t = new Translator();
		String DestSentence = "schmetterling fliegt gemachlich";
		String SourceSentence = "farfalla vola lentamente";
		t.AddTranslation("farfalla", "schmetterling");
		t.AddTranslation("vola", "fliegt");
		t.AddTranslation("lentamente", "gemachlich"); //gemaechlich, langsam
		t.AddTranslation("lentamente", "langsam");
		assertEquals(DestSentence, t.Translator(SourceSentence));
	}

	@Test
	void TranslateFromPropertiesFile() throws FileNotFoundException, IOException {
		Translator t = new Translator();
		String DestSentence = "schmetterling fliegt gemachlich";
		String SourceSentence = "farfalla vola lentamente";
		t.load("dictionary.prop");
		assertEquals(DestSentence, t.Translator(SourceSentence));
	}

	@Test
	void NonCaseSensitiveWordsTranslation() {
		Translator t = new Translator();
		t.AddTranslation("farfalla", "schmetterling");
		t.AddTranslation("farfalla", "SchmettErling");
		assertEquals("schmetterling, SchmettErling", t.Translator("farfalla"));
	}
	
	@Test
	void NonCaseSensitiveSentenceTranslation() {
		Translator t = new Translator();
		String SourceSentence = "farfalla vola lentamente";
		String DestSentence = "schmetterling fliegt langsam";
		t.AddTranslation("farfalla", "Schmetterling");
		t.AddTranslation("vola", "fliEgt");
		t.AddTranslation("lentamente", "Langsam");
		assertEquals(DestSentence, t.Translator(SourceSentence));
		
	}
	
	@Test
	void KeepPunctuation() {
		Translator t = new Translator();
		String SourceSentence = "farfalla ! vola lentamente";
		String DestSentence = "schmetterling ! fliegt gemachlich";
		t.AddTranslation("farfalla", "schmetterling");
		t.AddTranslation("vola", "fliegt");
		t.AddTranslation("lentamente", "gemachlich");
		assertEquals(DestSentence, t.Translator(SourceSentence));
		
		
	}
	
	
}
