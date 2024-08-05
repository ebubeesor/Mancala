package mancala;

public class MancalaDataStructure {
    private Player playerOne;
    private Player playerTwo;
    private final int[] pits; 
    

    public MancalaDataStructure() {
        pits = new int[14];
        setUpPits();
    }

    public int getNumStones(int pitNum) {
        if (pitNum >= 0 && pitNum < pits.length) {
            return pits[pitNum];
        } else {
            throw new IllegalArgumentException("Invalid pit number");
        }
    }

    public void setPlayers(Player player1, Player player2) {
        player1.setStore(new Store(6, this));
        player2.setStore(new Store(13, this));
    }

    public void setStore(Countable store, int storeNum) {
        if (storeNum == 6 || storeNum == 13) { 
            pits[storeNum] = store.getStoneCount();
        } else {
            throw new IllegalArgumentException("Invalid store number");
        }
    }

    //set up the pits ignoring the stores
    public void setUpPits() {
        for (int i = 0; i < pits.length; i++) {
            if (i != 6 && i != 13) { 
                pits[i] = 4; 
            }
        }
    }

    public void emptyStores() {
        pits[6] = 0;  
        pits[13] = 0; 
    }

    public String getStoreCount(int playerNum) {
        if (playerNum == 1) {
            return Integer.toString(pits[6]);
        } else if (playerNum == 2) {
            return Integer.toString(pits[13]);
        } else {
            throw new IllegalArgumentException("Invalid player number");
        }
    }

    public void removeStones(int pitNum) {
        if (pitNum >= 0 && pitNum < pits.length) {
            pits[pitNum] = 0;
        } else {
            throw new IllegalArgumentException("Invalid pit number");
        }
    }

    public void addStones(int pitNum, int numOfStones) {
        if (pitNum >= 0 && pitNum < pits.length) {
            pits[pitNum] += numOfStones;
        } else {
            throw new IllegalArgumentException("Invalid pit number");
        }
    }

    public int getPlayer(int currentPit) {
        if (currentPit >= 0 && currentPit < pits.length) {
            if (currentPit < 6) {
                return 1;
            } else if (currentPit > 6 && currentPit < 13) {
                return 2;
            } else {
                throw new IllegalArgumentException("Invalid pit number");
            }
        } else {
            throw new IllegalArgumentException("Invalid pit number");
        }
    }

    /**
     * Checks for a winner and returns the winning player.
     * @return The winning player, or null if the game is a tie or not yet over.
     */
    public Player checkWinner() {
        if (isSideEmpty(0, 5) || isSideEmpty(7, 12)) {
            int stonesPlayerOne = getTotalStonesOnSide(0, 5) + getNumStones(6);
            int stonesPlayerTwo = getTotalStonesOnSide(7, 12) + getNumStones(13);

            if (stonesPlayerOne > stonesPlayerTwo) {
                return playerOne;
            } else if (stonesPlayerTwo > stonesPlayerOne) {
                return playerTwo;
            } else {
                return tiePlayer();
            }
        }
        return null;
    }

    private Player tiePlayer() {
        return new Player("Tie");
    }

    private boolean isSideEmpty(int start, int end) {
        for (int i = start; i <= end; i++) {
            if (pits[i] > 0) {
                return false;
            }
        }
        return true;
    }

    private int getTotalStonesOnSide(int start, int end) {
        int totalStones = 0;
        for (int i = start; i <= end; i++) {
            totalStones += pits[i];
        }
        return totalStones;
    }
}
