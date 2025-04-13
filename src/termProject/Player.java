package termProject;

/**
 * Player Class of the Perils Along the Platte Game
 * Represents the player character with attributes such as health, hunger, and inventory.
 * Manages player status, item usage, currency, and interactions with game mechanics.
 *
 * @author : Alex Randall, Chase McCluskey, Painter Drury, and Domenic Pilla
 * @version : 1.0
 * @date : 03/25/2025
 * @file : player.java
 */
import java.util.ArrayList;

public class Player {
    static private String name;
    static private String gender;
    static private double lastDamage = 0;
    static private double currentDamage = 0;
    static private Health playerHealth;
    static private String causeOfDeath;
    static private int hunger;
    static private int playerFatigue;
    static private boolean isSick;
    static private String illness;
    static Currency money;
    static private Health health;


    /**
     * Default constructor for creating a new player.
     * Initializes player with default health, hunger, and fatigue values.
     */
    public Player() {
        playerHealth = new Health(100);
        hunger = 100;
        playerFatigue = 0;
        this.money = new Currency(100);
        isSick = false;
        illness = "None";
        oxenList = new ArrayList<>();
        this.foodList = new ArrayList<>();
    }

    /**
     * Constructs a player with specific name, gender, and money.
     * Calls the default constructor for basic initialization.
     *
     * @param name The player's name
     * @param gender The player's gender
     * @param money The starting amount of money
     */
    public Player(String name, String gender, double money) {
        this();
        this.name = name;
        this.gender = gender;
    }

    /**
     * Sets the player's name.
     *
     * @param name The name to assign to the player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the player's gender.
     *
     * @param gender The gender to assign to the player
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Gets the player's name.
     *
     * @return The player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the player's gender.
     *
     * @return The player's gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Records damage taken and returns the current damage value.
     *
     * @param damageDone The amount of damage done
     * @return The current damage value
     */
    public double getDamage(double damageDone){
        lastDamage = currentDamage;
        return currentDamage = damageDone;
    }

    /**
     * Gets the player's current health.
     *
     * @return The current health value
     */
    public double getHealth() {
        return playerHealth.getCurrentHealth();
    }

    /**
     * Gets the amount of damage taken in the previous damage event.
     *
     * @return The last damage amount
     */
    public double getLastDamage() {
        return lastDamage;
    }
    
    /**
     * Reduces player health by the specified amount with a cause of death if fatal.
     *
     * @param amount The amount of damage to apply
     * @param cause The cause of the damage/potential death
     */
    public void takeDamage(int amount, String cause) {
        playerHealth.takeDamage(amount, cause);
        if (playerHealth.getCurrentHealth() <= 0) {
            causeOfDeath = cause;
        }
        lastDamage = amount;
    }

    /**
     * Reduces player health by the specified amount with an unknown cause.
     *
     * @param amount The amount of damage to apply
     */
    public void takeDamage(int amount){
        takeDamage(amount, "Unknown");
    }

    /**
     * Gets the cause of player's death if they have died.
     *
     * @return The cause of death or null if player is alive
     */
    public String getCauseOfDeath() {
        return causeOfDeath;
    }

    /**
     * Increases the player's health by the specified amount.
     *
     * @param amount The amount of health to restore
     */
    public void heal(int amount) {
        playerHealth.heal(amount);
    }

    /**
     * Checks if the player is currently alive.
     *
     * @return true if the player is alive, false otherwise
     */
    public boolean isAlive() {
        return playerHealth.isAlive();
    }

    /**
     * Gets the player's current hunger level.
     *
     * @return The current hunger value
     */
    public int getHunger() {
        return hunger;
    }

    /**
     * Increases the player's hunger level.
     *
     * @param amount The amount to increase hunger by
     * @return The new hunger value
     */
    public int addHunger(int amount){
        hunger += amount;
        return hunger;
    }

    /**
     * Decreases the player's hunger level.
     *
     * @param amount The amount to decrease hunger by
     * @return The new hunger value
     */
    public int removeHunger(int amount){
        hunger -= amount;
        if(hunger < 0) hunger = 0;
        return hunger;
    }

    /**
     * Gets the player's current fatigue level.
     *
     * @return The current fatigue value
     */
    public int getFatigue() {
        return playerFatigue;
    }

