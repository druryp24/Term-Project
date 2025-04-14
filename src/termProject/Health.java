package termProject;

/**
 * Health Class of the Perils Along the Platte Game
 * Manages health status for players, companions, and animals.
 * Handles damage, healing, and health-related game mechanics.
 *
 * @author : Painter Drury, and Domenic Pilla
 * @version : 1.0
 * @ date : 03/25/2025
 * @ file : health.java
 */
public class Health {
    int maxHealth = 100;
    int currentHealth;

    /**
     * Constructor for creating a health object with specified starting health.
     * 
     * @param startingHealth The initial health value
     */
    public Health(int startingHealth){
        maxHealth = startingHealth;
        currentHealth = startingHealth;
    }

    /**
     * Increases health by the specified amount.
     * Health will not exceed the maximum health value.
     * 
     * @param addHealth The amount of health to add
     */
    public void heal(int addHealth){
        currentHealth += addHealth;
        if(currentHealth > maxHealth){
            currentHealth = maxHealth;
        }
    }

    /**
     * Reduces health by the specified amount due to a specific cause.
     * Health will not go below zero.
     * 
     * @param damage The amount of damage to apply
     * @param cause The cause of the damage
     */
    public void takeDamage(int damage, String cause){
        currentHealth -= damage;
        if(currentHealth <= 0) currentHealth = 0;
    }

    /**
     * Checks if the entity is currently alive.
     * 
     * @return true if health is greater than zero, false otherwise
     */
    public boolean isAlive(){
        return currentHealth > 0;
    }

    /**
     * Gets the current health value.
     * 
     * @return The current health value
     */
    public int getCurrentHealth(){
        return currentHealth;
    }

    /**
     * Gets the maximum possible health value.
     * 
     * @return The maximum health value
     */
    public int getMaxHeath(){
        return maxHealth;
    }
}
