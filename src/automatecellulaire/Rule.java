package automatecellulaire;

public class Rule {
    private RuleFormat bornRule;
    private RuleFormat surviveRule;

    
    public Rule(String rulestr) {
        this.read(rulestr);
    }

    

    //getters and stters

    public RuleFormat getBornRule() {
        return bornRule;
    }


    public void setBornRule(RuleFormat bornRule) {
        this.bornRule = bornRule;
    }


    public RuleFormat getSurviveRule() {
        return surviveRule;
    }


    public void setSurviveRule(RuleFormat surviveRule) {
        this.surviveRule = surviveRule;
    }


    // /**
    // *Cette méthode prend une chaîne de caractères userRule en entrée, extrait la partie avant le slash et détermine quel type de règle doit être utilisé pour la naissance de cellules.
    // *Si la chaîne userRule est au format "x-y" (avec x et y deux nombres), la variable bornRule est initialisée avec une nouvelle instance de la classe RuleRangeF
    // *Si la chaîne userRule est un nombre, la variable bornRule est initialisée avec une nouvelle instance de la classe RuleMulttF
    // *Si la chaîne userRule n'est pas dans un format valide, une exception de type IllegalArgumentException est levée avec le message "La règle est erronée
    // *Cette méthode ne retourne rien, mais affecte la variable bornRule de la classe courante en fonction de la règle de naissance déterminée
    // *@param userRule :une chaîne de caractères contenant la règle de naissance à déterminer
    
    //  */

    // public void readBorn(String born){

    //     if(born.matches("^[0-9]-[0-9]$")){
    //         bornRule = new RuleRangeF();
    //     }else if(born.matches("^[0-9]+$")){
    //         bornRule = new RuleMulttF();
    //     }else{
    //         throw new IllegalArgumentException("La règle est erronée");
    //     }
    // }


     /**
    *Cette méthode prend une chaîne de caractères userRule en entrée, extrait la partie après le slash et détermine quel type de règle doit être utilisé pour la survie de cellules.
    *Si la chaîne userRule est au format "x-y" (avec x et y deux nombres), la variable surviveRule est initialisée avec une nouvelle instance de la classe RuleRangeF
    *Si la chaîne userRule est un nombre, la variable surviveRule est initialisée avec une nouvelle instance de la classe RuleMulttF
    *Si la chaîne userRule n'est pas dans un format valide, une exception de type IllegalArgumentException est levée avec le message "La règle est erronée
    *Cette méthode ne retourne rien, mais affecte la variable surviveRule de la classe courante en fonction de la règle de naissance déterminée
    *@param userRule :une chaîne de caractères contenant la règle de naissance à déterminer
    
     */

    public RuleFormat readAux(String rulestr){
        RuleFormat rule = null;
        rulestr = rulestr.substring(1);
        // if(rulestr.matches("^[0-9]-[0-9]$")){
        //     rule = new RuleRangeF(rulestr);
        // }else if(rulestr.matches("^[0-9]+$")){
        //     rule = new RuleMulttF(rulestr);
        // }else{
        //     throw new IllegalArgumentException("La règle est erronée");
        // }

        if(rulestr.matches("^[0-9]-[0-9]$")){
            rule = new RuleRangeF(rulestr);
        }else if(rulestr.matches("^[0-9]+$")){
            rule = new RuleMulttF(rulestr);
        }else{
            throw new IllegalArgumentException("La règle est erronée");
        }

        return rule;
    }

   

    /**
    *La méthode "read" a pour but de lire les règles de naissance et de survie depuis la chaîne de caractères "userRule" en appelant les méthodes "readBorn" et "readSurvive
    *Elle ne renvoie aucune valeur mais permet de stocker les règles dans les attributs de l'objet
    *@param userRule est une règle valide entrée par l'utilisateur
    */

    public void read(String userRule){
        String[] parties = userRule.split("/");
        String born = parties[0];
        String survive = parties[1];
        this.bornRule = readAux(born);
        this.surviveRule = readAux(survive);
    }


    /**
    * Vérifie si la cellule spécifiée doit naître selon les règles de naissance spécifiées dans userRule.
    * @param cellule la cellule à vérifier
    * @param userRule la règle de naissance spécifiée par l'utilisateur
    * @param grid la grille dans laquelle se trouve la cellule
    * @return true si la cellule doit naître selon les règles de naissance, false sinon
    */

    public boolean checkBorn(int neighbors){
        //read(userRule);
        return bornRule.check(neighbors);
    }


        /**
    * Vérifie si la cellule spécifiée survit à la prochaine génération en se basant sur la règle de survie spécifiée par l'utilisateur.
    * Utilise la méthode read pour déterminer la règle de survie, puis fait appel à la méthode check de l'objet surviveRule pour vérifier si la cellule survit.
    * @param cellule la cellule à vérifier
    * @param userRule la règle de survie spécifiée par l'utilisateur
    * @param grid la grille dans laquelle se trouve la cellule
    * @return true si la cellule survit, false sinon
    */

    public boolean checkSurvive(int neighbors){
        //read(userRule);
        return surviveRule.check(neighbors);
    }


    

}
