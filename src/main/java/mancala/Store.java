package mancala;

public class Store {
    
    private int stones;
    private Player owner;
    
    // Constructor
    public Store() {
        this.stones = 0; // Assuming a new store starts with 0 stones.
        this.owner = null; // Initially, there's no owner assigned.
    }
    
    // Adds stones to the store.
    public void addStones(int amount) {
        this.stones += amount;
    }
    
    // Empties the store and returns the number of stones that were in it.
    public int emptyStore() {
        int removedStones = this.stones;
        this.stones = 0;
        return removedStones;
    }
    
    // Gets the owner of the store.
    public Player getOwner() {
        return this.owner;
    }
    
    // Gets the total number of stones in the store.
    public int getTotalStones() {
        return this.stones;
    }
    
    // Sets the owner of the store.
    public void setOwner(Player player) {
        this.owner = player;
    }
    
    // Represents the store object as a string.
    @Override
    public String toString() {
        return "Store with " + this.stones + " stones owned by " + (this.owner != null ? this.owner.getName() : "no one");
    }
}





















