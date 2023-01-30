package automatecellulaire;

import java.util.Random;

public class Grid{
    private Cellule [][] board;
    private Integer nbLine,nbColum;
    
    public Grid(Integer nbLine , Integer nbColum){
        this.board=new Cellule[nbLine][nbColum];
        
        for (int i = 0; i < this.board.length; i++){
            for (int j = 0; j < board[i].length; j++) {
                this.board[i][j]=new Cellule(new Position(i,j), false);
            }
        }
    }
    public Integer getNbLine(){
        return this.nbLine;
    }
    public Integer getNbColum(){
        return this.nbColum;
    }
    public void initRandomGrid(){
        Random rd=new Random();
        for (int i = 0; i < this.board.length; i++){
            for (int j = 0; j < board[i].length; j++) {
                this.board[i][j]=new Cellule(new Position(i,j), rd.nextBoolean());
            }
        }
    }
    public Cellule[][] getBoard(){
        return this.board;
    }
    public void initOneCellGrid(Position pos,boolean etat){
        this.board[pos.getRow()][pos.getCol()]=new Cellule(pos,etat);
    }
    
    public int getAliveCell() {
    	int count = 0;
    	for (int i = 0; i < this.board.length; i++){
            for (int j = 0; j < board[i].length; j++) {
                	Cellule cell = this.board[i][j];
                	if(cell.getEtat()) {
                		count++;
                	}
            }
        }
    	
    	return count;
    }
    
    /**
     * Return true if there all cells of the grid are dead and false else
     * @return
     */
    public boolean isAllDead() {
    	return this.getAliveCell() == 0;
    }
    
    
    @Override
    public String toString(){
        String chaine=" -----"+System.lineSeparator();
        for(int i=0;i<this.board.length;i++){
            for(int j=0;j<this.board[i].length;j++){
                chaine+="|"+this.board[i][j].getEtat();
            }
            chaine+="|"+System.lineSeparator();
            chaine+=" -----"+System.lineSeparator();
        }
        chaine+="\n";
        // System.out.println("hashCode: "+this.hashCode());
        return chaine;
    }
}