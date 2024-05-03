package mancala;

public class Pit {
    private int stones;

    // Constructor: Initializes a new pit.
    public Pit() {
        this.stones = 0;
    }

    // Adds a stone to the pit.
    public void addStone() {
        this.stones++;
    }

    // Gets the number of stones in the pit.
    public int getStoneCount() {
        return this.stones;
    }

    // Removes and returns the stones from the pit.
    public int removeStones() {
        int temp = this.stones;
        this.stones = 0;
        return temp;
    }

    // Represents the pit object as a string.
    @Override
    public String toString() {
        return "Pit with " + this.stones + " stones.";
    }
}



