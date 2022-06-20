/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Lucas
 * @author Souhaïl
 */
public class CellPane extends GridPane {
    
    /**
     * Constante 
     */
    
    private static int CELLSIZE = 5;
    
    /**
     * 
     */
    
    private int size;
    private Label[][] labTab;
    private boolean[][] cellTab; 
    
    public CellPane(int size){
        this.labTab = new Label[size][size];
        this.size = size;
        
        for (int i =0 ; i < this.size ; i++){
            for (int j =0 ; j < this.size ; j++){
                Label maCase = new Label();
                maCase.setStyle("-fx-background-color: white; -fx-border-color : red ; " +
                        "-fx-border-style : inset; -fx-border-width: 0.25");
                maCase.setMinSize(CELLSIZE,CELLSIZE);
                maCase.setMaxSize(CELLSIZE,CELLSIZE);
                this.add(maCase,i+1,j+1);
                labTab[i][j] = maCase;
            }
        }
        
    }


    public Label[][] getLabTab() {
        return labTab;
    }

    public void resetLabels() {
        for (int i =0 ; i < size ; i++) {
            for (int j = 0 ; j < size ; j++) {
                labTab[i][j].setStyle("-fx-background-color: white; -fx-border-color : red ; " +
                        "-fx-border-style : inset; -fx-border-width: 0.25");
            }
        }
    }
}
