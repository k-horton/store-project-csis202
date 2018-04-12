// khorton17@georgefox.edu

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Class Store creates an inventory from an existing file, and it processes
 * orders and returns.
 */
public class Store 
{
    
    Inventory _inventory;
    
    /**
     * Initializes an empty inventory using an empty array.
     */
    public Store()
    {
        Inventory _inventory = new Inventory();
    }
    
    /**
     * Reads lines from a given file and inputs them into the inventory.
     * @param filename name of the file containing the inventory
     */
    public void loadStore(String filename)
    {
        try
        {
            // create scanner for file
            Scanner in = new Scanner(new File(filename));
            
            // add each line to the inventory
            while (in.hasNextLine())
            {
                String line = in.nextLine();
                processReturn(line);
            }

            // close the scanner
            in.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Invalid file; enter an existing file.");
        }
    }
    
    /**
     * Saves the contents of the inventory to a given file.
     * @param filename name of the file to save the inventory to
     */
    public void saveStore(String filename)
    {
        try
        {
            // create scanner for file
            Scanner in = new Scanner(new File(filename));
            
            // add each line item to the file
            // for loop

            // close the scanner
            in.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Invalid file; enter an existing file.");
        }
    }
    
    /**
     * Takes in an order; if it's possible to fulfill, it takes the items out
     * of inventory and returns a success message; if it's not possible, it
     * returns the reason why it can't be done (either not in stock, or not
     * enough stock along with amount on hand.)
     * @param order product ID and quantity requested, i.e. FD_001,2
     * @return Status of order (either success, out of stock, or not enough
     * stock with amount on hand)
     */
    public String processOrder(String order)
    {
        // initialize variables
        String productID = "";
        int qty = 0;
        InventoryLineItem item = null;
        String returnMessage = "";
        
        // split string into the product ID and the quantity
        String[] orderParts = order.split(",");
        productID = orderParts[0];
        qty = Integer.parseInt(orderParts[1]);
        
        // set item
        item = _inventory.findInventoryItem(productID);
        
        if(item == null || item.getLineItemQty() == 0)
        {
            returnMessage = "Out of stock.";
        }
        else if(item.getLineItemQty() < qty)
        {
            returnMessage = "Not enough in stock; we only have " 
                    + item.getLineItemQty()
                    + " on hand.";
        }
        else
        {
            item.adjustLineItemQty(-qty);
            returnMessage = "Order of " + qty + " " + productID 
                    + " successful.";
        }
        
        return returnMessage;
    }
    
    /**
     * Returns specified quantity of an item OR adds an item not carried to
     * the inventory.
     * @param item full item string, same format as found in inventory file
     */
    public void processReturn(String item)
    {
        // initialize variables
        String productID = "";
        int qty = -1;
        double price = -1.0;
        int fdMemory = -1;
        Product product = null;
        
        // split string
        String[] orderParts = item.split(",");
        
        // set productID, quantity, and price
        productID = orderParts[0];
        qty = Integer.parseInt(orderParts[orderParts.length - 1]);
        price = Double.parseDouble(orderParts[2]);
        
        // if product is not stocked already,
        // create new product and use the restock() method to add to inventory
        if(_inventory.findInventoryItem(productID) == null)
        {
            if(productID.charAt(0) == 'B')
            {
                product = new Book(productID, orderParts[1], price,
                orderParts[3], orderParts[4]);
            }
            else if(productID.charAt(0) == 'C')
            {
                product = new CD(productID, orderParts[1], price,
                orderParts[3]);
            }
            else if(productID.charAt(0) == 'F')
            {
                fdMemory = Integer.parseInt(orderParts[3]);
                product = new FlashDrive(productID, orderParts[1],
                        price, fdMemory);
            }
            
            // use restock method to add product to inventory
            _inventory.restock(product, qty);
        }
        else
        {
            // adjust quantity of ILI
            _inventory.findInventoryItem(productID).adjustLineItemQty(qty);
        }
    }
    
    /**
     * Returns an array containing all instances of a specified product type
     * within the inventory.
     * @param productType Book, CD, or FlashDrive
     * @return array with all instances of the specified product type, or an
     * empty array if no instances are found
     */
    public Product[] getProductList(String productType)
    {
        // create new empty array list
        ArrayList<Product> listing = new ArrayList<>();
        // convert inventory into an array with only productType ILIs
        InventoryLineItem[] inventory = 
                _inventory.getInventoryListing(productType);
        
        // iterate through the ILI
        for (InventoryLineItem item : inventory)
        {
            listing.add(item.getProduct());
        }
        // cast the ArrayList back into an array
        return listing.toArray(new Product[inventory.length]);
    }
}
