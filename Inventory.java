// khorton17@georgefox.edu

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The Inventory class takes in a list of inventory line items, and it tracks
 * products and their quantities, can restock items, and calculates the value
 * of the entire inventory.
 */
public class Inventory 
{
    private ArrayList<InventoryLineItem> _lineItems;
    
    public Inventory()
    {
        // create a new array list
        _lineItems = new ArrayList<>();
    }
    
    /**
     * Takes in a product ID and returns the inventory line item corresponding
     * to that product.
     * @param productID id of the product
     * @return inventory line item that the product belongs to
     */
    public InventoryLineItem findInventoryItem(String productID)
    {
        InventoryLineItem item = null;
        for(int i = 0; i < _lineItems.size(); i++)
        {
            if(_lineItems.get(i).getProduct().getProductID().equals(productID))
            {
                item = _lineItems.get(i);
            }
        }
        
        return item;
    }
    
    /**
     * Removes a quantity of a product if enough is in stock - if there is 
     * enough in stock, it returns 0; if the product is not in stock, it returns
     * -1; and if there's not enough quantity, it returns the quantity on hand.
     * @param productID id of the product
     * @param qty quantity to be checked for
     * @return 0, -1, or quantity on hand
     */
    public int pick(String productID, int qty)
    {
        // get the item by calling findInventoryItem()
        InventoryLineItem item = findInventoryItem(productID);
        int canPick = 0;
        
        if (item.getLineItemQty() >= qty)
        {
            canPick = 0;
            item.adjustLineItemQty(-qty);
        }
        else if (item.getLineItemQty() == 0)
        {
            canPick = -1;
        }
        else if (item.getLineItemQty() < qty)
        {
            canPick = item.getLineItemQty();
        }
        
        return canPick;
    }
    
    /**
     * Increases the quantity of a product; adds a product to the inventory
     * if it wasn't in stock previously.
     * @param product given product
     * @param qty quantity to be added
     */
    public void restock(Product product, int qty)
    {
        // initialize the item to return
        InventoryLineItem item;
        
        // get product ID and store in a variable
        String productID = product.getProductID();
        
        // if product doesn't exist, add to inventory
        if(findInventoryItem(productID) == null)
        {
            item = new InventoryLineItem(product, qty);
            _lineItems.add(item);
        }
        // if product exists, add to quantity
        else
        {
            item = findInventoryItem(productID);
            item.adjustLineItemQty(qty);
        }
    }
    
    /**
     * Returns the quantity of the provided product.
     * @param productID id of the product
     * @return the quantity in stock
     */
    public int getQtyInStock(String productID)
    {
        InventoryLineItem item = findInventoryItem(productID);
        int qtyInStock = item.getLineItemQty();
        
        return qtyInStock;
    }
    
    /**
     * Returns the value of the total inventory.
     * @return subtotal/value of the total inventory
     */
    public double getInventoryValue()
    {
        int total = 0;
        for(int i = 0; i < _lineItems.size(); i++)
        {
            total += _lineItems.get(i).getLineItemSubtotal();
        }
        
        return total;
    }
    
    /**
     * Returns an array of line items that have a product of the specified
     * product type; if no matching line items are found, an empty array is
     * returned.
     * @param productType type of product
     * @return array of inventory line items that have products with the given
     * product types
     */
    public InventoryLineItem[] getInventoryListing(String productType)
    {
        // create new empty array list
        ArrayList<InventoryLineItem> listing = new ArrayList<>();
        
        // iterate through the ILI
        for (InventoryLineItem item : _lineItems)
        {
            if (item.getProduct().getProductID().charAt(0) 
                    == productType.charAt(0))
            {
                listing.add(item);
            }
            else if (productType == "")
            {
                // is this "good job you did recursive!" or 
                // "wow no that's too janky"?
                getInventoryListing("Book");
                getInventoryListing("CD");
                getInventoryListing("FD");
            }
        }
        // cast the ArrayList back into an array
        return listing.toArray(new InventoryLineItem[listing.size()]);
    }
}
