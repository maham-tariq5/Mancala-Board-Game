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

    // initializing pits
    public void setUpPits() {
        // Create 12 pits
        for (int i = 0; i < 12; i++) {
            pits.add(new Pit());
        }
    }

    public ArrayList<Pit> getPits() {
        return this.pits;
    }

    // initializing stores
    public void setUpStores() {
        stores = new ArrayList<>();
        stores.add(new Store());
        stores.add(new Store());
    }

    public ArrayList<Store> getStores() {
        return this.stores;
    }

    // setting stones up for all pits
    public void initializeBoard() {

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

    // linking players to their stores
    public void registerPlayers(Player one, Player two) {
        stores.get(1).setOwner(one);
        stores.get(0).setOwner(two);
        one.setStore(stores.get(1));
        two.setStore(stores.get(0));
    }

    // handling capturing of stones
    public int captureStones(int stoppingPoint) {
        int stonesCaptured = 0;
        int oppositePoint = 11 - stoppingPoint; // calc opposite pit index

        
        if (pits.get(oppositePoint).getStoneCount() > 0 && pits.get(stoppingPoint).getStoneCount() == 1) {
            stonesCaptured += pits.get(oppositePoint).getStoneCount();
            pits.get(oppositePoint).removeStones(); // clear captured stones
            stonesCaptured += pits.get(stoppingPoint).getStoneCount();
            pits.get(stoppingPoint).removeStones(); // clear last pit
        }
        return stonesCaptured;
    }

    // distribute from a chosen starting point
    public int distributeStones(int startingPoint) {
        int stonesToDistribute = getNumStones(startingPoint); // get # of stones in chosen pit
        int stonesAdded = stonesToDistribute; // tracking stones added
        int stonesCaptured;

        int player = (startingPoint < 6) ? 1 : 2; // determine which player is playing
        int storeTrack = (player == 1) ? 1 : 0; // determine which store to update

        if (stonesToDistribute == 0) {
            return 0; // no stones to distribute
        }

        pits.get(startingPoint).removeStones();

        int currentPit = startingPoint;

        // Continue distributing stones until none are left in hand.
        while (stonesToDistribute > 0) {
            currentPit = (currentPit + 1) % 12; // move to the next pit

             // Adjust the store track when skipping the opponent's store.
            if (currentPit == 6) {
                storeTrack = (player == 1) ? 0 : 1;
            }

            // Special condition for placing stones in pits and potentially adding one to the store.
            if ((currentPit == 5 && player == 1) || (currentPit == 11 && player == 2) ||
                    (currentPit == 5 && startingPoint == 11 && player == 2 && stonesToDistribute >= 2) ||
                    (currentPit == 11 && startingPoint == 5 && player == 1 && stonesToDistribute >= 2)) {
                pits.get(currentPit).addStone();
                stores.get(storeTrack).addStones(1);
                stonesToDistribute--;
            } else if ((currentPit == 5 && stonesToDistribute == 1 && player == 1) ||
                    (currentPit == 11 && stonesToDistribute == 1 && player == 2)) {
                stores.get(storeTrack).addStones(1);
                return 1; // add last stone to the store
            } else {
                pits.get(currentPit).addStone();
            }
            stonesToDistribute--;
        }

        // Attempt to capture stones from the opponent if the last stone lands in an appropriate pit.
        stonesCaptured = captureStones(currentPit);

        if (stonesCaptured > 0 && ((currentPit <= 6 && player == 1) || (currentPit >= 7 && player == 2))) {
            stonesAdded += stonesCaptured - 1;
            stores.get(storeTrack).addStones(stonesCaptured);
        }

        return stonesAdded;
    }

    // handle a player's move from a specfic pit
    public int moveStones(int startPit, Player player) throws InvalidMoveException {
        
        // Validate the start pit index to ensure it's within the playable range.
        if (startPit < 1 || startPit > 12) {
            throw new InvalidMoveException("Move is out of bounds.");
        }

        startPit -= 1;

        // Determine which player's store to use based on the pit index chosen for the move.
        int currentPlayerIndex = (startPit < 6) ? 0 : 1;
        // Capture the initial amount of stones in the player's store to calculate the net change after the move.
        int oldStoreValue = getStores().get(currentPlayerIndex).getTotalStones();

        distributeStones(startPit);

        // Fetch the new total of stones in the store after the stones have been distributed.
        int newStoreValue = getStores().get(currentPlayerIndex).getTotalStones();
        int stonesMoved = newStoreValue - oldStoreValue;

        return stonesMoved;
    }

    public int getNumStones(int pitNum) {
        return pits.get(pitNum).getStoneCount();
    }

    // checking if all pits on one side of the board are empty
    public boolean isSideEmpty(int pitNum) {
        int start = (pitNum < 6) ? 0 : 6;
        int end = start + 5;

        for (int i = start; i < end; i++) {
            if (pits.get(i).getStoneCount() > 0) {
                return false;
            }
        }
        return true;
    }

    // string representation of the board state
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


