package model;

import java.awt.*;

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
}
