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
   void read(String userRule );

    /**
    * 
    * @param cellule sur laquelle s'applique la règle
    * @param grid la grille sur laquelle se trouve la cellule
      @param userRule la règle entrée par l'utilisateur
    * @return un boolean qui dit si le nombre de voisin d'une cellule se trouve dans le tableau de valeur entrées par l'utilisateur
    */

   boolean check(int neighbors);

    
}
