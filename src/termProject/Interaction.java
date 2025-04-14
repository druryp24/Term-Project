package termProject;

/**
 * Interaction Class of the Perils Along the Platte Game
 * Manages player interactions with NPCs and merchants.
 * Handles buying, selling, trading, stealing, combat, and dialogue.
 *
 * @author : Painter Drury, and Domenic Pilla
 * @version : 1.0
 * @ date : 03/25/2025
 * @ file : interaction.java
 */
public class Interaction {

    private Player player;

    /**
     * Constructor for the interaction system.
     * 
     * @param player The player who will be interacting with NPCs
     */
    public Interaction(Player player) {
        this.player = player;
    }

    /**
     * Allows the player to buy an item from a merchant.
     * Checks if the player has enough currency before completing the purchase.
     * 
     * @param item The item to buy
     * @param quantity The quantity of the item to buy
     * @param pricePerItem The price of each individual item
     */
    public void buy(Item item, int quantity, int pricePerItem) {
        int totalCost = pricePerItem * quantity;

        if (player.getCurrency() >= totalCost) {
            player.decreaseCurrency(totalCost);
            player.getInventory().addItem(item, quantity);
            System.out.println("You bought " + quantity + " " + item.getName() + "(s).");
        } else {
            System.out.println("You don't have enough currency to buy that.");
        }
    }

    /**
     * Allows the player to sell an item to a merchant.
     * Checks if the player has enough of the item before completing the sale.
     * 
     * @param item The item to sell
     * @param quantity The quantity of the item to sell
     * @param pricePerItem The price merchant will pay for each item
     */
    public void sell(Item item, int quantity, int pricePerItem) {
        if (player.getInventory().hasItem(item.getName()) && player.getInventory().getItemQuantity(item) >= quantity) {
            int totalEarnings = pricePerItem * quantity;
            player.increaseCurrency(totalEarnings);
            player.getInventory().removeItem(item, quantity);
            System.out.println("You sold " + quantity + " " + item.getName() + "(s) for " + totalEarnings + " currency.");
        } else {
            System.out.println("You don't have enough " + item.getName() + " to sell.");
        }
    }

    /**
     * Allows the player to trade items with an NPC.
     * Checks if the player has enough of the item before completing the trade.
     * 
     * @param playerItem The item the player is giving
     * @param npcItem The item the player is receiving
     * @param playerItemQuantity The quantity of the player's item
     * @param npcItemQuantity The quantity of the NPC's item
     */
    public void trade(Item playerItem, Item npcItem, int playerItemQuantity, int npcItemQuantity) {
        // Ensure player has enough of the item
        if (player.getInventory().getItemQuantity(playerItem) >= playerItemQuantity) {
            // Remove player's item
            player.getInventory().removeItem(playerItem, playerItemQuantity);
            // Add NPC item to player's inventory
            player.getInventory().addItem(npcItem, npcItemQuantity);

            System.out.println("You traded " + playerItemQuantity + " " + playerItem.getName() + "(s) for " +
                    npcItemQuantity + " " + npcItem.getName() + "(s).");
        } else {
            System.out.println("You don't have enough " + playerItem.getName() + " to trade.");
        }
    }

    /**
     * Attempts to steal an item from an NPC.
     * Success is determined by a random chance that could be influenced by player attributes.
     * 
     * @param item The item to attempt to steal
     */
    public void steal(Item item) {
        // Check success rate based on player attributes (e.g., a random chance)
        int successChance = 50; // Default success chance (can be increased based on player attributes)
        if (Math.random() * 100 < successChance) {
            if (player.getInventory().hasItem(item.getName())) {
                player.getInventory().removeItem(item, 1);
                System.out.println("You successfully stole a " + item.getName() + ".");
            } else {
                System.out.println("Stealing failed. No item found.");
            }
        } else {
            System.out.println("You failed to steal the item.");
        }
    }

    /**
     * Allows the player to attack an NPC.
     * Damage is calculated based on player's weapon power.
     * 
     * @param npc The NPC to attack
     */
    public void attack(NPC npc) {
        if (npc.getHealth() > 0) {
            int attackPower = player.getWeaponPower(); // Assuming player has weapons
            npc.decreaseHealth(attackPower);
            System.out.println("You attacked " + npc.getName() + " for " + attackPower + " damage.");
            if (npc.getHealth() <= 0) {
                System.out.println(npc.getName() + " is defeated.");
            }
        } else {
            System.out.println(npc.getName() + " has already been defeated.");
        }
    }

    /**
     * Initiates a conversation with an NPC.
     * May result in random dialogue or trade opportunities.
     * 
     * @param npc The NPC to talk to
     */
    public void talk(NPC npc) {
        // Example of random dialogue or benefits
        String[] dialogues = {
                "Hello there, traveler! Do you need supplies?",
                "The land ahead is dangerous, be careful.",
                "I'm willing to trade for some of your goods."
        };
        int dialogueIndex = (int) (Math.random() * dialogues.length);
        System.out.println(npc.getName() + " says: " + dialogues[dialogueIndex]);

        // Example of a trade option when talking
        if (Math.random() * 100 < 50) {
            System.out.println("The NPC offers you a trade.");
        } else {
            System.out.println("The NPC does not offer anything this time.");
        }
    }
}

