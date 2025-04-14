package termProject;

import java.util.ArrayList;

/**
 * Inventory Class of the Perils Along the Platte Game
 * Manages the collection of items that the player can carry and use.
 * Provides functionality for adding, removing, sorting, and using items.
 *
 * @author : Painter Drury, and Domenic Pilla
 * @version : 1.0
 * @ date : 03/25/2025
 * @ file : inventory.java
 */
public class Inventory {

    int maxInventorySpace = 30;
    int inventory;
    Item[] items = new Item[maxInventorySpace];
    Item itemInUse;
    Food foodInUse;
	private Inventory playerInventory;

    /**
     * Default constructor for creating an empty inventory.
     * Initializes the inventory with 0 items.
     */
    public Inventory() {
        this.inventory = 0;
    }
    
    /**
     * Adds an item to the inventory with the specified quantity.
     * Attempts to stack items of the same type before using a new slot.
     *
     * @param item The item to add to the inventory
     * @param quantity The quantity of the item to add
     */
    public void addItem(Item item, int quantity) {
        if (inventory < maxInventorySpace) {
            boolean itemAdded = false;

            // Try to add to existing item stack or find an empty spot
            for (int i = 0; i < inventory; i++) {
                // Check if the item already exists or an empty spot is found
                if (items[i] == null || items[i].getName().equals(item.getName())) {
                    // Check if we can increase quantity of the current item
                    if (items[i].getQuantity() + quantity <= items[i].getMaximumQuantity()) {
                        items[i].setQuantity(items[i].getQuantity() + quantity);
                        itemAdded = true;
                        break;
                    }
                }
            }

            // If item was not added, add it to the next available slot
            if (!itemAdded) {
                // Ensure there's room before trying to add a new item
                if (inventory < maxInventorySpace) {
                    for (int i = 0; i < maxInventorySpace; i++) {
                        // Find the next empty slot
                        if (items[i] == null) {
                            items[i] = item;  // Add item to the empty slot
                            inventory++;
                            break;
                        }
                    }
                } else {
                    System.out.println("No more space in inventory.");
                }
            }
        } else {
            System.out.println("Your inventory is full. Drop an item if you want to pick this up.");
        }
    }

    /**
     * Removes a specified quantity of an item from the inventory.
     * If the quantity to remove exceeds the available quantity, the item is completely removed.
     *
     * @param item The item to remove
     * @param quantity The quantity to remove
     */
    public void removeItem(Item item, int quantity) {
        for(int i = 0; i < inventory; i++){
            if(items[i] == item){
                if(items[i].getQuantity() > quantity){
                    items[i].setQuantity(items[i].getQuantity() - quantity);
                }
                else{
                    items[i] = null;
                    inventory--;
                }
            }
        }
    }

    /**
     * Uses an item from the inventory, reducing its quantity.
     * If the item is perishable and expired, it is removed instead.
     *
     * @param item The item to use
     * @param quantity The quantity to use
     */
    public void useItem(Item item, int quantity) {
        for(int i = 0; i < inventory; i++){
            if(items[i].isPerishable()) {
                if (!items[i].isExpired()) {
                    if (items[i] == item) {
                        itemInUse = item;
                        items[i].setQuantity(items[i].getQuantity() - 1);
                    }
                } else {
                    removeItem(item, quantity);
                }
            }
            //Should subtract one ammo after gun shot
            //if(items[i].isUsable()){
              //  items[i].getName(items[i].setAmmo((items[i].getAmmo())));
            //}
        }
    }

    /**
     * Displays all items currently in the inventory.
     * @return 
     */
    public Inventory displayInventory() {
        for(int i = 0; i < inventory; i++){
            if(items[i] != null){
                System.out.print("Inventory: " + items[i] + " ");
            }
        }
		return null;
    }
    
    public ArrayList<Item> displayInventoryList() {
    	ArrayList<Item> itemList = new ArrayList<Item>();
        for(int i = 0; i < inventory; i++){
            if(items[i] != null){
                System.out.print("Inventory: " + items[i] + " ");
                itemList.add(items[i]);
            }
        }
		return itemList;
    }
    /**
     * Sorts the inventory alphabetically by item name.
     */
    public void sortInventory() {
        for(int i = 0; i < inventory; i++){
            if(items[i] != null){
                for(int j = i; j < inventory; j++){
                    if(items[j] != null){
                        if(items[i].getName().compareTo(items[j].getName()) > 0){
                            Item temp = items[i];
                            items[i] = items[j];
                            items[j] = temp;
                        }
                    }
                }
            }
        }
    }

