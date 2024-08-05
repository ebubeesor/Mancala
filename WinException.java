package mancala;

public class WinException extends Throwable {
    public WinException(String gameOver) {
        super(gameOver);
    }
}
