package utils;

import model.Block;

public class Utils {

    public static Block makeBlock(String s){
        switch (s) {
            case "r":
                return new Block(MyColor.RED);
            case "b":
                return new Block(MyColor.BLUE);
            case "g":
                return new Block(MyColor.GREEN);
            case "y":
                return new Block(MyColor.YELLOW);
            case "c":
                return new Block(MyColor.CYAN);
            case "o":
                return new Block(MyColor.ORANGE);
            case "p":
                return new Block(MyColor.PINK);
            case "w":
                return new Block(MyColor.WHITE);
            case "v":
                return new Block(MyColor.VIOLET);
            default:
                return null;
        }
    }
}
