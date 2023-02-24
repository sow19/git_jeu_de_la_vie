gipackage automatecellulaire;

public class RuleMulttF implements RuleFormat {

    

    /**
     * Cette méthode a pour but de lire la règle entrée par l'utilisateur
     * sous forme de chaîne de caractères et de la convertir en tableau d'entiers.
     * Elle retourne un tableau d'entiers, "values", qui contient les valeurs numériques correspondant aux caractères de la chaîne.
     * Si la chaîne ne peut pas être convertie en entiers, la méthode affiche un message d'erreur "entrée non valide" et retourne null
     * @param userRule chaine de caractere qui represente la regle saisi par l'utilisateur
     * @return un tableau d'entiers représentant le type de règle entré par l'utilisateur
     */

    @Override
    public int[] read(String userRule) {
       
      int[]  values = new int[userRule.length()];

        try{
            for(int i = 0 ; i< userRule.length();i++){
                values[i] = Character.getNumericValue(userRule.charAt(i));
            }
            return values;

        }catch (NumberFormatException e) {
            System.out.println("entrée non valide");
            return null;
        }
    }



    /**
     * cette fonction calcule le nombre de voisins d'une cellule
     * @param i entier : ligne de la cellule dont on veut compter les voisins vivants. 
     * @param j entier : colonne de la cellule dont on veut compter les voisins
     * @param grid objet Grid : une instance de la classe Grid représentant la grille de cellules
     * @requires les entées i et j doivent être des entiers compris entre 0 et les dimensions de la grille Grid
     * @requires L'objet grid doit être une instance valide de la classe Grid et doit contenir des cellules accessibles par grid.getBoard()
     * @ensures La méthode retourne un entier représentant le nombre de voisins vivants de la cellule donnée
     * @return Un entier représentant le nombre de voisins vivants de la cellule située à la ligne i et la colonne j dans la grille grid
     */
    @Override
    public int countLiveNeighbors(int i, int j, Grid grid) {
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
     * @param cellule : la cellule sur laquelle la règle s'applique
     * @param userRule : une chaine de caractère qui represente les règles personnalisées
     *@requires cellule est une instance valide de la classe Cellule
     *@requires userRule  userRule est une chaîne de caractères représentant une règle valide 
     *@requires grid est une instance valide de la classe Grid
     *@ensures La méthode renvoie true si le nombre de cellules voisines vivantes de la cellule cellule est égal à l'un des entiers de la liste values extraits de la chaîne userRule.La méthode renvoie false sinon.
     *@return un boolean qui dit si le nombre de voisin d'une cellule se trouve dans le tableau de valeur entrées par l'utilisateur
     */

    @Override
    public boolean check(Cellule cellule,String userRule,Grid grid) {
        
        int[] values = read(userRule);
        int liveNeighbors = countLiveNeighbors(cellule.getPosition().getRow(), cellule.getPosition().getCol(), grid);
               for(int k=0; k < values.length ; k++){
                if(liveNeighbors == values[k]){
                   
                    return true;
                }
               }

             return false;
            }

           
}

         
    
 


