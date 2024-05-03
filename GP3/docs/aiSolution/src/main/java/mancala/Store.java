package mancala;


public class Store {
    private Player owner;
    private int totalStones;

    public Store() {
        owner = null;
        totalStones = 0;
    }

    public void setOwner(Player player) {
        owner = player;
    }

    public Player getOwner() {
        return owner;
    }

    public void addStones(int amount) {
        totalStones += amount;
    }

    public int getTotalStones() {
        return totalStones;
    }

    public int emptyStore() {
        int stonesInStore = totalStones;
        totalStones = 0;
        return stonesInStore;
    }

}
