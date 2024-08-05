package mancala;

public class GameNotOverException extends Exception {
    public GameNotOverException(String message) {
        super("Game is not over");
    }
}
