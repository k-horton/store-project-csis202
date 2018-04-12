// khorton17@georgefox.edu

public class Book extends Product
{
    private String _bookTitle;
    private String _bookAuthor;
    
    /**
     * Creates a new book object.
     * @param id identification string
     * @param desc description of the book
     * @param price the book's price
     * @param title the book's title
     * @param author the book's author
     */
    public Book(String id, String desc, double price, 
            String title, String author)
    {
        super(id, desc, price);
        _bookTitle = title;
        _bookAuthor = author;
    }
    
    /**
     * Returns the title of the book.
     * @return book title
     */
    public String getBookTitle()
    {
        return _bookTitle;
    }
    
    /**
     * Returns the book's author.
     * @return book author
     */
    public String getBookAuthor()
    {
        return _bookAuthor;
    }
    
    /**
     * Returns a string containing the book's ID, description, price, title,
     * and author.
     * @return book's ID, description, price, title, and author
     */
    public String toString()
    {
        return super.toString() + "; " + "Title: " + _bookTitle + "; Author: "
                + _bookAuthor;
    }
}
