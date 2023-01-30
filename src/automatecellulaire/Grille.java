package automatecellulaire;

import java.util.Random;

public class Grille{
    private Cellule [][] board;
    private Integer nbLine,nbColum;
    public Grille(Integer line , Integer colum){
        this.board=new Cellule[line][colum];
    }
    public Integer getNbLine(){
        return this.nbLine;
    }
    public Integer getNbColum(){
        return this.nbColum;
    }
    public void initGrille(){
        Random rd=new Random();
        for (int i = 0; i < this.board.length; i++){
            for (int j = 0; j < board[i].length; j++) {
                this.board[i][j]=new Cellule(null, rd.nextBoolean());
            }
        }
    }
    @Override
    public String toString(){
        String chaine=" -----"+System.lineSeparator();
        for(int i=0;i<this.board.length;i++){
            for(int j=0;j<this.board[i].length;j++){
                chaine+="|"+this.board[i][j];
            }
            chaine+="|"+System.lineSeparator();
            chaine+=" -----"+System.lineSeparator();
        }
        chaine+="\n";
        // System.out.println("hashCode: "+this.hashCode());
        return chaine;
    }
}