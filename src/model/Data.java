package model;

import java.io.Serializable;

public class Data implements Serializable {
    private String name;
    private int level;

    public Data(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return this.name + "-" + this.level;
    }
}
