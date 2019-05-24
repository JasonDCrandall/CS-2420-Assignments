package assign02;

import java.util.GregorianCalendar;

/**
 * This class represents a library book object which contains the holder, due date,
 * and the availability of the book. Inherits the Book class.
 * 
 * @author Jason Crandall and Michael Hart
 * @version January 22, 2019
 *
 */
public class LibraryBook extends Book {
	
	private String holder;
	
	private GregorianCalendar dueDate;
	
	
	/**
	 * Creates a Library book from the given ISBN, author, and title.
	 * 
	 * @param isbn
	 * @param author
	 * @param title
	 */
	public LibraryBook(long isbn, String author, String title) {
		super(isbn, author, title);
		this.holder = null;
		this.dueDate = null; 
	}
	
	
	/**
	 * Accessor method to get the holder of library book.
	 * 
	 * @return the holder
	 */
	public String getHolder() {
		return this.holder;
	}
	
	/**
	 * Accessor method to get the due date of library book.
	 * 
	 * @return the due date
	 */
	public GregorianCalendar getDueDate() {
		return this.dueDate;
	}
	
	/**
	 * Setter method to set the holder of a library book.
	 * 
	 * @param setHolder - the person checking out the book
	 */
	public void setHolder(String setHolder) {
		this.holder = setHolder;
	}
	
	/**
	 * Setter method to set the due date of a library book.
	 * 
	 * @param year
	 * @param month
	 * @param dayOfMonth
	 */
	public void setDueDate(int year, int month, int dayOfMonth) {
		this.dueDate = new GregorianCalendar(year, month, dayOfMonth);
	}
	
	/**
	 * Resets the due date.
	 */
	public void resetDueDate() {
		this.dueDate = null;
	}
	
	/**
	 * Checks that the book is checked in or out.
	 * 
	 * @return true if the book is avaiable to checkout, false if it is unavailable.
	 */
	public boolean checkAvailability() {
		if(this.holder == null && this.dueDate == null)
			return true;
		else
			return false;
	}
	
	
}
