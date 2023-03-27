package model.hashlife;

import app.Generator;
import model.Cellule;
import model.Grid;
import model.Position;

import java.util.HashMap;

public class Hashlife {
    HashMap<QuadNode, QuadNode> results;
    HashMap<QuadNode, QuadNode> nodes;

    Generator generator;

    int counter = 0;

    public Hashlife() {
        this.results = new HashMap<>();
        this.nodes = new HashMap<>();
        this.generator = new Generator();
    }

    /**
     * Convertit une grille en un arbre de quadtree.
     *
     * @param g la grille à convertir.
     * @return l'arbre de quadtree résultant.
     */
    public QuadNode convertToQuadtree(Grid g) {
        int size = Math.max(g.getNbColum(), g.getNbLine());
        boolean[][] bg = new boolean[size][size];
        for (int i = 0; i < g.getNbLine(); i++) {
            for (int j = 0; j < g.getNbColum(); j++) {
                bg[i][j] = g.getBoard()[i][j].getEtat() == 1;
            }
        }
        return buildQuadtree(bg, size);
    }

    /**
     * Construit un arbre de quadtree à partir d'un tableau de booléens.
     *
     * @param bg   le tableau de booléens.
     * @param size la taille du tableau.
     * @return l'arbre de quadtree résultant.
     */
    public QuadNode buildQuadtree(boolean[][] bg, int size) {
        QuadNode node;
        if (size == 1) {
            node = this.buildNode(bg[0][0]);
        } else {
            int halfSize = size/2;
            boolean[][] nw = new boolean[halfSize][halfSize];
            boolean[][] ne = new boolean[halfSize][halfSize];
            boolean[][] sw = new boolean[halfSize][halfSize];
            boolean[][] se = new boolean[halfSize][halfSize];
            for (int i = 0; i < halfSize; i++) {
                for (int j = 0; j < halfSize; j++) {
                    nw[i][j] = bg[i][j];
                    ne[i][j] = bg[i][j + halfSize];
                    sw[i][j] = bg[i + halfSize][j];
                    se[i][j] = bg[i + halfSize][j + halfSize];
                }
            }
            QuadNode swNode = buildQuadtree(sw, halfSize);
            QuadNode seNode = buildQuadtree(se, halfSize);
            QuadNode neNode = buildQuadtree(ne, halfSize);
            QuadNode nwNode = buildQuadtree(nw, halfSize);
            node = this.buildNode(swNode, seNode, neNode, nwNode);
        }
        return node;
    }
    /**

     Construit un nouveau noeud de quadrillage à partir de quatre noeuds fils qui représentent les sous-quadrants Sud-Ouest, Sud-Est, Nord-Est et Nord-Ouest.
     Le nouveau noeud est stocké dans la structure de données du QuadTree et retourné.
     @param sw le noeud fils Sud-Ouest
     @param se le noeud fils Sud-Est
     @param ne le noeud fils Nord-Est
     @param nw le noeud fils Nord-Ouest
     @return le nouveau noeud construit
     */

    public QuadNode buildNode(QuadNode sw, QuadNode se, QuadNode ne, QuadNode nw) {
        QuadNode node = new QuadNode(sw, se, ne, nw);
        this.nodes.putIfAbsent(node, node);
        return this.nodes.get(node);
    }
    /**

     Construit un nouveau noeud de quadrillage à partir d'un état initial de cellule donné.
     Le nouveau noeud est stocké dans la structure de données du QuadTree et retourné.
     @param state l'état initial de la cellule à représenter dans le nouveau noeud
     @return le nouveau noeud construit
     */

