package termProject;

/**
 * Oxen Class of the Perils Along the Platte Game
 * Represents the draft animals that pull the pioneer wagon.
 * Manages oxen health, feeding, working, and other related mechanics.
 *
 * @author : Painter Drury, and Domenic Pilla
 * @version : 1.0
 * @ date : 03/25/2025
 * @ file : oxen.java
 */
import java.util.Random;

public class Oxen {
    private String name; // Name of the oxen (optional, can be used for specific oxen)
    private int health;  // Health of the oxen
    private int maxHealth; // Max health of the oxen
    private boolean isAlive; // Whether the ox is alive or dead
    private int cost; // The cost to buy the ox
    private static final Random random = new Random(); // Random object for events (if needed)
    Food food;

    /**
     * Constructor for creating a new oxen.
     * Initializes the oxen with a name, health, and cost.
     * 
     * @param name The name of the oxen
     * @param cost The purchase cost of the oxen
     *
     */
    public Oxen(String name, int cost) {
        this.name = name;
        this.health = 100; // Default health for the ox
        this.maxHealth = 100;
        this.isAlive = true; // Ox starts alive
        this.cost = cost;
    }

    /**
     * Checks if the oxen is currently alive.
     * 
     * @return true if the oxen is alive, false otherwise
     */
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * Feeds the oxen to restore health.
     * Only works if the oxen is still alive.
     */
    public void feed() {
        if (isAlive) {
            int healthRestored = random.nextInt(20) + 10; // Restore a random amount of health
            health = Math.min(health + healthRestored, maxHealth); // Ensure health doesn't exceed max
            System.out.println(name + " has been fed and restored " + healthRestored + " health!");
        } else {
            System.out.println(name + " is no longer alive and cannot be fed.");
        }
    }

    /**
     * Makes the oxen work, causing it to lose health.
     * If health drops to zero, the oxen dies.
     */
    public void work() {
        if (isAlive) {
            int damage = random.nextInt(10) + 5; // Oxen take some damage each time they work
            health -= damage;
            if (health <= 0) {
                health = 0;
                isAlive = false;
                System.out.println(name + " has died from exhaustion.");
            } else {
                System.out.println(name + " worked and lost " + damage + " health.");
            }
        } else {
            System.out.println(name + " is dead and cannot work.");
        }
    }

    /**
     * Displays the current health status of the oxen.
     */
    public void checkHealth() {
        if (isAlive) {
            System.out.println(name + "'s current health: " + health + "/" + maxHealth);
        } else {
            System.out.println(name + " is dead.");
        }
    }

    /**
     * Simulates the purchase of an oxen.
     * 
     * @param cost The amount paid for the oxen
     */
    public void buyOxen(int cost) {
        System.out.println("You have bought an ox for " + cost + " dollars.");
    }

    /**
     * Gets the purchase cost of the oxen.
     * 
     * @return The cost value
     */
    public int getCost() {
        return cost;
    }

    /**
     * Gets the name of the oxen.
     * 
     * @return The oxen's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the current health of the oxen.
     * 
     * @return The current health value
     */
    public int getHealth() {
        return health;
    }

    /**
     * Returns a string representation of the oxen.
     * Includes name and health status.
     * 
     * @return A string describing the oxen
     */
    @Override
    public String toString() {
        return name + " (Health: " + health + "/" + maxHealth + ")";
    }

    /**
     * Reduces the oxen's health by the specified amount.
     * If health drops to zero or below, the oxen dies.
     * 
     * @param damage The amount of damage to apply
     * @return The amount of damage applied
     */
    public int takeDamage(int damage){
        health -= damage;
        if(health <= 0){
            health = 0;
            isAlive = false;
        }
        return damage;
    }

    /**
     * Reduces the food available for the oxen by the specified amount.
     * 
     * @param amount The amount of food to consume
     */
    public void decreaseFood(int amount) {
        if (food != null) {
            food.eat(amount);
            System.out.println(name + " has consumed " + amount + " units of food. Remaining food: " + food.getQuantity());
        } else {
            System.out.println(name + " has no food.");
        }
    }

    /**
     * Gets the food object associated with this oxen.
     * 
     * @return The food object
     */
    public Food getFood(){
        return food;
    }
}


