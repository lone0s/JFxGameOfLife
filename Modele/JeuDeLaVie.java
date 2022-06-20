/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modele;

import java.util.Arrays;
import java.util.Random;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Lucas
 * @author Souhaïl
 */
public class JeuDeLaVie {

    protected int size;
    private boolean[][] board;
    public boolean boardIsCreated;
    public boolean boardIsInit;
    public int iteration;
    private Parameter parameter;
    public int probability;
    public Random cellProbaGenerator;
    private IntegerProperty iterationProperty;

    public JeuDeLaVie (int size, int probability){
        parameter = new Parameter();
        this.size = size ;
        cellProbaGenerator = new Random();
        this.probability = probability;
        this.iterationProperty = new SimpleIntegerProperty();
        this.iteration = 0;
        this.iterationProperty.setValue(iteration);
    }
    /*** Board ***/
    private void createBoard() {
        board = new boolean[size][size];
        for (int i = 0 ; i < size ; i ++) {
            for (int j = 0 ; j < size ; j++) {
                board[i][j] = false;
            }
        }
        boardIsCreated = true;
    }

    public boolean[][] createDeadBoard(int size) {
        return new boolean[size][size];
    }

    //public static void

    /**
     * Fonction d'initialisation, assigne a chaque case une cellule selon sa probabilité de création
     */
    public void initBoard() {
        createBoard();
        boolean cellState;
        for (int i = 0; i < size ; i++) {
            for (int j = 0 ; j < size ; j++) {
               cellState = isCellAlive(probability);
                   board[i][j] = cellState;
            }
        }
        boardIsInit = true;
    }


    /**
     * Permet de générer l'état d'une cellule selon la probabilité voulue
     * @param probabilityValue
     * @return
     */
    public boolean isCellAlive(int probabilityValue) {
        if (probabilityValue == 0) return false;
        else
            return (cellProbaGenerator.nextInt(probabilityValue) == 0);
    }

    /**
     * Remet a 0 l'état du jeu avec des nouvelles cellules aléatoirement crées
     */
    public void resetAll(){
        for (int i = 0 ; i < size ; i++) {
            for (int j = 0 ; j < size ; j++) {
                board[i][j] = isCellAlive(probability);
            }
        }
        this.resetIteration();
    }

     public int getSize() {
        return size;
    }


    /**
     *
     * @param newSize
     * Permet de redéfinir la taille du tableau selon des nouvelles dimensions
     * Deux cas de figures : nouvelle taile > ancienne taille || nouvelle taille < ancienne taile
     * On recrée un nouveau tableau dans lequel :
     * si nouvelle taile > ancienne taille ==> on recopie au centre le nouveau tableau a l'aide
     * si
     */

    public void resizeBoard(int newSize) {
        boolean[][] oldBoard = board;
        int oldSize = this.size;
        this.board = new boolean[newSize][newSize];
        this.size = newSize;
        initBoard();
        if(newSize > 0) {
            if(newSize <= oldSize) {
                for (int i = 0 ; i < newSize; i++) {
                    System.arraycopy(board[i],0,oldBoard[i],0,newSize);
                }
            }
            else {
                int min = (newSize / 2) - (oldSize / 2);
                for (int i = 0 ; i < oldSize ; i++) {
                    System.arraycopy(oldBoard[i], 0, board[i + min], min, oldSize);
                }
            }
        }
    }

    public void incrementIteration() {
            iteration ++;
            iterationProperty.set(iterationProperty.get() + 1);
    }

    public void resetIteration() {
        iteration = 0;
        iterationProperty.set(0);
    }



    /*** Cellules ***/


    /**
     *
     * @param x
     * @param y
     * @return
     * Permet de check l'entourage d'une position
     */
    public int checkNeighbours(int x, int y) {
        int sum = 0;
            for(int i = x - 1; i <= x + 1; i++) {
                if (i >= 0 && i < size) // fixed here
                    for(int j = y - 1; j <= y + 1; j++)
                        if (j >= 0 && j < board[i].length) // fixed here
                            if (i != x || j != y)
                                if (board[i][j])
                                    sum++;
            }
        return sum;
    }

    /**
     *
     * @param x
     * @param y
     * @return
     * Permet d'obtenir l'état d'une case et par conséquent d'une cellule
     */
    public boolean isCellAlive(int x, int y) {
        return board[x][y];
    }


