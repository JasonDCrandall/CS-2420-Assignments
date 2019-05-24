package comprehensive;

import java.util.ArrayList;

/**
 * This class parses through the individual strings in a production
 * and separates them by non terminnals. Produces ArrayLists of strings to 
 * be used by the grammar and Grammar Parser classes.
 * 
 * @author Jason Crandall & Michael Hart
 * @version April 16, 2019
 */
public class Production {

	private ArrayList<String> tokens;

	/**
	 * Constructor for the production, being represented as an ArrayList
	 * of strings.
	 */
	public Production() {
		tokens = new ArrayList<String>();
	}
	
	/**
	 * Constructor for a production that parses through an imported string
	 * and separates the terminals from the non terminals in an ArrayList in 
	 * the order in which they appear.
	 * 
	 * @param prodString
	 */
	public Production(String prodString) {
		tokens = new ArrayList<String>();
		int start = 0;
		char bracket = '<';
		for (int i = 0; i < prodString.length(); i++) {
			
			// Once the program reaches an angle bracket, it will add everything
			// that came before it to the array list, then everything in between
			// the angle brackets, and so on until the string is complete
			if (prodString.charAt(i) == bracket) {
				if (bracket == '<') {
					if (i != start) {
						tokens.add(prodString.substring(start, i));
					}
					bracket = '>';
					start = i;
				}
				else {
					tokens.add(prodString.substring(start, i+1));
					bracket = '<';
					start = i + 1;
				}
			}
		}
		if (start < prodString.length()) {
			tokens.add(prodString.substring(start));
		}	
	}

	/**
	 * Adds a value to the production list
	 * 
	 * @param token
	 */
	public void addToken(String token) {
		tokens.add(token);
	}
	
	/**
	 * Getter for the production. Returns a list of strings
	 * 
	 * @return tokens - ArrayList of strings
	 */
	public ArrayList<String> getTokens() {
		return tokens;
	}
}
