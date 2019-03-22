package connectfour.model;

public class DiskPosition {

    private static final DiskPosition invalidPosition = new DiskPosition(-1, -1);
    
    private final int column;
    private final int row;

    public DiskPosition(int column, int row) {
        this.column = column;
        this.row = row;
    }
    
    public int getColumn(){
        return this.column;
    }
    
    public int getRow() {
        return this.row;
    }
    
    public static DiskPosition getInvalidDiskPosition(){
        return invalidPosition;
    }
}
