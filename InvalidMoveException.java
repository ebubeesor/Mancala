package mancala;

public class InvalidMoveException extends Exception {
    public InvalidMoveException(String message) {
        super("Invalid move");
    }
}
