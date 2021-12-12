import DAO.TubeDAO;
import model.Block;
import model.Tube;

import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Point;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.util.List;

public class GamePanel extends JPanel implements MouseListener, MouseMotionListener {


    private List<Tube> tubeList;
    private int size;
    private boolean isDrag = false;
    private Block top;
    private double ax, ay, width, height;
    private int startX = 40;
    private int startY = 40;
    private int level;
    private int moveCount;

    public GamePanel(){
        this.level = 1;
        init(this.level);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    private void init(int level){
        TubeDAO tubeDAO = new TubeDAO(level);
        this.tubeList = tubeDAO.getTubeList();
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
        int x = startX;
        int y = startY;
        for (int i = 0; i < this.size; i++){
            r.setFrame(x, y, 60, 190);
            x += getWidth()/this.size;
            if (r.contains(p)){
                return i;
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g1=(Graphics2D)g;
        int x = startX;
        int y = startY;

        for (int i = 0; i < this.size; i++){
            tubeList.get(i).setX(x);
            tubeList.get(i).setY(y);
            tubeList.get(i).drawTube(g1);
            x += getWidth()/this.size;
        }
        if(isDrag ==true && top!=null) {
            g1.setColor(top.getColor());
            g1.fill(top);
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point p= e.getPoint();
        int n= currentTube(p);
        if (n != -1){
            if(!this.tubeList.get(n).isEmpty()){
                this.top = this.tubeList.get(n).top();
                if (top.contains(p)){
                    tubeList.get(n).pop();
                    this.ax=top.getX();
                    this.ay=top.getY();
                    this.width = p.getX() - ax;
                    this.isDrag = true;
                }
                else {
                    this.top = null;
                    this.isDrag = false;
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
                    if (!this.tubeList.get(tubeNum).isFull()) {
                        x = this.tubeList.get(tubeNum).getX() + 5;
                        y = this.tubeList.get(tubeNum).top().getY() - 42;
                        this.moveCount++;
                    } else {
                        JOptionPane.showMessageDialog(this, "This tube is full", "Block Sort", JOptionPane.ERROR_MESSAGE);
                        tubeNum = currentTube(new Point((int) this.ax, (int) this.ay));
                        x = this.tubeList.get(tubeNum).getX() + 5;
                        y = this.tubeList.get(tubeNum).top().getY() - 42;

                    }
                } else {
                    x = this.tubeList.get(tubeNum).getX() + 5;
                    y = this.tubeList.get(tubeNum).getY() - 190;
                    this.moveCount++;
                }
            }
            this.top.setFrame(x, y, this.top.getWidth(), this.top.getHeight());
            this.tubeList.get(tubeNum).push(this.top);

            this.top = null;
            this.isDrag = false;
            repaint();
        }

        if (isCompleteGame()) {
            JOptionPane.showMessageDialog(this, "Complete", "Block Sort", JOptionPane.INFORMATION_MESSAGE);
            this.level++;
            init(this.level);
            repaint();
        }
        System.out.println("move:" + moveCount);
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
