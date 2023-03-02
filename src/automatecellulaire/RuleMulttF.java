package automatecellulaire;

import java.util.ArrayList;

public class RuleMulttF implements RuleFormat {
    private ArrayList<Integer> values = new ArrayList<>();

    /**
     * constructeur de la classe qui initialise une liste de valeur en entier
     */
    public RuleMulttF() {
        this.values = new ArrayList<Integer>();
    }
    /**
     * constructeur de la classe qui prend une chaine de caractère et appelle la méthode read dessus
     * @param rulestr chaine de caractère entrée par l'utilisateur
     */

    public RuleMulttF(String rulestr) {
        this.values = new ArrayList<Integer>();
        this.read(rulestr);
    }


    /**
     * Cette méthode a pour but de lire la règle entrée par l'utilisateur
     * sous forme de chaîne de caractères,convertir chaque caractère en entier et l'ajouter dans l'attribut de la classe values.
     * Si la chaîne ne peut pas être convertie en entiers, la méthode affiche un message d'erreur "entrée non valide" et retourne null
     * @param userRule chaine de caractere qui represente la regle saisi par l'utilisateur
     * cette méthode ne retourne rien mais permet de stocker chaque caractère de userRule dans values
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
     *@param neighbors :le nombre de voisin d'une cellule
     *@return  La méthode renvoie true si le nombre de voision d'une cellule se trouve dans la liste values(entrées par l'utilisateur).false sinon
     */

    @Override
    public boolean check(int neighbors) {
        return values.contains(neighbors);
    }


           
}

         
    
 


