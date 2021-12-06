package model;


import java.util.Stack;

public class Tube {
    private final static Integer TubeSize = 4;
    private Stack<Block> tube = new Stack<>();

    public Tube() {
    }

    public Tube(Stack<Block> tube){
        this.tube = tube;
    }

    public Tube(Block... blocks ){
        for (Block block : blocks) {
            addBlock(block);
        }
    }

    public Boolean push(Block block) {
        if (isFull()) {
            return false;
        }
        tube.push(block);
        return true;
    }

    public Boolean addBlock(Block block){
        if (isFull()) return false;
        tube.add(block);
        return true;
    }

    public Boolean isFull() {
        return tube.size() == TubeSize;
    }

    public Boolean isEmpty() {
        return tube.empty();
    }

    public Block top() {
        return tube.peek();
    }

    public Block pop() {
        if (isEmpty()) {
            return null;
        }
        return tube.pop();
    }

    public Boolean isHomogenous() {
        if (isEmpty()) return true;
        Stack<Block> temp = (Stack<Block>) tube.clone();

        Block block = temp.pop();
        while (!temp.empty()) {
            if (!temp.peek().equals(block)) {
                return false;
            }
            temp.pop();
        }
        return true;
    }

    @Override
    public String toString() {
        return this.tube.toString();
    }
}
