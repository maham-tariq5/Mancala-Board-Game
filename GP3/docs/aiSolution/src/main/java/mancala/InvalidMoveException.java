public class InvalidMoveException extends Exception {
    public InvalidMoveException() {
        super("Invalid move.");
    }

    public InvalidMoveException(String message) {
        super(message);
    }
    
    // You can include additional methods and members related to the exception here.
}
