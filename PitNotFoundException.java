package mancala;

public class PitNotFoundException extends Exception {
    public PitNotFoundException(String message) {
        super("Pit not found");
    }
}
