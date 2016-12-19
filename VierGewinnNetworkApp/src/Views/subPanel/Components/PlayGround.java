package Views.subPanel.Components;

import Views.subPanel.Components.DiskField;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Arc2D;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

public class PlayGround extends JComponent{
    private static final int ROWCOUNT=4;
    private static final int COLUMNCOUNT=4;
    private static final int YSIZE=200;
    private static final int XSIZE=250;
    private static final double GAPSIZEPROZENT=0.02;
    
    private int currentLocation;
    private int xCount;
    private int yCount;
    private Color color;
    private Color player;
    private Color opponent;
    private int spaceX;
    private int spaceY;
    private int diskSize;
    private boolean mouseEventEnabled;
    private int currentPlayer;
    private double gap;
    
    private DiskField[][] disks;
    
    
    public PlayGround(){
        this(new Dimension(XSIZE, YSIZE), ROWCOUNT, COLUMNCOUNT, GAPSIZEPROZENT);
    }
    public PlayGround(Dimension size){
        this(size, ROWCOUNT, COLUMNCOUNT, GAPSIZEPROZENT);
    }
    public PlayGround(int rowCount, int columnCount){
        this(new Dimension(XSIZE, YSIZE), rowCount, columnCount, GAPSIZEPROZENT);
    }
    public PlayGround(Dimension size, int rowCount, int columnCount){
        this(size, rowCount, columnCount, GAPSIZEPROZENT);        
    }
    public PlayGround(int rowCount, int columntCount, double gapSizeProzent){
        this(new Dimension(XSIZE, YSIZE), rowCount, columntCount, gapSizeProzent);
    }
    public PlayGround(int xWidth, int yWidth, int rowCount,int columnCount){
        this(new Dimension(xWidth, yWidth), rowCount, columnCount, GAPSIZEPROZENT);
    }
    public PlayGround(Dimension size, int xCount, int yCount, double gapProzent){
        super();
        this.initialize(size, xCount, yCount, gapProzent);
    }
    private void initialize(Dimension size, int xCount, int yCount, double gapProzent){
        this.gap = gapProzent;
        this.xCount = xCount;
        this.yCount = yCount;
        this.color = new Color(51, 153, 255);
        this.player = new Color(255,255,51);
        this.opponent = new Color(255,51,51);
        this.spaceX = (int) (size.width * gapProzent);
        this.diskSize = (size.width - ((xCount+1)*this.spaceX))/xCount;
        this.spaceY = (int) (size.height-(this.diskSize*yCount))/(yCount+1);
        this.spaceY = (this.spaceY<0)?2:this.spaceY;
        this.mouseEventEnabled = true;
        this.currentPlayer = 0;
        size.width = (this.spaceX*(xCount+1))+(this.diskSize*xCount);
        size.height = (this.spaceY*(yCount+1))+(this.diskSize*yCount)+(this.diskSize/2);
        this.setPreferredSize(size);
        this.currentLocation = 0;
        
        this.disks = new DiskField[xCount][yCount];
        for(int i=0;i<xCount;i++){
            for(int i2=0; i2<yCount;i2++){
                this.disks[i][i2] = new DiskField(this.diskSize);
            }
        }
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e){
                mouseMovedInComponent(e);
            }        
        });
        
    }
    
    public void setColor(Color color){
        this.color = color;
    }
    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        
        graphics.setColor((this.currentPlayer==0)?this.player:this.opponent);
        ((Graphics2D) (graphics)).fill(new Arc2D.Double(this.currentLocation*
                (this.spaceX+this.diskSize)+(this.spaceX), 0, this.diskSize, 
                this.diskSize, 0, 180, Arc2D.PIE));
        
        graphics.setColor(this.color);
        graphics.fillRect(0,this.diskSize/2, this.getWidth(), this.getHeight()-(this.diskSize/2));
        int x=this.spaceX;
        for(DiskField[] disks:this.disks){
            int y=this.spaceY+this.diskSize/2;
            for(DiskField disk:disks){
                disk.draw(x, y, graphics, this.diskSize);
                y+=this.spaceY+this.diskSize;
            }
            x+=this.spaceX+this.diskSize;
        }
    }
    public void mouseMovedInComponent(MouseEvent event){
        int loc = calcLocation(event.getX());
        if(this.currentLocation != loc  && this.mouseEventEnabled){
            this.currentLocation = loc;
            this.repaint(0, 0, this.getWidth(),this.diskSize/2);
        }
    }
    protected int calcLocation(int x){
        int ret = (int)(x/(this.diskSize+this.spaceX));
        if(ret >= this.xCount)
            return ret-1;
        return ret;
    }
    public int getColumn(){
        return this.currentLocation;
    }
    public void playerDiskPlayed(int x, int y){
        this.disks[x][y].diskPlayed(this.player);
        repaint();
    }
    public void opponentDiskPlayed(int x, int y){
        this.disks[x][y].diskPlayed(this.opponent);
        repaint();
    }

    public void start() {
        for(int i=0;i<xCount;i++){
            for(int i2=0; i2<yCount;i2++){
                this.disks[i][i2] = new DiskField(this.diskSize);
            }
        }
    }
    public void start(int x, int y){
        this.initialize(this.getSize(), x, y, this.gap);
    }
    public void playerDiskPlayedUpsideDown(int x, int y) {
        this.playerDiskPlayed(x, (this.yCount-1)-y);
    }

    public void opponentDiskPlayedUpsideDown(int x, int y) {
       this.opponentDiskPlayed(x, (this.yCount-1)-y);
    }

    public boolean notFull() {
        return !this.disks[this.currentLocation][0].isPlayed();
    }
    @Override
    public Dimension getSize(){
        return new Dimension((xCount+1)*spaceX+xCount*diskSize, (yCount+1)*spaceY+yCount*diskSize);
    }
}
    
