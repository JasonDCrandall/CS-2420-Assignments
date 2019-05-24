package comprehensive;

import java.io.FileNotFoundException;

/**
 * This class generates a set of random phroses based on an inputed grammar
 * and integer indicating the desired number of phrases
 * 
 * @author Jason Crandall & Michael Hart
 * @version April 16, 2019
 */
public class RandomPhraseGenerator {

	/**
	 * Main method that prints to the console a set of randomly generated phrases 
	 * based on the inputed grammar. 
	 * 
	 * @param args - a grammar file and the number of phrases to generate
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		if (args.length < 2 || args.length > 2) {
			System.out.println("Please provide a grammar file and the number of phrases to generate");
			return;
		}
		
		String filename = args[0];
		int phraseCount = 0;
		try {
			phraseCount = Integer.parseInt(args[1]);
		} catch (NumberFormatException e) {
			System.out.println("Please enter a valid number of phrases");
		} 
				
		Grammar grammar = GrammarParser.parseGrammar(filename);
		
		for(int i = 0; i < phraseCount; i++) {
			System.out.println(grammar.generatePhrase());
		}
		
	}

}