    /**
     * Checks if the player is currently sick.
     *
     * @return true if the player is sick, false otherwise
     */
    public boolean isSick() {
        return isSick;
    }

    /**
     * Gets the name of the player's current illness if sick.
     *
     * @return The name of the illness or "None" if not sick
     */
    public String getIllness() {
        return illness;
    }

    /**
     * Makes the player rest, reducing fatigue and hunger while restoring health.
     */
    public void rest(){
        playerFatigue += (int) Math.abs((playerFatigue + 10 * 0.5) / 2);
        playerHealth.heal(10);
        removeHunger(10);
    }

    /**
     * Displays the player's current status including health, food, and money.
     */
    public void displayStatus(){
        System.out.println("\n===== Player Status =====");
        System.out.println("Name: " + name);
        System.out.println("Health: " + health);
        System.out.println("Food: " + food + " lbs");
        System.out.println("Money: $" + money);
        System.out.println("=========================");
    }

    /**
     * Uses an item from the player's inventory with appropriate effects.
     *
     * @param item The item to use
     */
    public void useItem(Item item) {
        if (!inventory.hasItem(item.getName())) {
            System.out.println("You do not have a " + item.getName() + " to use.");
            return;
        }

        switch (item.getName().toLowerCase()) {
            case "medicine":
                if (health.getCurrentHealth() < 100) {
                    health.heal(Math.min(health.getCurrentHealth() + 20, 100));
                    inventory.removeItem(item, 1);
                    System.out.println("You used medicine. Health is now: " + health);
                } else {
                    System.out.println("Your health is already full.");
                }
                break;

            case "food":
                if (food.getQuantity() > 0) {
                    food.setQuantity(Math.max(food.getQuantity() - 5, 0));  // Eating food reduces supply
                    System.out.println("Your " + item.getName() + " gave you " + food.getNutrition() + " health");
                    System.out.println("You eat some food. Food left: " + food + " lbs");
                } else {
                    System.out.println("You have no food left!");
                }
                break;

            default:
                System.out.println("You cannot use " + item.getName() + ".");
                break;
        }
    }

    /**
     * Gets the player's inventory.
     *
     * @return The player's inventory object
     */
    public inventory getInventory(){
        return inventory;
    }
    
    /**
     * Sets the player's inventory reference.
     * This method connects an existing inventory object to the player.
     * 
     * @param playerInventory The inventory object to associate with this player
     */
    public void setInventory(inventory playerInventory) {
        this.inventory = playerInventory;
    }

    /**
     * Gets a specific item from the player's inventory by name.
     *
     * @param itemName The name of the item to retrieve
     * @return The item object or null if not found
     */
    public Item getItem(String itemName){
        return inventory.getItemInUse(itemName);
    }
    
    public void addItemtoInventory(Item item, int number) {
    	inventory.addItem(item, number);
    }

    /**
     * Gets the player's current money balance.
     *
     * @return The current amount of money
     */
    public double getCurrency(){
        return money.getBalance();
    }

    /**
     * Increases the player's money by the specified amount.
     *
     * @param amount The amount of money to add
     */
    public void increaseCurrency(int amount){
        money.addMoney(amount);
    }

    /**
     * Decreases the player's money by the specified amount.
     *
     * @param amount The amount of money to remove
     */
    public void decreaseCurrency(int amount){
        money.removeMoney(amount);
    }

    /**
     * Equips a weapon for the player to use.
     *
     * @param weapon The weapon to equip
     */
    public void equipWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    /**
     * Gets the attack power of the player's equipped weapon.
     *
     * @return The weapon's power or 0 if no weapon is equipped
     */
    public int getWeaponPower() {
        if (weapon != null) {
            return weapon.getPower();
        } else {
            return 0; // If the player has no weapon, return 0 attack power
        }
    }

    /**
     * Adds an oxen to the player's team.
     *
     * @param ox The oxen to add
     */
    public void addOxen(Oxen ox) {
        oxenList.add(ox);
    }

