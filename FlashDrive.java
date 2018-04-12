// khorton17@georgefox.edu

public class FlashDrive extends Product
{
    private int _flashDriveCapacity;
    
    /**
     * Creates a new Flash Drive object.
     * @param id identification string
     * @param desc description of the flash drive
     * @param price flash drive price
     * @param capacity flash drive storage capacity
     */
    public FlashDrive(String id, String desc, double price, int capacity)
    {
        super(id, desc, price);
        _flashDriveCapacity = capacity;
    }
    
    /**
     * Returns the flash drive's capacity.
     * @return flash drive capacity
     */
    public int getFlashDriveCapacity()
    {
        return _flashDriveCapacity;
    }
    
    /**
     * Returns a string containing the flash drive's ID, description, price,
     * and it's storage capacity.
     * @return 
     */
    public String toString()
    {
        return super.toString() + "; " + "Capacity: " + _flashDriveCapacity;
    }
}
