package termProject;

/**
 * Hunting Class of the Perils Along the Platte Game
 * Manages hunting mechanics to acquire food during the journey.
 * Handles weapon use, ammunition, and food acquisition through hunting activities.
 *
 * @author : Alex Randall, Chase McCluskey, Painter Drury, and Domenic Pilla
 * @version : 1.0
 * @date : 03/25/2025
 * @file : hunting.java
 */
import java.util.Random;

public class Hunting extends RandomEvent {

        private Player player;
        private Random random;

        /**
         * Constructor for the hunting system.
         * Initializes hunting with the player who will be hunting.
         * 
         * @param player The player object that will perform hunting actions
         */
        public Hunting(Player player) {
            this.player = player;
            this.random = new Random();
        }

        /**
         * Initiates a hunting session to attempt to acquire food.
         * Requires the player to have a weapon and ammunition.
         * Success depends on weapon type and random chance.
         */
        public void hunt() {
            if (!player.getInventory().hasItem("weapon")) {
                System.out.println("You have no weapon to hunt with.");
                return;
            }

            Item weapon = player.getInventory().getItemInUse("weapon");
            Item ammo = player.getInventory().getItemInUse("ammo");

            if (ammo == null || player.getInventory().getItemQuantity(ammo) == 0) {
                System.out.println("You have no ammunition left!");
                return;
            }

            int ammoUsed = 1;  // Adjust as needed for different weapon types
            player.getInventory().removeItem(ammo, ammoUsed);

            int successChance = 50 + (weapon.getName().equalsIgnoreCase("rifle") ? 20 : 0); // Rifle increases chance
            boolean successfulHunt = random.nextInt(100) < successChance;

            if (successfulHunt) {
                int foodGained = random.nextInt(10) + 5; // Random amount of food
                Item food = new Item("food");
                player.getInventory().addItem(food, foodGained);
                System.out.println("You successfully hunted and gained " + foodGained + " lbs of food!");
            } else {
                System.out.println("You missed! No food gained.");
            }
        }
}
