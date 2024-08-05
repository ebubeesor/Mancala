package mancala;

public class Store implements Countable {
    private int storeLocation;
    private MancalaDataStructure dataStructure;

    public Store(int location, MancalaDataStructure dataStructure) {
        this.storeLocation = location;
        this.dataStructure = dataStructure;
    }

    public Store() {
        // Default constructor for testing
        this.storeLocation = 1;
        this.dataStructure = new MancalaDataStructure();
    }

    @Override
    public int getStoneCount() {
        return dataStructure.getNumStones(storeLocation);
    }

    @Override
    public void addStone() {
        dataStructure.addStones(storeLocation, 1);
    }

    @Override
    public void addStones(int numToAdd) {
        dataStructure.addStones(storeLocation, numToAdd);
    }

    @Override
    public int removeStones() {
        int stones = getStoneCount();
        dataStructure.removeStones(storeLocation);
        return stones;
    }

    public int getTotalStones() {
        return getStoneCount();
    }
}
