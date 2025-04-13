package termProject;

/**
 * Commands Class of the Perils Along the Platte Game 
 * Processes and executes player commands during gameplay.
 * Handles movement, hunting, trading, checking inventory/status, and using items.
 *
 * @author : Alex Randall, Chase McCluskey, Painter Drury, and Domenic Pilla
 * @version : 1.0
 * @date : 03/25/2025
 * @file : commands.java
 */
import java.util.Scanner;

public class Commands {
    private Player player;
    private inventory inventory;
    private Movement movement;
    private Hunting hunting;
    private Trading trading;
    private Wagon wagon;

    /**
     * Constructor for the commands system.
     * Initializes references to all game components needed for command execution.
     * 
     * @param player The player object
     * @param inventory The inventory management system
     * @param movement The movement system for travel
     * @param hunting The hunting system for food acquisition
     * @param trading The trading system for commerce
     * @param wagon The wagon object for transportation
     */
    public Commands(Player player, inventory inventory, Movement movement, Hunting hunting, Trading trading, Wagon wagon) {
        this.player = player;
        this.inventory = inventory;
        this.movement = movement;
        this.hunting = hunting;
        this.trading = trading;
        this.wagon = wagon;
    }

    /**
     * Processes player input and executes the appropriate command.
     * Parses the input string and routes to specific handlers based on the command.
     * 
     * @param input The command string entered by the player
     */
    public void processCommand(String input) {
        String[] words = input.toLowerCase().split("\\s+"); // Split input into words
        if (words.length == 0) {
            System.out.println("Enter a valid command.");
            return;
        }

        String command = words[0]; // First word is the main command

        switch (command) {
            case "go":
            case "move":
                handleMovement(words);
                break;
            case "hunt":
                hunting.hunt();
                break;
            case "trade":
                trading.initiateTrading();
                break;
            case "check":
                handleCheckCommand(words);
                break;
            case "use":
                handleUseCommand(words);
                break;
            case "climb":
                if (words.length > 1) {
                    System.out.println("You attempt to climb the " + words[1] + ".");
                } else {
                    System.out.println("Climb what?");
                }
                break;
            case "quit":
                System.out.println("Exiting game...");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid command.");
                break;
        }
    }

    /**
     * Handles movement commands by parsing direction and distance.
     * Ensures the command format is valid before initiating travel.
     * 
     * @param words Array of words from the player's command
     */
    private void handleMovement(String[] words) {
        if (words.length < 3) {
            System.out.println("Specify a direction and distance. Example: 'go north 30 miles'");
            return;
        }

        String direction = words[1]; // Second word should be the direction
        int distance;

        try {
            distance = Integer.parseInt(words[2]); // Third word should be the distance
            movement.travelDirection(direction, distance);
        } catch (NumberFormatException e) {
            System.out.println("Invalid distance. Example: 'go north 30 miles'");
        }
    }

    /**
     * Handles check commands for inventory, status, and wagon.
     * Routes to appropriate display methods based on what's being checked.
     * 
     * @param words Array of words from the player's command
     */
    private void handleCheckCommand(String[] words) {
        if (words.length < 2) {
            System.out.println("Check what? (inventory/status/wagon)");
            return;
        }
        switch (words[1]) {
            case "inventory":
                inventory.displayInventory();
                break;
            case "status":
                player.displayStatus();
                break;
            case "wagon":
                wagon.getCondition();
                break;
            default:
                System.out.println("Unknown check command.");
                break;
        }
    }

    /**
     * Handles use commands for items.
     * Ensures an item is specified before attempting to use it.
     * 
     * @param words Array of words from the player's command
     */
    private void handleUseCommand(String[] words) {
        if (words.length < 2) {
            System.out.println("Use what? (food/medicine/tool)");
            return;
        }
        
        String itemName = words[1];
        if (inventory.hasItem(itemName)) {
            Item useItem = inventory.getItemInUse(itemName);
            if (useItem instanceof Food) {
                ((Food) useItem).eat(1);
                player.heal(((Food) useItem).getNutrition());
            } else if (useItem instanceof Medicine) {
                ((Medicine) useItem).useMedicine(player);
            } else {
                System.out.println("You use the " + itemName);
            }
        } else {
            System.out.println("You don't have that item.");
        }
    }

    /**
     * Starts the main command loop for continuous player input.
     * Continuously prompts for and processes commands until quit.
     */
    public void startCommandLoop() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            processCommand(input);
        }
    }
}