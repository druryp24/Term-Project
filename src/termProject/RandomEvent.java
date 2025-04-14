package termProject;

/**
 * RandomEvent Class of the Perils Along the Platte Game
 * Manages random occurrences that can happen during gameplay.
 * Handles probability-based triggers and messaging for events.
 *
 * @author : Alex Randall, Chase McCluskey, Painter Drury, and Domenic Pilla
 * @version : 1.0
 * @ date : 03/25/2025
 * @ file : randomEvent.java
 */
public class RandomEvent {
    private int probability;
    String eventMessage;

    /**
     * Default constructor for random events.
     * Initializes a random event with zero probability and default message.
     */
    public RandomEvent() {
        probability = 0;
        eventMessage = "A random event has occurred!";
    }

    /**
     * Determines if a random event should trigger based on its probability.
     * Generates a random number and compares it to the event's probability.
     * 
     * @return true if the event is triggered, false otherwise
     */
    public boolean triggerEvent() {
        return Math.random() * 100 < probability;
    }

    /**
     * Gets the message associated with this random event.
     * 
     * @return The event message string
     */
    public String getEventMessage() {
        return eventMessage;
    }

    /**
     * Sets the probability of the random event occurring.
     * Only accepts values between 0 and 100 inclusive.
     * 
     * @param probability The percentage chance (0-100) of the event occurring
     */
    public void setProbability(int probability) {
        if (probability >= 0 && probability <= 100) {
            this.probability = probability;
        }
    }
}