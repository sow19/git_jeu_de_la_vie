package automatecellulaire;

/*
 * A class to generate the next generation of the grid
 */

public class Generator {
    
    public Generator() {
    }
    
    private int countLiveNeighbors(int i, int j, Grid grid) {
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
     * this function mimics the new generation
     * @param old_grid The grid
     * @return A next generation grid
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
                    if (liveNeighbors == 2 || liveNeighbors == 3) {
                    	nextBoard[i][j].setEtat(1);
                    } else {
                    	nextBoard[i][j].setEtat(0);
                    }
                } else {
                    if (liveNeighbors == 3) {
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


    

