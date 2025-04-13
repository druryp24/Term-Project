    package termProject;
    import javax.swing.*;
    import java.awt.*;
    import java.util.ArrayList;

    public class MapPanel extends JPanel {
        private GameMap gameMap;  // Reference to your map class

        public MapPanel(GameMap gameMap) {
            this.gameMap = gameMap;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Draw background
            g.setColor(new Color(230, 230, 200));
            g.fillRect(0, 0, getWidth(), getHeight());

            // Draw trail
            g.setColor(new Color(139, 69, 19));
            g.drawLine(50, getHeight()-50, getWidth()-50, 50);

            // Draw landmarks
            ArrayList<Landmark> landmarks = gameMap.getLandmarks();
            for (Landmark lm : landmarks) {
                drawLandmark(g, lm);
            }

            // Draw player position
            drawPlayer(g);

            // Draw distance indicator
            drawDistanceIndicator(g);
        }

        private void drawLandmark(Graphics g, Landmark lm) {
            int x = lm.getX();
            int y = lm.getY();

            g.setColor(Color.BLUE);
            g.fillRect(x-5, y-5, 10, 10);

            g.setColor(Color.BLACK);
            g.drawString(lm.getName(), x+10, y);
        }

        private void drawPlayer(Graphics g) {
            g.setColor(Color.RED);
            g.fillOval(gameMap.getPlayerX()-5, gameMap.getPlayerY()-5, 10, 10);
        }

        private void drawDistanceIndicator(Graphics g) {
            g.setColor(Color.BLACK);
            g.drawString("Distance: " + gameMap.getCurrentDistanceTraveled() + " miles", 10, 20);
        }
    }

