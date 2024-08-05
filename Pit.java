package mancala;

public class Pit implements Countable {
    private int stoneCount;

    @Override
    public int getStoneCount() {
        return stoneCount;
    }

    @Override
    public void addStone() {
        stoneCount++;
    }

    @Override
    public void addStones(int numToAdd) {
        stoneCount += numToAdd;
    }

    @Override
    public int removeStones() {
        int removed = stoneCount;
        stoneCount = 0;
        return removed;
    }
}
