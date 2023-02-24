package automatecellulaire;

/*
 * A class to generate the next generation of the grid
 */

public class Generator {
    
    public Generator() {
    }

  


    /**
     * Cette fonction prend en entrée une grille actuelle et renvoie la
     * grille de la generation suivante en utilisant les règles du jeu de la vie
     * les Cellules vivantes sont conservées si elles ont exactement 2 ou 3 voisins vivants
     * sinon elles meurent.Les cellules mortes renaissent si elles ont exactement 3 voisins vivants
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
        Rule regle = new Rule(null,null);// à ajouter la règle entréé par l'utilisateur
        // y'a une confusion avec l'appel de la méthode read
        String userRule = "";// à spécifier par l'utilisateur
    	
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j].getEtat() == 1) {
                    if (regle.checkSurvive(board[i][j],userRule,grid)) {
                    	nextBoard[i][j].setEtat(1);
                    } else {
                    	nextBoard[i][j].setEtat(0);
                    }
                } else {
                    if (regle.checkBorne(board[i][j],userRule,grid)) {
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


    

