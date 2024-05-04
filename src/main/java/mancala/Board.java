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

    
    public int captureStones(int stoppingPoint) {

        int stonesCaptured = 0;
        int oppositePoint = stoppingPoint;

        // find opposite side 
        oppositePoint = 11 - stoppingPoint;

        // find if opposite side is empty
        if(this.getPits().get(oppositePoint).getStoneCount() > 0 && this.getPits().get(stoppingPoint).getStoneCount() == 1){
            stonesCaptured += this.getPits().get(oppositePoint).getStoneCount();
            this.getPits().get(oppositePoint).removeStones();
            stonesCaptured += this.getPits().get(stoppingPoint).getStoneCount();
            this.getPits().get(stoppingPoint).removeStones();
        }
        return stonesCaptured;
    }

    public int distributeStones(int startingPoint){  
        int stonesToDistribute = this.getNumStones(startingPoint);
        int stonesAdded = stonesToDistribute;
        int tracker1 = 0, tracker2 = 0, stonesCaptured;

        //this figures out which player is going to see what store to use
        int player = 1;
        int storeTrack = 0;
        if(startingPoint >= 0 && startingPoint <= 5){
            player = 1;
            storeTrack = 1;
        } 
        else{
            player = 2;
            storeTrack = 0;
        }

        //there will be no move if we're not distributing
        if (stonesToDistribute == 0) {
           return 0;
        }

        //remove the stones that are in current pit
        this.getPits().get(startingPoint).removeStones();

        int currentPit = startingPoint; //current pit keeps track of pit to "edit"

        while (stonesToDistribute > 0) {
            currentPit++;
            if(currentPit == 12){
                currentPit = 0;
                tracker1 = 1;
            }
            if(currentPit == 6){
                tracker2 = 1;
            }

            // giant if-else statement for a bunch of different cases
            if(currentPit == 5 && stonesToDistribute >= 2 && player == 1){
                this.getPits().get(currentPit).addStone();
                this.getStores().get(storeTrack).addStones(1);
                stonesToDistribute--;
            }
            else if(currentPit == 11 && stonesToDistribute >= 2 && player == 2){
                this.getPits().get(currentPit).addStone();
                this.getStores().get(storeTrack).addStones(1);
                stonesToDistribute--;
            }
            else if((tracker1 == 1 && startingPoint == 11) && stonesToDistribute >= 2 && player == 2){
                this.getPits().get(currentPit).addStone();
                this.getStores().get(storeTrack).addStones(1);
                stonesToDistribute--;
                tracker1 = 0;
            }
            else if((tracker2 == 1 && startingPoint == 5) && stonesToDistribute >= 2 && player == 1){
                this.getPits().get(currentPit).addStone();
                this.getStores().get(storeTrack).addStones(1);
                stonesToDistribute--;
                tracker2 = 0;
            }
            else if(startingPoint == 5 && tracker2 == 1 && stonesToDistribute == 1 && player == 1){
                this.getStores().get(storeTrack).addStones(1);
                return 1;
            }
            else if(startingPoint == 11 && tracker1 == 1 && stonesToDistribute == 1 && player == 2){
                this.getStores().get(storeTrack).addStones(1);
                return 1;
            }
            else{
                this.getPits().get(currentPit).addStone();
            }
            stonesToDistribute--;
        }

        stonesCaptured = this.captureStones(currentPit);

        if(stonesCaptured > 0 && currentPit <= 6 && player == 1){
            stonesAdded += stonesCaptured - 1;
            this.getStores().get(storeTrack).addStones(stonesCaptured);
        }
        else if(stonesCaptured > 0 && currentPit >= 7 && player == 2){
            stonesAdded += stonesCaptured - 1;
            this.getStores().get(storeTrack).addStones(stonesCaptured);
        }

        
        return stonesAdded;
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
