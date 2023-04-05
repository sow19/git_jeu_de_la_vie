package graphique;

import javax.swing.JComponent;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import model.Grid;

public class GridGraphique extends JComponent {
    public static final long serialVersionUID = 1L;

    public Grid gridModel;

    public boolean[][] cell;

    private int rows=0;
    private int cols=0;

    public GridGraphique(Grid grid) {
        this.gridModel = grid;
        this.rows = this.gridModel.getNbLine();
        this.cols = this.gridModel.getNbColum();
        cell = new boolean[rows][cols];
        this.eventClicked();
    }

    public void setCell(int row, int col, boolean value) {
        cell[row][col] = value;
        repaint();
    }

    public boolean getCell(int row, int col) {
        return cell[row][col];
    }

    @Override
    public void paintComponent(Graphics g) {
    super.paintComponent(g);
    int cellWidth = this.getWidth() / this.cols;
    int cellHeight = this.getHeight() / this.rows;
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                g.setColor(Color.GRAY);
                g.drawRect(j * cellWidth-1, i * cellHeight-1, cellWidth-1, cellHeight-1);
                if (this.getCell(i, j)) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.BLACK);
                }
                g.fillRect(j * cellWidth, i * cellHeight, cellWidth, cellHeight);
            }
        }
    }

    public void clicked(int row, int col) {
//        this.gridModel.initOneCellGrid(new Position(row,col), );
        this.setCell(row,col, !this.getCell(row,col));
    }

    public void eventClicked() {
        this.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                int row = GridGraphique.this.getHeight()/e.getY();
                int col = GridGraphique.this.getWidth()/e.getX();
                clicked(row, col);
                System.out.println(row + " " + col);
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
