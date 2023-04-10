package views;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import app.Game;
import model.Position;
import model.Grid;

/**
 * cette classe permet de créer une grille graphique avec un jeu de la vie
 * @author Mamadou Alpha Diallo
 *
 */
public class GridGraphique extends JComponent {
    public static final long serialVersionUID = 1L;

    public Game gridModel;

    public boolean[][] cell;

    private int rows=0;
    private int cols=0;

    public float scale=1f;

    protected int cellWidth;
    protected int cellHeight;

    public Game getGridModel() {
        return gridModel;
    }

    public GridGraphique(Game grid) {
        this.gridModel = grid;
        this.rows = this.gridModel.getGrid().getNbLine();
        this.cols = this.gridModel.getGrid().getNbColum();
        
        this.eventClicked();
        this.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
        this.setBackground(Color.BLACK);
        repaint();
    }


    public void setScale(float scale) {
        this.scale = scale;
        repaint();
    }
    public float getScale() {
        return scale;
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

    /**
     * cette fonction permet de mettre à jour la grille
     * @param row ligne à mettre à jour
     * @param col colonne à mettre à jour
     * @param value valeur souhété 0 ou 1
     */
     public void setCell(int row, int col, int value) {
         if(value==1) {
             this.gridModel.getGrid().getBoard()[row][col].setEtat(1);
         }else{
         this.gridModel.getGrid().getBoard()[row][col].setEtat(0);
         }
         repaint();
     }

    public void setGridModel(Grid grid) {
        this.gridModel.setGrid(grid);
//        this.rows = this.gridModel.getNbLine();
//        this.cols = this.gridModel.getNbColum();

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
        return this.gridModel.getGrid().getCellState(row, col);
    }

    @Override
    public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    g2d.scale(this.scale, this.scale);

         cellWidth = this.getWidth() / this.rows;
         cellHeight = this.getHeight() / this.cols;
        for (int i=0 ; i < this.rows; i++) {
            for (int j=0; j < this.cols; j++) {
                if (this.getCellState(i, j)) {
                    g2d.setColor(Color.WHITE);
                } else {
                    g2d.setColor(Color.BLACK);
                }
                g2d.fillRect(j*cellWidth,i*cellHeight, cellWidth, cellHeight);
                g2d.setColor(Color.GRAY);
                g2d.drawRect(j*cellWidth, i*cellHeight, cellWidth,cellHeight);
            }
        }
        g2d.dispose();
    }
@Override
    public Dimension getPreferredSize() {
        return new Dimension((int)(this.cols*cellWidth*scale), (int)(this.rows*cellHeight*scale));
    }

    /**
     * cette fonction permet de faire un clic sur la grille
     * @param row est le numero de la ligne de la cellule à cliquer
     * @param col est le numero de la colonne de la cellule à cliquer
     */
    public void clicked(int row, int col) {
        if(this.getCellState(row, col)) {
            this.gridModel.getGrid().initOneCellGrid(new Position(row, col), 0);
            this.setCell(row, col, 0);
        }else{
            this.gridModel.getGrid().initOneCellGrid(new Position(row, col), 1);
            this.setCell(row, col, 1);
        }
    }

    /**
     * cette fonction permert de gérer les evenements sur la grille
     */
    public void eventClicked() {
        this.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                int row = e.getY()/cellHeight;
                int col = e.getX()/cellWidth;
                try {
                    clicked(row, col);
                } catch (Exception e1) {
                    System.out.println(e1.getMessage());
                }
//                clicked(row, col);
//                System.out.println(row + " " + col);
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
