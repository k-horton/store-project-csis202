// khorton17@georgefox.edu

/**
 * Class InventoryLineItem tracks a product, its quantity and price, the
 * discount rate it has, and the subtotal of all the inventory.
 */
public class InventoryLineItem 
{
    private int _lineItemNum;
    private Product _product;
    private int _lineItemQty;
    private double _lineItemDiscountRate;
    private double _lineItemSubtotal;
    
    // count the total number of items
    public static int _totalLineItems = 0;
    
    /**
     * Creates a new inventory line item.
     * @param product the line item product
     * @param lineItemQty the quantity of the product
     */
    public InventoryLineItem(Product product, int lineItemQty)
    {
        // increment number of line items up one
        _totalLineItems++;
        // set item's number
        _lineItemNum = _totalLineItems;
        
        _product = product;
        _lineItemQty = lineItemQty;
        
        // set the discount rate and subtotal
         setLineItemDiscountRate();
         setLineItemSubtotal();
         
    }
    
    /**
     * Sets the discount rate depending on the quantity of the product.
     */
    public void setLineItemDiscountRate()
    {
        if (_lineItemQty >= 10)
        {
            _lineItemDiscountRate = 0.1;
        }
        else if (_lineItemQty >= 25)
        {
            _lineItemDiscountRate = 0.2;
        }
        else if (_lineItemQty >= 50)
        {
            _lineItemDiscountRate = 0.25;
        }
        else
        {
            _lineItemDiscountRate = 0.0;
        }
    }
    
    /**
     * Calculates the current value of all of this inventory line item based 
     * upon current quantity, price, and discount rate.
     */
    public void setLineItemSubtotal()
    {
        _lineItemSubtotal = _lineItemQty * _product.getProductPrice() 
                * (1 - _lineItemDiscountRate);
    }
    
    public void adjustLineItemQty(int adjustment)
    {
        _lineItemQty += adjustment;
    }
    
    /**
     * Returns the line item number.
     * @return line item number
     */
    public int getLineItemNum()
    {
        return _lineItemNum;
    }
    
    /**
     * Returns the product.
     * @return product
     */
    public Product getProduct()
    {
        return _product;
    }
    
    /**
     * Returns the line item quantity.
     * @return line item quantity
     */
    public int getLineItemQty()
    {
        return _lineItemQty;
    }
    
    /**
     * Returns the line item discount rate.
     * @return discount rate
     */
    public double getLineItemDiscountRate()
    {
        return _lineItemDiscountRate;
    }
    
    /**
     * Returns the line item subtotal.
     * @return subtotal
     */
    public double getLineItemSubtotal()
    {
        return _lineItemSubtotal;
    }
    
    /**
     * Returns a string containing the line item number, product, quantity,
     * discount rate, and subtotal.
     * @return string with all the stored information in the line item
     */
    public String toString()
    {
        return "Line Item Number: " + _lineItemNum + "; Product: ((" 
                + getProduct() + ")); \nQuantity: " + _lineItemQty 
                + "; Discount Rate: " + (100 * _lineItemDiscountRate) 
                + "%; Subtotal: $" + String.format("%.2f", _lineItemSubtotal);
    }
}
