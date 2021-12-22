package DAO;

import model.Step;
import utils.Constant;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class SolverDAO {
    private Stack<Step> stepStack;
    private int level;

    public SolverDAO(int level){
        this.level = level;
        stepStack = readSolFile(level);
    }

    public Stack<Step> readSolFile(int level){
        BufferedReader reader;
        Stack<Step> steps = new Stack<>();

        try {
            reader = new BufferedReader(new FileReader(Constant.SOLUTION_PATH + level +".sol"));
            String line = reader.readLine();

            while (line != null) {
                String[] str = line.split(" ");
                Step step = new Step((int)Integer.parseInt(str[0]), (int)Integer.parseInt(str[1]));
                steps.push(step);

                line = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return steps;
    }

    public Stack<Step> getStepStack() {
        return stepStack;
    }
}
