package mancala;

import javax.swing.*;

public class KalahRules extends GameRules {
    public KalahRules() {
        super();
    }

    @Override
    public void registerPlayers(Player player1, Player player2) {
        getDataStructure().setPlayers(player1, player2);
    }

     private void switchPlayer() {
        currentPlayer = (currentPlayer == 1) ? 2 : 1;
    }

    @Override
    int distributeStones(int startPit) {
        int stones = getDataStructure().getNumStones(startPit);
        getDataStructure().removeStones(startPit);
        int currPit = startPit;

        //get current player
        int playerNum = getCurrentPlayer();

        while (stones > 0) {
            currPit++;

            if (currPit > 13) {
                currPit = 0;
            }

            if ((currentPlayer == 1 && currPit == 13) || (currentPlayer == 2 && currPit == 6)) {
                continue;
            }
            getDataStructure().addStones(currPit, 1);
            stones--;
        }

        return currPit;
    }

     private int getStoreIndex(int playerNum) {
        return (playerNum == 1) ? 6 : 13;
    }


    @Override
    public int moveStones(int startPit, int playerNum) throws InvalidMoveException {
        validateMove(startPit, playerNum);

        int lastPit = distributeStones(startPit);

        if (lastPit != getStoreIndex(playerNum)) {
            switchPlayer();
        }

        checkWinner();
        return getDataStructure().getNumStones(getStoreIndex(playerNum));
    }

    private void validateMove(int startPit, int playerNum) throws InvalidMoveException {
        if (startPit < 0 || startPit > 12) {
            throw new InvalidMoveException("Invalid pit number.");
        }
        if (playerNum != currentPlayer) {
            throw new InvalidMoveException("Not player " + playerNum + "'s turn.");
        }
        if (getDataStructure().getNumStones(startPit) == 0) {
            throw new InvalidMoveException("Selected pit is empty.");
        }
    }

    

    @Override
    int captureStones(int stoppingPoint) {
        int capturedStones = 0;
        int playerNum = getCurrentPlayer();
        int opponentNum = (playerNum == 1) ? 2 : 1;

        //if the last stone was dropped in an empty pit on the player's side capture opposing stones
        int oppositePit = 12 - stoppingPoint;

        if (playerNum == 1) {
            if (stoppingPoint >= 0 && stoppingPoint <= 5 && getDataStructure().getNumStones(stoppingPoint) == 1) {
                if (getDataStructure().getNumStones(oppositePit) > 0) {
                    capturedStones = getDataStructure().getNumStones(stoppingPoint) + getDataStructure().getNumStones(oppositePit)-1;
                    getDataStructure().removeStones(oppositePit);
                    getDataStructure().addStones(getStoreIndex(playerNum), capturedStones);
                }
            }
        } else {
            if (stoppingPoint >= 7 && stoppingPoint <= 12 && getDataStructure().getNumStones(stoppingPoint) == 1) {
                if (getDataStructure().getNumStones(oppositePit) > 0) {
                    capturedStones = getDataStructure().getNumStones(stoppingPoint) + getDataStructure().getNumStones(oppositePit)-1;
                    getDataStructure().removeStones(oppositePit);
                    getDataStructure().addStones(getStoreIndex(playerNum), capturedStones);
                }
            }
        }
        return capturedStones;
    }

    private void checkWinner() {
        if (canMove(1) && canMove(2)) {
            return;
        }

        int p1SStones = 0;
        for (int i = 0; i <= 6; i++) {
            p1SStones += getDataStructure().getNumStones(i);
        }

        int p2SStones = 0;
        for (int i = 7; i <= 13; i++) {
            p2SStones += getDataStructure().getNumStones(i);
        }

        String winner = (p1SStones > p2SStones) ? "Player 1" : "Player 2";
        String message = "Game over! " + winner + " wins!";
        JDialog dialog = new JDialog();
        JOptionPane.showMessageDialog(dialog, message);
        System.exit(0);
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

   

   
}