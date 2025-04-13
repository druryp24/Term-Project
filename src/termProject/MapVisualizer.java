package termProject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MapVisualizer extends JPanel{
	private JFrame frame;
	private GameMap gameMap = new GameMap(0,0);
	private MapPanel mapPanel;

    // Constructor that takes the map object
    public MapVisualizer(GameMap gameMap) {
        this.gameMap = gameMap;
        setPreferredSize(new Dimension(800, 600)); // Set size for the map panel
    }

    // Override the paintComponent method to render the map
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw background (optional)
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Draw landmarks
        for (Landmark lm : gameMap.getLandmarks()) {
            int x = lm.getX() * 2; // Adjust scale factor for visual display
            int y = lm.getY() * 2; // Adjust scale factor for visual display
            g.setColor(Color.BLACK);
            g.fillOval(x, y, 10, 10); // Draw landmark as a small circle
            g.drawString(lm.getName(), x + 15, y + 5); // Draw the name of the landmark next to it
        }

        // Draw the player position
        int playerX = gameMap.getPlayerX() * 2; // Scale player position
        int playerY = gameMap.getPlayerY() * 2; // Scale player position
        g.setColor(Color.RED);
        g.fillRect(playerX, playerY, 10, 10); // Draw player as a red square

        // Optional: Draw a line connecting landmarks or other map features
    }
    
    public void refreshMap() {
    	mapPanel.repaint();
    }
}
