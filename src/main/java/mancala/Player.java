
package mancala;

public class Player {
    private String name;
    private Store store; // Player's store where they collect stones.

    // Zero parameter constructor
    public Player() {
        this.name = "";
        this.store = new Store();
    }

    // Constructor with a name parameter
    public Player(String name) {
        this.name = name;
        this.store = new Store();
    }

    // Gets the name of the player.
    public String getName() {
        return this.name;
    }

    // Sets the player's name.
    void setName(String name) {
        this.name = name;
    }

    // Gets the player's store where they collect stones.
    Store getStore() {
        return this.store;
    }

    // Sets the player's store.
    void setStore(Store store) {
        this.store = store;
    }

    // Gets the count of the number of stones in the player's store.
    int getStoreCount() {
        return this.store.getTotalStones();
    }

    // Represents the player object as a string.
    @Override
    public String toString() {
        return "Player " + this.name + " with " + getStoreCount() + " stones in store.";
    }
}




