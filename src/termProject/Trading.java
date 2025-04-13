package termProject;

/**
 * Trading Class of the Perils Along the Platte Game
 * Manages trading interactions with NPCs and merchants.
 * Handles buying, selling, bartering, and price negotiation.
 *
 * @author : Alex Randall, Chase McCluskey, Painter Drury, and Domenic Pilla
 * @version : 1.0
 * @date : 03/25/2025
 * @file : trading.java
 */
import java.util.ArrayList;
import java.util.Scanner;

public class Trading extends RandomEvent {
    private Player player;
    private NPC npc;  // Assuming you have an NPC class for trading with NPCs
    private Market market;  // Assuming there's a Market class for trading with the market
    private ArrayList<Item> tradeItems;  // List of items available for trade
    private double playerCurrency;
    private int npcCurrency;
    private boolean tradeCompleted;

    /**
     * Default constructor for the Trading class.
     * Initializes a new trading interaction.
     */
    public Trading(Player player, NPC npc, Market market) {
        this.player = player;
        this.npc = npc;
        this.market = market;
        this.tradeItems = new ArrayList<>();
        this.tradeCompleted = false;
        this.playerCurrency = player.getCurrency();
        this.npcCurrency = npc.getCurrency();
    }

    /**
     * Initiates the trading process.
     * Displays available items and allows the player to interact with the trade system.
     */
    public void initiateTrading() {
        // Display trade options to the player
        displayTradeMenu();

        // Player chooses items to trade
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select an option (1-4): ");
        int option = scanner.nextInt();

        switch (option) {
            case 1: // Trade with NPC
                tradeWithNPC();
                break;
            case 2: // Trade with Market
                tradeWithMarket();
                break;
            case 3: // Exit Trading
                System.out.println("You have exited the trading system.");
                break;
            default:
                System.out.println("Invalid option.");
                break;
        }
    }

    /**
     * Displays a menu of trade options.
     */
    private void displayTradeMenu() {
        System.out.println("Welcome to the Trading System!");
        System.out.println("1. Trade with NPC");
        System.out.println("2. Trade with Market");
        System.out.println("3. Exit");
    }

    /**
     * Handles the trade process with the NPC.
     */
    private void tradeWithNPC() {
        // Display NPC's available items and ask player to choose
        System.out.println("You are trading with: " + npc.getName());
        System.out.println("NPC's Currency: " + npcCurrency);

        ArrayList<Item> npcItems = npc.getInventory();  // Assuming NPC has an inventory method
        System.out.println("Items available for trade:");
        for (int i = 0; i < npcItems.size(); i++) {
            System.out.println((i + 1) + ". " + npcItems.get(i).getName() + " - Price: " + npcItems.get(i).getPrice());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Select an item to trade: ");
        int itemIndex = scanner.nextInt() - 1;
        Item itemToTrade = npcItems.get(itemIndex);

        System.out.println("Do you want to buy this item? (Yes/No): ");
        String response = scanner.next();

        if (response.equalsIgnoreCase("Yes")) {
            if (playerCurrency >= itemToTrade.getPrice()) {
                playerCurrency -= itemToTrade.getPrice();
                npcCurrency += itemToTrade.getPrice();
                player.addItemtoInventory(itemToTrade, 1);
                npc.removeItemFromInventory(itemToTrade, 1);
                System.out.println("Trade successful!");
                tradeCompleted = true;
            } else {
                System.out.println("You don't have enough currency to trade.");
            }
        } else {
            System.out.println("You decided not to trade.");
        }
    }

    /**
     * Handles the trade process with the Market.
     */
    private void tradeWithMarket() {
        System.out.println("You are trading with the Market.");
        ArrayList<Item> marketItems = market.displayItems();  // Assuming Market has available items
        System.out.println("Items available for trade:");

        for (int i = 0; i < marketItems.size(); i++) {
            System.out.println((i + 1) + ". " + marketItems.get(i).getName() + " - Price: " + marketItems.get(i).getPrice());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Select an item to buy from the market: ");
        int itemIndex = scanner.nextInt() - 1;
        Item itemToBuy = marketItems.get(itemIndex);

        if (playerCurrency >= itemToBuy.getPrice()) {
            playerCurrency -= itemToBuy.getPrice();
            player.addItemtoInventory(itemToBuy, 1);
            System.out.println("You have bought the " + itemToBuy.getName() + " from the market.");
            tradeCompleted = true;
        } else {
            System.out.println("You don't have enough currency to buy this item.");
        }
    }

    /**
     * Calculates the trade value based on the items being traded and the negotiation logic.
     */
    private int calculateTradeValue(Item item, boolean isBuying) {
        int tradeValue = item.getPrice();

        if (isBuying) {
            tradeValue -= tradeValue * 0.1; // Discount on buying from market
        } else {
            tradeValue += tradeValue * 0.1; // Markup on selling items to NPCs
        }

        return tradeValue;
    }

    /**
     * Returns whether the trade was completed successfully.
     */
    public boolean isTradeCompleted() {
        return tradeCompleted;
    }
}