package utils;

import model.Block;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class Utils {

    public static Block makeBlock(String s){
        switch (s) {
            case "r":
                return new Block(CustomizeColor.RED);
            case "b":
                return new Block(CustomizeColor.BLUE);
            case "g":
                return new Block(CustomizeColor.GREEN);
            case "y":
                return new Block(CustomizeColor.YELLOW);
            case "c":
                return new Block(CustomizeColor.CYAN);
            case "o":
                return new Block(CustomizeColor.ORANGE);
            case "p":
                return new Block(CustomizeColor.PINK);
            case "w":
                return new Block(CustomizeColor.WHITE);
            case "v":
                return new Block(CustomizeColor.VIOLET);
            case "n":
                return new Block(CustomizeColor.NAVY);
            case "m":
                return new Block(CustomizeColor.MAGENTA);
            case "a":
                return new Block(CustomizeColor.AZURE);
            case "d":
                return new Block(CustomizeColor.CORAL);
            case "e":
                return new Block(CustomizeColor.EGGPLANT);
            case "f":
                return new Block(CustomizeColor.FERN);
            case "h":
                return new Block(CustomizeColor.HELIOTROPE);
            case "k":
                return new Block(CustomizeColor.KHAKI);
            case "i":
                return new Block(CustomizeColor.INDIGO);
            case "j":
                return new Block(CustomizeColor.JADE);
            case "t":
                return new Block(CustomizeColor.TEAL);
            case "l":
                return new Block(CustomizeColor.LIME);
            default:
                return null;
        }
    }
    public static int getLevelQuantity(){
        File directory=new File(Constant.LEVEL_PATH);
        int fileCount=directory.list().length;
        return fileCount;
    }
    public static Font getFont(String style){
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File(Constant.FONT_PATH + style + ".ttf"));
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return font;
    }
}