    public QuadNode buildNode(boolean state) {
        QuadNode node = new QuadNode(state);
        this.nodes.putIfAbsent(node, node);
        return this.nodes.get(node);
    }
    /**

     Ajoute une bordure vide (cellules mortes) autour d'un noeud de quadrillage donné et retourne le nouveau noeud créé.
     La profondeur du nouveau noeud créé est la même que celle du noeud d'origine.
     @param node le noeud de quadrillage auquel ajouter une bordure
     @return le nouveau noeud créé avec la bordure ajoutée
     */
    public QuadNode addBorder(QuadNode node) {
        QuadNode nodeBorder = this.buildNode(false);
        for (int i=0; i < node.getDepth()-1; i++) {
            nodeBorder = this.buildNode(nodeBorder, nodeBorder, nodeBorder, nodeBorder);
        }
        QuadNode sw = this.buildNode(nodeBorder, nodeBorder, node.getSW(), nodeBorder);
        QuadNode se = this.buildNode(nodeBorder, nodeBorder, nodeBorder, node.getSE());
        QuadNode ne = this.buildNode(node.getNE(), nodeBorder, nodeBorder, nodeBorder);
        QuadNode nw = this.buildNode(nodeBorder, node.getNW(), nodeBorder, nodeBorder);
        return this.buildNode(sw, se, ne, nw);
    }
    /**

     Calcule la prochaine génération de l'état de la grille de jeu de la vie à partir de la grille de jeu de la vie donnée.
     Convertit la grille de jeu de la vie en une structure de données de quadrillage (QuadTree), ajoute une bordure vide autour du QuadTree, calcule la prochaine génération du QuadTree, puis convertit le QuadTree résultant en une grille de jeu de la vie.
     @param grid la grille de jeu de la vie à partir de laquelle calculer la prochaine génération
     @return la grille de jeu de la vie représentant la prochaine génération de l'état du jeu de la vie
     */
    public Grid nextGeneration(Grid grid) {
        QuadNode node = this.convertToQuadtree(grid);
        node = this.addBorder(node);
        node = this.computeNextGeneration(node);
        return this.convertToGrid(node);
    }
    /**

     Renvoie le noeud central d'un quadrillage donné en tant que nouvel objet QuadNode construit à partir des noeuds voisins du noeud donné.
     @param node le noeud dont le centre doit être récupéré
     @return le noeud central de la grille donnée, construit à partir des noeuds voisins du noeud donné
     */
    private QuadNode getCenterNode(QuadNode node) {
        return this.buildNode(node.getSW().getNE(), node.getSE().getNW(), node.getNE().getSW(), node.getNW().getSE());
    }
    /**

     Calcule la génération suivante pour un noeud donné dans l'arbre QuadTree.
     Si le noeud a une population de 0, retourne le noeud central de son carré parent.
     Si le noeud a déjà été calculé et stocké dans la table de hachage 'results', retourne ce noeud calculé.
     Si le noeud est à une profondeur de 2 (c'est-à-dire qu'il a des feuilles), convertit le noeud en grille,
     calcule la prochaine génération de la grille avec un générateur de grille donné,
     convertit la grille résultante en QuadTree et retourne le noeud central de ce nouveau QuadTree.
     Si le noeud n'a pas de feuilles, divise le carré en 9 carrés plus petits et récursivement
     calcule la génération suivante pour chacun d'entre eux. Ensuite, construit un nouveau noeud QuadTree
     en utilisant les noeuds calculés pour les carrés voisins et retourne le noeud central de ce nouveau QuadTree.
     @param node le noeud pour lequel on calcule la génération suivante
     @return le noeud central du QuadTree représentant la génération suivante pour le noeud donné
     */
    private QuadNode computeNextGeneration(QuadNode node) {
        QuadNode resultNode;
        if (node.getPopulation() == 0) {
            return this.getCenterNode(node);
        } else if (this.results.get(node) != null) {
            return this.results.get(node);
        } else if (node.getDepth() == 2) {
            Grid grid = this.convertToGrid(node);
            Grid nextGrid = this.generator.nextGeneration(grid);
            QuadNode nextNode = this.convertToQuadtree(nextGrid);
            resultNode = this.getCenterNode(nextNode);
        } else {
            /*
             * n1 | n2 | n3
             * ---|----|---
             * n4 | n5 | n6
             * ---|----|---
             * n7 | n8 | n9
             */
            QuadNode n7 = this.buildNode(node.getSW().getSW(), node.getSW().getSE(), node.getSW().getNE(), node.getSW().getNW());
            QuadNode n4 = this.buildNode(node.getSW().getNW(), node.getSW().getNE(), node.getNW().getSE(), node.getNW().getSW());
            QuadNode n1 = this.buildNode(node.getNW().getSW(), node.getNW().getSE(), node.getNW().getNE(), node.getNW().getNW());
            QuadNode n8 = this.buildNode(node.getSW().getSE(), node.getSE().getSW(), node.getSE().getNW(), node.getSW().getNE());
            QuadNode n5 = this.buildNode(node.getSW().getNE(), node.getSE().getNW(), node.getNE().getSW(), node.getNW().getSE());
            QuadNode n2 = this.buildNode(node.getNW().getSE(), node.getNE().getSW(), node.getNE().getNW(), node.getNW().getNE());
            QuadNode n9 = this.buildNode(node.getSE().getSW(), node.getSE().getSE(), node.getSE().getNE(), node.getSE().getNW());
            QuadNode n6 = this.buildNode(node.getSE().getNW(), node.getSE().getNE(), node.getNE().getSE(), node.getNE().getSW());
            QuadNode n3 = this.buildNode(node.getNE().getSW(), node.getNE().getSE(), node.getNE().getNE(), node.getNE().getNW());

            QuadNode r7 = this.computeNextGeneration(n7);
            QuadNode r8 = this.computeNextGeneration(n8);
            QuadNode r9 = this.computeNextGeneration(n9);
            QuadNode r4 = this.computeNextGeneration(n4);
            QuadNode r5 = this.computeNextGeneration(n5);
            QuadNode r6 = this.computeNextGeneration(n6);
            QuadNode r1 = this.computeNextGeneration(n1);
            QuadNode r2 = this.computeNextGeneration(n2);
            QuadNode r3 = this.computeNextGeneration(n3);

            QuadNode sw = this.getCenterNode(this.buildNode(r7, r8, r5, r4));
            QuadNode se = this.getCenterNode(this.buildNode(r8, r9, r6, r5));
            QuadNode nw = this.getCenterNode(this.buildNode(r4, r5, r2, r1));
            QuadNode ne = this.getCenterNode(this.buildNode(r5, r6, r3, r2));

            resultNode = this.buildNode(sw, se, ne, nw);
        }

        this.results.put(node, resultNode);
        return resultNode;
    }

