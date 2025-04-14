package termProject;

/**
 * Map Class of the Perils Along the Platte Game
 * Represents the game world map with landmarks, distances, and trail information.
 * Manages player position, travel progress, and geographic features along the trail.
 *
 * @author : Painter Drury, and Domenic Pilla
 * @version : 1.0
 * @ date : 03/25/2025
 * @ file : map.java
 */
import java.util.*;
public class GameMap {
    private String trailType;
    private String startLocation;
    private String endLocation = "Independence Rock"; // or somewhere between there and ft. laramie
    private int totalDistance;
    private int currentDistanceTraveled = 0;

    private int currentLandmarkIndex;

    private static ArrayList<Landmark> landmarks1 = new ArrayList<>();
    private static int playerX;
    private static int playerY;
    private Landmark landmark;

    /**
     * Constructor for the map with initial player coordinates.
     * 
     * @param startX The starting X-coordinate for the player
     * @param startY The starting Y-coordinate for the player
     *
     */
    public GameMap(int startX, int startY) {
        playerX = startX;
        playerY = startY;
    }

    /**
     * Gets the player's current X-coordinate on the map.
     * 
     * @return The player's X-coordinate
     */
    public int getPlayerX() {
        return playerX;
    }

    /**
     * Gets the player's current Y-coordinate on the map.
     * 
     * @return The player's Y-coordinate
     */
    public int getPlayerY() {
        return playerY;
    }
    
    public int getX(int landmarkIndex) {
        if (landmarkIndex >= 0 && landmarkIndex < landmarks1.size()) {
            return landmarks1.get(landmarkIndex).getX();  // Assuming landmark class has a getX() method
        }
        return -1;  // Return -1 if index is out of bounds
    }

    public int getY(int landmarkIndex) {
        if (landmarkIndex >= 0 && landmarkIndex < landmarks1.size()) {
            return landmarks1.get(landmarkIndex).getY();  // Assuming landmark class has a getY() method
        }
        return -1;  // Return -1 if index is out of bounds
    }
    
    /**
     * Updates the player's position on the map.
     * 
     * @param dx The change in X-coordinate
     * @param dy The change in Y-coordinate
     */
    public void updatePosition(int dx, int dy){
        playerX += dx;
        playerY += dy;
    }
    /**
     * Sets the trail type and starting location for the journey.
     * Initializes landmarks and distances based on the selected trail.
     * 
     * @param trailType The type of trail ("Oregon", "California", or "Mormon")
     * @param startLocation The starting location name
     */
    public void setTrail(String trailType, String startLocation) {
        this.trailType = trailType;
        this.startLocation = startLocation;
        this.currentDistanceTraveled = 0;
        this.currentLandmarkIndex = 0;

        if (trailType.equals("Oregon")) {
            totalDistance = 830;
            initializeOregonTrail();
        } else if (trailType.equals("California")) {
            totalDistance = 830;
            initializeCaliforniaTrail();
        } else if (trailType.equals("Mormon")) {
            totalDistance = 965;
            initializeMormonTrail();
        }
    }

    /**
     * Displays the starting point information to the player.
     */
    public void startingPoint() {
        System.out.println("You are starting your journey at " + startLocation + ".");
    }

    /**
     * Displays the ending point information to the player.
     */
    public void endingPoint() {
        System.out.println("Your destination is " + endLocation + ".");
    }
    
    public void makeMarket(String name, int positionX, int positionY, int distance, String description, String historicalInfo, boolean hasRiver, boolean isTradePost) {
    	Landmark market = new Landmark(name, positionX, positionY, distance, description, historicalInfo, false, false);
    	landmarks.add(market);
    }
    
    public boolean isAtTradePost(int x, int y) {
    	for(Landmark lm : landmarks) {
    		if(lm.getX() == x && lm.getY() == y && lm.isTradePost()) {
    			return true;
    		}
    	}
    	return false;
    }
    