    /**
     * Selects an item to be the currently used item.
     *
     * @param item The item to select for use
     */
    public void selectItem(Item item) {
        itemInUse = item;
    }

    /**
     * Retrieves an item from the inventory by name.
     *
     * @param itemName The name of the item to retrieve
     * @return The item if found, null otherwise
     */
    public Item getItemInUse(String itemName) {
        for(int i = 0; i < inventory; i++){
            if(items[i] != null){
                if(items[i].getName().equals(itemName)){
                    return items[i];
                }
            }
        }
        return null;
    }

    /**
     * Displays all weapons in the inventory.
     */
    public void displayWeapons() {
        for(int i = 0; i < inventory; i++){
            if(items[i] != null){
                if(items[i].isWeapon()){
                    System.out.print(items[i] + " ");
                }
            }
        }
    }

    /**
     * Displays all consumable items in the inventory.
     */
    public void displayConsumables() {
        for(int i = 0; i < inventory; i++){
            if(items[i] != null){
                if(items[i].isConsumable()){
                    System.out.print(items[i] + " ");
                }
            }
        }
    }

    /**
     * Displays all wagon parts in the inventory.
     */
    public void displayWagonParts() {
        for(int i = 0; i < inventory; i++){
            if(items[i] != null){
                if(items[i].isWagonPart()){
                    System.out.print(items[i] + " ");
                }
            }
        }
    }

    /**
     * Calculates the total amount of food items in the inventory.
     *
     * @return The total quantity of food items
     */
    public void consumeDailyRations(int partySize) {
        int dailyConsumption = partySize * 2; // 2 food units per person
        for (Item item : items) {
            if (item instanceof Food) {
                int currentAmount = item.getQuantity();
                if (currentAmount >= dailyConsumption) {
                    item.setQuantity(currentAmount - dailyConsumption);
                    return;
                }
            }
        }
    }

    public int getFoodAmount() {
        int totalFood = 0;
        for (Item item : items) {
            if (item instanceof Food) {
                totalFood += item.getQuantity();
            }
        }
        return totalFood;
    }

    /**
     * Calculates the total weight of all food items in the inventory.
     *
     * @return The total weight of all food items
     */
    public int getTotalFoodWeight(){
        int totalFoodWeight = 0;
        for(int i = 0; i < inventory; i++){
            if(items[i] != null){
                if(items[i].isConsumable()){
                    totalFoodWeight += items[i].getWeight() * items[i].getQuantity();
                }
            }
        }
        return totalFoodWeight;
    }

    /**
     * Consumes a specified amount of food from the inventory.
     * Reduces food quantities or removes food items as needed.
     *
     * @param amount The amount of food to consume
     */
    public void consumeFood(int amount) {
        for(int i = 0; i < inventory; i++){
            if(items[i] != null){
                if(items[i].isConsumable()){
                    if(items[i].getQuantity() > amount){
                        items[i].setQuantity(items[i].getQuantity() - amount);
                    }
                    else{
                        items[i] = null;
                        inventory--;
                    }
                }
            }
        }
    }

    /**
     * Checks if the inventory contains an item with the specified name.
     *
     * @param itemName The name of the item to check for
     * @return true if the item exists in the inventory, false otherwise
     */
    public boolean hasItem(String itemName) {
        for(int i = 0; i < inventory; i++){
            if(items[i] != null){
                if(items[i].getName().equals(itemName)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Gets the quantity of a specific item in the inventory.
     *
     * @param item The item to check
     * @return The quantity of the specified item
     */
    public int getItemQuantity(Item item){
        for(int i = 0; i < inventory; i++){
            if(items[i] != null){
                if(items[i].getName().equals(item.getName())){
                    return items[i].getQuantity();
                }
            }
        }
        return 0;
    }

    public void damageRandomItem() {
        if (inventory > 0) {
            int randomIndex = (int)(Math.random() * inventory);
            if (items[randomIndex] != null) {
                items[randomIndex].setQuantity(items[randomIndex].getQuantity() - 1);
            }
        }
    }

    public void addRandomSupplies() {
        String[] possibleItems = {"food", "ammunition", "medicine", "spare parts"};
        String randomItem = possibleItems[(int)(Math.random() * possibleItems.length)];
        addItem(new Item(randomItem), 1 + (int)(Math.random() * 5));
    }

    public void loseRandomSupplies() {
        if (inventory > 0) {
            int randomIndex = (int)(Math.random() * inventory);
            if (items[randomIndex] != null) {
                removeItem(items[randomIndex], 1);
            }
        }
    }
}
