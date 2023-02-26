package automatecellulaire;

import java.util.Random;
/**
 * la classe correspondant à la grille de jeu.
 * @param board est un tableau de cellule de deux dimensions
 * @param nbLine correspond au nombre de lignes du tableau board.
 * @param nbColum correspond au nombre de colonnes du tableau board. 
 */
public class Grid{
    private Cellule [][] board;
    private Integer nbLine,nbColum;
    
    public Grid(Integer nbLine , Integer nbColum){
        this.board=new Cellule[nbLine][nbColum];
        for (int i = 0; i < this.board.length; i++){
            for (int j = 0; j < board[i].length; j++) {
                this.board[i][j]=new Cellule(new Position(i,j), 0);
            }
        }
    }
    public Cellule[][] getBoard(){
        return this.board;
    }
    public Integer getNbLine(){
        return this.nbLine;
    }
    public Integer getNbColum(){
        return this.nbColum;
    }
    /**
     * Il s'agit de la fonction permettant d'initialiser aléatoirement la grille.
     */
    public void initRandomGrid(){
        Random rd=new Random();
        for (int i = 0; i < this.board.length; i++){
            for (int j = 0; j < board[i].length; j++) {
                this.board[i][j]=new Cellule(new Position(i,j), rd.nextInt(2));
            }
        }
    }
    /**
     * il s'agit de la fonction d'initialisation de la grille à une position donnée avec un état défini.
     * @param pos est la position de la cellule
     * @param etat est l'état qu'il faut définir pour la cellule.
     */
    public void initOneCellGrid(Position pos,int etat){
        this.board[pos.getRow()][pos.getCol()]=new Cellule(pos,etat);
    }
    /**
     * C'est la fonction qui compte le nombre de cellules vivante.
     * @return 0 si aucune cellule vivante, le nombre de cellules vivantes sinon.
     */
    public int getAliveCell() {
    	int count = 0;
    	for (int i = 0; i < this.board.length; i++){
            for (int j = 0; j < board[i].length; j++) {
                	Cellule cell = this.board[i][j];
                	if(cell.getEtat() == 1) {
                		count++;
                	}
            }
        }
    	return count;
    }
    
    /**
     * cette fonction vérifie que toutes les cellules sont mortes.
     * @return Retour true si elles sont toutes mortes false sinon.
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
        return chaine;
    }
}