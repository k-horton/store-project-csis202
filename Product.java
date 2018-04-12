// khorton17@georgefox.edu

public class Product
{
    private String _productID;
    private String _productDesc;
    private double _productPrice;
    
    /**
     * Creates a new product object.
     * @param id product id string
     * @param desc product description
     * @param price product price
     */
    public Product(String id, String desc, double price)
    {
        _productID = id;
        _productDesc = desc;
        _productPrice = price;
    }
    
    /**
     * Returns the product's ID
     * @return product id
     */
    public String getProductID()
    {
        return _productID;
    }
    
    /**
     * Returns the product's description.
     * @return product description
     */
    public String getProductDesc()
    {
        return _productDesc;
    }
    
    /**
     * Returns the product's price.
     * @return product price
     */
    public double getProductPrice()
    {
        return _productPrice;
    }
    
    /**
     * Returns a string containing the product's ID, description, and price.
     * @return product ID, description, and price
     */
    public String toString()
    {
        return "ID: " + _productID + "; Description: " + _productDesc 
                + "; Price: $" + String.format("%.2f", _productPrice);
    }
}
