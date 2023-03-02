package app;

import model.Cellule;
import model.Grid;
import model.rule.Rule;

/*
 * A class to generate the next generation of the grid
 */

public class Generator {
    private Rule rule;

    public Generator(Rule rule) {
        this.rule = rule;
    }


    /**
     * cette fonction calcule le nombre de voisins d'une cellule
     * @param i entier : ligne de la cellule dont on veut compter les voisins vivants. 
     * @param j entier : colonne de la cellule dont on veut compter les voisins
     * @param grid objet Grid : une instance de la classe Grid représentant la grille de cellules
     * @requires les entées i et j doivent être des entiers compris entre 0 et les dimensions de la grille Grid
     * @requires L'objet grid doit être une instance valide de la classe Grid et doit contenir des cellules accessibles par grid.getBoard()
     * @ensures La méthode retourne un entier représentant le nombre de voisins vivants de la cellule donnée
     * @return Un entier représentant le nombre de voisins vivants de la cellule située à la ligne i et la colonne j dans la grille grid
     */
    public int countLiveNeighbors(int i, int j, Grid grid) {
        int liveNeighbors = 0;
        int row = grid.getBoard().length;
        int col = grid.getBoard()[0].length;
        Cellule [][]board = grid.getBoard();
        
        for (int x = Math.max(i - 1, 0); x <= Math.min(i + 1, row - 1); x++) {
            for (int y = Math.max(j - 1, 0); y <= Math.min(j + 1, col - 1); y++) {
                if (board[x][y].getEtat() == 1 && !(x == i && y == j)) {
                    liveNeighbors++;
                }
            }
        }
        return liveNeighbors;
    }


    /**
     * Cette fonction prend en entrée une grille actuelle et renvoie la
     * grille de la generation suivante en utilisant les règles spécifié par l'utilisateur
     * @param grid une instance de la classe Grid
     * @requires la fonction countLiveNeighbors doit être créé avant son utilisation dans la fonction
     * @return un objet de type Grid representant la nouvelle generation 
     */

    public Grid nextGeneration(Grid grid){
    	int row = grid.getBoard().length;
        int col = grid.getBoard()[0].length;
    	Grid nextGrid = new Grid(grid.getBoard().length, grid.getBoard()[0].length);
    	Cellule [][] nextBoard = nextGrid.getBoard();
    	Cellule [][]board = grid.getBoard();
            	
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int liveNeighbors = countLiveNeighbors(i, j, grid);
                if (board[i][j].getEtat() == 1) {
                    if (rule.checkSurvive(liveNeighbors)) {
                    	nextBoard[i][j].setEtat(1);
                    } else {
                    	nextBoard[i][j].setEtat(0);
                    }
                } else {
                    if (rule.checkBorn(liveNeighbors)) {
                    	nextBoard[i][j].setEtat(1);
                    } else {
                    	nextBoard[i][j].setEtat(0);
                    }
                }
            }
        }
        
        return nextGrid;
        
    }

   }


    

