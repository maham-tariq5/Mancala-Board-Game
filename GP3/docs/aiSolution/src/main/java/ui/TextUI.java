package ui;
import mancala.MancalaGame;
import mancala.Player;
import java.util.Scanner;

public class TextUI {
    
    

   
 
    private MancalaGame mancalaGame;
    private Scanner scanner;

    public TextUI() {
        mancalaGame = new MancalaGame();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        TextUI textUI = new TextUI();
        textUI.runGame();
    }

    public void runGame() {
        System.out.println("Welcome to Mancala!");

        // Prompt the user to set player names
        System.out.print("Enter Player 1 name: ");
        String player1Name = scanner.nextLine();
        Player player1 = new Player(player1Name);

        System.out.print("Enter Player 2 name: ");
        String player2Name = scanner.nextLine();
        Player player2 = new Player(player2Name);

        mancalaGame.setPlayers(player1, player2);

        boolean gameOver = false;

        while (!gameOver) {
            System.out.println("Current Board:");
            // Print the current state of the game board here (use mancalaGame.toString() or a custom method)

            Player currentPlayer = mancalaGame.getCurrentPlayer();
            System.out.println(currentPlayer.getName() + "'s turn");

            System.out.print("Enter pit number to move stones from: ");
            int startPit = scanner.nextInt();
            int capturedStones = mancalaGame.move(startPit);

            // Print the result of the move and the captured stones
            System.out.println("Stones moved: " + capturedStones);

            // Check if the game is over
            gameOver = mancalaGame.isGameOver();

            if (!gameOver) {
                // Switch to the next player
                mancalaGame.setCurrentPlayer(player1.equals(currentPlayer) ? player2 : player1);
            }
        }

        Player winner = mancalaGame.getWinner();
        System.out.println("Game over!");
        if (winner != null) {
            System.out.println("The winner is: " + winner.getName());
        } else {
            System.out.println("It's a tie!");
        }

        scanner.close();
    }
}