package assign02;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * This class contains tests for Library.
 * 
 * @author Erin Parker and Jason Crandall and Michael Hart
 * @version January 16, 2019
 */
public class LibraryTester {

	private Library emptyLib, smallLib, mediumLib, largeLib;
	
	@BeforeEach
	void setUp() throws Exception {
		emptyLib = new Library();
		
		smallLib = new Library();
		smallLib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		smallLib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		smallLib.add(9780446580342L, "David Baldacci", "Simple Genius");

		mediumLib = new Library();
		mediumLib.addAll("src/assign02/Mushroom_Publishing.txt");
		
		// Large library consisting of 70 books with unique ISBN #'s
		largeLib = new Library();
		largeLib.addAll("src/assign02/Large_lib");
		
	}

	@Test
	public void testEmptyLookupISBN() {
		assertNull(emptyLib.lookup(978037429279L));
	}
	
	@Test
	public void testEmptyLookupHolder() {
		ArrayList<LibraryBook> booksCheckedOut = emptyLib.lookup("Jane Doe");
		assertNotNull(booksCheckedOut);
		assertEquals(0, booksCheckedOut.size());
	}
	
	@Test
	public void testEmptyCheckout() {
		assertFalse(emptyLib.checkout(978037429279L, "Jane Doe", 1, 1, 2008));
	}

	@Test
	public void testEmptyCheckinISBN() {
		assertFalse(emptyLib.checkin(978037429279L));
	}
	
	@Test
	public void testEmptyCheckinHolder() {
		assertFalse(emptyLib.checkin("Jane Doe"));
	}

	@Test
	public void testSmallLibraryLookupISBN() {
		assertNull(smallLib.lookup(9780330351690L));
	}
	
	@Test 
	public void testLargeLibraryLookupISBN() {
		assertNull(largeLib.lookup(9781843191230L));
	}
	
	@Test
	public void testSmallLibraryLookupHolder() {
		smallLib.checkout(9780330351690L, "Jane Doe", 1, 1, 2008);
		ArrayList<LibraryBook> booksCheckedOut = smallLib.lookup("Jane Doe");
		
		assertNotNull(booksCheckedOut);
		assertEquals(1, booksCheckedOut.size());
		assertEquals(new Book(9780330351690L, "Jon Krakauer", "Into the Wild"), booksCheckedOut.get(0));
		assertEquals("Jane Doe", booksCheckedOut.get(0).getHolder());
	}
	
	@Test
	public void testLargeLibraryLookupHolder() {
		largeLib.checkout(9781843191230L, "Jane Doe", 1, 1, 2008);
		ArrayList<LibraryBook> booksCheckedOut = largeLib.lookup("Jane Doe");
		
		assertNotNull(booksCheckedOut);
		assertEquals(1, booksCheckedOut.size());
		assertEquals(new Book(9781843191230L, "Mary Lancaster", "An Endless Exile"), booksCheckedOut.get(0));
		assertEquals("Jane Doe", booksCheckedOut.get(0).getHolder());
	}

	@Test
	public void testSmallLibraryCheckout() {
		assertTrue(smallLib.checkout(9780330351690L, "Jane Doe", 1, 1, 2008));
	}
	
	@Test
	public void testLargeLibraryCheckout() {
		assertTrue(largeLib.checkout(9781843191230L, "Jane Doe", 1, 1, 2008));
	}

	@Test
	public void testSmallLibraryCheckinISBN() {
		smallLib.checkout(9780330351690L, "Jane Doe", 1, 1, 2008);
		assertTrue(smallLib.checkin(9780330351690L));
	}
	
	@Test
	public void testLargeLibraryCheckinISBN() {
		largeLib.checkout(9781843191230L, "Jane Doe", 1, 1, 2008);
		assertTrue(largeLib.checkin(9781843191230L));
	}

	@Test
	public void testSmallLibraryCheckinHolder() {
		assertFalse(smallLib.checkin("Jane Doe"));
	}
	
	@Test
	public void testLargeLibraryCheckinHolder() {
		assertFalse(largeLib.checkin("Jane Doe"));
	}
	
	// Medium libraries
	
	@Test
	public void testMediumLibraryLookupISBN() {
		assertNull(mediumLib.lookup(9781843190349L));
	}
	
	
	@Test
	public void testMediumLibraryLookupHolder() {
		mediumLib.checkout(9781843190004L, "Jane Doe", 1, 1, 2008);
		ArrayList<LibraryBook> booksCheckedOut = mediumLib.lookup("Jane Doe");
		
		assertNotNull(booksCheckedOut);
		assertEquals(1, booksCheckedOut.size());
		assertEquals(new Book(9781843190004L, "Moyra Caldecott", "Weapons of the Wolfhound"), booksCheckedOut.get(0));
		assertEquals("Jane Doe", booksCheckedOut.get(0).getHolder());
	}

	@Test
	public void testMediumLibraryCheckout() {
		assertTrue(mediumLib.checkout(9781843190004L, "Jane Doe", 1, 1, 2008));
	}

	@Test
	public void testMediumLibraryCheckinISBN() {
		mediumLib.checkout(9781843190110L, "Jane Doe", 1, 1, 2008);
		assertTrue(mediumLib.checkin(9781843190110L));
	}

	@Test
	public void testMediumLibraryCheckinHolder() {
		assertFalse(mediumLib.checkin("Jane Doe"));
	}
	
	@Test
	public void testGetHoler() {
		LibraryBook book = new LibraryBook(9781843190004L, "Moyra Caldecott", "Weapons of the Wolfhound");
		book.setHolder("Jane Doe");
		assertTrue(book.getHolder().equals("Jane Doe"));
		
	}
	
	@Test 
	public void testGetIsbn() {
		LibraryBook book = new LibraryBook(9781843190004L, "Moyra Caldecott", "Weapons of the Wolfhound");
		assertEquals(book.getIsbn(), 9781843190004L);
	}
	
	@Test 
	public void testGetDueDate () {
		LibraryBook book = new LibraryBook(9781843190004L, "Moyra Caldecott", "Weapons of the Wolfhound");
		book.setDueDate(2012, 1, 15);
		GregorianCalendar DueDate = new GregorianCalendar(2012, 1, 15);
		assertTrue(book.getDueDate().equals(DueDate));
	}
	
	@Test
	public void testResetDueDate () {
		LibraryBook book = new LibraryBook(9781843190004L, "Moyra Caldecott", "Weapons of the Wolfhound");
		book.setDueDate(2012, 1, 15);
		book.resetDueDate();
		assertNull(book.getDueDate());
	}
	
	@Test
	public void testIsAvailable() {
		LibraryBook book = new LibraryBook(9781843190004L, "Moyra Caldecott", "Weapons of the Wolfhound");
		assertTrue(book.checkAvailability());
	}
}