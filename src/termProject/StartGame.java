package termProject;

import java.util.ArrayList; 
import java.util.Scanner;

/**
 * StartGame Class of the Perils Along the Platte Game
 * Controls the initialization, progression, and main gameplay loop of the Oregon Trail-style game.
 * Manages player creation, trail selection, game mechanics, and narrative elements.
 *
 * @author : Alex Randall, Chase McCluskey, Painter Drury, and Domenic Pilla
 * @version : 1.0
 * @date : 03/25/2025
 * @file : startGame.java
 */
public class StartGame {
    private Player player;
    private GameMap gameMap;
    private Wagon wagon;
    private inventory inventory;
    private Currency money;
    private Hunting hunting;
    private Time gameTime;
    private Weather weather;
    private boolean isGameRunning = true;
    private Scanner scanner = new Scanner(System.in);
    
    private ArrayList<String> checkpoints = new ArrayList<>();
    private final int WINNING_DISTANCE = 2000; // Distance to Independence Rock
    
    String[] months = {"March", "April", "May", "June", "July"};
	int monthChoice = 0;
	
	private int totalTrailDistance;
	private int daysTraveled;
	private int distanceTraveled;
	private static String trail;
	private static String departureLocation;
	private static String departureMonth;

    public StartGame() {
        initializeGame();
        gameLoop();
    }

    private void initializeGame() {
        // Initialize player and components
        player = new Player("Player", "Male", 100);
        inventory = new inventory();
        wagon = new Wagon();
        gameMap = new GameMap(0, 0);
        money = new Currency(1000); // Starting money
        hunting = new Hunting(player);
        gameTime = new Time();

        // Add starting supplies
        inventory.addItem(new Food("Rations", 1, 50), 100);
        inventory.addItem(new Weapon("Rifle", 5), 1);
        inventory.addItem(new Item("Ammunition", 1), 50);
        inventory.addItem(new Medicine("Basic Medicine", 1, 1, 5), 5);

        displayIntroduction();
    }


    private void gameLoop() {
        Commands commandSystem = new Commands(player, inventory, new Movement(gameMap), hunting, new Trading(player, null, null), wagon);
        
        while (isGameRunning) {
            // Check win/lose conditions
            if (checkGameOver()) {
                break;
            }

            // Process daily events
            processDailyEvents();

            // Update game state
            updateGameState();

            // Process player commands
            commandSystem.startCommandLoop();
        }
    }

