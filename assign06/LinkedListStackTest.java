package assign06;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This is a tester class to test the methods in the LinkedListStack class
 * 
 * @author Jason Crandall && Michael Hart
 * @version February 28, 2019
 */
class LinkedListStackTest {
	
	private LinkedListStack stack, smallStack, stringStack;
	
	@BeforeEach
	void setup() {
		stack = new LinkedListStack<Integer>();
		smallStack = new LinkedListStack<Integer>();
		smallStack.push(2);		
		smallStack.push(5);
		smallStack.push(6);
		smallStack.push(1);
		smallStack.push(35);
		stringStack = new LinkedListStack<String>();
		stringStack.push("Hello");
		stringStack.push("Steve");
		stringStack.push("iPhone");
		stringStack.push("Shoe");
		stringStack.push("Chair");
		stringStack.push("Bike");

	}

	@Test
	void pushAndPeekSizeOneTest() {
		stack.push(1);
		assertEquals(1, stack.peek());
	}
	
	@Test
	void peekStringStackTest() {
		assertEquals("Bike", stringStack.peek());
	}
	
	@Test
	void pushAndPopTest() {
		stack.push(1);
		assertEquals(1, stack.pop());
	}
	
	@Test
	void popStringStackTest() {
		assertEquals("Bike", stringStack.pop());
		assertEquals("Chair", stringStack.peek());
	}
	
	@Test
	void doublePopStringStackTest() {
		stringStack.pop();
		stringStack.pop();
		assertEquals(4, stringStack.size());
		assertEquals("Shoe", stringStack.peek());
	}
	
	@Test
	void stackSizeTest() {
		stack.push(1);
		stack.push(3);
		stack.push(5);
		assertEquals(3, stack.size());
	}
	
	@Test
	void stringStackSizeTest() {
		assertEquals(6, stringStack.size());
	}
	
	@Test
	void peekSmallSizeTest() {
		assertEquals(35, smallStack.peek());
	}
	
	@Test
	void popAndPeekSmallSizeTest() {
		assertEquals(35, smallStack.pop());
		assertEquals(1, smallStack.peek());
	}
	
	@Test
	void stackIsEmptyTest() {
		stack.push(1);
		stack.pop();
		assertTrue(stack.isEmpty());
	}
	
	@Test
	void stringStackNotEmptyTest() {
		assertFalse(stringStack.isEmpty());
	}
	
	@Test
	void stringStackClearTest() {
		stringStack.clear();
		assertTrue(stringStack.isEmpty());
	}
	
	@Test
	void smallStackClearTest() {
		smallStack.clear();
		assertTrue(smallStack.isEmpty());
	}
	
	

}
