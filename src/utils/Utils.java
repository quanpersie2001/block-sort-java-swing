package utils;

import model.Block;

import java.awt.*;
import java.io.File;
import java.io.IOException;

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
            case "n":
                return new Block(MyColor.NAVY);
            case "m":
                return new Block(MyColor.MAGENTA);
            case "a":
                return new Block(MyColor.AZURE);
            case "d":
                return new Block(MyColor.CORAL);
            case "e":
                return new Block(MyColor.EGGPLANT);
            case "f":
                return new Block(MyColor.FERN);
            case "h":
                return new Block(MyColor.HELIOTROPE);
            case "k":
                return new Block(MyColor.KHAKI);
            case "i":
                return new Block(MyColor.INDIGO);
            case "j":
                return new Block(MyColor.JADE);
            case "t":
                return new Block(MyColor.TEAL);
            case "l":
                return new Block(MyColor.LIME);
            default:
                return null;
        }
    }
    public static int getLevelQuantity(){
        File directory=new File(Constant.LEVEL_PATH);
        int fileCount=directory.list().length;
        return fileCount;
    }
    public static Font getMontserratFont(){
        Font montserrat = null;
        try {
            montserrat = Font.createFont(Font.TRUETYPE_FONT, new File(Constant.FONT_PATH + "Montserrat-ExtraBold.ttf"));
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return montserrat;
    }
}
