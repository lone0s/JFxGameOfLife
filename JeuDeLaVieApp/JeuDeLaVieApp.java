package JeuDeLaVieApp;

import Controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vue.VueJeuDeLaVie;

public class JeuDeLaVieApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        VueJeuDeLaVie vueJeuDeLaVie = new VueJeuDeLaVie();
        Controller controller = new Controller(vueJeuDeLaVie,100,10);
        Scene scene = new Scene(controller.vue);
        stage.setTitle("Jeu De La Vie || CC2 IHM");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    /*public static void test(String[] args) {

        JeuDeLaVie jeuDeLaVie = new JeuDeLaVie(5,2);
        jeuDeLaVie.initBoard();
        for (int i = 0 ; i < 10 ; i++) {
            for (boolean[] cellLine: jeuDeLaVie.getBoard()
            ) {
                System.out.println(Arrays.toString(cellLine));
                System.out.println(
            }
            jeuDeLaVie.nextIteration();
            System.out.println("\n---------------------------------\n");
        }
    }*/

}
