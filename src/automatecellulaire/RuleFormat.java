package automatecellulaire;

/**
 * Interface définissant les méthodes pour un format de règle
 */

public interface RuleFormat {

   /**
    * 
    * @param userRule la règle entrée par l'utilisateur
    * @return le tableau d'entiers representant la règle
    */
   int[] read(String userRule );

    /**
    * 
    * @param cellule sur laquelle s'applique la règle
    * @param grid la grille sur laquelle se trouve la cellule
      @param userRule la règle entrée par l'utilisateur
    * @return un boolean qui dit si le nombre de voisin d'une cellule se trouve dans le tableau de valeur entrées par l'utilisateur
    */

   boolean check(Cellule cellule,String userRule,Grid grid);

   /**
    * 
   * @param i represente l'indice de la ligne d'une cellule de la grille
   * @param j represente l'indice de la colonne d'une cellule de la grille
   * @param grid represente la grille
   * @return le nombre de voisin d'une cellule de la grille
   */

   int countLiveNeighbors(int i,int j,Grid grid);
    
}
