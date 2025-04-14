package termProject;

/**
 * Weapon Class of the Perils Along the Platte Game
 * Represents weapons that can be used for hunting and protection.
 * Extends the item class with weapon-specific attributes and behaviors.
 *
 * @author : Painter Drury, and Domenic Pilla
 * @version : 1.0
 * @ date : 03/25/2025
 * @ file : weapon.java
 */
public class Weapon extends Item {
    private int power;
    private int accuracy;
    private int ammo;

    /**
     * Constructor for creating a weapon with a specified name and power.
     * Automatically sets power and accuracy based on weapon type.
     * 
     * @param name The name of the weapon
     * @param power The base power value (may be adjusted based on weapon type)
     */
    public Weapon(String name, int power) {
        super(name);
        // Set power and accuracy based on weapon type
        if (name.contains("Rifle")) {
            this.power = 8;
            this.accuracy = 7;
        } else if (name.contains("Pistol")) {
            this.power = 5;
            this.accuracy = 5;
        } else {
            this.power = 3; // Basic weapons like knives
            this.accuracy = 9;
        }
    }

    /**
     * Constructor for creating a weapon with specified name, power, and accuracy.
     * 
     * @param name The name of the weapon
     * @param power The power value of the weapon
     * @param accuracy The accuracy value of the weapon
     */
    public Weapon(String name, int power, int accuracy) {
        super(name);
        this.power = power;
        this.accuracy = accuracy;
    }

    /**
     * Gets the power value of the weapon.
     * 
     * @return The weapon's power
     */
    public int getPower() {
        return power;
    }

    /**
     * Sets the power value of the weapon.
     * 
     * @param power The power value to set
     */
    public void setPower(int power) {
        this.power = power;
    }

    /**
     * Gets the accuracy value of the weapon.
     * 
     * @return The weapon's accuracy
     */
    public int getAccuracy() {
        return accuracy;
    }

    /**
     * Sets the accuracy value of the weapon.
     * 
     * @param accuracy The accuracy value to set
     */
    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    /**
     * Simulates shooting the weapon.
     * Displays information about the weapon being used.
     * 
     * @param weapon The weapon being fired
     */
    public void shoot(Weapon weapon) {
        System.out.println("You shoot the " + weapon.getName() + "!");
        System.out.println("Power: " + weapon.getPower());
        System.out.println("Accuracy: " + weapon.getAccuracy());
    }

    /**
     * Gets the current ammunition count for the weapon.
     * 
     * @return The amount of ammunition
     */
    public int getAmmo() { return ammo;}

    /**
     * Sets the ammunition count for the weapon.
     * 
     * @param ammo The amount of ammunition to set
     */
    public void setAmmo(int ammo) { this.ammo = ammo;}

    /**
     * Returns a string representation of the weapon.
     * Includes name, power, accuracy, and quantity.
     * 
     * @return A string describing the weapon
     */
    public String toString() {
        return getName() + " (Power: " + power + ", Accuracy: " + accuracy + ", Qty: " + getQuantity() + ")";
    }

    /**
     * Displays historical information about the weapon type.
     * Provides educational content about firearms and weapons of the Oregon Trail era.
     */
    public void displayHistoricalInfo() {
        System.out.println("\n=====================================================");
        System.out.println("            HISTORICAL WEAPON INFORMATION            ");
        System.out.println("=====================================================");

        System.out.println("\nFirearms on the Oregon Trail (1840s-1850s):");

        if (getName().contains("Rifle")) {
            System.out.println("\nRifles were essential tools for pioneers, used for hunting and protection.");
            System.out.println("Most common was the muzzle-loading percussion rifle that fired a");
            System.out.println("single shot. Loading required powder, a lead ball, and a percussion cap.");
            System.out.println("\nPopular models included:");
            System.out.println("- Hawken Rifle: Favored by mountain men and skilled hunters");
            System.out.println("- Kentucky/Pennsylvania Long Rifle: Accurate but slow to reload");
            System.out.println("- Model 1841 'Mississippi' Rifle: Used by military and civilians");
        } else if (getName().contains("Pistol")) {
            System.out.println("\nPistols were less common than rifles on the trail, but some");
            System.out.println("emigrants carried them for personal protection. They were less");
            System.out.println("practical for hunting due to limited range and stopping power.");
            System.out.println("\nTypical pistols of the era were:");
            System.out.println("- Colt Paterson: Early five-shot revolver");
            System.out.println("- Colt Walker: Powerful but heavy six-shot revolver");
            System.out.println("- Single-shot percussion pistols");
        } else {
            System.out.println("\nBeside firearms, pioneers carried various edged weapons:");
            System.out.println("- Large knives (Bowie knives) for hunting and utility purposes");
            System.out.println("- Tomahawks or hatchets for chopping wood and self-defense");
            System.out.println("- Basic hunting knives for skinning game and everyday tasks");
        }

        System.out.println("\nHistorical Note: Ammunition was precious and carefully conserved.");
        System.out.println("Many pioneers became skilled at molding their own bullets using");
        System.out.println("lead and bullet molds, as pre-made ammunition was limited.");
    }

    /**
     * Factory method to create a hunting rifle.
     * 
     * @param quantity The quantity of rifles to create
     * @return A new hunting rifle weapon
     */
    public static Weapon createHuntingRifle(int quantity) {
        return new Weapon("Hunting Rifle", 8, 7);
    }

    /**
     * Factory method to create a pistol.
     * 
     * @param quantity The quantity of pistols to create
     * @return A new pistol weapon
     */
    public static Weapon createPistol(int quantity) {
        return new Weapon("Pistol", 5, 5);
    }

    /**
     * Factory method to create a knife.
     * 
     * @param quantity The quantity of knives to create
     * @return A new knife weapon
     */
    public static Weapon createKnife(int quantity) {
        return new Weapon("Hunting Knife", 3, 9);
    }
}