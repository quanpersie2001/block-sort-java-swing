package model;

import utils.Constant;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

public class Block extends Rectangle2D.Double {
    private Color color;
    private int x;
    private int y;

    public Block(int x,int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
        setRect(x, y, Constant.BLOCK_WIDTH, Constant.BLOCK_HEIGHT);
    }

    public Block(Color color){
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public boolean equals(Block other) {
        return this.getColor().equals(other.getColor());
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setRect(double x, double y) {
        setRect(x, y, Constant.BLOCK_WIDTH, Constant.BLOCK_HEIGHT);
    }

    @Override
    public String toString() {
        return color.toString();
    }
}
