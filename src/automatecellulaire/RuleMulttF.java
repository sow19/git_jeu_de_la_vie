package automatecellulaire;

import java.util.ArrayList;

public class RuleMulttF implements RuleFormat {
    private ArrayList<Integer> values = new ArrayList<>();

    public RuleMulttF() {
        this.values = new ArrayList<Integer> ();
    }

    public RuleMulttF(String rulestr) {
        this.values = new ArrayList<Integer> ();
        this.read(rulestr);
    }


    /**
     * Cette méthode a pour but de lire la règle entrée par l'utilisateur
     * sous forme de chaîne de caractères et de la convertir en tableau d'entiers.
     * Elle retourne un tableau d'entiers, "values", qui contient les valeurs numériques correspondant aux caractères de la chaîne.
     * Si la chaîne ne peut pas être convertie en entiers, la méthode affiche un message d'erreur "entrée non valide" et retourne null
     * @param userRule chaine de caractere qui represente la regle saisi par l'utilisateur
     * @return un tableau d'entiers représentant le type de règle entré par l'utilisateur
     */

    @Override
    public void read(String userRule) {
       
        try{
            for(int i = 0 ; i< userRule.length();i++){
                values.add(Character.getNumericValue(userRule.charAt(i)));
            }

        }catch (NumberFormatException e) {
            System.out.println("entrée non valide");
        }
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
    public boolean check(int neighbors) {
        return values.contains(neighbors);
    }


           
}

         
    
 


