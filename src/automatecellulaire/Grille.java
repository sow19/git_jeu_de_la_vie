package automatecellulaire;

public class Grille{
    private String [][] grille;
    private Integer line,colum;
    
    public Grille(Integer line , Integer colum){
        this.grille=new String[line][colum];
    }
    
    public void initGrille(){
        for (int i = 0; i < this.grille.length; i++){
   
        }
    }
    
    public int getDeadCell() {
    	return 0; // todo: impplement this function
    }
    
    /**
     * Return true if there all cells of the grid are dead and false else
     * @return
     */
    public boolean isAllDead() {
    	return this.getDeadCell() == 0;
    }
    
}