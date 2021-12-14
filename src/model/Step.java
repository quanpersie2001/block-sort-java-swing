package model;

public class Step {
    private int tubeTaken;
    private int tubeGiven;
    private Block blockPop;

    public Step(int tubeTaken, int tubeGiven, Block blockPop) {
        this.tubeTaken = tubeTaken;
        this.tubeGiven = tubeGiven;
        this.blockPop = blockPop;
    }

    public int getTubeTaken() {
        return tubeTaken;
    }

    public void setTubeTaken(int tubeTaken) {
        this.tubeTaken = tubeTaken;
    }

    public int getTubeGiven() {
        return tubeGiven;
    }

    public void setTubeGiven(int tubeGiven) {
        this.tubeGiven = tubeGiven;
    }

    public Block getBlockPop() {
        return blockPop;
    }
}