    /**
     * Prompts the player to select their character's gender and name.
     * Gender choice affects certain encounters and situations in the game.
     */
    public void selectPlayerGender() {
        System.out.println("\n=====================================================");
        System.out.println("                CHARACTER SELECTION                  ");
        System.out.println("=====================================================");
        System.out.println("\nIn the 1800s, men and women faced different challenges");
        System.out.println("on the trail west. Your choice will affect some of the");
        System.out.println("situations you encounter.");

        boolean validChoice = false;
        while (!validChoice) {
            System.out.println("\nSelect your character's gender:");
            System.out.println("1. Male");
            System.out.println("2. Female");
            System.out.print("\nEnter your choice (1-2): ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                player.setGender("male");
                validChoice = true;
            } else if (choice.equals("2")) {
                player.setGender("female");
                validChoice = true;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        validChoice = false;
        while (!validChoice) {
            System.out.println("\nEnter your character's name:");
            String name = scanner.nextLine();

            if (!(name.equals(null))) {
                player.setName(name);
                validChoice = true;
            } else {
                System.out.println("Invalid name. Please try again.");
            }
        }
    }

    /**
     * Allows the player to choose which historical trail to follow.
     * Each trail has different distances, destinations, and challenges.
     */
    public void selectTrail() {
        
        System.out.println("\n=====================================================");
        System.out.println("                   TRAIL SELECTION                   ");
        System.out.println("=====================================================");

        System.out.println("\nThere were several major routes west. Each trail had");
        System.out.println("different destinations, terrain, and challenges:");

        System.out.println("\n1. Oregon Trail (2,170 miles)");
        System.out.println("   Destination: Oregon's Willamette Valley");
        System.out.println("   Historical Note: Most popular route for farmers seeking fertile land");

        System.out.println("\n2. California Trail (1,950 miles)");
        System.out.println("   Destination: California's gold fields and farmlands");
        System.out.println("   Historical Note: Became heavily traveled after the 1848 Gold Rush");

        System.out.println("\n3. Mormon Trail (1,300 miles)");
        System.out.println("   Destination: Salt Lake Valley, Utah");
        System.out.println("   Historical Note: Used by Mormon pioneers fleeing religious persecution");

        boolean validChoice = false;
        
        while (!validChoice) {
            System.out.print("\nWhich trail would you like to take? (1-3): ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    trail = "Oregon";
                    departureLocation = "Independence, Missouri";
                    validChoice = true;
                    break;
                case "2":
                    trail = "California";
                    departureLocation = "Independence, Missouri";
                    validChoice = true;
                    break;
                case "3":
                    trail = "Mormon";
                    departureLocation = "Nauvoo, Illinois";
                    validChoice = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        gameMap.setTrail(trail, departureLocation);

        System.out.println("\nYou have chosen to travel along the " + trail + " Trail.");
        System.out.println("Your journey will begin in " + departureLocation + ".");
    }

    /**
     * Prompts the player to select which month to depart on their journey.
     * The departure month affects weather conditions, trail status, and available time.
     */
    public void selectDepartureMonth() {
        int weatherMonthSet = 3;
        
        System.out.println("\n=====================================================");
        System.out.println("              DEPARTURE MONTH SELECTION              ");
        System.out.println("=====================================================");

        System.out.println("\nThe timing of your departure was crucial for pioneers.");
        System.out.println("Leave too early: face mud and flooding from spring rains.");
        System.out.println("Leave too late: risk being trapped in mountain snow.");
        System.out.println("\nMost emigrants departed between April and June.");

        System.out.println("\nSelect your departure month:");
        for (int i = 0; i < months.length; i++) {
            System.out.println((i+1) + ". " + months[i]);
        }

        boolean validChoice = false;
        while (!validChoice) {
            System.out.print("\nEnter your choice (1-5): ");
            try {
                monthChoice = Integer.parseInt(scanner.nextLine());
                if (monthChoice >= 1 && monthChoice <= 5) {
                    departureMonth = months[monthChoice - 1];
                    validChoice = true;

                    switch (monthChoice) {
                        case 1:
                            System.out.println("\nMarch: An early start, but you'll face muddy trails and swollen rivers.");
                            weatherMonthSet = 3;
                            break;
                        case 2:
                            System.out.println("\nApril: A good balance - the trails are drying out and you'll have plenty of time.");
                            weatherMonthSet = 4;
                            break;
                        case 3:
                            System.out.println("\nMay: The most popular month for departure - grass for animals is plentiful.");
                            weatherMonthSet = 5;
                            break;
                        case 4:
                            System.out.println("\nJune: Still a good time to leave, but you'll need to maintain a steady pace.");
                            weatherMonthSet = 6;
                            break;
                        case 5:
                            System.out.println("\nJuly: A late start - you'll need to hurry to cross mountains before winter.");
                            weatherMonthSet = 7;
                            break;
                        default: 
                            weatherMonthSet = 3;
                    }
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number between 1 and 5.");
            }
        }
        
        weather = new Weather();
    }


    /**
     * Sets up the initial game conditions based on player choices.
     * Initializes inventory, currency, and other game state variables.
     */
    public void setupInitialConditions() {
        System.out.println("\n=====================================================");
        System.out.println("                PREPARING FOR DEPARTURE              ");
        System.out.println("=====================================================");

        System.out.println("\nYou are in " + departureLocation + ".");
        System.out.println("It is " + departureMonth + " 1848, and you're preparing to depart along the " + trail + ".");

        // Historical context based on starting location
        if (departureLocation.equals("Independence, Missouri")) {
            System.out.println("\nIndependence is bustling with activity as wagon trains form up.");
            System.out.println("The town is crowded with emigrants buying supplies for the journey.");
            System.out.println("Wagons, oxen, and provisions are selling at premium prices.");
        } else {
            System.out.println("\nNauvoo has been largely abandoned by the Mormons after");
            System.out.println("persecution and violence. Most are heading west to find");
            System.out.println("religious freedom in the Utah Territory.");
        }

        System.out.println("\nPress Enter to continue to equipping your wagon...");
        scanner.nextLine();

        money = new Currency(1600); // Standard starting money
        System.out.println("\nYou have $" + money.getBalance() + " to spend on supplies for the journey.");

        // Continue with game setup
        beginJourney();
    }

    /**
     * Starts the main journey and gameplay loop.
     * Handles day-to-day travel, events, and player decisions.
     */
    public void beginJourney() {
        boolean gameRunning = true;
        Movement playerMovement = new Movement(gameMap);
        Weather currentWeather = new Weather();
        currentWeather.updateWeather();

        totalTrailDistance = gameMap.getTotalDistance();
        daysTraveled = 1;
        distanceTraveled = 0;

        // test - visitStore();

        // test - fastForwardToFortKearny();

        System.out.println("\nGame started! Your current position is: " + gameMap.getPlayerX() + ", " + gameMap.getPlayerY());

        while (gameRunning) {
            // Display current day and location
            System.out.println("\n===== DAY " + daysTraveled + " =====");
            System.out.println("Current Location: " + gameMap.getCurrentLandmark());
            System.out.println("Distance traveled: " + distanceTraveled + " miles");
            System.out.println("Distance to next landmark: " + gameMap.getDistanceToNextLandmark() + " miles");

            // Update weather each day with a small chance of change
            currentWeather.updateWeather(daysTraveled, monthChoice);
            System.out.println("Weather Strength: " + currentWeather.getWeather());

            // Daily food consumption
            // - commented out for testing purpose consumeDailyFood();

            // Display status
            displayDailyStatus();
            
            // Get user choice
            int choice = getUserChoice();
            //do user input
            userInput(choice);

           

            /*
             * this should prob be its own method
             */
      //      if (inventory.getFoodAmount() < 500)
            {
                if (Math.random()*100+1 < 15) {
                    System.out.println("You have less than 500 pounds of food left!");
                    boolean validChoice = false;
                    while (!validChoice) {
                        System.out.println("Would you like to hunt for more food? (Y/N)");
                        String huntingChoice = scanner.nextLine();
                        if (huntingChoice.equalsIgnoreCase("Y")) {
                            hunting.hunt();
                            validChoice = true;
                        } else if (huntingChoice.equalsIgnoreCase("N")) {
                            validChoice = true;
                        } else {
                            System.out.println("Invalid choice. Please enter Y or N.");
                        }
                    }
                }
            }

            /*
             * make this its own method
             */
            if (player.getHealth() <= 0) 
            {
                System.out.println("\nYou have died from " + player.getCauseOfDeath() + ".");
                System.out.println("Many pioneers faced similar fates on the trail.");
                System.out.println("Game Over.");
                gameRunning = false;
            }

            daysTraveled++;
            
            playerMovement.getDirection();
            
            System.out.println("Current position: " + gameMap.getPlayerX() + ", " + gameMap.getPlayerY());

        }
    }

    private boolean checkGameOver() {
        if (gameMap.getCurrentDistanceTraveled() >= WINNING_DISTANCE) {
            System.out.println("Congratulations! You've reached Independence Rock!");
            return true;
        }
        if (player.getHealth() <= 0) {
            System.out.println("Game Over: You have died from " + player.getCauseOfDeath());
            return true;
        }
        if (gameTime.isWinter()) {
            System.out.println("Game Over: Winter has arrived before reaching your destination.");
            return true;
        }
        return false;
    }

    private void processDailyEvents() {
        Perils dailyPerils = new Perils(player);
        dailyPerils.accident();
        dailyPerils.illness();
        dailyPerils.weather();
        
        // Process landmark events
        if (gameMap.hasReachedNewLandmark()) {
            Landmark currentLandmark = gameMap.getCurrentLandmark1();
            handleLandmarkEvent(currentLandmark);
        }
    }

    private void handleLandmarkEvent(Landmark currentLandmark) {
        System.out.println("\nYou have reached " + currentLandmark.getName());
        System.out.println(currentLandmark.getDescription());
        
        if (currentLandmark.hasRiver()) {
            new RiverCrossing().handleCrossing(player, wagon);
        }
        
        if (currentLandmark.isTradePost()) {
            System.out.println("Would you like to trade? (yes/no)");
            Scanner scanner = new Scanner(System.in);
            if (scanner.nextLine().toLowerCase().equals("yes")) {
                new Trading(player, null, null).initiateTrading();
            }
        }
    }

    private void updateGameState() {
        // Update time
        gameTime.advanceDay();
        
        // Consume daily resources
        inventory.consumeDailyRations(player.getPartySize());
        
        // Update weather
        weather.updateWeather();
        
        // Display daily status
    }

    private void displayDailyStatus() {
        System.out.println("\n=== Daily Status Update ===");
        System.out.println("Date: " + gameTime.getDate());
        System.out.println("Distance traveled: " + gameMap.getCurrentDistanceTraveled() + " miles");
        System.out.println("Health: " + player.getHealth());
        System.out.println("Food remaining: " + inventory.getFoodAmount());
        System.out.println("Money: $" + money.getBalance());
        System.out.println("Weather: " + weather.getCurrentWeather());
        System.out.println("========================\n");
    }

    private void displayIntroduction() {
        System.out.println("\n=== Welcome to Perils Along the Platte ===");
        System.out.println("The year is 1848. You are embarking on a journey to Independence Rock.");
        System.out.println("Your goal is to reach your destination before winter sets in.");
        System.out.println("Be careful of the many dangers along the way!");
        System.out.println("========================================\n");
    }
    
    /**
     * userInput will decipher what the user wants to do during the day 
     * @param choice the user input (an integer) that coreleates with what the user wants to do
     */
    public void userInput(int choice)
    {
    	 switch (choice) {
         case 1: // Continue journey
         {
         	System.out.println("The Journey Continues");
             break;
         }
         case 2: // Rest
             {
             	player.rest();
             	daysTraveled++;
             	break;
             }
         case 3:
         {
        	 System.out.println("Hunt for food");
        	 break;
        }

         case 4: // Look at inventory
         { 
         	System.out.println("----------Inventory----------");
             inventory.displayInventory();
             break;
         }

         case 5: // Learn about history
        	 {
        		 displayHistoricalInformation();
        		 break;
        	 }

         case 6: // Exit game
         {
         	System.out.println("Are you sure you want to quit? (Y/N)");
             if (scanner.next().equalsIgnoreCase("Y")) {
                 //gameRunning = false;
             }
             break;
         }
         default:
         	System.out.println("Error reading in input");
         	break;
     	}
    }
    
    /**
     * Gets a numeric choice input from the user with validation.
     * 
     * @return The validated numeric choice entered by the user
     */
    private int getUserChoice() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("1. Continue on the trail");
        System.out.println("2. Rest for the day");
        System.out.println("3. Hunt for food");
        System.out.println("4. Check inventory");
        System.out.println("5. Learn about the trail history");
        System.out.println("6. Quit game");

        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= 6) {
                    return choice;
                } else {
                    System.out.println("Please enter a number between 1 and 6.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number between 1 and 6.");
            }
        }
    }
    
    /**
     * Checks and reports on the wagon's current condition.
     * Handles consequences of wagon damage or deterioration.
     */
    private void checkWagonCondition() {
        if (wagon.getCondition() <= 0) {
            System.out.println("\nYour wagon has completely broken down!");
            System.out.println("Without a functioning wagon, you cannot continue your journey.");
            System.out.println("\nHistorical Note: Abandoned wagons were a common sight along the trail.");
            System.out.println("Pioneers often had to discard possessions or even entire wagons");
            System.out.println("when they became too damaged to continue.");

            // Game over
            player.takeDamage(100, "being stranded with a broken wagon");
        }
    }
    
    /**
     * Displays historical information relevant to the current location or situation.
     * Provides educational content about the Oregon Trail period.
     */
    private void displayHistoricalInformation() {
        System.out.println("\n=====================================================");
        System.out.println("              HISTORICAL INFORMATION                ");
        System.out.println("=====================================================");

        // Random historical facts about the current trail
        String[] oregonTrailFacts = {
                "The Oregon Trail stretched about 2,170 miles from Independence, Missouri to Oregon's Willamette Valley.",
                "Between 1840 and 1860, more than 400,000 pioneers traveled the Oregon Trail seeking farmland and new opportunities.",
                "The journey typically took 4-6 months by wagon, with travelers walking most of the way.",
                "Wagon trains usually traveled 15-20 miles per day when conditions were good.",
                "Disease was the biggest killer on the trail, with cholera being particularly deadly.",
                "Contrary to popular belief, attacks by Native Americans were relatively rare. Only about 4% of pioneer deaths were due to conflicts."
        };

        String[] californiaTrailFacts = {
                "The California Trail branched from the Oregon Trail and became heavily used after the 1848 Gold Rush.",
                "Over 250,000 gold-seekers and farmers used the California Trail during the Gold Rush period.",
                "The most difficult stretch was the Sierra Nevada mountains, especially the 40-mile desert in Nevada.",
                "The Donner Party's tragic story happened on the California Trail when they became snowbound in the Sierra Nevada in 1846-47.",
                "Many California-bound pioneers abandoned their wagons and possessions to complete the difficult journey.",
                "The discovery of gold at Sutter's Mill in 1848 transformed California from a sparsely populated territory to a state by 1850."
        };

        String[] mormonTrailFacts = {
                "The Mormon Trail was used by members of the Church of Jesus Christ of Latter-day Saints fleeing religious persecution.",
                "The trail stretched 1,300 miles from Nauvoo, Illinois to Salt Lake Valley, Utah.",
                "Unlike other trails, the Mormon migration was highly organized, with carefully planned rest stops.",
                "Mormons used handcarts instead of wagons for part of their migration (1856-1860).",
                "The Mormon Trail followed the north side of the Platte River, while the Oregon Trail followed the south side.",
                "Brigham Young led the first group of Mormon pioneers to the Salt Lake Valley in 1847."
        };

        // Select appropriate facts based on chosen trail
        String[] currentTrailFacts = new String[0];
        switch (trail) {
            case "Oregon":
                currentTrailFacts = oregonTrailFacts;
                break;
            case "California":
                currentTrailFacts = californiaTrailFacts;
                break;
            case "Mormon":
                currentTrailFacts = mormonTrailFacts;
                break;
        }

        if (currentTrailFacts.length > 0) {
            System.out.println("\nFacts about the " + trail + " Trail:");
            int fact1 = (int)(Math.random() * currentTrailFacts.length);
            int fact2 = (fact1 + 1 + (int)(Math.random() * (currentTrailFacts.length - 1))) % currentTrailFacts.length;

            System.out.println("\n• " + currentTrailFacts[fact1]);
            System.out.println("\n• " + currentTrailFacts[fact2]);
        }

        String[] pioneerLifeFacts = {
                "Pioneer women typically wore cotton dresses, sunbonnets, and aprons. Men wore cotton shirts, vests, and trousers.",
                "A typical day on the trail started around 4 AM and ended with setting up camp around 4 PM.",
                "Pioneer children still had chores and sometimes school on the trail. They gathered buffalo chips (dried dung) for fuel.",
                "Pioneers often walked alongside their wagons to reduce weight and strain on the animals.",
                "Food staples included flour, bacon, coffee, dried beans, and dried fruit. Fresh meat came from hunting.",
                "Music and storytelling were important forms of entertainment around campfires at night."
        };

        System.out.println("\nDaily Life on the Trail:");
        System.out.println("\n• " + pioneerLifeFacts[(int)(Math.random() * pioneerLifeFacts.length)]);

        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
}