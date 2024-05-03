package mancala;

import java.util.ArrayList;
import java.util.Arrays;

public class MancalaGame {
    
    private Board board;
    private ArrayList<Player> players;
    private Player currentPlayer;

    public MancalaGame() {
        this.board = new Board();
        this.players = new ArrayList<>();
    }

    public void setPlayers(Player onePlayer, Player twoPlayer) {
        players.clear();
        players.addAll(Arrays.asList(onePlayer, twoPlayer));
        board.registerPlayers(onePlayer, twoPlayer);
        currentPlayer = onePlayer; // By default, let the first player start
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }

    public void setBoard(Board theBoard) {
        this.board = theBoard;
    }

    public Board getBoard() {
        return this.board;
    }

    public int getNumStones(int pitNum) throws PitNotFoundException {
        if (pitNum < 1 || pitNum > 12) {
            throw new PitNotFoundException("The specified pit does not exist.");
        }
        
        
        return board.getNumStones(pitNum);
    }

    public int move(int startPit) throws InvalidMoveException, NoSuchPlayerException, PitNotFoundException {

    int index = 0, lastPitMoveTracker, lastStoreMoveTracker = 0, newPitTracker, newStoreTracker = 0, playerNum, pitNum;

    // throwing exception
    if(startPit < 0 || startPit > 12){
        throw new InvalidMoveException("Invalid move, enter between 1-12 please");
    }

    if ((currentPlayer == players.get(0) && (startPit < 1 || startPit > 6))
        || (currentPlayer == players.get(1) && (startPit < 7 || startPit > 12))) {
        throw new InvalidMoveException("Invalid move: Player " + currentPlayer.getName() + " can only choose pits on their side.");
    }
    // throwing exception
    if (startPit < 0 || startPit > 12) {
        throw new PitNotFoundException("The specified pit number " + startPit + " does not exist.");
    }
    // Check if the currentPlayer exists in the players list also an exception
    if(!players.contains(currentPlayer)) {
        throw new NoSuchPlayerException("The current player is not registered in the game.");
    }

    if(currentPlayer == players.get(0)){
        pitNum = 6;
        playerNum = 1;
    }
    else{
        pitNum = 0;
        playerNum = 0;
    }

    
    lastPitMoveTracker = board.getPits().get(pitNum).getStoneCount(); // keeping track of last move for pits
    lastStoreMoveTracker = board.getStores().get(playerNum).getTotalStones(); // keeping track of last move for stores

    //calling move stones from board class
    index = board.moveStones(startPit, currentPlayer);

    newPitTracker = board.getPits().get(pitNum).getStoneCount(); // newer version for pits
    newStoreTracker= board.getStores().get(playerNum).getTotalStones(); // newer version for stores

    // rotating turns so if the last stone lands in a players store they get another turn
    if (!(newPitTracker == lastPitMoveTracker && lastStoreMoveTracker != newStoreTracker)) {
       
        setCurrentPlayer(getCurrentPlayer() == players.get(0) ? players.get(1) : players.get(0));
    }
    return index;
}

    public int getStoreCount(Player player) {
        return player.getStore().getTotalStones();
    }

    public Player getWinner() throws GameNotOverException {
        if (!isGameOver()) {
            throw new GameNotOverException();
        }

        int playerOneStones = getStoreCount(players.get(0));
        int playerTwoStones = getStoreCount(players.get(1));

        if (playerOneStones > playerTwoStones) {
            return players.get(0);
        } else if (playerOneStones < playerTwoStones) {
            return players.get(1);
        } else {
            return null; // It's a draw
        }
    }

    public boolean isGameOver() {
        
        if (board.isSideEmpty(0)) {
            // Transfer remaining stones from Player 2's pits to their store
            for (int i = 6; i < 12; i++) {
                int stones = board.getNumStones(i);
                players.get(1).getStore().addStones(stones);  // Assuming Player's store has an `addStones` method
               
            }
            return true;
        } else if (board.isSideEmpty(6)) {
            // Transfer remaining stones from Player 1's pits to their store
            for (int i = 0; i < 6; i++) {
                int stones = board.getNumStones(i);
                players.get(0).getStore().addStones(stones);
                
            }
            return true;
        }
        return false;
        
        
    }

    public void startNewGame() {
        board.resetBoard();
        setCurrentPlayer(players.get(0)); // First player starts again by default
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Player 1 Store: ").append(getStoreCount(players.get(0))).append("\n");
        sb.append("Player 2 Store: ").append(getStoreCount(players.get(1))).append("\n");

        for (int i = 0; i < 6; i++) {
            sb.append("Pit ").append(i).append(": ").append(board.getNumStones(i)).append("\n");
        }

        sb.append("----\n");

        for (int i = 6; i < 12; i++) {
            sb.append("Pit ").append(i).append(": ").append(board.getNumStones(i)).append("\n");
        }

        return sb.toString();
    }
}








