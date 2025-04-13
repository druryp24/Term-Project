package termProject;

/**
 * Food Class of the Perils Along the Platte Game
 * Represents food items that can be consumed by players and animals.
 * Extends the item class with food-specific attributes such as nutrition and perishability.
 *
 * @author : Alex Randall, Chase McCluskey, Painter Drury, and Domenic Pilla
 * @version : 1.0
 * @date : 03/25/2025
 * @file : food.java
 */
public class Food extends Item {
    private int nutrition;
    private boolean perishable;
    private boolean marketFood;
    private int price;

    /**
     * Constructor for creating a non-perishable food item.
     * 
     * @param name The name of the food item
     * @param weight The weight of the food item
     * @param nutrition The nutritional value of the food
     * @param  
     */
    public Food(String name, int weight, int nutrition) {
        super(name, weight);
        this.nutrition = nutrition;
        this.perishable = false;
        
    }
    
    public Food(String name, int weight, int nutrition, int price, boolean market, boolean perishable) {
    	super(name, weight);
    	this.nutrition = nutrition;
    	this.marketFood = market;
    	this.perishable = perishable;
    }
    /**
     * Constructor for creating a food item with specified perishability.
     * 
     * @param name The name of the food item
     * @param weight The weight of the food item
     * @param nutrition The nutritional value of the food
     * @param perishable Whether the food can expire
     */
    public Food(String name, int weight, int nutrition, boolean perishable) {
        super(name, weight);
        this.nutrition = nutrition;
        this.perishable = perishable;
    }

    /**
     * Constructor for creating a food item with quantity and perishability.
     * 
     * @param name The name of the food item
     * @param weight The weight of the food item
     * @param quantity The quantity of food
     * @param nutrition The nutritional value of the food
     * @param perishable Whether the food can expire
     */
    public Food(String name, int weight, int quantity, int nutrition, boolean perishable) {
        super(name, weight, quantity);
        this.nutrition = nutrition;
        this.perishable = perishable;
    }

    /**
     * Simulates eating the food and reduces its quantity.
     * 
     * @param amount The amount of food to consume
     */
    public void eat(int amount) {
        System.out.println("You eat the " + this.getName() + ".");
        System.out.println("Nutrition value: " + nutrition);
        this.decreaseQuantity(amount);
    }

    /**
     * Gets the nutritional value of the food.
     * 
     * @return The nutrition value
     */
    public int getNutrition() {
        return nutrition;
    }

    /**
     * Checks if the food item is perishable.
     * 
     * @return true if the food can expire, false otherwise
     */
    public boolean isPerishable() {
        return perishable;
    }

    /**
     * Checks if a perishable food item has expired.
     * Uses random chance to determine expiration.
     * 
     * @return true if the food has expired, false otherwise
     */
    public boolean isExpired() {
        return Math.random() < 0.1; // 10% chance food is expired
    }

    /**
     * Returns a string representation of the food item.
     * Includes information about perishability.
     * 
     * @return A string describing the food item
     */
    public String toString() {
        String description = super.toString();
        if (perishable) {
            description += " (Perishable)";
        }
        return description;
    }

    /**
     * Factory method to create bacon food items.
     * 
     * @param quantity The quantity of bacon to create
     * @return A new bacon food item
     */
    public static Food createBacon(int quantity) {
        return new Food("Bacon", 10, quantity, 6, false);
    }
}