package mancala;
import java.util.ArrayList;


public class Board {
    private ArrayList<Pit> pits;
    private ArrayList<Store> stores;

    public Board() {
        pits = new ArrayList<>();
        stores = new ArrayList<>();
        setUpPits();
        setUpStores();
        initializeBoard();
    }

    protected void setUpPits() {
        // Create and add 12 empty pits to the board
        // You can add pits as needed
    }

    protected void setUpStores() {
        // Create and add 2 empty stores to the board
        // You can add stores as needed
    }

    protected void initializeBoard() {
        // Distribute stones to pits as per the Mancala game rules
    }


    protected void resetBoard() {
        // Reset the board by redistributing stones but retain the players
    }

    protected void registerPlayers(Player one, Player two) {
        // Connect players to their stores
    }

    protected int moveStones(int startPit, Player player) {
        // Move stones for the player starting from a specific pit
        // Return any captured stones
        return 0;
    }

    protected int distributeStones(int startingPoint) {
        // Helper method that distributes stones into pits and stores, skipping the opponent's store
        return 0;
    }

    protected int captureStones(int stoppingPoint) {
        // Capture stones from the opponent's pits
        return 0;
    }

    protected int getNumStones(int pitNum) {
        // Get the number of stones in a specific pit
        return 0;
    }

    protected boolean isSideEmpty(int pitNum) {
        // Indicates whether one side of the board is empty
        return false;
    }

    protected Player getWinner(Player[] players) {
        // Determine and return the winner based on the game's rules
        return null;
    }

    protected boolean isGameOver(Player[] players) {
        // Check if the game is over based on the game's rules
        return false;
    }

    protected ArrayList<Pit> getPits() {
        return pits;
    }

    protected ArrayList<Store> getStores() {
        return stores;
    }
}
