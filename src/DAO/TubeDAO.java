package DAO;

import model.Tube;
import utils.Constant;
import utils.Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TubeDAO {
    private List<Tube> tubeList;
    private int level;
    public TubeDAO(int level){
        this.level = level;
        this.tubeList = readLevelFile(level);
    }

    private List<Tube> readLevelFile(int level){
        BufferedReader reader;
        int emptyTubeNum = 0;
        List<Tube> tubes = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(Constant.LEVEL_PATH + level +".level"));
            String line = reader.readLine();
            while (line != null) {
                if (line.contains("#")){
                    line = line.replaceAll("#", "");
                    emptyTubeNum = Integer.valueOf(line);
                }else {
                    Tube tube = new Tube();
                    line = line.replaceAll("\\n", "");
                    for (char ch: line.toCharArray()){
                        tube.push(Utils.makeBlock(String.valueOf(ch)));
                    }
                    tubes.add(tube);
                }

                // read next line
                line = reader.readLine();
            }
            for (int i = 0; i < emptyTubeNum; i++){
                tubes.add(new Tube());
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tubes;
    }

    public List<Tube> getTubeList(){
        return this.tubeList;
    }
}
