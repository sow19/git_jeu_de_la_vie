package model.rule;

public class RuleRangeF  implements RuleFormat{
    
    protected int[] range;
    

    /**
     * constructeur de la classe qui initialise un tableau d'entiers de taille deux
     */
    public RuleRangeF() {
        this.range = new int[2];
    }
    /**
     * constructeur de la classe qui prend en entrée une chaine et appelle la méthode read dessus
     * @param rulestr
     */

    public RuleRangeF(String rulestr) {
        this.range = new int[2];
        this.read(rulestr);
    }

    /**
     * Cette méthode a pour but de lire la règle entrée par l'utilisateur
     * sous forme de chaîne de caractères et de la diviser en deux parties.
     * @param userRule chaine de caractere qui represente la regle saisi par l'utilisateur
     */
    @Override
    public void read(String userRule){

        try{
            String[] parts = userRule.split("-");
            range[0] = Integer.parseInt(parts[0]);
            range[1] = Integer.parseInt(parts[1]);

        }catch (NumberFormatException e) {
            System.out.println("entrée non valide");
        }
       

    }


     
    /**
     *@param neighbors :le nombre de voisin d'une cellule
     *@return  La méthode renvoie true si le nombre de voision d'une cellule est compris entre la premiere et la deuxieme valeur de l'attribut de la classe range.false sinon
     */
    @Override
    public boolean check(int neighbors) {
        return (neighbors >= range[0] && neighbors <= range[1]);
    }

       
}

        
    