    /**
     * Decreases the health of all oxen in the player's team.
     *
     * @param amount The amount of health to decrease
     */
    public void decreaseOxHealth(int amount) {
        if (oxenList.isEmpty()) {
            System.out.println("You have no oxen to decrease health for.");
            return;
        }

        // Assuming you want to decrease health for all oxen equally
        for (Oxen ox : oxenList) {
            ox.takeDamage(amount);
            System.out.println("One of your oxen's health has decreased by " + amount + " points.");
            if (ox.getHealth() == 0) {
                System.out.println(ox.getName() + " has died.");
            }
        }
    }

    /**
     * Gets the number of oxen in the player's team.
     *
     * @return The count of oxen
     */
    public int getOxenCount() {
        return oxenList.size();
    }

    /**
     * Adds food to the player's food supply.
     *
     * @param food The food to add
     */
    public void AddFood(Food food){
        this.foodList.add(food);
    }

    /**
     * Decreases a specific type of food by the specified amount.
     *
     * @param food The food type to decrease
     * @param amount The amount to decrease by
     */
    public void decreaseFood(Food food, int amount){
        if(food == null){
            System.out.println("No food item selected.");
            return;
        }

        food.decreaseQuantity(amount);

        System.out.println("You have consumed " + amount + " units of " + food.getName() + ". Remaining food: " + food.getQuantity());
    }

    /**
     * Gets the quantity of a specific type of food.
     *
     * @param foodName The name of the food to check
     * @return The quantity of the specified food
     */
    public int getFood(String foodName){
        for(Food food : foodList){
            if(food.getName().equals(foodName)){
                return food.getQuantity();
            }
        }
        return 0;
    }
    
    public Food getFoodType(String foodName){
        for(Food food : foodList){
            if(food.getName().equals(foodName)){
                return food;
            }
        }
        return null;
    }
    /**
     * Decreases the total food supply by the specified amount.
     *
     * @param amount The amount of food to consume/remove
     */
    public void decreaseFood(int amount){
        for(int i = 0; i < foodList.size(); i++){
            if(foodList.get(i).getQuantity() >= amount){
                foodList.get(i).decreaseQuantity(amount);
                return;
            }
        }
    }

    public void decreaseMovementSpeed(double factor) {
        // Implementation for movement speed reduction
    }

    public void setIllness(String illnessName) {
        this.illness = illnessName;
        this.isSick = true;
    }

    public int getOxenHealth() {
        int totalHealth = 0;
        for (Oxen ox : oxenList) {
            totalHealth += ox.getHealth();
        }
        return oxenList.isEmpty() ? 0 : totalHealth / oxenList.size();
    }
    
    public boolean checkGameOver() {
        if (health.getCurrentHealth() <= 0) {
            causeOfDeath = "poor health";
            return true;
        }
        if (inventory.getFoodAmount() <= 0) {
            causeOfDeath = "starvation";
            return true;
        }
        if (Wagon.isBroken() && Currency.getBalance() < 50) {
            causeOfDeath = "broken wagon with insufficient funds for repair";
            return true;
        }
        return false;
    }

    public String getGameOverMessage() {
        return "Game Over: Your party has died from " + causeOfDeath;
    }

    public boolean hasWon(GameMap gameMap) {
        return gameMap.getCurrentDistanceTraveled() >= gameMap.getRemainingDistance();
    }

    
    public int getPartySize() {
        return partySize;
    }
    
    public Wagon getWagon() {
         return playerWagon;
    }
    
    public void increaseFatigue(double amount) {
         playerFatigue += amount;
         if (playerFatigue > 100) playerFatigue = 100;
    }
    
    /**
     * Creates and connects an inventory to the player.
     * This method initializes the player's inventory and sets up the basic starting items.
     */
    public void createInventory() {
        // Initialize a new inventory for this player
        inventory playerInventory = new inventory();
        
        // Add basic starting items to the inventory
        playerInventory.addItem(new Food("Rations", 1, 100), 50);      // 50 pounds of food
        playerInventory.addItem(new Weapon("Rifle", 5), 1);            // 1 rifle
        playerInventory.addItem(new Item("Ammunition", 1), 50);        // 50 ammunition
        playerInventory.addItem(new Medicine("Medicine", 1, 5, 1), 3); // 3 medicine items
        
        // Set this inventory as the player's inventory
        this.setInventory(playerInventory);
    }
}
