/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package vue;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 *
 * @author Lucas
 * @author Souhaïl
 */
public class vue extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        //-----------------------------------------------
        /* bouton quit */
        
        Button btn = new Button("Quit");
        btn.setOnAction(
                event -> {
                    Platform.exit();
                });
        
        //------------------------------------------------
        
        LeftPane lp = new LeftPane();
        MidPane mp = new MidPane();
        RightPane rp = new RightPane();
        
        //------------------------------------------------
        
        HBox root = new HBox();
        root.getChildren().addAll(lp,mp,rp);
        HBox.setMargin(mp, new Insets(0,20,0,20));
        
        Scene scene = new Scene(root,1024,624);
        
        primaryStage.setTitle("Projet IHM");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
