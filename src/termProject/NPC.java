package termProject;

import java.util.ArrayList;

/**
 * NPC Class of the Perils Along the Platte Game
 * Represents non-player characters that players can interact with during the journey.
 * Manages NPC attributes, dialogue, trading, and combat interactions.
 *
 * @author : Painter Drury, and Domenic Pilla
 * @version : 1.0
 * @date : 03/25/2025
 * @file : NPC.java
 */
public class NPC {
    private String name;
    private int health;
    private int maxHealth;
    private int currency;
    private Inventory inventory;

    /**
     * Constructor for creating a new NPC with specified attributes.
     * 
     * @param name The name of the NPC
     * @param maxHealth The maximum health of the NPC
     * @param currency The amount of currency the NPC possesses
     */
    public NPC(String name, int maxHealth, int currency) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth; // Initially, the NPC has full health
        this.currency = currency;
        this.inventory = new Inventory(); // NPC's inventory (optional, could store items)
    }

    /**
     * Gets the name of the NPC.
     * 
     * @return The NPC's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the current health of the NPC.
     * 
     * @return The NPC's current health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Reduces the NPC's health by the specified amount.
     * Prevents health from going below zero.
     * 
     * @param amount The amount of health to decrease
     */
    public void decreaseHealth(int amount) {
        this.health = Math.max(0, this.health - amount); // Prevent negative health
    }

    /**
     * Gets the amount of currency the NPC possesses.
     * 
     * @return The NPC's currency amount
     */
    public int getCurrency() {
        return currency;
    }

    /**
     * Sets the NPC's currency to a specific amount.
     * 
     * @param currency The new currency amount
     */
    public void setCurrency(int currency) {
        this.currency = currency;
    }

    /**
     * Gets the NPC's inventory.
     * 
     * @return The NPC's inventory object
     */
    public ArrayList<Item> getInventory() {
        return inventory.displayInventoryList();
    }

    /**
     * Adds an item to the NPC's inventory.
     * 
     * @param item The item to add
     * @param quantity The quantity of the item to add
     */
    public void addItemToInventory(Item item, int quantity) {
        inventory.addItem(item, quantity);
    }

    /**
     * Initiates a conversation with the NPC.
     * Displays random dialogue and potentially offers to trade.
     */
    public void talk() {
        String[] dialogues = {
                "Hello there, traveler! Do you need supplies?",
                "The land ahead is dangerous, be careful.",
                "I'm willing to trade for some of your goods."
        };

        int dialogueIndex = (int) (Math.random() * dialogues.length);
        System.out.println(name + " says: " + dialogues[dialogueIndex]);

        // Example of random trade offer (this could trigger a trade or just a chat)
        if (Math.random() * 100 < 50) {
            System.out.println("The NPC offers to trade with you.");
        } else {
            System.out.println("The NPC doesn't have anything to offer right now.");
        }
    }

    /**
     * Checks if the NPC has enough currency for a transaction.
     * 
     * @param amount The amount of currency needed
     * @return true if the NPC has enough currency, false otherwise
     */
    public boolean hasEnoughCurrency(int amount) {
        return this.currency >= amount;
    }

    /**
     * Increases the NPC's currency by the specified amount.
     * 
     * @param amount The amount of currency to add
     */
    public void addCurrency(int amount) {
        this.currency += amount;
    }

    /**
     * Decreases the NPC's currency by the specified amount if possible.
     * Only removes currency if the NPC has enough.
     * 
     * @param amount The amount of currency to remove
     */
    public void removeCurrency(int amount) {
        if (this.currency >= amount) {
            this.currency -= amount;
        }
    }

    /**
     * Makes the NPC attack the player, causing damage.
     * 
     * @param player The player to attack
     */
    public void attack(Player player) {
        int attackPower = 10; // Default attack power, can be enhanced based on NPC type
        player.takeDamage(attackPower);
        System.out.println(name + " attacks you for " + attackPower + " damage!");
    }

    /**
     * Determines how the NPC reacts after being attacked by the player.
     * Different reactions based on whether the NPC is defeated or not.
     * 
     * @param player The player who attacked the NPC
     */
    public void reactToAttack(Player player) {
        if (this.health <= 0) {
            System.out.println(name + " has been defeated!");
        } else {
            System.out.println(name + " says: 'You will regret this!'");
        }
    }

	public void removeItemFromInventory(Item itemToTrade, int i) {
		inventory.removeItem(itemToTrade, i);
		// TODO Auto-generated method stub
		
	}
}
