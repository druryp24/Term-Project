package termProject;

/**
 *
 * Perils Class of the Perils Along the Platte Game
 * Manages various hazards and events that can occur during the journey.
 * Handles accidents, illnesses, river crossings, and other random events.
 *
 * @author : Painter Drury, and Domenic Pilla
 * @version : 1.0
 * @ date : 03/25/2025
 * @ file : perils.java
 */
import java.util.Random;

public class Perils {
    private Player player;  // The player involved in the peril events
    private Random random;  // Random object to simulate chance events

    /**
     * Constructor to initialize the Perils class with a player.
     * 
     * @param player The player who will be affected by perils
     */
    public Perils(Player player) {
        this.player = player;
        this.random = new Random();
    }

    /**
     * Simulates the possibility of an accident occurring.
     * Accidents can damage the wagon, injure oxen, or harm the player.
     */
    public void accident() {
        int chance = random.nextInt(100);
        if (chance < 30) {  // 30% chance of an accident
            System.out.println("An accident occurred!");
            // Example: Randomly choose what is damaged (Wagon, Ox, or Human)
            int event = random.nextInt(3);
            switch (event) {
                case 0:
                    System.out.println("The wagon was damaged! Repairing costs 20 dollars.");
                    player.decreaseCurrency(20);
                    break;
                case 1:
                    System.out.println("One of your oxen was injured! It needs rest.");
                    player.decreaseOxHealth(10);  // Decreases ox health
                    break;
                case 2:
                    System.out.println("You were injured! Lose 5 health.");
                    player.takeDamage(5);  // Decrease player health
                    break;
            }
        }
    }

    /**
     * Simulates the possibility of illness affecting the party.
     * Illnesses can affect humans or oxen, reducing their health.
     */
    public void illness() {
        int chance = random.nextInt(100);
        if (chance < 25) {  // 25% chance of illness
            System.out.println("An illness has struck your group!");
            int event = random.nextInt(2);
            switch (event) {
                case 0:
                    System.out.println("A human member is sick! Lose 10 health.");
                    player.takeDamage(10);  // Decrease player health
                    break;
                case 1:
                    System.out.println("One of your oxen is sick! It loses 10 health.");
                    player.decreaseOxHealth(10);  // Decrease ox health
                    break;
            }
        }
    }

    /**
     * Simulates the possibility of a pregnancy occurring in the party.
     * Pregnancy temporarily reduces health due to the need for rest.
     */
    public void pregnancy() {
        int chance = random.nextInt(100);
        if (chance < 15) {  // 15% chance of pregnancy
            System.out.println("A human member is pregnant! They will need rest.");
            player.takeDamage(10);  // Decreases the human's health due to pregnancy
            System.out.println("The human member's health is temporarily reduced by 10.");
        }
    }

    /**
     * Simulates crossing a river with varying difficulty.
     * River crossings can damage the wagon, requiring repairs.
     */
    public void riverCrossing() {
        int chance = random.nextInt(100);
        if (chance < 50) {  // 50% chance of a difficult river crossing
            System.out.println("You are crossing a river...");
            if (random.nextInt(100) < 50) {
                System.out.println("The wagon got stuck in the river!");
                player.decreaseCurrency(10);  // Costs money to repair the wagon
                System.out.println("Repairing the wagon costs 10 dollars.");
            } else {
                System.out.println("The crossing was successful!");
            }
        } else {
            System.out.println("The river crossing was calm, no problems.");
        }
    }

    /**
     * Simulates issues with supplies including theft, spoilage, and loss.
     * Supply issues can reduce food quantities or cost money.
     */
    public void supplies() {
        int chance = random.nextInt(100);
        if (chance < 20) {  // 20% chance of supply issue
            System.out.println("There was a problem with your supplies!");
            int event = random.nextInt(3);
            switch (event) {
                case 0:
                    // Select the specific food item you want to decrease
                    Food foodToSteal = player.getFoodType("Hay");  // Assuming player has "Hay" in their inventory
                    if (foodToSteal != null) {
                        System.out.println("Some of your supplies were stolen! You lost 10 food.");
                        player.decreaseFood(foodToSteal, 10);  // Decrease the specific food item
                    } else {
                        System.out.println("You don't have any Hay to lose!");
                    }
                    break;
                case 1:
                    // Select the specific food item you want to decrease
                    Food foodToRancid = player.getFoodType("Grain");  // Assuming player has "Grain" in their inventory
                    if (foodToRancid != null) {
                        System.out.println("Your food has gone rancid! You lost 5 food.");
                        player.decreaseFood(foodToRancid, 5);  // Decrease the specific food item
                    } else {
                        System.out.println("You don't have any Grain to lose!");
                    }
                    break;
                case 2:
                    System.out.println("You lost some supplies! You lost 5 dollars.");
                    player.decreaseCurrency(5);  // Lose money
                    break;
            }
        }
    }

    /**
     * Simulates weather events that can impact the journey.
     * Bad weather can spoil food or affect the health of oxen.
     */
    public void weather() {
        int chance = random.nextInt(100);
        if (chance < 30) {  // 30% chance of bad weather
            System.out.println("A weather event is happening!");
            int event = random.nextInt(2);
            switch (event) {
                case 0:
                    System.out.println("A storm has hit! You lose 10 food to spoilage.");
                    player.decreaseFood(10);  // Food spoilage
                    break;
                case 1:
                    System.out.println("Extreme heat has made your oxen lose health!");
                    player.decreaseOxHealth(15);  // Oxen health decreases
                    break;
            }
        }
    }

    private void handleRandomEvent() {
            int eventType = random.nextInt(4);
            switch (eventType) {
                case 0: // Weather event
                    handleWeatherEvent();
                    break;
                case 1: // Health event
                    handleHealthEvent();
                    break;
                case 2: // Wagon damage
                    handleWagonEvent();
                    break;
                case 3: // Resource event
                    handleResourceEvent();
                    break;
            }
        }

    private void handleWeatherEvent() {
        System.out.println("Weather event occurred!");
        int severity = random.nextInt(3); // 0=mild, 1=moderate, 2=severe
        switch (severity) {
            case 0:
                System.out.println("Light rain slows your progress.");
                player.decreaseMovementSpeed(0.8);
                break;
            case 1:
                System.out.println("Heavy storm damages supplies!");
                player.getInventory().damageRandomItem();
                break;
            case 2:
                System.out.println("Severe weather causes significant problems!");
                player.takeDamage(15);
                player.getWagon().damage(20);
                break;
        }
    }

    private void handleHealthEvent() {
        String[] illnesses = {"cholera", "dysentery", "fever", "exhaustion"};
        String illness = illnesses[random.nextInt(illnesses.length)];
        System.out.println("A party member has contracted " + illness + "!");
        player.setIllness(illness);
        player.takeDamage(10);
    }

    private void handleWagonEvent() {
        String[] wagonProblems = {"broken wheel", "cracked axle", "loose tongue"};
        String problem = wagonProblems[random.nextInt(wagonProblems.length)];
        System.out.println("Wagon problem: " + problem);
        player.getWagon().damage(25);
    }

    private void handleResourceEvent() {
        if (random.nextBoolean()) {
            System.out.println("Found abandoned supplies!");
            player.getInventory().addRandomSupplies();
        } else {
            System.out.println("Lost some supplies while crossing rough terrain.");
            player.getInventory().loseRandomSupplies();
        }
    }
}
