package model.hashlife;

import model.Grid;

public class Hashlife {
    private QuadNode quadtree;

    /**
     * Convertit une grille en un arbre de quadtree.
     * @param g la grille à convertir.
     * @return l'arbre de quadtree résultant.
     */
    public QuadNode convertToQuadtree(Grid g) {
        int size = Math.max(g.getNbColum(), g.getNbLine());
        boolean[][] bg = new boolean[size][size];
        for (int i = 0; i < g.getNbLine(); i++) {
            for (int j = 0; j < g.getNbColum(); j++) {
                bg[i][j] = g.getBoard()[i][j].getEtat()==1;
            }
        }
        return buildQuadtree(bg, size);
    }
    /**
     * Construit un arbre de quadtree à partir d'un tableau de booléens.
     * @param bg le tableau de booléens.
     * @param size la taille du tableau.
     * @return l'arbre de quadtree résultant.
     */
    public QuadNode buildQuadtree(boolean[][] bg,int size){
        QuadNode node = new QuadNode(size);
        if (size == 1) {
            node.state = bg[0][0];
        } else {
            boolean[][] nw = new boolean[size/2][size/2];
            boolean[][] ne = new boolean[size/2][size/2];
            boolean[][] sw = new boolean[size/2][size/2];
            boolean[][] se = new boolean[size/2][size/2];
            for (int i = 0; i < size/2; i++) {
                for (int j = 0; j < size/2; j++) {
                    nw[i][j] = bg[i][j];
                    ne[i][j] = bg[i][j+size/2];
                    sw[i][j] = bg[i+size/2][j];
                    se[i][j] = bg[i+size/2][j+size/2];
                }
            }
            node.children = new QuadNode[4];
            node.children[0] = buildQuadtree(nw,size/2);
            node.children[1] = buildQuadtree(ne,size/2);
            node.children[2] = buildQuadtree(sw,size/2);
            node.children[3] = buildQuadtree(se,size/2);
        }
        return node;
    }
}
