package mancala;

import java.util.ArrayList;

public class Board {
    private ArrayList<Pit> pits;
    private ArrayList<Store> stores;

    public Board() {
        this.pits = new ArrayList<>();
        this.stores = new ArrayList<>();
        setUpPits();
        setUpStores();
        initializeBoard();
    }

    public void setUpPits() {
        // Create 12 pits
        for (int i = 0; i < 12; i++) {
            pits.add(new Pit());
        }
    }

    public ArrayList<Pit> getPits() {
        return this.pits;
    }

    public void setUpStores() {
        stores = new ArrayList<>();
        stores.add(new Store());
        stores.add(new Store());
    }

    public ArrayList<Store> getStores() {
        return this.stores;
    }

    public void initializeBoard() {
        // Distribute stones initially
        for (Pit pit : pits) {
            for (int i = 0; i < 4; i++) {
                pit.addStone();
            }
        }
    }

    public void resetBoard() {
        // Reset the board by removing all stones and initializing again
        for (Pit pit : pits) {
            pit.removeStones();
        }
        initializeBoard();
    }

    public void registerPlayers(Player one, Player two) {
        stores.get(1).setOwner(one);
        stores.get(0).setOwner(two);
        one.setStore(stores.get(1));
        two.setStore(stores.get(0));
    }

    public int captureStones(int stoppingPoint) {
        int stonesCaptured = 0;
        int oppositePoint = 11 - stoppingPoint;

        if (pits.get(oppositePoint).getStoneCount() > 0 && pits.get(stoppingPoint).getStoneCount() == 1) {
            stonesCaptured += pits.get(oppositePoint).getStoneCount();
            pits.get(oppositePoint).removeStones();
            stonesCaptured += pits.get(stoppingPoint).getStoneCount();
            pits.get(stoppingPoint).removeStones();
        }
        return stonesCaptured;
    }

    
    

   

    public int moveStones(int startPit, Player player) throws InvalidMoveException{
        
        // Validate the start pit is within the board limits.
    if (startPit < 1 || startPit > 12) {
        throw new InvalidMoveException("Move is out of bounds.");
    }

    // Adjust the startPit index to match array indexing (0-based).
    startPit -= 1;

    // Determine the current player's index based on the start pit.
    int currentPlayerIndex = (startPit < 6) ? 0 : 1;
    
    // Retrieve the old value of stones in the store before the move.
    int oldStoreValue = getStores().get(currentPlayerIndex).getTotalStones();
    
    // Distribute stones from the selected pit across the board.
    distributeStones(startPit);

    // Retrieve the new value of stones in the store after the move.
    int newStoreValue = getStores().get(currentPlayerIndex).getTotalStones();
    
    // Calculate the difference in stones in the store to determine stones moved.
    int stonesMoved = newStoreValue - oldStoreValue;
    
    return stonesMoved;
    }
    
    public int getNumStones(int pitNum) {
        return pits.get(pitNum).getStoneCount();
    }

    public boolean isSideEmpty(int pitNum) {
        // Determine which side based on the pit number
        int start = (pitNum < 6) ? 0 : 6;
        int end = start + 5;

        for (int i = start; i < end; i++) {
            if (pits.get(i).getStoneCount() > 0) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();

    
    for (int i = 11; i >= 6; i--) {
        builder.append(pits.get(i).getStoneCount()).append(" ");
    }
  
    builder.append(" ").append(stores.get(0).getTotalStones()).append("\n");

   
    for (int i = 0; i < 6; i++) {
        builder.append(pits.get(i).getStoneCount()).append(" ");
    }
    
    builder.insert(0, stores.get(1).getTotalStones() + " ");

    return builder.toString();
    }

}
