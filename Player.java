package mancala;

public class Player {
    private String playerName;
    private Store playerStore;
    private boolean playingRn;

    public Player() {
        playerName = " ";
        playingRn = false;
    }

    public Player(String name) {
        this.playerName = name;
        this.playingRn = true; // Set to true when the player is active in a game
    }

    public void setName(String name) {
        this.playerName = name;
    }

    public void setStore(Store store) {
        this.playerStore = store;
    }

    public Store getStore() {
        return playerStore;
    }

    public String getName() {
        return playerName;
    }

    public int getStoreCount() {
        return playerStore.getTotalStones();
    }

    public boolean isPlaying() {
        return playingRn;
    }

    public void setPlaying(boolean playing) {
        this.playingRn = playing;
    }

    @Override
    public String toString() {
        return "Player Name: " + playerName + ", Store: " + playerStore.getTotalStones() + " stones";
    }
}
