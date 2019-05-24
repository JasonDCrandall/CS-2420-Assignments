package assign06;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Class containing the checkFile method for checking if the (, [, and { symbols
 * in an input file are correctly matched.
 * 
 * @author Erin Parker && Jason Crandall && Michael Hart
 * @version February 28, 2019
 */
public class BalancedSymbolChecker {

	/**
	 * Generates a message indicating whether the input file has unmatched symbols.
	 * (Use the helper methods below for constructing messages.)
	 * 
	 * @param filename - name of the input file to check
	 * @return a message indicating whether the input file has unmatched symbols
	 * @throws FileNotFoundException if the file does not exist
	 */
	public static String checkFile(String filename) throws FileNotFoundException {
		
		ArrayStack<Character> stack = new ArrayStack<Character>();

		// Variables to keep track of the row numbers and the block comments and string literal states.
		int rowNum = 0;
		boolean blockCom = false;
		boolean strLiteral = false;
		
		
			// Buffer Reader to read in the file
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String s;
			
			try {
				while ((s = reader.readLine()) != null) {
					rowNum++;
					int colNum = s.length();
					for (int i = 0; i < colNum; i++) {
						char current = s.charAt(i);

						// checks for two character pairings for comments
						if (i + 1 < colNum) {
							if (!blockCom && !strLiteral) {
								if (current == '/' && s.charAt(i + 1) == '*')
									blockCom = true;
								if (current == '/' && s.charAt(i + 1) == '/')
									break;
							}
							if (blockCom)
								if (current == '*' && s.charAt(i + 1) == '/')
									blockCom = false;
						}

						if (!(blockCom)) {
							// Check for character Literals, increases i to continue
							if (!(strLiteral)) {
								if (current == '\'') {
									if (s.charAt(i + 1) == '\\')
										i++;
									i += 2;
								}
							}
							if (current == '\"') {
								if (strLiteral && s.charAt(i - 1) != '\\')
									strLiteral = false;
								else
									strLiteral = true;
							}
						}
						if (!(blockCom) && !(strLiteral)) {
							// Checks the file for matching Brackets, Parenthesis, and Curly Braces
							if (current == '(' || current == '{' || current == '[')
								stack.push(current);
							if (current == ')' || current == '}' || current == ']') {
								if (stack.isEmpty()) {
									return unmatchedSymbol(rowNum, i + 1, current, ' ');
								}
								
								else if (getExpectedSymbol(stack.peek()) != current){
									return unmatchedSymbol(rowNum, i + 1, current, getExpectedSymbol(stack.pop()));
								}
								
								stack.pop();
								
							}
						}
					}
				}
				
			// Checks if there was no file inputed or found
			} catch (NoSuchElementException e) {
				throw new FileNotFoundException("There was no file found with that name");
			} catch (IOException e) {
				throw new FileNotFoundException("No file inputed");
			}
			

		// Returns unfinished comment if the block comment never ended
		if (blockCom) {
			return unfinishedComment();
		}
		// If there is still an unpaired symbol in the stack, it will return the symbol
		if (!stack.isEmpty()) {
			return unmatchedSymbolAtEOF(getExpectedSymbol(stack.pop()));
		}
		// Returns if all symbols are paired
		return allSymbolsMatch();
	}
	
	/**
	 * Method to return the corresponding closing symbol to the character entered
	 * 
	 * @param c -- the entered symbol
	 * @return the corresponding symbol
	 */
	private static char getExpectedSymbol(char c) {
		if (c == '(')
			return ')';
		if (c == '{')
			return '}';
		if (c == '[')
			return ']';
		return ' ';
	}

	/**
	 * Use this error message in the case of an unmatched symbol.
	 * 
	 * @param lineNumber     - the line number of the input file where the matching
	 *                       symbol was expected
	 * @param colNumber      - the column number of the input file where the
	 *                       matching symbol was expected
	 * @param symbolRead     - the symbol read that did not match
	 * @param symbolExpected - the matching symbol expected
	 * @return the error message
	 */
	private static String unmatchedSymbol(int lineNumber, int colNumber, char symbolRead, char symbolExpected) {
		return "ERROR: Unmatched symbol at line " + lineNumber + " and column " + colNumber + ". Expected "
				+ symbolExpected + ", but read " + symbolRead + " instead.";
	}

	/**
	 * Use this error message in the case of an unmatched symbol at the end of the
	 * file.
	 * 
	 * @param symbolExpected - the matching symbol expected
	 * @return the error message
	 */
	private static String unmatchedSymbolAtEOF(char symbolExpected) {
		return "ERROR: Unmatched symbol at the end of file. Expected " + symbolExpected + ".";
	}

	/**
	 * Use this error message in the case of an unfinished comment (i.e., a file
	 * that ends with an open /* comment).
	 * 
	 * @return the error message
	 */
	private static String unfinishedComment() {
		return "ERROR: File ended before closing comment.";
	}

	/**
	 * Use this message when no unmatched symbol errors are found in the entire
	 * file.
	 * 
	 * @return the success message
	 */
	private static String allSymbolsMatch() {
		return "No errors found. All symbols match.";
	}
}