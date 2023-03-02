package model;

import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Grid{
    private Cellule [][] board;
    private Integer nbLine,nbColum;
    
    public Grid(Integer nbLine , Integer nbColum){
        this.board=new Cellule[nbLine][nbColum];
        this.nbLine=nbLine;
        this.nbColum=nbColum;
        for (int i = 0; i < this.board.length; i++){
            for (int j = 0; j < board[i].length; j++) {
                this.board[i][j]=new Cellule(new Position(i,j), 0);
            }
        }
    }
    public Integer getNbLine(){
        return this.nbLine;
    }
    public Integer getNbColum(){
        return this.nbColum;
    }
    public void initGridUser(Position[] tabPosition){
        for (int i = 0; i < this.board.length; i++){
            for (int j = 0; j < board[i].length; j++) {
                Cellule cell = this.board[i][j];
                for(int z = 0; z < tabPosition.length;z++){
                    if(cell.getPosition().equals(tabPosition[z])){
                        cell.setEtat(1);
                    }
                }

            }
        }
    }
    public  void initPattern(String fileName) {
        int[][] pattern = new int[this.nbLine][this.nbColum];
        int patternRow = 0;
        int patternCol = 0;

        try {
            Scanner fileScanner = new Scanner(new File(fileName));
            while(fileScanner.hasNextLine()) {
                patternCol = 0;
                if (patternRow >= this.nbLine) {
                    throw new Exception("Too many lines");
                }
                String line = fileScanner.nextLine();
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter("");
                while(lineScanner.hasNext()) {
                    if (patternCol >= this.nbColum) {
                        throw new Exception("Too many columns");
                    }
                    char value = lineScanner.next().charAt(0);
                    if (value == '1') {
                        pattern[patternRow][patternCol] = 1;
                    } else if (value == '0') {
                        pattern[patternRow][patternCol] = 0;
                    } else {
                        String message = String.format("Invalid character at (%d, %d)", patternRow+1, patternCol+1);
                        throw new Exception(message);
                    }
                    patternCol++;
                }
                patternRow++;
            }

            int startRow = (this.nbLine - patternRow) / 2;
            int startCol = (this.nbColum - patternCol) / 2;
            for (int i = 0; i < patternRow; i++) {
                for (int j = 0; j < patternCol; j++) {
                    this.getBoard()[startRow + i][startCol + j].setEtat(pattern[i][j]);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void initRandomGrid(){
        Random rd=new Random();
        for (int i = 0; i < this.board.length; i++){
            for (int j = 0; j < board[i].length; j++) {
                this.board[i][j]=new Cellule(new Position(i,j), rd.nextInt(2));
            }
        }
    }
    public Cellule[][] getBoard(){
        return this.board;
    }
    public void initOneCellGrid(Position pos,int etat){
        this.board[pos.getRow()][pos.getCol()]=new Cellule(pos,etat);
    }
    
    public int getAliveCell() {
    	int count = 0;
    	for (int i = 0; i < this.board.length; i++){
            for (int j = 0; j < board[i].length; j++) {
                	Cellule cell = this.board[i][j];
                	if(cell.getEtat() == 1) {
                		count++;
                	}
            }
        }
    	
    	return count;
    }
    
    /**
     * Return true if there all cells of the grid are dead and false else
     * @return
     */
    public boolean isAllDead() {
    	return this.getAliveCell() == 0;
    }
    
    
    @Override
    public String toString(){
        String chaine=" -----"+System.lineSeparator();
        for(int i=0;i<this.board.length;i++){
            for(int j=0;j<this.board[i].length;j++){
                chaine+="|"+this.board[i][j].getEtat();
            }
            chaine+="|"+System.lineSeparator();
            chaine+=" -----"+System.lineSeparator();
        }
        chaine+="\n";
        // System.out.println("hashCode: "+this.hashCode());
        return chaine;
    }
}