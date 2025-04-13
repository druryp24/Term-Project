package termProject;

/**
 * Medicine Class of the Perils Along the Platte Game
 * Represents medical items that can heal players and companions.
 * Extends the item class with medicine-specific healing abilities.
 *
 * @author : Alex Randall, Chase McCluskey, Painter Drury, and Domenic Pilla
 * @version : 1.0
 * @date : 03/25/2025
 * @file : medicine.java
 */
public class Medicine extends Item {
    private int healingPower;
    private int price;
    
    /**
     * Constructor for creating a medicine item.
     * Automatically sets healing power based on medicine type.
     * 
     * @param name The name of the medicine
     * @param quantity The quantity of medicine
     */
    public Medicine(String name, int weight, int value, int maxQuantity) {
        super(name, weight, 1, value, maxQuantity); // Added value and maxQuantity parameters
        this.healingPower = 20; // Default healing power
    }

    /**
     * Uses medicine on a player to restore health.
     * Displays healing information to the console.
     * 
     * @param player The player to heal
     */
    public void useMedicine(Player player) {
        System.out.println("Using " + this.getName() + " on " + player.getName() + ".");

        player.heal(healingPower);

        System.out.println("Healed " + player.getName() + " for " + healingPower + " points.");
        System.out.println("New health: " + player.getHealth());
    }

    /**
     * Gets the healing power of the medicine.
     * 
     * @return The healing amount
     */
    public int getHealingPower() {
        return healingPower;
    }

    /**
     * Sets the healing power of the medicine.
     * 
     * @param healingPower The new healing amount
     */
    public void setHealingPower(int healingPower) {
        this.healingPower = healingPower;
    }

    /**
     * Returns a string representation of the medicine.
     * Includes name, healing power, and quantity.
     * 
     * @return A string describing the medicine
     */
    public String toString() {
        return getName() + " (Healing: " + healingPower + ", Qty: " + getQuantity() + ")";
    }

    /**
     * Displays historical information about medicine on the Oregon Trail.
     * Provides educational content about 19th century medical practices.
     */
    public void displayHistoricalMedicalInfo() {
        System.out.println("\n=====================================================");
        System.out.println("          HISTORICAL MEDICINE ON THE TRAIL           ");
        System.out.println("=====================================================");

        System.out.println("\nMedical care in the 1840s-1850s was primitive by today's standards:");
        System.out.println("- Few trained doctors were available on the trail");
        System.out.println("- Folk remedies and herbal medicines were commonly used");
        System.out.println("- Bloodletting was still practiced for many ailments");
        System.out.println("- Laudanum (opium tincture) was used for pain relief");
        System.out.println("- Diseases like cholera, dysentery, and typhoid were common and deadly");
        System.out.println("- Injuries from accidents were often fatal due to infection");

        System.out.println("\nCommon medicines carried by pioneers:");
        System.out.println("- Quinine (for malaria and fevers)");
        System.out.println("- Calomel (mercury chloride, used as a purgative)");
        System.out.println("- Epsom salts (for various digestive issues)");
        System.out.println("- Peppermint oil (for stomach ailments)");
        System.out.println("- Camphor (for skin conditions and as a disinfectant)");

        System.out.println("\nAn estimated 1 in 10 emigrants died during the journey west,");
        System.out.println("with disease being the most common cause of death.");
    }

    /**
     * Factory method to create basic medicine items.
     * 
     * @param quantity The quantity of medicine to create
     * @return A new basic medicine item
     */
    public static Medicine createBasicMedicine(int quantity) {
        return new Medicine("Basic Medicine", 1, quantity, 5);
    }

    /**
     * Factory method to create advanced medicine items.
     * 
     * @param quantity The quantity of medicine to create
     * @return A new advanced medicine item
     */
    public static Medicine createAdvancedMedicine(int quantity) {
        return new Medicine("Advanced Medicine", 5, quantity, 2);
    }
}