    /*private void updateCellsParameter(Parameter newParameter){
        for (Cell cell: livingCells
        ) {
            cell.setParameter(newParameter);
        }
    }*/
    /**
     * @param nbNeighbours
     * Permet de mettre a jour l'état d'une cellule selon les regles du jeu de la vie, définis via l'entité paramètre
     */
    public void updateCellState(int nbNeighbours, int x, int y) {
        if (nbNeighbours >= parameter.getVieMin() && nbNeighbours <= parameter.getVieMax()) {
            if (!isCellAlive(x,y))
                board[x][y] = true;
        }
        if (nbNeighbours < parameter.getMortSolitude()) {
            if(isCellAlive(x,y))
            board[x][y] = false;
        }
        if (nbNeighbours > parameter.getMortAsphyxie()) {
            if(isCellAlive(x,y))
            board[x][y] = false;
        }
    }

    /**
     * Met a jour le tableau selon les relations liant les différentes cellules dependemment des parametres de jeu
     */
    public void nextIteration() {
        for (int i = 0 ; i < size ; i++ ) {
            for(int j = 0 ; j < size ; j++) {
                updateCellState(checkNeighbours(i,j),i,j);
            }
        }
        incrementIteration();
    }

    public void setProbability(int probability) {
        this.probability = probability;
    }

    /*** Parametres ***/

    /**
     * Permet de mettre a jour les regles du jeu selon le nouveau paramètre <-- Inutile
     * @param newParameter
     */
    public void modifyParameter(Parameter newParameter) {
        this.parameter = newParameter;
    }

    public Parameter newParameter(int mortSolitudeValue, int mortAsphyxieValue, int vieMinValue, int vieMaxValue) {
        return new Parameter(mortSolitudeValue,mortAsphyxieValue,vieMinValue,vieMaxValue);
    }
    public void setMortSolitudeValue(int mortSolitudeValue) {
        parameter.setMortSolitude(mortSolitudeValue);
    }
    public void setmortAsphyxieValue(int mortAsphyxieValue) {
        parameter.setMortAsphyxie(mortAsphyxieValue);
    }
    public void setvieMinValue(int vieMinValue) {
        parameter.setVieMin(vieMinValue);
    }
    public void setvieMaxValue(int vieMaxValue) {
        parameter.setVieMax(vieMaxValue);
    }


    /*** Getters & Setters ***/
    public boolean[][] getBoard() {
        return board;
    }

    public int getIteration() {
        return iteration;
    }

    public int getProbability() {
        return probability;
    }

    public Parameter getParameter() {
        return parameter;
    }

    public int getIterationProperty() {
        return iterationProperty.get();
    }




    // Pas d'abstraction ici désolé, ca a été hardcode !
    public boolean[][] createClignotant() {
        boolean[][] clignotant = new boolean[10][10];
        boolean[] cellulesMortes = new boolean[10]; // <-- Faux par défaut
        boolean[] ligne4_6 = new boolean[10];
        ligne4_6[4] = true;
        boolean[] ligne5 = new boolean[10];
        ligne5[3] = true;
        ligne5[5] = true;
        for(int i = 0 ; i < 10 ; i++) {
            if (i < 4 || i > 6) {
                clignotant[i] = cellulesMortes;
            }
        }
        clignotant[4] = ligne4_6;
        clignotant[5] = ligne5;
        clignotant[6] = ligne4_6;
        return clignotant;
    }
    public boolean[][] createStable() {
        boolean[][] stable = new boolean[10][10];
        boolean[] cellulesMortes = new boolean[10]; // <-- Faux par défaut
        boolean[] ligne5 = new boolean[10];
        ligne5[3] = true;
        ligne5[4] = true;
        ligne5[5] = true;
        for(int i = 0 ; i < 10 ; i++) {
            if (i != 5) {
                stable[i] = cellulesMortes;
            }
            else stable[i] = ligne5;
        }
        return stable;
    }

    public static boolean compare2patterns(boolean[][] fstPtrn, boolean[][] scdPtrn) {
        boolean res = false;
        if (fstPtrn.length == scdPtrn.length) {
            for (int i = 0 ; i < fstPtrn.length ; i++) {
                res = Arrays.equals(fstPtrn[i],scdPtrn[i]);
            }
        }
        return res;
    }

    public void setBoard(boolean[][] board) {
        this.board = board;
    }
}