    //Change positions of the landmarks later(adjust to proper location on the map
    /**
     * Initializes the landmarks for the Oregon Trail.
     * Creates landmarks with their locations, distances, and descriptions.
     */
    public static void initializeOregonTrail()
    {
        landmarks1.add(new Landmark("Independence, Missouri", 0, 0, 0, "Starting point for many westward journeys.",
                "This busy frontier town serves as the main outfitting point for wagon trains. Shops are selling everything from wagons and oxen to food supplies at premium prices.",
                false, true));

        landmarks1.add(new Landmark("Kansas River Crossing", 40, 40, 83, "A challenging river that must be forded, ferried, or crossed by raft.",
                "This river is approximately 200 feet wide and up to 20 feet deep in places. Crossing it poses your first major challenge of the journey.",
                true, false));

        landmarks1.add(new Landmark("Fort Kearny", 150, 150, 304, "Military post established to protect travelers on the Oregon Trail.",
                "Established in 1848, this fort provides protection, supplies, mail service, and repairs for emigrants. It's your first chance to resupply since Independence.",
                false, true));

        landmarks1.add(new Landmark("Chimney Rock", 250, 250, 554, "Distinctive rock formation that served as an important landmark.",
                "This unusual natural formation rises nearly 300 feet and was mentioned in many pioneer journals. It meant travelers were making good progress.",
                false, false));

        landmarks1.add(new Landmark("Fort Laramie", 320, 320, 640, "Major stopping point for pioneers to rest and resupply.",
                "This former fur trading post became a military fort in 1849. It offers protection, supplies, and much-needed rest for weary travelers.",
                false, true));

        landmarks1.add(new Landmark("Independence Rock", 415, 415, 830, "Travelers tried to reach here by July 4th (Independence Day). This is your final destination.",
                "This large granite rock contains thousands of pioneer names carved into its surface. Reaching it means you have successfully completed your journey along the Platte River portion of the Oregon Trail.",
                false, true));
    }
    
    //Change positions of the landmarks later(adjust to proper location on the map
    /**
     * Initializes the landmarks for the California Trail.
     * Creates landmarks with their locations, distances, and descriptions.
     */
    public static void initializeCaliforniaTrail() {
        landmarks1 = new ArrayList<>();

        landmarks1.add(new Landmark("Independence, Missouri", 0, 0, 0, "Starting point for many westward journeys.",
                "This busy frontier town serves as the main outfitting point for wagon trains. Shops are selling everything from wagons and oxen to food supplies at premium prices.",
                false, true));

        landmarks1.add(new Landmark("Fort Kearny", 150, 150, 304, "Military post on the Platte River.",
                "This fort provides protection and supplies for travelers, as well as mail service to send letters back east.",
                false, true));

        landmarks1.add(new Landmark("Chimney Rock", 250, 250, 554, "Distinctive rock formation visible for miles.",
                "Rising nearly 300 feet above the North Platte River valley, this landmark let pioneers know they were on the right path.",
                false, false));

        landmarks1.add(new Landmark("Fort Laramie", 320, 320, 640, "Trading post and military fort.",
                "This fort provides a chance to rest, resupply, and repair equipment before the more difficult terrain ahead.",
                false, true));

        landmarks1.add(new Landmark("Independence Rock", 415, 415, 830, "Travelers tried to reach here by July 4th. This is your final destination.",
                "This large granite rock contains thousands of pioneer names carved into its surface. Reaching Independence Rock means you have successfully completed your journey along the Platte River portion of the California Trail.",
                false, true));
    }
    
