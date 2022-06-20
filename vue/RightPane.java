/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue;

import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.geometry.Insets;

/**
 *
 * @author lucas
 */
public class RightPane extends VBox {
    
    private CellPane cp;
    private Button btnleft;
    private Button btnright;
    private Button loading;
    private Button quit;
    
    public RightPane(){
        
        //----------------------------------------------------------------------
        
        this.cp = new CellPane(10); // avoir si il faut utiliser autre chose
        cp.maxHeight(20);
        cp.maxWidth(20);
        
        VBox.setMargin(cp,new Insets(0,10,0,10)); // top / right / bottom / left
        
        //----------------------------------------------------------------------
        // bouton de selection des templates
        
        this.btnleft = new Button("<-");
        this.btnright = new Button("->");
        
        HBox hb = new HBox();
        hb.getChildren().addAll(btnleft,btnright);
        VBox.setMargin(hb,new Insets(0,10,0,10)); // top / right / bottom / left
        
        //----------------------------------------------------------------------
        // bouton de chargement des templates
        
        this.loading = new Button("Charger");
        VBox.setMargin(loading,new Insets(0,10,0,10));
        //----------------------------------------------------------------------
        // espace entre charger et quitter
        
        Region space = new Region();
        VBox.setVgrow(space, Priority.ALWAYS);
        
        //----------------------------------------------------------------------
        // bouton quit
        
        this.quit = new Button("Quitter");
        VBox.setMargin(quit,new Insets(0,10,20,10));
        //----------------------------------------------------------------------
        
        this.getChildren().addAll(cp,hb,loading,space,quit);
        
        this.setSpacing(10);
        
        /*
        this.setStyle("-fx-border-color: blue ;" +
                      "-fx-border-width: 5 ;" +
                      "-fx-border-style: inset ;"+
                      "-fx-border-radius: 15 ;");
        */
    }
    
    /**
     * @return the cellpane
     */
    public CellPane getCp() {
        return cp;
    }

    /**
     * @return the btnleft button
     */
    public Button getBtnleft() {
        return btnleft;
    }

    /**
     * @return the btnright button
     */
    public Button getBtnright() {
        return btnright;
    }

    /**
     * @return the loading button
     */
    public Button getLoading() {
        return loading;
    }

    /**
     * @return the quit button
     */
    public Button getQuit() {
        return quit;
    }
    
}
