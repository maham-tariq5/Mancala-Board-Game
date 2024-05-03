package mancala;


// Used AI code for this
public class PitNotFoundException extends Exception {
    public PitNotFoundException() {
        super("Pit not found.");
    }

    public PitNotFoundException(String message) {
        super(message);
    }
}
