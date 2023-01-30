package automatecellulaire;

import java.util.Collections;
import java.util.List;

/*
 * A class to generate the next generation of the grid
 */

public class Generator {
    



    public Generator() {
    }


    /**
     * this function mimics the new generation
     * @param old_grid The grid
     */
    public void nextGeneration(Grille old_grid){
        // old_grid = new Grille(10, 10);
        Grille future_grid = new Grille(null,null) ;

        //walk through the grid and check the number of neighbors for each cell

        for(int i =1;i+1< old_grid.getLine();i++){
            for( int j =1;j+1< old_grid.getColum();j++){

                int count = Collections.frequency(List.of(old_grid.getGrille()[i-1][j-1],old_grid.getGrille()[i-1][j]
                ,old_grid.getGrille()[i-1][j+1],old_grid.getGrille()[i][j-1],old_grid.getGrille()[i][j+1]),true);
                if(old_grid.getGrille()[i][j].getEtat() ==true && (count !=2 && count !=3)){
                    future_grid.getGrille()[i][j].setEtat(false);

                }else if(old_grid.getGrille()[i][j].getEtat()==false && count==3){
                    future_grid.getGrille()[i][j].setEtat(true);
                }else{
                    future_grid.getGrille()[i][j].setEtat(old_grid.getGrille()[i][j].getEtat())  ;
                }
                       
                
              
            }

        }
    }



      



    }


    

