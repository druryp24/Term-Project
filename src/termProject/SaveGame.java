package termProject;

import java.io.*;

public class SaveGame {
    public void saveGame(Player player, GameMap gameMap, inventory inventory, Time gameTime) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("savegame.dat"))) {
            out.writeObject(player);
            out.writeObject(gameMap);
            out.writeObject(inventory);
            out.writeObject(gameTime);
        } catch (IOException e) {
            System.out.println("Error saving game: " + e.getMessage());
        }
    }

    public GameState loadGame() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("savegame.dat"))) {
            Player player = (Player) in.readObject();
            GameMap gameMap = (GameMap) in.readObject();
            inventory inventory = (inventory) in.readObject();
            Time gameTime = (Time) in.readObject();
            return new GameState(player, gameMap, inventory, gameTime);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading game: " + e.getMessage());
            return null;
        }
    }
}