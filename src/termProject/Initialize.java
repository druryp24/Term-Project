package termProject;

import javax.swing.*;

public class Initialize
{

    private Player currentPlayer;


    private boolean setupCharacter() {
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

        currentPlayer.setGender(selectedGender.toLowerCase());
        currentPlayer.setName(name);
        return true;
    }

}
