package termProject;

/**
 * Landmark Class of the Perils Along the Platte Game
 * Represents significant locations along the trail that players can visit.
 * Contains information about the landmark's position, description, and special features.
 *
 * @author : Alex Randall, Chase McCluskey, Painter Drury, and Domenic Pilla
 * @version : 1.0
 * @date : 03/25/2025
 * @file : landmark.java
 */
public class Landmark extends GameMap {
    private String name;
    private int distance;
    private String description;
    private String historicalInfo;
    private boolean hasRiver;
    private boolean isTradePost;
    private int x;
    private int y;

    /**
     * Constructor for creating a landmark with all attributes.
     * 
     * @param name The name of the landmark
     * @param positionX The X-coordinate of the landmark on the map
     * @param positionY The Y-coordinate of the landmark on the map
     * @param distance The distance from the starting point in miles
     * @param description Brief description of the landmark
     * @param historicalInfo Historical information about the landmark
     * @param hasRiver Whether there's a river at this landmark
     * @param isTradePost Whether this landmark is a trading post
     */
    public Landmark(String name, int positionX, int positionY, int distance, String description, String historicalInfo, boolean hasRiver, boolean isTradePost) {
        super(positionX, positionY);
        this.name = name;
        this.distance = distance;
        this.description = description;
        this.historicalInfo = historicalInfo;
        this.hasRiver = hasRiver;
        this.isTradePost = isTradePost;
    }

    /**
     * Gets the name of the landmark.
     * 
     * @return The landmark name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the distance of this landmark from the start.
     * 
     * @return The distance in miles
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Gets the description of the landmark.
     * 
     * @return The landmark description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets historical information about the landmark.
     * 
     * @return The historical information text
     */
    public String getHistoricalInfo() {
        return historicalInfo;
    }

    /**
     * Checks if the landmark has a river that needs to be crossed.
     * 
     * @return true if there's a river at this landmark, false otherwise
     */
    public boolean hasRiver() {
        return hasRiver;
    }

    /**
     * Checks if the landmark is a trading post where supplies can be bought.
     * 
     * @return true if this is a trading post, false otherwise
     */
    public boolean isTradePost() {
        return isTradePost;
    }
    
    public int getX() {
    	return x;
    }
    
    public int getY() {
    	return y;
    }
    
    /**
     * Gets the position of the landmark on the map.
     * 
     * @return The distance-based position of the landmark
     */
    public int getPosition() {
        return distance;  // Using the distance as the position for landmark checking
    }
}