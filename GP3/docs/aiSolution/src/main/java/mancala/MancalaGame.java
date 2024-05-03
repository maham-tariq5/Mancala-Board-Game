package mancala;
import java.util.ArrayList;

public class MancalaGame {
    private Player[] players;
    private Board board;
    private Player currentPlayer;

    public MancalaGame() {
        players = new Player[2];
        board = new Board();
        currentPlayer = null;
    }

    public void setPlayers(Player onePlayer, Player twoPlayer) {
        players[0] = onePlayer;
        players[1] = twoPlayer;
        board.registerPlayers(onePlayer, twoPlayer);
    }
 
    public Player[] getPlayers() {
        return players;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player player) {
        currentPlayer = player;
    }

    public void setBoard(Board theBoard) {
        board = theBoard;
    }

    public Board getBoard() {
        return board;
    }

    public int getNumStones(int pitNum) {
        return board.getNumStones(pitNum);
    }

    public int move(int startPit) {
        return board.moveStones(startPit, currentPlayer);
    }

    public int getStoreCount(Player player) {
        return player.getStore().getTotalStones();
    }

    public Player getWinner() {
        return board.getWinner(players);
    }

    public boolean isGameOver() {
        return board.isGameOver(players);
    }

    public void startNewGame() {
        board.resetBoard();
    }

    @Override
    public String toString() {
        // Generate a string representation of the game
        // You can customize this method as needed
        return "MancalaGame[CurrentPlayer=" + currentPlayer.getName() + "]";

    }











}
