package views;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import model.Grid;
import model.Position;

public class GridGraphique extends JComponent {
    public static final long serialVersionUID = 1L;

    public Grid gridModel;

    public boolean[][] cell;

    private int rows=0;
    private int cols=0;

    protected int cellWidth;
    protected int cellHeight;

    public Grid getGridModel() {
        return gridModel;
    }

    public GridGraphique(Grid grid) {
        this.gridModel = grid;
        this.rows = this.gridModel.getNbLine();
        this.cols = this.gridModel.getNbColum();
        
        this.eventClicked();
        this.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
        repaint();
    }


    public int getCellWidth() {
        return cellWidth;
    }

    public void setCellWidth(int cellWidth) {
        this.cellWidth = cellWidth;
    }

    public int getCellHeight() {
        return cellHeight;
    }

    public void setCellHeight(int cellHeight) {
        this.cellHeight = cellHeight;
    }

    // public void setCell(int row, int col, int value) {
    //     if(value==1) {
    //         this.gridModel.getBoard()[row][col].setEtat(1);
    //     }else{
    //     this.gridModel.getBoard()[row][col].setEtat(0);
    //     }
    //     repaint();
    // }

    public void setGridModel(Grid grid) {
        this.gridModel = grid;
        this.rows = this.gridModel.getNbLine();
        this.cols = this.gridModel.getNbColum();
        
        cellWidth = this.getWidth() / this.cols;
        cellHeight = this.getHeight() / this.rows;

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                Rectangle cell = new Rectangle(j*cellWidth, i*cellHeight, cellWidth,
                cellHeight);
                paintImmediately(cell);
            }
        }
    }

    public boolean getCellState(int row, int col) {
        return this.gridModel.getCellState(row, col);
    }

    @Override
    public void paintComponent(Graphics g) {
    super.paintComponent(g);

        cellWidth = this.getWidth() / this.cols;
        cellHeight = this.getHeight() / this.rows;

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                g.setColor(Color.GRAY);
                g.drawRect(j * cellWidth-1, i * cellHeight-1, cellWidth-1, cellHeight-1);
                if (this.getCellState(i, j)) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.BLACK);
                }
                g.fillRect(j * cellWidth, i * cellHeight, cellWidth, cellHeight);
            }
        }
    }

    public void clicked(int row, int col) {
        if(this.getCellState(row, col)) {
            this.gridModel.initOneCellGrid(new Position(row, col), 0);
        }else{
            this.gridModel.initOneCellGrid(new Position(row, col), 1);
        }
    }
    public void eventClicked() {
        this.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                int row = e.getY() / cellHeight;
                int col = e.getX() / cellWidth;
                clicked(row, col);
            }
            public void mousePressed(MouseEvent e) {
            }
            public void mouseReleased(MouseEvent e) {
            }
            public void mouseEntered(MouseEvent e) {
            }
            public void mouseExited(MouseEvent e) {
            }
        });
    }


}
