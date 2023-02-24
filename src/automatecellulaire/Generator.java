package automatecellulaire;

/*
 * A class to generate the next generation of the grid
 */

public class Generator {
	private int[][] neighbors;
    
    public Generator() {
    	this.neighbors = constants.NeighborsType.type1;
    }
    
    public int countAliveNeighbors(int row, int col, Grid grid) {
        int count = 0;
        for (int[] n : neighbors) {
          int r = row + n[0];
          int c = col + n[1];
          if (r >= 0 && r < grid.getRows() && c >= 0 && c < grid.getCols() && grid.getBoard()[r][c].getEtat() == 1) {
            count++;
          }
        }
        return count;
    }
      


    public int[][] getNeighbors() {
		return neighbors;
	}

	public void setNeighbors(int[][] neighbors) {
		this.neighbors = neighbors;
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
                int liveNeighbors = countAliveNeighbors(i, j, grid);
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


    

