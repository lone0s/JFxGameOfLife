/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;

/**
 *
 * @author lucas
 */
public class VueJeuDeLaVie extends HBox {
    
    private LeftPane lp ;
    private MidPane mp ;
    private RightPane rp ;
    
    public VueJeuDeLaVie(){
        
        this.lp = new LeftPane();
        this.mp = new MidPane();
        this.rp = new RightPane();
    
        this.getChildren().addAll(lp,mp,rp);
        HBox.setMargin(mp, new Insets(0,20,0,20));
    }

    /**
     * @return the lp
     */
    public LeftPane getLeftpane() {
        return lp;
    }

    /**
     * @return the mp
     */
    public MidPane getMiddlepane() {
        return mp;
    }

    /**
     * @return the rp
     */
    public RightPane getRightpane() {
        return rp;
    }
    
    
}
