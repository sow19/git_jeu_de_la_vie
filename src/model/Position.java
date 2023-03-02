package model;

/*
 * A class representing the position of a cell in the grid
 */

public class Position {
    
    // the row
    
    private int row;

    // the column
    private int col;

    
    /**
     * Buids a new instance
     * 
     * @param row The row
     * @param col The col
     */
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Return a row
     * @return row
     */
    public int getRow() {
        return row;
    }

     /**
     * Set the value of  row
     * @param row The row
     */
    public void setRow(int row) {
        this.row = row;
    }

     /**
     * Return a column
     * @return col
     */
    public int getCol() {
        return col;
    }

     /**
     * Set the value of column
     * @param col The column
     */
    public void setCol(int col) {
        this.col = col;
    }
    
    
}
