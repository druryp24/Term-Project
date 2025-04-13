package termProject;

import java.util.HashMap;
import java.util.*;

public class Movement extends Player {
   private final java.util.Map<String, int[]> directionMap = new HashMap<>();
   private boolean isMoving = true;
   int movementSpeed = 1;
   private final GameMap gameMap;
   private Time gameTime;
   private Player currentPlayer;
   private final Scanner scanner;
   private Wagon playerWagon;
   private final Weather currentWeather;
   public String[] direction = {"north", "south", "east", "west", "northeast", "northwest", "southeast", "southwest"};

    /**
     * Constructor for the movement system.
     * Initializes the directional movement values and required components.
     * 
     * @param map The game map on which movement occurs
     */
    public Movement(GameMap map) {
        super();
        currentWeather  = new Weather();
        this.gameMap = map;
        this.scanner = new Scanner(System.in);

        directionMap.put("north", new int[]{0, 1});
        directionMap.put("south", new int[]{0, -1});
        directionMap.put("east", new int[]{1, 0});
        directionMap.put("west", new int[]{-1, 0});
        directionMap.put("northeast", new int[]{1, 1});
        directionMap.put("northwest", new int[]{-1, 1});
        directionMap.put("southeast", new int[]{1, -1});
        directionMap.put("southwest", new int[]{-1, -1});
    }

    /**
     * Initiates travel in a specified direction for a set distance.
     * Allows the player to stop movement by typing 'stop'.
     * 
     * @param direction The compass direction to travel (north, south, east, west, etc.)
     * @param distance The number of miles to travel
     */
    public void travelDirection(String direction, int distance) {
    	double multiplier = 1.0;
    	
    	 if (!directionMap.containsKey(direction.toLowerCase())) {
             System.out.println("Invalid direction: " + direction);
             return;

         }
         
         // Wagon condition effects
        /* commented out for testing
         if (playerWagon.getCondition() < 50) {
             multiplier *= 0.8;
         }
         */
         
         // Weather conditions
         if (currentWeather.isRaining()) {
             multiplier *= 0.6;
         }
         if (currentWeather.isSnowing()) {
             multiplier *= 0.4;
         }
         
         // Vehicle and animal health
        /* commented out for testing
         if (playerWagon.getCondition() < 30) {
             multiplier *= 0.5;
         }
         if (currentPlayer.getOxenHealth() < 50) {
             multiplier *= 0.7;
         }
         */
       
         System.out.println("You moved " + distance + " miles.");

        int actualDistance = calculateActualDistance(distance);
        
        // comomented out for testing - gameTime.advanceTime(actualDistance / 10); // Advance time based on distance
        // commonted out for testeing - currentPlayer.increaseFatigue(actualDistance * 0.1);
        // commonted out for testing playerWagon.updateCondition(actualDistance * 0.05);
        
        // Update position based on direction
        switch(direction.toLowerCase()) {
            case "north":
                gameMap.updatePosition(0, -actualDistance);
                break;
            case "south":
                gameMap.updatePosition(0, actualDistance);
                break;
            case "east":
                gameMap.updatePosition(actualDistance, 0);
                break;
            case "west":
                gameMap.updatePosition(-actualDistance, 0);
                break;
        }
        
        handleRandomEvent();
    }

    private int calculateActualDistance(int intendedDistance) {
        double multiplier = 1.0;
        
        // Weather effects
        if (currentWeather.getCurrentWeather().equals("Rainy")) {
            multiplier *= 0.7;
        }
        return (int)(intendedDistance * multiplier);
    }
          
    public void getDirection()
    {
    	System.out.println("What direction would you like to go? \n You can choose from the follwing inputs ");
    	for(String s: direction)
    	{
    		System.out.println( s);
    	}
    	Scanner input = new Scanner(System.in);
    	String direction = input.nextLine();
    	System.out.println("How far would you like to go?");
    	int distance = input.nextInt();
    	input.nextLine();
    	travelDirection(direction,distance);
    }

    public void handleRandomEvent() {
        // Random event logic will be implemented here
        int eventChance = (int)(Math.random() * 100);
        if (eventChance < 20) {
            // Implement random events
        }
    }
}
