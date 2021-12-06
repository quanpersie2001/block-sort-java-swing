package model;

import java.awt.Color;

public class Block {
    Color color;
    public Block(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public boolean equals(Block other) {
        return this.getColor().equals(other.getColor());
    }

    @Override
    public String toString() {
        return color.toString();
    }
}
