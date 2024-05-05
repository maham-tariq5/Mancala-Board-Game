package mancala;



public class InvalidMoveException extends Exception {
    public InvalidMoveException() {
        super("Invalid move.");
    }

    public InvalidMoveException(String message) {
        super(message);
    }
    
    
}
