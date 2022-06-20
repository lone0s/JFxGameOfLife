/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 *
 * @author lucas
 */
public class MidPane extends VBox{
    
    private CellPane cp;
    private Button btn;
    private Label lab;
    
    public MidPane(){
        
        //----------------------------------------------------------------------
        // Cellpane
        
        this.cp = new CellPane(100);
        cp.setMaxHeight(200);
        cp.setMaxWidth(200);
        
        //----------------------------------------------------------------------
        // Label compteur
        this.lab = new Label("0");
        Region r1 = new Region();
        Region r2 = new Region();
        HBox hboxlab = new HBox();
        
        hboxlab.getChildren().addAll(r1,lab,r2);
        
        HBox.setHgrow(r1, Priority.ALWAYS);
        HBox.setHgrow(r2, Priority.ALWAYS);
        //----------------------------------------------------------------------
        // Bouton play/pause
        this.btn = new Button("Play/Pause");
        Region r3 = new Region();
        Region r4 = new Region();
        HBox hboxbtn = new HBox();
        
        hboxbtn.getChildren().addAll(r3,btn,r4);
        HBox.setHgrow(r3, Priority.ALWAYS);
        HBox.setHgrow(r4, Priority.ALWAYS);
        
        //----------------------------------------------------------------------
        
        this.getChildren().addAll(cp,hboxlab,hboxbtn);
    
        this.setSpacing(10);
        
        /*
        this.setStyle("-fx-border-color: red ;" +
                      "-fx-border-width: 5 ;" +
                      "-fx-border-style: inset ;"+
                      "-fx-border-radius: 15 ;");
        */
        
    }

    /**
     * @return the CellPane 
     */
    public CellPane getCp() {
        return cp;
    }

    /**
     * @return the button play/pause
     */
    public Button getBtn() {
        return btn;
    }

    /**
     * @return the label compteur
     */
    public Label getLab() {
        return lab;
    }
    
    
    public void updateCellPaneSize(int newSize){
        this.cp = new CellPane(newSize);
    }
    
}
