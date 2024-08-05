package mancala;

public class MancalaGame {
    private GameRules board;
    private Player currentPlayer;
    private Player player1;
    private Player player2;

    public MancalaGame() {
        player1 = new Player();
        player2 = new Player();
        board = new AyoRules(); 
        board.registerPlayers(player1, player2);
        currentPlayer = player1;
    }

    public GameRules getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public int getStoreCount(Player player) {
        return player.getStoreCount();
    }

    public Player getWinner() {
        if (player1.getStoreCount() > player2.getStoreCount()) {
            return player1;
        } else if (player1.getStoreCount() < player2.getStoreCount()) {
            return player2;
        } else {
            return null; // It's a tie
        }
    }

    public int move(int startPit) {
        return board.moveStones(startPit, currentPlayer.getPlayerNum());
    }

    public void setBoard(GameRules theBoard) {
        this.board = theBoard;
    }

    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }

    public void setPlayers(Player onePlayer, Player twoPlayer) {
        this.player1 = onePlayer;
        this.player2 = twoPlayer;
    }

    public void startNewGame() {
        board = new AyoRules(); // or new KalahRules();
        board.registerPlayers(player1, player2);
        currentPlayer = player1;
    }

    public String toString() {
        return board.toString();
    }
}