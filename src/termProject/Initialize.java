package termProject;

import javax.swing.*;

public class Initialize
{
    public static void start()
    {
        if(setupCharacter() && selectDepartureMonth() && selectTrail());
        else
            Main.main.logMessage("Failed to set up game.");
    }

    public static boolean setupCharacter() {
        // Gender selection
        String[] genders = {"Male", "Female"};
        String selectedGender = (String) JOptionPane.showInputDialog(
                null,
                "In the 1800s, men and women faced different challenges on the trail west.\n" +
                        "Your choice will affect some of the situations you encounter.",
                "Character Selection",
                JOptionPane.QUESTION_MESSAGE,
                null,
                genders,
                genders[0]);

        if (selectedGender == null) return false;

        // Name input
        String name = JOptionPane.showInputDialog(
                null,
                "Enter your character's name:",
                "Character Name",
                JOptionPane.QUESTION_MESSAGE);

        if (name == null || name.trim().isEmpty()) return false;

        Player.setGender(selectedGender.toLowerCase());
        Player.setName(name);
        Main.main.logMessage("Hello " + Player.name );
        return true;

    }

    public static boolean selectDepartureMonth() {
        String[] months = {"March", "April", "May", "June", "July"};
        String[] descriptions = {
                "March: An early start, but you'll face muddy trails and swollen rivers.",
                "April: A good balance - the trails are drying out and you'll have plenty of time.",
                "May: The most popular month for departure - grass for animals is plentiful.",
                "June: Still a good time to leave, but you'll need to maintain a steady pace.",
                "July: A late start - you'll need to hurry to cross mountains before winter."
        };

        String selectedMonth = (String) JOptionPane.showInputDialog(
                null,
                "The timing of your departure was crucial for pioneers.\n" +
                        "Leave too early: face mud and flooding from spring rains.\n" +
                        "Leave too late: risk being trapped in mountain snow.\n\n" +
                        "Most emigrants departed between April and June.",
                "Departure Month Selection",
                JOptionPane.QUESTION_MESSAGE,
                null,
                descriptions,
                descriptions[1]);

        if (selectedMonth == null) return false;

        for (int i = 0; i < descriptions.length; i++) {
            if (descriptions[i].equals(selectedMonth)) {
                Time.monthNum = i;
                break;
            }
        }

        // Set weather based on month choice
       //todo currentWeather = new Weather(); // Weather constructor takes month as 3-7

        Main.main.logMessage("Selected month: " + Time.monthName[Time.monthNum]);
        return true;
    }

    public static boolean selectTrail() {
        String[] trails = {
                "Oregon Trail (2,170 miles) - Most popular route for farmers seeking fertile land",
                "California Trail (1,950 miles) - Heavily traveled after the 1848 Gold Rush",
                "Mormon Trail (1,300 miles) - Used by Mormon pioneers fleeing religious persecution"
        };

        String selectedTrail = (String) JOptionPane.showInputDialog(
                null,
                "There were several major routes west. Each trail had\n" +
                        "different destinations, terrain, and challenges:",
                "Trail Selection",
                JOptionPane.QUESTION_MESSAGE,
                null,
                trails,
                trails[0]);

        if (selectedTrail == null) return false;

        String trail;
        String departureLocation;

        if (selectedTrail.startsWith("Oregon")) {
            trail = "Oregon";
            departureLocation = "Independence, Missouri";
            GameMap.initializeOregonTrail();
        } else if (selectedTrail.startsWith("California")) {
            trail = "California";
            departureLocation = "Independence, Missouri";
            GameMap.initializeCaliforniaTrail();
        } else {
            trail = "Mormon";
            departureLocation = "Nauvoo, Illinois";
            GameMap.initializeMormonTrail();
        }

        Main.main.logMessage("You have chosen to travel along the " + trail + " Trail.");
        Main.main.logMessage("Your journey will begin in " + departureLocation + ".");
        //.setGameLogMessage("You have chosen to travel along the " + trail + " Trail.");
        //Main.setGameLogMessage("Your journey will begin in " + departureLocation + ".");

        return true;
    }

}
