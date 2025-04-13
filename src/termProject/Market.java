package termProject;

/**
 * Market Class of the Perils Along the Platte Game
 * Represents stores and trading posts where players can buy and sell items.
 * Manages inventory, pricing, transactions, and location-based market access.
 *
 * @author : Alex Randall, Chase McCluskey, Painter Drury, and Domenic Pilla
 * @version : 1.0
 * @date : 03/25/2025
 * @file : market.java
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Market {
    private Currency money;  // Instance of currency class to track money
    private List<Item> availableItems;  // List to hold available items in the market
    private List<Item> playerInventory; // Player's inventory of items
    private GameMap gameMap;  // Instance of the map class
    private String marketLocation;  // Market's location on the map

    /**
     * Constructor for creating a market with initial money, location and map.
     * Initializes the market's inventory with some default items.
     * 
     * @param startingMoney The currency object to track money
     * @param gameMap The game map instance
     * @param marketLocation The location of the market on the map
     */
    public Market(Currency startingMoney, GameMap gameMap, String marketLocation) {
        this.money = startingMoney;
        this.gameMap = gameMap;
        this.marketLocation = marketLocation;
        this.availableItems = new ArrayList<>();
        this.playerInventory = new ArrayList<>();

        // Add some sample items to the market
        addItemToMarket(new Item("apple", 1, 10, 2, 50));  // apple with weight 1, 10 quantity, value 2, max quantity 50
        addItemToMarket(new Item("bread", 2, 5, 1, 30));   // bread with weight 2, 5 quantity, value 1, max quantity 30
        addItemToMarket(new Item("steak", 3, 2, 10, 10));  // steak with weight 3, 2 quantity, value 10, max quantity 10
    }

    /**
     * Adds a new item to the market's available inventory.
     * 
     * @param newItem The item to add to the market
     */
    public void addItemToMarket(Item newItem) {
        availableItems.add(newItem);
    }

    /**
     * Displays all items currently available for purchase in the market.
     * Shows name, quantity, weight, and price for each item.
     * @return 
     */
    public ArrayList<Item> displayItems() {
        System.out.println("\nAvailable Items in the Market:");
        ArrayList<Item> items = new ArrayList<Item>();
        for (Item i : availableItems) {
            System.out.println(i.toString() + " | Value: $" + i.getValue());
           items.addAll(availableItems);
        }
        return items;
    }

    /**
     * Allows the player to buy an item from the market.
     * Checks if the item exists, if there's enough quantity available,
     * and if the player has enough money.
     * 
     * @param itemName The name of the item to buy
     * @param quantity The quantity of the item to buy
     */
    public void buyItem(String itemName, int quantity) {
        Item itemToBuy = findItemInMarket(itemName);
        if (itemToBuy != null) {
            int totalCost = itemToBuy.getValue() * quantity;
            if (itemToBuy.getQuantity() >= quantity && money.getBalance() >= totalCost) {
                // Check if enough money and quantity are available
                itemToBuy.decreaseQuantity(quantity);
                money.removeMoney(totalCost);  // Using currency class method to decrease money

                // Add item to player's inventory
                Item existingItem = findItemInInventory(itemName);
                if (existingItem != null) {
                    existingItem.increaseQuantity(quantity);
                } else {
                    Item newItem = new Item(itemName, itemToBuy.getWeight(), quantity, itemToBuy.getValue(), itemToBuy.getMaximumQuantity());
                    playerInventory.add(newItem);
                }

                System.out.println("You bought " + quantity + " " + itemName + "(s).");
            } else {
                System.out.println("Not enough money or quantity available for that item.");
            }
        } else {
            System.out.println("Item not found in the market.");
        }
    }

    /**
     * Allows the player to sell an item to the market.
     * Checks if the player has the item and enough quantity to sell.
     * 
     * @param itemName The name of the item to sell
     * @param quantity The quantity of the item to sell
     */
    public void sellItem(String itemName, int quantity) {
        Item itemToSell = findItemInInventory(itemName);
        if (itemToSell != null && itemToSell.getQuantity() >= quantity) {
            itemToSell.decreaseQuantity(quantity);
            int totalEarned = itemToSell.getValue() * quantity;
            money.addMoney(totalEarned);  // Using currency class method to increase money

            System.out.println("You sold " + quantity + " " + itemName + "(s).");
        } else {
            System.out.println("You do not have enough of that item to sell.");
        }
    }

    /**
     * Finds an item in the market's inventory by name.
     * 
     * @param name The name of the item to find
     * @return The item if found, null otherwise
     */
    private Item findItemInMarket(String name) {
        for (Item i : availableItems) {
            if (i.getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return null; // Item not found
    }

    /**
     * Finds an item in the player's inventory by name.
     * 
     * @param name The name of the item to find
     * @return The item if found, null otherwise
     */
    private Item findItemInInventory(String name) {
        for (Item i : playerInventory) {
            if (i.getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return null; // Item not found
    }

    /**
     * Displays all items currently in the player's inventory.
     * Shows name, quantity, and weight for each item.
     */
    public void displayInventory() {
        System.out.println("\nYour Inventory:");
        if (playerInventory.isEmpty()) {
            System.out.println("Your inventory is empty.");
        } else {
            for (Item i : playerInventory) {
                System.out.println(i.toString());
            }
        }
    }

    /**
     * Displays the current money balance to the player.
     */
    public void checkBalance() {
        System.out.println("Current balance: $" + money.getBalance());
    }

    /**
     * Provides a complete market interface for the player to interact with.
     * Only works if the player is at the correct location.
     * Allows buying, selling, and viewing inventory.
     * 
     * @param playerLocation The player's current location
     */
    public void visitStore(String playerLocation) {
        if (playerLocation.equalsIgnoreCase(marketLocation)) {
            // Proceed to interact with the market
            Scanner scanner = new Scanner(System.in);
            boolean exitStore = false;

            while (!exitStore) {
                System.out.println("\nWelcome to the Market! What would you like to do?");
                System.out.println("1. View available items");
                System.out.println("2. Buy an item");
                System.out.println("3. Sell an item");
                System.out.println("4. Check balance");
                System.out.println("5. View your inventory");
                System.out.println("6. Exit store");
                System.out.print("Choose an option: ");

                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        displayItems();
                        break;
                    case "2":
                        System.out.print("Enter food item to buy: ");
                        String buyItem = scanner.nextLine();
                        System.out.print("Enter quantity: ");
                        int buyQuantity = Integer.parseInt(scanner.nextLine());
                        buyItem(buyItem, buyQuantity);
                        break;
                    case "3":
                        System.out.print("Enter food item to sell: ");
                        String sellItem = scanner.nextLine();
                        System.out.print("Enter quantity: ");
                        int sellQuantity = Integer.parseInt(scanner.nextLine());
                        sellItem(sellItem, sellQuantity);
                        break;
                    case "4":
                        checkBalance();
                        break;
                    case "5":
                        displayInventory();
                        break;
                    case "6":
                        exitStore = true;
                        System.out.println("Thank you for visiting the market. Come again soon!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        } else {
            System.out.println("You are not at the correct location to visit the market.");
        }
    }

    /**
     * Gets the market's location on the map.
     * 
     * @return The name of the market's location
     */
    public String getMarketLocation() {
        return marketLocation;
    }

    /**
     * Sets the market's location on the map.
     * 
     * @param marketLocation The new location for the market
     */
    public void setMarketLocation(String marketLocation) {
        this.marketLocation = marketLocation;
    }

    public void initializeMarket() {
        availableItems = new ArrayList<>();
        availableItems.add(new Food("Rations", 1, 50, true)); // Added maxQuantity parameter
        availableItems.add(new Medicine("Medicine", 1, 20, 50)); // Added value and maxQuantity
        availableItems.add(new Item("Wagon Part", 5, 50));
        availableItems.add(new Weapon("Ammunition", 1, 2));
    }

    public void displayMarketItems() {
        System.out.println("\n=== Available Items ===");
        for (int i = 0; i < availableItems.size(); i++) {
            Item item = availableItems.get(i);
            System.out.println((i+1) + ". " + item.getName() + " - $" + item.getValue());
        }
    }

    public boolean buyItem(int index, int quantity) {
        if (index >= 0 && index < availableItems.size()) {
            Item selectedItem = availableItems.get(index);
            int totalCost = selectedItem.getValue() * quantity;
            
            if (money.getBalance() >= totalCost) {
                money.removeMoney(totalCost);
                // Create a new item instance with the specified quantity
                Item newItem = new Item(selectedItem.getName(),
                                      selectedItem.getWeight(), 
                                      quantity,
                                      selectedItem.getValue(),
                                      selectedItem.getMaximumQuantity());
                playerInventory.add(newItem);
                return true;
            }
        }
        return false;
    }
}