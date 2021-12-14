package ui;

import DAO.TubeDAO;
import model.Block;
import model.Step;
import model.Tube;
import utils.Constant;
import utils.Utils;

import javax.swing.*;
import java.awt.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.List;

public class GamePanel extends JPanel implements MouseListener, MouseMotionListener {

    private List<Tube> tubeList;
    private int size;
    private boolean isDrag = false;
    private Block top;
    private double ax, ay, width, height;
    private int level;
    private int moveCount;
    public int levelQuantity;
    private int tubePop;
    private LinkedList<Step> undo;
    private static final int undoSize = 5;

    public GamePanel(){
        this.levelQuantity = Utils.getLevelQuantity();
        this.level = 1;
        init(this.level);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    public int getLevel(){
        return this.level;
    }

    private void init(int level){
        TubeDAO tubeDAO = new TubeDAO(level);
        this.tubeList = tubeDAO.getTubeList();
        this.undo = new LinkedList<>();
        this.size = tubeList.size();
        this.top = null;
        this.ax = 0.0;
        this.ay = 0.0;
        this.width = 0.0;
        this.height = 0.0;
        this.moveCount = 0;
    }

    public int currentTube(Point p){
        Rectangle2D.Double r= new Rectangle2D.Double();
        int x;
        int y;
        if (this.size < 7) {
            x = getXStart(this.size);
            y = (getHeight() - Constant.TUBE_HEIGHT) / 2 - 50;

            for (int i = 0; i < this.size; i++){
                r.setFrame(x, y, Constant.TUBE_WIDTH, Constant.TUBE_HEIGHT);
                x += this.getWidth()/this.size;
                if (r.contains(p)){
                    return i;
                }
            }
        } else {
            int row1Size = (this.size + 1) / 2;
            int row2Size = this.size - row1Size;
            // Get Position of row 1
            x = getXStart(row1Size);
            y = 50;

            for (int i = 0; i < row1Size; i++){
                r.setFrame(x, y, Constant.TUBE_WIDTH, Constant.TUBE_HEIGHT);
                x += this.getWidth()/row1Size;
                if (r.contains(p)){
                    return i;
                }
            }
            // get Position of row 2
            x = getXStart(row2Size);
            y = y + Constant.TUBE_HEIGHT + Constant.ROW_TUBE_DISTANCE;

            for (int i = row1Size; i < this.size; i++){
                r.setFrame(x, y, Constant.TUBE_WIDTH, Constant.TUBE_HEIGHT);
                x += this.getWidth()/row2Size;
                if (r.contains(p)){
                    return i;
                }
            }
        }
        return -1;
    }

    public boolean isCompleteGame(){
        for (Tube tube: this.tubeList){
            if (!tube.isHomogenous()){
                return false;
            }
        }
        return true;
    }

    public void reset(){
        init(this.level);
        repaint();
    }

    public void nextLevel() {
        this.level++;
        init(this.level);
        repaint();
    }
    public void preLevel() {
        if (this.level > 1){
            this.level--;
            init(this.level);
            repaint();
        }
    }

    public boolean isUndo() {
        if (this.undo.size() == 0){
            return false;
        }
        return true;
    }

    public void undo() {
        if (isUndo()){
            Step step = this.undo.getLast();
            this.tubeList.get(step.getTubeTaken()).push(step.getBlockPop());
            this.tubeList.get(step.getTubeGiven()).pop();
            this.undo.removeLast();
            this.moveCount--;
            repaint();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g1=(Graphics2D)g;
        int x;
        int y;

        if (this.size < 7) {
//          Reduced from x = (getWidth() - (Constant.TUBE_WIDTH * this.size + (getWidth() / this.size - Constant.TUBE_WIDTH) * (this.size - 1)))/2
            x = getXStart(this.size);
            y = (getHeight() - Constant.TUBE_HEIGHT) / 2 - 50;

            drawAllTube(g1, 0, this.size, this.size, x, y);
        } else {
            int row1Size = (this.size + 1) / 2;
            int row2Size = this.size - row1Size;
            // Get Position of row 1
            x = getXStart(row1Size);
            y = 50;
            // Draw tube in row 1
            drawAllTube(g1,0, row1Size, row1Size, x, y);

            // get Position of row 2
            x = getXStart(row2Size);
            y = y + Constant.TUBE_HEIGHT + Constant.ROW_TUBE_DISTANCE;
            // Draw tube in row 2
            drawAllTube(g1, row1Size, this.size, row2Size, x, y);
        }

        if(isDrag ==true && top!=null) {
            g1.setColor(top.getColor());
            g1.fill(top);
        }
        this.setOpaque(false);
    }

    private void drawAllTube(Graphics2D g, int startTube, int endTube, int rowSize, int x, int y){
        for (int i = startTube; i < endTube; i++){
            tubeList.get(i).setX(x);
            tubeList.get(i).setY(y);
            tubeList.get(i).drawTube(g);
            x += this.getWidth()/rowSize;
        }
    }

    private int getXStart(int rowSize) {
        return (getWidth()/rowSize - Constant.TUBE_WIDTH) / 2;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point p = e.getPoint();
        int n = currentTube(p);
//        System.out.println(n);
        if (n != -1){
            if(!this.tubeList.get(n).isEmpty()){
                this.top = this.tubeList.get(n).top();
                if (top.contains(p)){
                    tubeList.get(n).pop();
                    this.ax=top.getX();
                    this.ay=top.getY();
                    this.width = p.getX() - ax;
                    this.isDrag = true;
                    this.tubePop = n;
                }
                else {
                    this.top = null;
                    this.isDrag = false;
                    this.tubePop = -1;
                }
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(this.top != null && isDrag) {
            int tubeNum = currentTube(e.getPoint());
            double x, y;
            if (tubeNum == -1) {
                tubeNum = currentTube(new Point((int) ax, (int) ay));
                if (!this.tubeList.get(tubeNum).isEmpty()) {
                    x = this.tubeList.get(tubeNum).getX() + 5;
                    y = this.tubeList.get(tubeNum).top().getY() - 42;
                }else {
                    x = this.tubeList.get(tubeNum).getX() + 5;
                    y = this.tubeList.get(tubeNum).getY() + 148;
                }
            }else {
                if (!this.tubeList.get(tubeNum).isEmpty()) {
                    if (!this.tubeList.get(tubeNum).isFull() && top.equals(tubeList.get(tubeNum).top())) {
                        x = this.tubeList.get(tubeNum).getX() + 5;
                        y = this.tubeList.get(tubeNum).top().getY() - 42;
                        if (this.tubePop != tubeNum) {
                            this.moveCount++;
                        }
                    } else {
                        tubeNum = currentTube(new Point((int) this.ax, (int) this.ay));
                        if (!this.tubeList.get(tubeNum).isEmpty()) {
                            x = this.tubeList.get(tubeNum).getX() + 5;
                            y = this.tubeList.get(tubeNum).top().getY() - 42;
                        }else {
                            x = this.tubeList.get(tubeNum).getX() + 5;
                            y = this.tubeList.get(tubeNum).getY() + 148;
                        }

                    }
                } else {
                    x = this.tubeList.get(tubeNum).getX() + 5;
                    y = this.tubeList.get(tubeNum).getY() - 190;
                    if (this.tubePop != tubeNum) {
                        this.moveCount++;
                    }
                }
            }
            this.top.setFrame(x, y, this.top.getWidth(), this.top.getHeight());
            this.tubeList.get(tubeNum).push(this.top);

            if (this.undo.size() >= undoSize){
                this.undo.removeFirst();
            }
            if (this.tubePop != tubeNum){
                this.undo.addLast(new Step(tubePop, tubeNum, this.top));
            }


            this.top = null;
            this.isDrag = false;
            repaint();
        }

//        System.out.println("move:" + moveCount);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int cx = e.getX();
        int cy = e.getY();
        if(top!=null && isDrag == true){
            top.setFrame(cx-width,cy-height,top.getWidth(),top.getHeight());
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