    /**
     * Convertit un QuadTree en grille.
     * @param root le noeud racine du QuadTree
     * @return la grille correspondante
     */
    public Grid convertToGrid(QuadNode root) {
        int size = root.getSize();
        Grid grid = new Grid(size, size);
        Cellule[][] board = grid.getBoard();
        this.convertToGridRecursive(root, board, 0, 0, size);
        return grid;
    }
    /**
     * Fonction récursive pour convertir un QuadTree en grille.
     * @param node le noeud courant du QuadTree
     * @param board la grille à remplir
     * @param x l'abscisse du coin supérieur gauche du sous-quadtree courant dans la grille
     * @param y l'ordonnée du coin supérieur gauche du sous-quadtree courant dans la grille
     * @param size la taille du sous-quadtree courant
     */
    private void convertToGridRecursive(QuadNode node, Cellule[][] board, int x, int y, int size) {
        if (node.getSW() == null ){
            int state = node.getState() ? 1 : 0;
            for (int i = x; i < x + size; i++) {
                for (int j = y; j < y + size; j++) {
                    board[i][j] = new Cellule(new Position(i, j), state);
                }
            }
        } else {
            int halfSize = size/2;
            convertToGridRecursive(node.getNW(), board, x, y, halfSize);
            convertToGridRecursive(node.getNE(), board, x, y + halfSize, halfSize);
            convertToGridRecursive(node.getSW(), board, x + halfSize, y, halfSize);
            convertToGridRecursive(node.getSE(), board, x + halfSize, y + halfSize, halfSize);
        }
    }
}
