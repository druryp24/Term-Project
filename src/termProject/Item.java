package termProject;

/**
 * Item Class of the Perils Along the Platte Game
 * Base class for all in-game items including food, weapons, tools, and supplies.
 * Manages common item properties such as weight, quantity, and special attributes.
 *
 * @author : Painter Drury, and Domenic Pilla
 * @version : 1.0
 * @ date : 03/25/2025
 * @ file : item.java
 */
public class Item {
    private String name;
    private int weight;
    private int quantity;
    private int value;
    private int maximumQuantity;
    private boolean isPerishable;
    private boolean isDrinkable;
    private boolean isConsumable;
    private boolean isUsable;
    private boolean isWeapon;
    private boolean isWagonPart;

    /**
     * Full constructor for creating an item with all properties specified.
     * 
     * @param name The name of the item
     * @param weight The weight of the item in pounds
     * @param quantity The initial quantity of the item
     * @param value The monetary value of the item
     * @param maximumQuantity The maximum stackable quantity
     */
    public Item(String name, int weight, int quantity, int value, int maximumQuantity) {
        this.name = name;
        this.weight = weight;
        this.quantity = quantity;
        this.value = value;
        this.maximumQuantity = maximumQuantity;
    }

    /**
     * Constructor for creating an item without specifying value.
     * 
     * @param name The name of the item
     * @param weight The weight of the item in pounds
     * @param quantity The initial quantity of the item
     * @param maximumQuantity The maximum stackable quantity
     */
    public Item(String name, int weight, int quantity, int maximumQuantity) {
        this(name, weight, quantity, 0, maximumQuantity);
    }

    /**
     * Constructor for creating a single item with weight.
     * 
     * @param name The name of the item
     * @param weight The weight of the item in pounds
     */
    public Item(String name, int weight) {
        this(name, weight, 1, 0);
    }

    /**
     * Basic constructor for creating an item with only a name.
     * 
     * @param name The name of the item
     */
    public Item(String name) {
        this.name = name;
    }

    /**
     * Constructor for creating an item with quantity but no value.
     * 
     * @param name The name of the item
     * @param weight The weight of the item in pounds
     * @param quantity The initial quantity of the item
     */
    public Item(String name, int weight, int quantity) {
        this(name, weight, quantity, 0);
    }

    /**
     * Sets the special attributes of an item.
     * 
     * @param isDrinkable Whether the item can be drunk
     * @param isConsumable Whether the item can be eaten
     * @param isUsable Whether the item can be used as a tool
     * @param isPerishable Whether the item can expire
     */
    public void setItemBoolean(boolean isDrinkable, boolean isConsumable, boolean isUsable, boolean isPerishable) {
        this.isDrinkable = isDrinkable;
        this.isConsumable = isConsumable;
        this.isUsable = isUsable;
        this.isPerishable = isPerishable;
    }

    /**
     * Checks if the item is perishable.
     * 
     * @return true if the item can expire, false otherwise
     */
    public boolean isPerishable() {
        return isPerishable;
    }

    /**
     * Checks if the item is drinkable.
     * 
     * @return true if the item can be drunk, false otherwise
     */
    public boolean isDrinkable(){
        return isDrinkable;
    }

    /**
     * Checks if the item is consumable (can be eaten).
     * 
     * @return true if the item can be eaten, false otherwise
     */
    public boolean isConsumable(){
        return isConsumable;
    }

    /**
     * Checks if the item is a weapon.
     * 
     * @return true if the item is a weapon, false otherwise
     */
    public boolean isWeapon(){
        return isWeapon;
    }

    /**
     * Checks if the item is a wagon part.
     * 
     * @return true if the item is a wagon part, false otherwise
     */
    public boolean isWagonPart(){
        return isWagonPart;
    }

    /**
     * Checks if a perishable item has expired.
     * Uses random chance to determine expiration.
     * 
     * @return true if the item has expired, false otherwise
     */
    public boolean isExpired() {
        return Math.random() * 1 + 10 < 5;
    }

    /**
     * Checks if the item is usable as a tool.
     * 
     * @return true if the item can be used, false otherwise
     */
    public boolean isUsable(){
        return isUsable;
    }

    /**
     * Gets the name of the item.
     * 
     * @return The item name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the weight of a single unit of the item.
     * 
     * @return The item weight in pounds
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Gets the current quantity of the item.
     * 
     * @return The item quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Gets the maximum stackable quantity for this item.
     * 
     * @return The maximum quantity
     */
    public int getMaximumQuantity() {
        return maximumQuantity;
    }

    /**
     * Sets the quantity of the item.
     * 
     * @param quantity The new quantity value
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Sets the maximum stackable quantity for this item.
     * 
     * @param maximumQuantity The new maximum quantity value
     */
    public void setMaximumQuantity(int maximumQuantity) {
        this.maximumQuantity = maximumQuantity;
    }

    /**
     * Increases the quantity of the item by the specified amount.
     * 
     * @param amount The amount to increase by
     */
    public void increaseQuantity(int amount) {
        this.quantity += amount;
    }

    /**
     * Decreases the quantity of the item by the specified amount.
     * Ensures quantity doesn't go below zero.
     * 
     * @param amount The amount to decrease by
     */
    public void decreaseQuantity(int amount) {
        this.quantity -= amount;
        if (this.quantity < 0) {
            this.quantity = 0;
        }
    }

    /**
     * Gets the monetary value of a single unit of the item.
     * 
     * @return The item value
     */
    public int getValue() {
        return value;
    }

    /**
     * Calculates the total weight of all units of this item.
     * 
     * @return The total weight (individual weight × quantity)
     */
    public int getTotalWeight() {
        return weight * quantity;
    }

    /**
     * Calculates the total value of all units of this item.
     * 
     * @return The total value (individual value × quantity)
     */
    public int getTotalValue() {
        return value * quantity;
    }

    /**
     * Simulates equipping an item for use.
     * 
     * @param item The item to equip
     */
    public void equipItem(Item item) {
        System.out.println("You have equipped " + item.getName());
    }

    /**
     * Returns a string representation of the item.
     * Includes name, quantity, and weight information.
     * 
     * @return A string describing the item
     */
    public String toString() {
        return name + " (Qty: " + quantity + ", Weight: " + weight + " lbs each)";
    }

	public int getPrice() {
		return value;
	}
}