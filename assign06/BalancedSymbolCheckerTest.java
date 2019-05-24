package assign06;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import assign06.BalancedSymbolChecker;

/**
 * This is a tester class to test the checkfile method in the 
 * BalancedSymbolChecker class
 * 
 * @author Jason Crandall && Michael Hart
 *
 */
class BalancedSymbolCheckerTest {

	@Test
	void class1Test() throws FileNotFoundException { 
		assertEquals("ERROR: Unmatched symbol at line 6 and column 1. Expected ), but read } instead.",
				BalancedSymbolChecker.checkFile("Class1.java"));
	}
	
	@Test
	void class2Test() throws FileNotFoundException {
		assertEquals("ERROR: Unmatched symbol at line 7 and column 1. Expected  , but read } instead.",
				BalancedSymbolChecker.checkFile("Class2.java"));
	}
	
	@Test
	void class3Test() throws FileNotFoundException {
		assertEquals("No errors found. All symbols match.",
				BalancedSymbolChecker.checkFile("Class3.java"));
	}
	
	@Test
	void class4Test() throws FileNotFoundException {
		assertEquals("ERROR: File ended before closing comment.",
				BalancedSymbolChecker.checkFile("Class4.java"));
	}
	
	@Test
	void class5Test() throws FileNotFoundException {
		assertEquals("ERROR: Unmatched symbol at line 3 and column 18. Expected ], but read } instead.",
				BalancedSymbolChecker.checkFile("Class5.java"));
	}
	
	@Test
	void class6Test() throws FileNotFoundException {
		assertEquals("No errors found. All symbols match.",
				BalancedSymbolChecker.checkFile("Class6.java"));
	}
	
	@Test
	void class7Test() throws FileNotFoundException {
		assertEquals("ERROR: Unmatched symbol at line 3 and column 33. Expected ], but read ) instead.",
				BalancedSymbolChecker.checkFile("Class7.java"));
	}
	
	@Test
	void class8Test() throws FileNotFoundException {
		assertEquals("ERROR: Unmatched symbol at line 5 and column 30. Expected }, but read ) instead.",
				BalancedSymbolChecker.checkFile("Class8.java"));
	}
	
	@Test
	void class9Test() throws FileNotFoundException {
		assertEquals("ERROR: Unmatched symbol at line 3 and column 33. Expected ), but read ] instead.",
				BalancedSymbolChecker.checkFile("Class9.java"));
	}
	
	@Test
	void class10Test() throws FileNotFoundException {
		assertEquals("ERROR: Unmatched symbol at line 5 and column 10. Expected }, but read ] instead.",
				BalancedSymbolChecker.checkFile("Class10.java"));
	}
	
	@Test
	void class11Test() throws FileNotFoundException {
		assertEquals("ERROR: Unmatched symbol at the end of file. Expected }.",
				BalancedSymbolChecker.checkFile("Class11.java"));
	}
	
	@Test
	void class12Test() throws FileNotFoundException {
		assertEquals("No errors found. All symbols match.",
				BalancedSymbolChecker.checkFile("Class12.java"));
	}
	
	@Test
	void class13Test() throws FileNotFoundException {
		assertEquals("No errors found. All symbols match.",
				BalancedSymbolChecker.checkFile("Class12.java"));
	}
	
	@Test
	void class14Test() throws FileNotFoundException {
		assertEquals("No errors found. All symbols match.",
				BalancedSymbolChecker.checkFile("Class12.java"));
	}
	
	
}
