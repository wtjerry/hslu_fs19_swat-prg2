package connectfour.views.subpanel.components;

import java.awt.Color;
import java.awt.Graphics;

class DiskField {
    private Color color;
    private boolean played;
    private final int diameter;

    DiskField(int diameter) {
        this.diameter = diameter;
        this.played = false;
    }

    void diskPlayed(Color color){
        this.diskPlayed();
        this.setColor(color);
    }

    boolean isPlayed(){
        return this.played;
    }

    void draw(int x, int y, Graphics g){
        if(this.played){
            g.setColor(this.color);
        }else{
            g.setColor(Color.WHITE);
        }
        g.fillOval(x, y, this.diameter, this.diameter);
    }

    private void diskPlayed(){
        this.played = true;
    }

    private void setColor(Color color){
        this.color = color;
    }
}
