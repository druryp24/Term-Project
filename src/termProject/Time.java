package termProject;

/**
 * Time Class of the Perils Along the Platte Game
 * Manages game time, date tracking, and temporal events.
 * Handles date calculations, time display, and temporal progression through the journey.
 *
 * @author : Alex Randall, Chase McCluskey, Painter Drury, and Domenic Pilla
 * @version : 1.0
 * @ date : 03/25/2025
 * @ file : time.java
 */
import java.lang.*;

public class Time {
    public static String month;
    public static String day;
    public static String year;
    public static String hour;
    public static String minute;
    public static String[] monthName = new String[] {"March", "April", "May", "June", "July"};
    public static int monthNum;
    
    // Added variables for time progression
    private int currentDay;
    private int currentMonth;
    private int currentYear;
    private int currentHour;
    private final int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    /**
     * Constructor that initializes a time object with all time components.
     * 
     * @param month The month value as a string
     * @param day The day value as a string
     * @param year The year value as a string
     * @param hour The hour value as a string
     * @param minute The minute value as a string
     */
    public Time(String month, String day, String year, String hour, String minute) {
        this.month = month;
        this.day = day;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
        
        // Initialize current time values
        this.currentDay = Integer.parseInt(day);
        this.currentMonth = monthNum;
        this.currentYear = Integer.parseInt(year);
        this.currentHour = Integer.parseInt(hour);
    }

    /**
     * Constructor that initializes a time object with only month specified.
     * 
     * @param monthNum1 The month value as a string
     */
    public Time(int monthNum1){
        monthNum = monthNum1;
        this.month = monthName[monthNum1];
        
        // Initialize with default values
        this.currentDay = 1;
        this.currentMonth = monthNum1;
        this.currentYear = 1847;
        this.currentHour = 6;
    }

    /**
     * Default constructor for time object.
     * Creates an empty time object that can be populated later.
     */
    public Time(){
        // Initialize with starting date: April 1st, 1847, 6:00 AM
        this.currentDay = 1;
        this.currentMonth = 4;
        this.currentYear = 1847;
        this.currentHour = 6;
        
        this.month = monthName[currentMonth - 1];
        this.day = String.valueOf(currentDay);
        this.year = String.valueOf(currentYear);
        this.hour = String.valueOf(currentHour);
        this.minute = "00";
        this.monthNum = currentMonth;
    }
    
    /**
     * Calculates a numeric representation of the current time.
     * 
     * @return A double value representing the time based on year, hour, and minute
     */
    public double returnTime(){
        return Double.parseDouble(year) + Double.parseDouble(hour) + Double.parseDouble(minute);
    }

    /**
     * Sets the day and month values of the time object.
     * 
     * @param day The day value as an integer
     * @param month The month value as an integer
     */
    public void setTime(int day, int month){
        this.day = Integer.toString(day);
        this.monthNum = month;
        this.currentDay = day;
        this.currentMonth = month;
    }

    /**
     * Gets the month value as an integer.
     * 
     * @return The month value
     */
    public int getMonth(){
        return this.monthNum; 
    }

    /**
     * Gets the day value as an integer.
     * 
     * @return The day value
     */
    public int getDay(){
        return Integer.parseInt(day);
    }

    /**
     * Gets the hour value as an integer.
     * 
     * @return The hour value
     */
    public int getHour(){
        return Integer.parseInt(hour);
    }

    public void advanceTime(int hours) {
        currentHour += hours;
        while (currentHour >= 24) {
            currentHour -= 24;
            advanceDay();
        }
        // Update string representations
        this.hour = String.valueOf(currentHour);
    }

    public void advanceDay() {
        currentDay++;
        if (currentDay > daysInMonth[currentMonth - 1]) {
            currentDay = 1;
            currentMonth++;
            if (currentMonth > 12) {
                currentMonth = 1;
                currentYear++;
            }
        }
        // Update string representations
        this.day = String.valueOf(currentDay);
        this.month = monthName[currentMonth - 1];
        this.year = String.valueOf(currentYear);
        this.monthNum = currentMonth;
    }

    public String getDate() {
        return String.format("%s %d, %d", monthName[currentMonth - 1], currentDay, currentYear);
    }

    public boolean isWinter() {
        return currentMonth == 12 || currentMonth <= 2;
    }

    public static void setMonthNum(int monthNum1) {
        monthNum = monthNum1;
    }
}
