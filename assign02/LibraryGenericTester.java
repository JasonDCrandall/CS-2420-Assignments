package assign02;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * This class contains tests for LibraryGeneric.
 * 
 * @author Erin Parker and Jason Crandall and Michael Hart
 * @version January 16, 2019
 */
public class LibraryGenericTester {
	
	private LibraryGeneric<String> emptyLib;
	private LibraryGeneric<String> nameLib;  // library that uses names to identify patrons (holders)
	private LibraryGeneric<PhoneNumber> phoneLib;  // library that uses phone numbers to identify patrons
	
	@BeforeEach
	void setUp() throws Exception {
		emptyLib = new LibraryGeneric<String>();
		
		nameLib = new LibraryGeneric<String>();
		nameLib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		nameLib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		nameLib.add(9780446580342L, "David Baldacci", "Simple Genius");

		phoneLib = new LibraryGeneric<PhoneNumber>();
		phoneLib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		phoneLib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		phoneLib.add(9780446580342L, "David Baldacci", "Simple Genius");		
	}
	
	@Test
	public void testNameLibCheckout() {
		String patron = "Jane Doe";
		assertTrue(nameLib.checkout(9780330351690L, patron, 1, 1, 2008));
		assertTrue(nameLib.checkout(9780374292799L, patron, 1, 1, 2008));
	}

	@Test
	public void testNameLibLookup() {
		String patron = "Jane Doe";
		nameLib.checkout(9780330351690L, patron, 1, 1, 2008);
		nameLib.checkout(9780374292799L, patron, 1, 1, 2008);
		ArrayList<LibraryBookGeneric<String>> booksCheckedOut = nameLib.lookup(patron);
		
		assertNotNull(booksCheckedOut);
		assertEquals(2, booksCheckedOut.size());
		assertTrue(booksCheckedOut.contains(new Book(9780330351690L, "Jon Krakauer", "Into the Wild")));
		assertTrue(booksCheckedOut.contains(new Book(9780374292799L, "Thomas L. Friedman", "The World is Flat")));
		assertEquals(patron, booksCheckedOut.get(0).getHolder());
		assertEquals(patron, booksCheckedOut.get(1).getHolder());
	}
	
	@Test
	public void testNameLibCheckin() {
		String patron = "Jane Doe";
		nameLib.checkout(9780330351690L, patron, 1, 1, 2008);
		nameLib.checkout(9780374292799L, patron, 1, 1, 2008);
		assertTrue(nameLib.checkin(patron));
	}

	@Test
	public void testPhoneLibCheckout() {
		PhoneNumber patron = new PhoneNumber("801.555.1234");
		assertTrue(phoneLib.checkout(9780330351690L, patron, 1, 1, 2008));
		assertTrue(phoneLib.checkout(9780374292799L, patron, 1, 1, 2008));
	}

	@Test
	public void testPhoneLibLookup() {
		PhoneNumber patron = new PhoneNumber("801.555.1234");
		phoneLib.checkout(9780330351690L, patron, 1, 1, 2008);
		phoneLib.checkout(9780374292799L, patron, 1, 1, 2008);
		ArrayList<LibraryBookGeneric<PhoneNumber>> booksCheckedOut = phoneLib.lookup(patron);
		
		assertNotNull(booksCheckedOut);
		assertEquals(2, booksCheckedOut.size());
		assertTrue(booksCheckedOut.contains(new Book(9780330351690L, "Jon Krakauer", "Into the Wild")));
		assertTrue(booksCheckedOut.contains(new Book(9780374292799L, "Thomas L. Friedman", "The World is Flat")));
		assertEquals(patron, booksCheckedOut.get(0).getHolder());
		assertEquals(patron, booksCheckedOut.get(1).getHolder());
	}

	@Test
	public void testPhoneLibCheckin() {
		PhoneNumber patron = new PhoneNumber("801.555.1234");
		phoneLib.checkout(9780330351690L, patron, 1, 1, 2008);
		phoneLib.checkout(9780374292799L, patron, 1, 1, 2008);
		assertTrue(phoneLib.checkin(patron));
	}
	
	@Test
	public void testNameLibOrderedByTitle() {
		assertNotNull(nameLib.getOrderedByTitle());
	}
	
	@Test
	public void testPhoneLibOrderedByTitle() {
		assertNotNull(phoneLib.getOrderedByTitle());
	}
	
	@Test
	public void testPhoneLibOrderedByTitleSort() {
		assertEquals("[9780330351690, Jon Krakauer, \"Into the Wild\", 9780446580342, David Baldacci, \"Simple Genius\", 9780374292799, Thomas L. Friedman, \"The World is Flat\"]", phoneLib.getOrderedByTitle().toString());
	}
	
	@Test
	public void testNameLibOrderedByTitleSort() {
		assertEquals("[9780330351690, Jon Krakauer, \"Into the Wild\", 9780446580342, David Baldacci, \"Simple Genius\", 9780374292799, Thomas L. Friedman, \"The World is Flat\"]", nameLib.getOrderedByTitle().toString());
	}
	
	@Test
	public void testNameGetOverdueListEmpty() {
		assertEquals(nameLib.getOverdueList(04, 04, 1999).toString(), "[]");
	}
	
	@Test
	public void testNameGetOverdueList() {
		nameLib.checkout(9780374292799L, "Jane Doe", 1, 1, 2019);
		assertEquals("[9780374292799, Thomas L. Friedman, \"The World is Flat\"]", nameLib.getOverdueList(1, 2, 2019).toString());
	}
	
	@Test
	public void testPhoneGetOverdueList() {
		PhoneNumber patron = new PhoneNumber("801.555.1234");
		phoneLib.checkout(9780374292799L, patron, 1, 1, 2019);
		assertEquals("[9780374292799, Thomas L. Friedman, \"The World is Flat\"]", phoneLib.getOverdueList(1, 2, 2019).toString());
	}
	
	@Test
	public void testPhoneGetOverdueListEmpty() {
		assertEquals(phoneLib.getOverdueList(04, 04, 1999).toString(), "[]");
	}
}
