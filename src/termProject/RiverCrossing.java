package termProject;

/**
 * RiverCrossing Class of the Perils Along the Platte Game
 * Manages river crossing events during the journey.
 * Handles the risks, costs, and options for crossing rivers.
 *
 * @author : Alex Randall, Chase McCluskey, Painter Drury, and Domenic Pilla
 * @version : 1.0
 * @date : 03/25/2025
 * @file : riverCrossing.java
 */
public class RiverCrossing extends RandomEvent {
    private static final int baseWidth = 50;
	private static final int baseDepth = 100;
	private Weather currentWeather;
	private Inventory inventory;

	public void handleCrossing(Player player, Wagon wagon) {
        double depth = calculateRiverDepth();
        int width = calculateRiverWidth();
        
        if (depth > 3 || width > 100) {
            // Dangerous crossing
            if (Math.random() < 0.3) {
                wagon.updateCondition(-20);
                player.takeDamage(10, "Drowning during river crossing");
            }
        }
    }

    /**
     * Calculates the current river depth based on weather conditions.
     * 
     * @return The calculated river depth in feet
     */
    private double calculateRiverDepth() {
        double depth = this.baseDepth;
        
        // Weather affects river depth
        if (this.currentWeather.isRaining()) {
            depth += 2;
        }
        if (this.currentWeather.isSnowing()) {
            depth += 1;
        }
        
        // Random variation
        depth += (Math.random() * 3);
        
        return depth;
    }

    /**
     * Calculates the current river width based on weather conditions.
     * 
     * @return The calculated river width in feet
     */
    private int calculateRiverWidth() {
        int width = this.baseWidth;
        
        // Weather affects river width
        if (this.currentWeather.isRaining()) {
            width += 20;
        }
        
        // Random variation
        width += (int)(Math.random() * 30);
        
        return width;
    }
    
    /**
     * Attempts to ford the river with the given player and wagon.
     * Success depends on river depth, weather conditions, and wagon health.
     * 
     * @param player The player attempting to ford the river
     * @param wagon The wagon being used to cross
     * @return true if successfully crossed, false if failed
     */
    public boolean fordRiver(Player player, Wagon wagon) {
        // Get current river conditions
        double riverDepth = calculateRiverDepth();
        int suppliesLostCounter = 0;
        
        // Base chance of success (out of 100)
        int successChance = 70;
        
        // Adjust for river depth (deeper = more dangerous)
        if (riverDepth > 5.0) {
            successChance -= 30; // Very deep river
        } else if (riverDepth > 3.0) {
            successChance -= 15; // Moderately deep
        }
        
        // Adjust for weather
        if (riverDepth > 20) {
            successChance -= 20;
        }
        
        // Adjust for wagon condition
        double wagonHealth = wagon.getCondition();
        if (wagonHealth < 50) {
            successChance -= 15; // Damaged wagon is riskier
        }
        
        // Random check for success
        boolean success = (Math.random() * 100) < successChance;
        
        // Handle the consequences of failure
        if (!success) {
            // Lose some supplies
            int suppliesLost = (int)(Math.random() * 10) + 5; // Lose between 5-15 supplies
            while(suppliesLostCounter < suppliesLost) {
                inventory.loseRandomSupplies();
                suppliesLostCounter++;
            }
            
            // Damage the wagon
            int wagonDamage = (int)(Math.random() * 20) + 10; // 10-30 damage
            wagon.damage(wagonDamage);
            
            // Hurt the player
            int healthLost = (int)(Math.random() * 15) + 5; // Lose between 5-20 health
            player.takeDamage(healthLost);
        }
        
        return success;
    }
}
