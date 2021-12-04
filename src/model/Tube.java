package model;

import java.util.Stack;

public class Tube {
    private final static Integer TubeSize = 4;
    private Stack<Block> tube = new Stack<>();

    public Boolean push(Block block) {
        if (isFull()) {
            return false;
        }
        tube.push(block);
        return true;
    }

    private Boolean isFull() {
        return tube.size() == TubeSize;
    }

    private Boolean isEmpty() {
        return tube.empty();
    }

    public Block top() {
        return tube.peek();
    }

    public Block pop() {
        if (isEmpty()) {
            return null;
        }
        Block block = tube.peek();
        tube.pop();
        return block;
    }

}
