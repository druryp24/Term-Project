package termProject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;


public class Main {
    static Main main;

    private JPanel mainWindow;
    private JButton moveButton, huntButton, restButton, tradeButton, statusButton, historyButton, saveButton, quitButton;
    private JTextArea gameLog;

    private MapPanel gameMapPanel;
    private JPanel map;
    private static JFrame frame;

    private Player currentPlayer;

    private GameMap gameMap;
    private Inventory playerInventory;
    private Wagon playerWagon;
    public Time gameTime;
    private Weather currentWeather;
    private Currency playerMoney;
    private Hunting huntingSystem;
    private boolean gameStarted = false;
    private int totalTrailDistance;
    private int daysTraveled = 0;
    private final int WINNING_DISTANCE = 2000; // Distance to Independence Rock


    public Main()
    {
        setUpBtn();
        gameLog.setOpaque(false);
        map.setVisible(false);
        frame.pack();

        gameMapPanel =  new MapPanel(gameMap);

        moveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(gameMap == null) { logMessage("gameMap is null");}
                else
                {
                    handleMove();
                    map.setVisible(true);
                    map.setLayout(new BorderLayout());
                    map.add(gameMapPanel, BorderLayout.CENTER);
                    frame.pack();
                }
            }
        });
    }

    public static void main(String[] args) {
        frame = new JFrame("Perils Along the Platte");
        main = new Main();
        frame.setContentPane(main.mainWindow);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

       Initialize.start();
    }

    private void initializeGameComponents() {
        // Initialize basic components
        currentPlayer = new Player("Player 1", "Male", 100);
        gameMap = new GameMap(0, 0);
        playerInventory = new Inventory();
        playerWagon = new Wagon();
        gameTime = new Time();
        playerMoney = new Currency(1600); // Standard starting money
        huntingSystem = new Hunting(currentPlayer);
        daysTraveled = 1;

        currentPlayer.createInventory();
    }

    private void initializeGameWithChoices() {
        // Add initial inventory (similar to startGame's initialization)
        playerInventory.addItem(new Food("Rations", 1, 100), 50);
        playerInventory.addItem(new Weapon("Rifle", 5), 1);
        playerInventory.addItem(new Item("Ammunition", 1), 50);
        playerInventory.addItem(new Medicine("Medicine", 1, 5, 1), 3);

        // Set total trail distance from map
        totalTrailDistance = gameMap.getTotalDistance();
    }

    public void logMessage(String message) {
        gameLog.append(message + "\n");
        gameLog.setCaretPosition(gameLog.getDocument().getLength());
    }

    private void handleMove() {
        String[] directions = {"North", "South", "East", "West"};
        String direction = (String) JOptionPane.showInputDialog(
                mainWindow,
                "Choose direction:",
                "Move",
                JOptionPane.QUESTION_MESSAGE,
                null,
                directions,
                directions[0]);

        if (direction != null) {
            String distanceStr = JOptionPane.showInputDialog("Enter distance (miles):");
            try {
                int distance = Integer.parseInt(distanceStr);

                Movement moveSystem = new Movement(gameMap);

                moveSystem.travelDirection(direction.toLowerCase(), distance);

                // Process travel events
                processTravelEvents();

                // Consume resources based on travel
                /*
                playerInventory.consumeDailyRations(currentPlayer.getPartySize());

                 */

                // Update game time
                gameTime.advanceDay();
                daysTraveled++;

                // Check if reached landmark
                checkForLandmarks();

                updateGameState();
                logMessage("Traveled " + distance + " miles " + direction + ".");
            } catch (NumberFormatException e) {
                logMessage("Invalid distance entered.");
            }
        }
    }

    private void processTravelEvents() {
        // Random chance for perils to occur during travel
        Perils dailyPerils = new Perils(currentPlayer);
        checkGameMapInitialization();

        // Random accident
        if (Math.random() < 0.15) {
            dailyPerils.accident();
            logMessage("Accident occurred! Health reduced to " + currentPlayer.getHealth() + "%");
        }

        // Random illness
        if (Math.random() < 0.10) {
            dailyPerils.illness();
            logMessage("Someone in your party has fallen ill! Health reduced to " + currentPlayer.getHealth() + "%");
        }

        // Weather effects
        if (Math.random() < 0.20) {
            dailyPerils.weather();
            logMessage("Harsh weather conditions affected your journey! Health reduced to " + currentPlayer.getHealth() + "%");
        }

        // Update weather
        currentWeather.updateWeather();

        // Random event to ask about hunting if food is low
        if (playerInventory.getFoodAmount() < 50 && Math.random() < 0.5) {
            int choice = JOptionPane.showConfirmDialog(
                    mainWindow,
                    "You have less than 50 pounds of food left! Would you like to hunt?",
                    "Food Warning",
                    JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION) {
                handleHunt();
            }
        }
    }

    private void checkGameMapInitialization() {
        if (gameMap == null) {
            gameMap = new GameMap(0, 0);
        }
    }

    private void handleHunt() {
        // Use hunting system from the startGame class
        int foodBefore = playerInventory.getFoodAmount();
        huntingSystem.hunt();
        int foodAfter = playerInventory.getFoodAmount();

        if (foodAfter > foodBefore) {
            logMessage("Hunting successful! Added " + (foodAfter - foodBefore) + " pounds of food.");
        } else {
            logMessage("Hunting unsuccessful. No food gained.");
        }

        // Advance time when hunting
        gameTime.advanceDay();
        daysTraveled++;

        updateGameState();
    }

    private void updateGameState() {
        gameMapPanel.repaint();
        checkGameConditions();
    }

    private void checkGameConditions() {
        if (currentPlayer.getHealth() <= 0) {
            logMessage("Game Over: You have died from " + currentPlayer.getCauseOfDeath());
            handleGameOver();
        } else if (gameMap.getCurrentDistanceTraveled() >= WINNING_DISTANCE) {
            logMessage("Congratulations! You've reached Independence Rock!");
            handleGameOver();
        } else if (gameTime.isWinter()) {
            logMessage("Game Over: Winter has arrived before reaching your destination.");
            handleGameOver();
        }
    }

    private void handleGameOver() {
        int choice = JOptionPane.showConfirmDialog(
                mainWindow,
                "Would you like to start a new game?",
                "Game Over",
                JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            initializeGameComponents();
            Initialize.start();
            updateGameState();
        } else {
            System.exit(0);
        }
    }

    private void checkForLandmarks() {
        if (gameMap.hasReachedNewLandmark()) {
            Landmark currentLandmark = gameMap.getCurrentLandmark1();

            if(currentLandmark != null) {
                logMessage("You have reached " + currentLandmark.getName());
                logMessage(currentLandmark.getDescription());

                if (currentLandmark.hasRiver()) {
                    handleRiverCrossing();
                }
            }
        }
    }

    private void handleRiverCrossing() {
        // Show dialog for river crossing options
        String[] options = {"Ford the river", "Use a ferry (costs $5)", "Find a guide (costs $10)", "Wait for better conditions"};

        int choice = JOptionPane.showOptionDialog(
                mainWindow,
                "You've reached a river crossing. How would you like to proceed?",
                "River Crossing",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        RiverCrossing crossing = new RiverCrossing();

        switch (choice) {
            case 0: // Ford the river
                boolean success = crossing.fordRiver(currentPlayer, playerWagon);
                if (success) {
                    logMessage("You successfully forded the river!");
                } else {
                    logMessage("The river was too dangerous! You lost supplies and health.");
                }
                break;
            case 1: // Use ferry
                if (playerMoney.getBalance() >= 5) {
                    playerMoney.removeMoney(5);
                    logMessage("You paid $5 to use the ferry and crossed safely.");
                } else {
                    logMessage("You don't have enough money for the ferry! You must choose another option.");
                    handleRiverCrossing();
                }
                break;
            case 2: // Use guide
                if (playerMoney.getBalance() >= 10) {
                    playerMoney.removeMoney(10);
                    logMessage("You paid $10 for a guide and crossed safely.");
                } else {
                    logMessage("You don't have enough money for a guide! You must choose another option.");
                    handleRiverCrossing();
                }
                break;
            case 3: // Wait
                gameTime.advanceDay();
                currentPlayer.heal(5);
                logMessage("You wait a day for better conditions. The water level has gone down slightly.");
                handleRiverCrossing();
                break;
        }
    }

    public void setUpBtn()
    {
        moveButton.setText("Move " + "🚶");
        huntButton.setText("Hunt " + "🏹");
        restButton.setText("Rest " + "⛺");
        tradeButton.setText("Trade "+"💰");
        statusButton.setText("Status "+"📊");
        historyButton.setText("History "+"📜");
        saveButton.setText("Save "+"💾");
        quitButton.setText("Quit "+"🚪");
    }

}
