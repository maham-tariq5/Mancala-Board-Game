package ui;
import mancala.Board;
import mancala.MancalaGame;
import mancala.Player;
import mancala.Store;
import mancala.Pit;
import mancala.GameNotOverException;
import mancala.InvalidMoveException;
import mancala.NoSuchPlayerException;
import mancala.PitNotFoundException;

import java.util.Scanner;

public class TextUI {

    private MancalaGame game;
    private Scanner scanner;

    public TextUI() {
        game = new MancalaGame();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        TextUI ui = new TextUI();
        ui.run();
    }

    // Main logic for running the game
    public void run() {
        System.out.println("Welcome to Mancala!");

        String playAgain;

        // used a do-while loop that runs through, once a game is complete it asks user if they wanna play again
        do {
            // Setting up players
            System.out.print("Enter name for Player 1: ");
            String name1 = scanner.nextLine();
            Player player1 = new Player(name1);

            System.out.print("Enter name for Player 2: ");
            String name2 = scanner.nextLine();
            Player player2 = new Player(name2);

            game.setPlayers(player1, player2);
            game.startNewGame();

            // Game loop
            while (!game.isGameOver()) {
                displayBoard();
                System.out.println(game.getCurrentPlayer().getName() + "'s turn.");
                System.out.print("Select a pit (1-12): ");
                
                int pit;
                while (true) {
                    try {
                        pit = Integer.parseInt(scanner.nextLine());
                
                        if (pit >= 1 && pit <= 12) {
                            
                            
                            if (game.getBoard().getPits().get(pit-1).getStoneCount() == 0) {
                                System.out.println("Pit already empty. Choose another pit.");
                               continue; // Ask for another input
                           }
                            
                            
                            
                            try {
                                game.move(pit);
                                break;  // exit the inner while loop once a valid move is made
                            } catch (InvalidMoveException ime) {
                                System.out.println(ime.getMessage());
                            } catch (NoSuchPlayerException nspe) {
                                System.out.println(nspe.getMessage());
                            } catch (PitNotFoundException pnfe) {   // Handle the PitNotFoundException here
                                System.out.println(pnfe.getMessage());
                            }
                        } else {
                            System.out.println("Please enter a valid pit number between 1 and 12.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid number.");
                    }
                }
            }

            // Announcing the winner
            displayBoard();
            try {
                Player winner = game.getWinner();
                if (winner == null) {
                    System.out.println("The game is a tie!");
                } else {
                    System.out.println(winner.getName() + " wins!");
                }
            } catch (GameNotOverException e) {
                System.out.println(e.getMessage());
            }

            // Prompt to play again
            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = scanner.nextLine().trim().toLowerCase();

        } while (playAgain.equals("yes"));

        System.out.println("Thanks for playing!");
    }

    public void displayBoard() {
        Board currentBoard = game.getBoard();

        // Display Player 2's pits
        System.out.print("\t");
        for (int i = 11; i >= 6; i--) {  
            System.out.print(currentBoard.getPits().get(i).getStoneCount() + " ");
        }
        System.out.println();
    
        // Display Player 2 Store and Player 1 Store
        System.out.println("(" + currentBoard.getStores().get(0).getTotalStones() + ")\t\t\t("
                + currentBoard.getStores().get(1).getTotalStones() + ")");
    
        // Display Player 1's pits
        System.out.print("\t");
        for (int i = 0; i < 6; i++) {
            System.out.print(currentBoard.getPits().get(i).getStoneCount() + " ");
        }
        System.out.println("\n");
    }
}

