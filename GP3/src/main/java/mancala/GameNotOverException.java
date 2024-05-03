package mancala;


// used AI Code 
public class GameNotOverException extends Exception {
    public GameNotOverException() {
        super("The game is not over yet.");
    }

    public GameNotOverException(String message) {
        super(message);
    }
}
