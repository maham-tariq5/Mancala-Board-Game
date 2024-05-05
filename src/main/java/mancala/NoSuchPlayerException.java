package mancala;


public class NoSuchPlayerException extends Exception {
    public NoSuchPlayerException() {
        super("No such player exists.");
    }

    public NoSuchPlayerException(String message) {
        super(message);
    }
}
