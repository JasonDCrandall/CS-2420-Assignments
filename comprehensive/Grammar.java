package comprehensive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Stack;

/**
 * The Grammar class is backed by a hash map that stores non terminals
 * as the keys and then a list of productions as the values in the map.
 * 
 * @author Jason Crandall & Michael Hart
 * @version April 16, 2019
 */
public class Grammar {
	
	private HashMap<String,ArrayList<Production>> map;
	private Random rng = new Random();
	
	/**
	 * Constructor for the grammar. Backed by Java's HashMap
	 */
	public Grammar() {
		map = new HashMap<String,ArrayList<Production>>();
	}
	
	/**
	 * Adds a non terminal to the hash map with the key being the 
	 * name of the non terminal, and the value being an array list of
	 * all of the possible productions
	 * 
	 * @param key - name of non terminal
	 * @param productions - list of possible productions
	 */
	public void addNonTerminal(String key, ArrayList<Production> productions) {
		map.put(key, productions);
	}

	/**
	 * Uses a stack to store the phrase generated. If the item popped from the stack
	 * is a non terminal, it will select a random production from that terminal and add
	 * it to the stack. If it is a terminal, it will be added to a string buffer. This repeats 
	 * until the stack is empty and the buffered string is returned.
	 * 
	 * @return the generated phrase
	 */
	public String generatePhrase() {
		StringBuffer buffer = new StringBuffer();
		Stack<String> stack = new Stack<String>();
		stack.push("<start>");
		while (!stack.isEmpty()) {
			String token = stack.pop();
			
			// If a non terminal is popped from the stack, it will add a random
			// production to the stack in reverse order
			if (token.startsWith("<")) {
				ArrayList<String> tokenList = getRandomProduction(token);
				for (int i = tokenList.size() - 1; i >= 0; i--) {
					stack.push(tokenList.get(i));
				}
			}
			else {
				buffer.append(token);
			}
		}
		return buffer.toString();
	}

	/**
	 * Selects a random production from a non terminal and returns
	 * it as an arraylist of strings.
	 * 
	 * @param token - non terminal
	 * @return ArrayList of strings of one of the terminal's possible productions
	 */
	private ArrayList<String> getRandomProduction(String token) {
		ArrayList<Production> prodList = map.get(token);
		if (prodList == null) {
			return new ArrayList<String>();
		}
		int index = rng.nextInt(prodList.size());
 		return prodList.get(index).getTokens();		
	}

}
