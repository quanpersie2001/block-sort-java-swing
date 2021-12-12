package model;


import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.Stack;

public class Tube {
    private final static Integer tubeSize = 4;
    private Stack<Block> tube = new Stack<>();
    private int x;
    private int y;

    public Tube() {
    }

    public Tube(Stack<Block> tube){
        this.tube = tube;
    }

    public Tube(int x, int y, Block... blocks){
        this.x = x;
        this.y = y;
        for (Block block : blocks) {
            push(block);
        }
    }

    public void setPosBlock(){
        Iterator iterator = tube.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            Block block = (Block) iterator.next();
            int blockX = x + 5;
            int blockY = y + 148 - count * 42;
            block.setX(blockX);
            block.setY(blockY);
            block.setRect(blockX, blockY);
            count++;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Boolean push(Block block) {
        if (isFull()) {
            return false;
        }
        tube.push(block);
        return true;
    }
    public int size(){
        return tube.size();
    }

    public Boolean isFull() {
        return tube.size() == tubeSize;
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
        if (size() < tubeSize) return false;

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

    public void drawTube(Graphics2D g){
        Iterator iterator = tube.iterator();

        g.setColor(Color.black);
        //fill tube
        g.fillRect(this.x, this.y, 3, 190);
        g.fillRect(this.x + 57, this.y, 3, 190);
        g.fillRect(this.x, y+190, 60, 3);
        //fill block
        setPosBlock();
        while (iterator.hasNext()) {
            Block block = (Block) iterator.next();
            g.setColor(block.getColor());
            g.fill(block);
        }
    }

    @Override
    public String toString() {
        return this.tube.toString();
    }
}
