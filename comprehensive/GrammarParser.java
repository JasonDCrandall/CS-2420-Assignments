package comprehensive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is designed to parse through an inputed file and add
 * the inputs to a created Grammar.
 * 
 * @author Jason Crandall & Michael Hart
 * @version April 16, 2019
 */
public class GrammarParser {

	/**
	 * With a scanner, it will go through each of the lines
	 * in an imported grammar file and will parse out the non terminals and their 
	 * productions. It then adds them to a grammar, the non terminal being the key,
	 * the list of productions being the value.
	 * 
	 * @param filename - grammar file
	 * @return grammar
	 * @throws FileNotFoundException
	 */
	public static Grammar parseGrammar(String filename) throws FileNotFoundException {
		File file = new File(filename);
		Scanner s = new Scanner(file);
		Grammar grammar = new Grammar();
		while (s.hasNext()) {
			String text = s.nextLine();
			
			// If an open bracket is found, it will store it as the nonterminal
			// and each line after it will be considered a production until the 
			// closing bracket
			if (text.equals("{")) {
				String name = s.nextLine();
				text = s.nextLine();
				ArrayList<Production> prodList = new ArrayList<Production>();
				while (!text.equals("}")) {
					prodList.add(new Production(text));
					text = s.nextLine();
				}
				grammar.addNonTerminal(name, prodList);
			}
			
		}
		s.close();
		return grammar;
	}
}
