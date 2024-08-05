package mancala;

import javax.swing.*;

public class AyoRules extends GameRules {
    public AyoRules() {
        super();
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    private int getStoreIndex(int playerNum) {
        return (playerNum == 1) ? 6 : 13;
    }

    @Override
    public int moveStones(int startPit, int playerNum) throws InvalidMoveException {
        // error throws
        if (startPit < 0 || startPit > 12) {
            throw new InvalidMoveException("Invalid pit number.");
        }
        if (playerNum != currentPlayer) {
            throw new InvalidMoveException("It is not player " + playerNum + "'s turn.");
        }
        if (getDataStructure().getNumStones(startPit) == 0) {
            throw new InvalidMoveException("The selected pit is empty.");
        }

        
        int finalPit = distributeStones(startPit);
        captureStones(finalPit);

        // bonus turn check
        if (finalPit != getStoreIndex(playerNum)) {
            currentPlayer = (currentPlayer == 1) ? 2 : 1;
        }else {
            System.out.println("Player " + playerNum + " gets a bonus turn.");
        }

        return getDataStructure().getNumStones(getStoreIndex(playerNum)); 
    }


    @Override
    public void registerPlayers(Player player1, Player player2) {
        getDataStructure().setPlayers(player1, player2);
    }


    @Override
    int distributeStones(int startPit) {
        int stones = getDataStructure().getNumStones(startPit);
        getDataStructure().removeStones(startPit);
        int currentPit = startPit;

        //get current player
        int playerNum = getCurrentPlayer();

        while (stones > 0) {
            currentPit++;

            if (currentPit > 13) {
                currentPit = 0;
            }

            if ((currentPlayer == 1 && currentPit == 13) || (currentPlayer == 2 && currentPit == 6)) {
                continue;
            }

            getDataStructure().addStones(currentPit, 1);
            stones--;

            //System.out.println("Distributed to pit " + currentPit);

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        canMove(playerNum);

        return currentPit;
    }

    private boolean canMove(int playerNum) {
        if (playerNum == 1) {
            for (int i = 0; i < 6; i++) {
                if (getDataStructure().getNumStones(i) > 0) {
                    return true;
                }
            }
        } else {
            for (int i = 7; i < 13; i++) {
                if (getDataStructure().getNumStones(i) > 0) {
                    return true;
                }
            }
        }

        return false;
    }

    public void endGame() {

        if (canMove(1) && canMove(2)) {
            return;
        }

        int stonesInStoreForPlayer1 = 0;
        for (int i = 0; i <= 6; i++) {
            stonesInStoreForPlayer1 += getDataStructure().getNumStones(i);
        }

        int stonesInStoreForPlayer2 = 0;
        for (int i = 7; i <= 13; i++) {
            stonesInStoreForPlayer2 += getDataStructure().getNumStones(i);
        }

        String winner = (stonesInStoreForPlayer1 > stonesInStoreForPlayer2) ? "Player 1" : "Player 2";
        String message = "Game over " + winner + " wins";
        JDialog dialog = new JDialog();
        JOptionPane.showMessageDialog(dialog, message);
        System.exit(0);
    }

    

    @Override
    int captureStones(int stopPit) {
        int capturedStones = 0;
        int playerNum = getCurrentPlayer();
        int opponentNum = (playerNum == 1) ? 2 : 1;

        //if last stone was dropped in an empty pit on the player's side capture the opposing pit
        int oppositePit = 12 - stopPit;

        if (playerNum == 1) {
            if (stopPit >= 0 && stopPit <= 5 && getDataStructure().getNumStones(stopPit) == 1) {
                if (getDataStructure().getNumStones(oppositePit) > 0) {
                    capturedStones = getDataStructure().getNumStones(stopPit) + getDataStructure().getNumStones(oppositePit)-1;
                    getDataStructure().removeStones(oppositePit);
                    getDataStructure().addStones(getStoreIndex(playerNum), capturedStones);
                }
            }
        } else {
            if (stopPit >= 7 && stopPit <= 12 && getDataStructure().getNumStones(stopPit) == 1) {
                if (getDataStructure().getNumStones(oppositePit) > 0) {
                    capturedStones = getDataStructure().getNumStones(stopPit) + getDataStructure().getNumStones(oppositePit)-1;
                    getDataStructure().removeStones(oppositePit);
                    getDataStructure().addStones(getStoreIndex(playerNum), capturedStones);
                }
            }
        }
        return capturedStones;
    }

    
}