    //Change the landmark locations to be the proper locations on the map
    /**
     * Initializes the landmarks for the Mormon Trail.
     * Creates landmarks with their locations, distances, and descriptions.
     */
    public static void initializeMormonTrail() {
        landmarks1 = new ArrayList<>();

        landmarks1.add(new Landmark("Nauvoo, Illinois", 0, 0, 0, "Starting point for Mormon pioneers fleeing religious persecution.",
                "Once a thriving city of 12,000 Mormons, Nauvoo has been largely abandoned after persecution and the murder of church leader Joseph Smith in 1844.",
                false, false));

        landmarks1.add(new Landmark("Sugar Creek", 3, 3, 7, "First camp across the Mississippi, where Brigham Young organized the exodus.",
                "This temporary settlement became the gathering place for Mormons after crossing the frozen Mississippi River in February 1846.",
                false, false));

        landmarks1.add(new Landmark("Garden Grove", 64, 64, 128, "First permanent settlement established to help future travelers.",
                "Mormon pioneers created this settlement to plant crops that would help later emigrants following the same route.",
                false, false));

        landmarks1.add(new Landmark("Mount Pisgah", 73, 79, 153, "Second way station with farms to supply later pioneers.",
                "Like Garden Grove, this settlement was established to grow food for future Mormon emigrants on their journey west.",
                false, false));

        landmarks1.add(new Landmark("Council Bluffs", 135, 135, 265, "Winter quarters where many Mormons stayed during 1846-1847.",
                "This settlement on the east bank of the Missouri River became a major staging area for the Mormon migration west.",
                true, false));

        landmarks1.add(new Landmark("Winter Quarters", 233, 233, 266, "Settlement in present-day Omaha where many died during the harsh winter.",
                "Over 700 temporary log cabins housed Mormons during the difficult winter of 1846-47. Disease claimed many lives here.",
                false, false));

        landmarks1.add(new Landmark("Fort Kearny", 250, 250, 493, "Military post where trails converged.",
                "Here the Mormon Trail joins the Oregon Trail. The fort provides protection and supplies for travelers.",
                false, true));

        landmarks1.add(new Landmark("Chimney Rock", 350, 350, 718, "Notable landmark visible for miles.",
                "This distinctive rock formation was a welcome sight indicating good progress on the journey west.",
                false, false));

        landmarks1.add(new Landmark("Fort Laramie", 390, 390, 788, "Trading post and military fort.",
                "This fort provides a chance to rest, resupply, and repair equipment before the more difficult terrain ahead.",
                false, true));

        landmarks1.add(new Landmark("Independence Rock", 490, 490, 965, "Large granite outcropping where travelers carved their names. This is your final destination.",
                "Many Mormon pioneers added their names to thousands of others on this 'register of the desert.' Reaching Independence Rock means you have successfully completed your journey along the Platte River portion of the Mormon Trail.",
                false, true));
    }

    /**
     * Gets the name of the current location.
     * 
     * @return The name of the current landmark
     */
    // Add these fields at the class level
    private int currentPosition = 0;
    private ArrayList<Landmark> landmarks;
    private String currentTrail;
    
    /**
     * Gets the current landmark based on position.
     * 
     * @return The current landmark or null if not at a landmark
     */
    public Landmark getCurrentLandmark1() {
        for (Landmark l : landmarks1) {
            if (Math.abs(currentPosition - l.getDistance()) < 5) {
                return l;
            }
        }
        return null;
    }

    /**
     * Updates the current position on the map.
     * 
     * @param distance The distance to move
     */
    public void updatePosition(int distance) {
        currentPosition += distance;
        if (currentPosition < 0) {
            currentPosition = 0;
        }
    }

    /**
     * Gets the total distance traveled so far.
     * 
     * @return The current distance traveled in miles
     */
    public int getCurrentDistanceTraveled(){
        return currentDistanceTraveled;
    }

    /**
     * Gets the name of the current landmark.
     * 
     * @return The name of the current landmark or "Unknown location" if none
     */
    public String getCurrentLandmark() {
        if (currentLandmarkIndex < landmarks1.size()) {
            return landmarks1.get(currentLandmarkIndex).getName();
        }
        return "Unknown location";
    }

    /**
     * Calculates the remaining distance to the final destination.
     * 
     * @return The remaining distance in miles
     */
    public int getRemainingDistance() {
        if (currentLandmarkIndex < landmarks1.size()) {
            return totalDistance - landmarks1.get(currentLandmarkIndex).getDistance();
        }
        return 0;
    }

    /**
     * Gets the total distance of the selected trail.
     * 
     * @return The total trail distance in miles
     */
    public int getTotalDistance() {
        return totalDistance;
    }

