package termProject;

import javax.swing.*;

public class Initialize
{



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
