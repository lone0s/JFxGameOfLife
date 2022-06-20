/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
/**
 *
 * @author lucas
 */
public class LeftPane extends VBox {
    
    private TextField tfSize;
    private Button btnSize;
    
    private Button btnReset;
    
    private TextField tfProb;
    private Button btnProb;
    
    
    private ComboBox cbDeathS;
    private ComboBox cbDeathA;
    private ComboBox cbLifeMin;
    private ComboBox cbLifeMax;
    
    public LeftPane(){
        
        //----------------------------------------------------------------------
        // Gestion de la taille du plateau
        
        Label labSize = new Label("Taille du Tableau :");
        this.tfSize = new TextField("100");
        this.btnSize = new Button("Execution");
        
        
        tfSize.setMaxWidth(40);
        
        //----------------------------------------------------------------------
        // Gestion du bouton reset
        
        Label labReset = new Label("Réinitialiser le plateau :");
        this.btnReset = new Button("Reset");
        
        //----------------------------------------------------------------------
        // Gestion de probabilité d'apparition de cellule
        
        Label labProb = new Label("Probabilité d'apparition d'une cellule :");
        this.tfProb = new TextField("0");
        Label pour = new Label("%");        
        this.btnProb = new Button("Execution");
        
        HBox hbProb = new HBox();
        hbProb.getChildren().addAll(tfProb,pour);
        hbProb.setSpacing(5);
        
        
        tfProb.setMaxWidth(40);
        //----------------------------------------------------------------------
        // Parametre de la partie
        //--------------------------------
        
        Label lab = new Label("Parametre de la partie :");
        
        // mort par solitude
        int[] maxParams = {1,2,3,4,5,6,7,8};
        this.cbDeathS = new ComboBox();
        this.cbDeathA = new ComboBox();
        this.cbLifeMin = new ComboBox();
        this.cbLifeMax = new ComboBox();
        for (int value : maxParams
             ) {
            cbDeathA.getItems().add(value);
            cbDeathS.getItems().add(value);
            cbLifeMax.getItems().add(value);
            cbLifeMin.getItems().add(value);
        }
        
        cbDeathS.setValue(1);
        cbDeathA.setValue(4);
        cbLifeMin.setValue(3);
        cbLifeMax.setValue(3);

                /*cbDeathS.getItems().addAll(
                1,
                2,
                3,
                4,
                5,
                6,
                7,
                8
        );*/
        Label labCbS = new Label(" - Mort par Solitude ");
        
        HBox hbS = new HBox();
        hbS.getChildren().addAll(cbDeathS,labCbS);
        hbS.setSpacing(5);
        
        //--------------------------------
        // mort par asphyxie
        

        /*cbDeathA.getItems().addAll(
                1,
                2,
                3,
                4
        );*/
        

        
        Label labCbA = new Label(" - Mort par Asphyxie ");
        
        HBox hbA = new HBox();
        hbA.getChildren().addAll(cbDeathA,labCbA);
        hbA.setSpacing(5);
        
        //--------------------------------
        // vie min
        

        /*cbLifeMin.getItems().addAll(
                1,
                2,
                3,
                4
        );*/
        

        
        Label labCbMin = new Label(" - nb de cellule min pour que la cellule vive");
        
        HBox hbMin = new HBox();
        hbMin.getChildren().addAll(cbLifeMin,labCbMin);
        hbMin.setSpacing(5);
        
        //--------------------------------
        // vie max

        
        /*cbLifeMax.getItems().addAll(
                1,
                2,
                3,
                4
        );*/
        

        
        Label labCbMax = new Label(" - nb de cellule max pour que la cellule vive");
        
        HBox hbMax = new HBox();
        hbMax.getChildren().addAll(cbLifeMax,labCbMax);
        hbMax.setSpacing(5);
        
        //----------------------------------------------------------------------
        this.getChildren().addAll(
                labSize,
                tfSize,
                btnSize,
                labReset,
                btnReset,
                labProb,
                hbProb,
                btnProb,
                lab,
                hbS,
                hbA,
                hbMin,
                hbMax
                );
                
        this.setSpacing(10);
        
        /*
        this.setStyle("-fx-border-color: green ;" +
                      "-fx-border-width: 5 ;" +
                      "-fx-border-style: inset ;"+
                      "-fx-border-radius: 15 ;");
        */
    }

    /**
     * @return the textfield for the Size of the board
     */
    public TextField getTfSize() {
        return tfSize;
    }

    /**
     * @return the button for the Size of the board
     */
    public Button getBtnSize() {
        return btnSize;
    }

    /**
     * @return the btnReset
     */
    public Button getBtnReset() {
        return btnReset;
    }

    /**
     * @return the tfProb
     */
    public TextField getTfProb() {
        return tfProb;
    }

    /**
     * @return the btnProb
     */
    public Button getBtnProb() {
        return btnProb;
    }

    /**
     * @return the cbDeathS
     */
    public ComboBox getCbDeathS() {
        return cbDeathS;
    }

    /**
     * @return the cbDeathA
     */
    public ComboBox getCbDeathA() {
        return cbDeathA;
    }

    /**
     * @return the cbLifeMin
     */
    public ComboBox getCbLifeMin() {
        return cbLifeMin;
    }

    /**
     * @return the cbLifeMax
     */
    public ComboBox getCbLifeMax() {
        return cbLifeMax;
    }
    
    
}
