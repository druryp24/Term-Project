package termProject;

public class GameState {
    private Player player;
    private GameMap gameMap;
    private inventory inventory;
    private Time gameTime;

    public GameState(Player player, GameMap gameMap, inventory inventory, Time gameTime) {
        this.player = player;
        this.gameMap = gameMap;
        this.inventory = inventory;
        this.gameTime = gameTime;
    }

    // Getters
    public Player getPlayer() { return player; }
    public GameMap getMap() { return gameMap; }
    public inventory getInventory() { return inventory; }
    public Time getTime() { return gameTime; }
}