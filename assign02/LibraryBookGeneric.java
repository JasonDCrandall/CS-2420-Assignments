package assign02;

import java.util.GregorianCalendar;

/**
 * This class represents a generic library book object which contains the holder, due date,
 * and the availability of the book. Inherits the Book class.
 * 
 * @author Jason Crandall and Michael Hart
 * @version January 22, 2019
 *
 * @param <Type>
 */
public class LibraryBookGeneric<Type> extends Book {
	
	private Type holder;
	
	private GregorianCalendar dueDate;
	
	
	/**
	 * Creates a generic library book with isbn, author, and a title.
	 * 
	 * @param isbn
	 * @param author
	 * @param title
	 */
	public LibraryBookGeneric(long isbn, String author, String title) {
		super(isbn, author, title);
		this.holder = null;
		this.dueDate = null; 
	}
	
	/**
	 * Accessor method to get the holder of the generic library book. 
	 * 
	 * @return holder - owner of the checked out book.
	 */
	public Type getHolder() {
		return this.holder;
	}
	
	/**
	 * Accessor method to get the due date of the generic library book.
	 * 
	 * @return dueDate - the due date of the checked out book.
	 */
	public GregorianCalendar getDueDate() {
		return this.dueDate;
	}
	
	/**
	 * Setter method to set the holder of the checked out library book
	 * 
	 * @param setHolder - the holder checking the book out
	 */
	public void setHolder(Type setHolder) {
		this.holder = setHolder;
	}
	
	/**
	 * Setter method setting the due date of the checked out book.
	 * 
	 * @param year
	 * @param month
	 * @param dayOfMonth
	 */
	public void setDueDate(int year, int month, int dayOfMonth) {
		this.dueDate = new GregorianCalendar(year, month, dayOfMonth);
	}
	
	/**
	 * Resets the duedate of the generic library book when checked in.
	 */
	public void resetDueDate() {
		this.dueDate = null;
	}
	
	/**
	 * Checks that the generic library book is checked in or out.
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
