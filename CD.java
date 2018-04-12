// khorton17@georgefox.edu

public class CD extends Product
{
    private String _cdTitle;
    
    /**
     * Creates a new CD object.
     * @param id identification string
     * @param desc CD description
     * @param price CD price
     * @param title CD title
     */
    public CD(String id, String desc, double price, String title)
    {
        super(id, desc, price);
        _cdTitle = title;
    }
    
    /**
     * Returns the CD's title.
     * @return CD title
     */
    public String getCDTitle()
    {
        return _cdTitle;
    }
    
    /**
     * Returns a string containing the CD's ID, description, price, and title.
     * @return CD id, description, price, and title
     */
    public String toString()
    {
        return super.toString() + "; " + "Title: " + _cdTitle;
    }
}
