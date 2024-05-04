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

    // main stones stuff
    public int distributeStones(int startingPoint) {
        int stonesToDistribute = getNumStones(startingPoint);
        int stonesAdded = stonesToDistribute;
        int stonesCaptured;

        int player = (startingPoint < 6) ? 1 : 2;
        int storeTrack = (player == 1) ? 1 : 0;

        if (stonesToDistribute == 0) {
            return 0;
        }

        pits.get(startingPoint).removeStones();

        int currentPit = startingPoint;

        while (stonesToDistribute > 0) {
            currentPit = (currentPit + 1) % 12;

            if (currentPit == 6) {
                storeTrack = (player == 1) ? 0 : 1;
            }

            if ((currentPit == 5 && player == 1) || (currentPit == 11 && player == 2) ||
                    (currentPit == 5 && startingPoint == 11 && player == 2 && stonesToDistribute >= 2) ||
                    (currentPit == 11 && startingPoint == 5 && player == 1 && stonesToDistribute >= 2)) {
                pits.get(currentPit).addStone();
                stores.get(storeTrack).addStones(1);
                stonesToDistribute--;
            } else if ((currentPit == 5 && stonesToDistribute == 1 && player == 1) ||
                    (currentPit == 11 && stonesToDistribute == 1 && player == 2)) {
                stores.get(storeTrack).addStones(1);
                return 1;
            } else {
                pits.get(currentPit).addStone();
            }
            stonesToDistribute--;
        }

        stonesCaptured = captureStones(currentPit);

        if (stonesCaptured > 0 && ((currentPit <= 6 && player == 1) || (currentPit >= 7 && player == 2))) {
            stonesAdded += stonesCaptured - 1;
            stores.get(storeTrack).addStones(stonesCaptured);
        }

        return stonesAdded;
    }

    public int moveStones(int startPit, Player player) throws InvalidMoveException {
        if (startPit < 1 || startPit > 12) {
            throw new InvalidMoveException("Move is out of bounds.");
        }

        startPit -= 1;

        int currentPlayerIndex = (startPit < 6) ? 0 : 1;
        int oldStoreValue = getStores().get(currentPlayerIndex).getTotalStones();

        distributeStones(startPit);

        int newStoreValue = getStores().get(currentPlayerIndex).getTotalStones();
        int stonesMoved = newStoreValue - oldStoreValue;

        return stonesMoved;
    }

    public int getNumStones(int pitNum) {
        return pits.get(pitNum).getStoneCount();
    }

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