    /**
     * Updates the player's location based on distance traveled.
     * 
     * @param distanceTraveled The distance traveled to add to current position
     */
    public void updateLocation(int distanceTraveled) {
        updateLocationToDistance(distanceTraveled);
    }

    /**
     * Gets the type of trail being traveled.
     * 
     * @return The trail type ("Oregon", "California", or "Mormon")
     */
    public String getTrailType() {
        return trailType;
    }

    /**
     * Checks if a new landmark has been reached after traveling a distance.
     * 
     * @param distanceTraveled The distance traveled
     * @return true if a new landmark was reached, false otherwise
     */
    public boolean checkLandmark(int distanceTraveled) {
        int previousIndex = currentLandmarkIndex;
        updateLocationToDistance(distanceTraveled);
        return currentLandmarkIndex > previousIndex;
    }

    /**
     * Gets the name of the landmark at the specified index.
     * 
     * @param index The index of the landmark in the landmarks list
     * @return The name of the landmark or null if index is invalid
     */
    public String getLandmarkAtIndex(int index) {
        if (index >= 0 && index < landmarks1.size()) {
            return landmarks1.get(index).getName();
        }
        return null;
    }

    /**
     * Gets the distance of the landmark at the specified index.
     * 
     * @param index The index of the landmark in the landmarks list
     * @return The distance of the landmark or -1 if index is invalid
     */
    public int getLandmarkDistanceAtIndex(int index) {
        if (index >= 0 && index < landmarks1.size()) {
            return landmarks1.get(index).getDistance();
        }
        return 0;
    }

    /**
     * Checks if the player has reached the final destination.
     * 
     * @return true if the final landmark has been reached, false otherwise
     */
    public boolean hasReachedNewLandmark() {
        for (Landmark l : landmarks1) {
            if (Math.abs(currentPosition - l.getPosition()) < 5) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the total number of landmarks on the current trail.
     * 
     * @return The size of the landmarks array
     */
    public int getLandmarkArraySize() {
        return landmarks1.size();
    }
    
    public ArrayList<Landmark> getLandmarks(){
    	return landmarks1;
    }
    /**
     * Updates the player's location to reflect traveling a specified distance.
     * Updates the current landmark index if new landmarks are reached.
     * 
     * @param distanceTraveled The total distance traveled so far
     */
    public void updateLocationToDistance(int distanceTraveled) {
        int newDistance = currentDistanceTraveled + distanceTraveled;

        if(newDistance > totalDistance){
            newDistance = totalDistance;
        }

        currentDistanceTraveled = newDistance;

        for(int i = currentLandmarkIndex; i < landmarks1.size(); i++){
            if(landmarks1.get(i).getDistance() <= currentDistanceTraveled){
                currentLandmarkIndex = i;
            }
            else{
                break;
            }
        }
    }

    /**
     * Calculates the distance to the next landmark from the current position.
     * 
     * @return The distance to the next landmark in miles
     */
    public int getDistanceToNextLandmark() {
        if (currentLandmarkIndex < landmarks1.size() - 1) {
            int currentDistance = landmarks1.get(currentLandmarkIndex).getDistance();
            int nextDistance = landmarks1.get(currentLandmarkIndex + 1).getDistance();
            return nextDistance - currentDistance;
        }
        return totalDistance - landmarks1.get(currentLandmarkIndex).getDistance();
    }

    /**
     * Displays information about the current landmark to the player.
     * Includes the landmark name, description, and other relevant details.
     */
    public void displayLandmarkInformation() {
        if (currentLandmarkIndex < landmarks1.size()) {
            Landmark current = landmarks1.get(currentLandmarkIndex);
            System.out.println("\n=====================================================");
            System.out.println("                  " + current.getName());
            System.out.println("=====================================================");
            System.out.println("\n" + current.getDescription());

            System.out.println("\nHistorical Information:");
            System.out.println(current.getHistoricalInfo());

            if (current.hasRiver()) {
                System.out.println("\nThere is a river here that you'll need to cross.");
            }
        }
    }
}
