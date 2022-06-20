package Controller;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import vue.LeftPane;
import vue.MidPane;
import vue.RightPane;
import vue.VueJeuDeLaVie;
import Modele.JeuDeLaVie;

import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author Lucas
 * @author Souhaïl
 */
public class Controller {
        final static int DEFAULT_ZONE_TAMPON_SIZE = 10; // <== Pour changer la taille de la zone tampon /!\ resize marche pas
        final static String DEFAULT_COLOR_LIVING_CELL = "lime";
        final static String DEFAULT_COLOR_DEAD_CELL = "black";
        public VueJeuDeLaVie vue;
        private JeuDeLaVie modele;
        public boolean gameStarted;
        public boolean gameStopped;
        public boolean hasLocalPattern;
        public boolean hasBoardPattern;
        boolean[][] zoneTampon;
        private int switchCPT;
        Map<Label, Boolean> listeAssociativeTampon;
        Map<Label,Boolean> listeAssociativeBoard;


        public Controller(VueJeuDeLaVie vue,int size, int prob) {
            this.vue = vue;
            listeAssociativeTampon = new HashMap<>();
            listeAssociativeBoard = new HashMap<>();
            this.modele = new JeuDeLaVie(size,prob);
            modele.initBoard();
            gameStarted = true;
            gameStopped = true;
            hasLocalPattern = false;
            hasBoardPattern = false;
            LeftPane leftPane = vue.getLeftpane();
            MidPane midPane = vue.getMiddlepane();
            RightPane rightPane = vue.getRightpane();
            boolean[][][] models = {modele.createClignotant(), modele.createStable()};
            zoneTampon = modele.createDeadBoard(DEFAULT_ZONE_TAMPON_SIZE);
            fillAssociativeTamponList();
            fillAssociativeMainTamponList();

            //midPane.getLab().textProperty().bind(Bindings.concat("" + modele.getIterationProperty())); <== Ne fonctionne pas

            /*** Gestion des EventHandlers ***/

                // Parametres de jeu
            leftPane.getCbDeathA().addEventHandler(ActionEvent.ANY, actionEvent -> {
                modele.setmortAsphyxieValue((Integer) leftPane.getCbDeathA().getValue());
            });
            leftPane.getCbDeathS().addEventHandler(ActionEvent.ANY,actionEvent -> {
                modele.setMortSolitudeValue((Integer)leftPane.getCbDeathS().getValue());
            });
            leftPane.getCbLifeMin().addEventHandler(ActionEvent.ANY,actionEvent -> {
                modele.setvieMinValue((Integer)leftPane.getCbLifeMin().getValue());
            });
            leftPane.getCbLifeMax().addEventHandler(ActionEvent.ANY,actionEvent -> {
                modele.setvieMaxValue((Integer)leftPane.getCbLifeMax().getValue());
            });
                // Reset
            leftPane.getBtnReset().addEventHandler(MouseEvent.MOUSE_CLICKED,mouseEvent -> {
                modele.resetAll();
                fillAssociativeMainTamponList();
                updateVueOfMainCells();
            });
                //Probabilité apparition d'une cellule
            leftPane.getBtnProb().addEventHandler(MouseEvent.MOUSE_CLICKED,mouseEvent -> {
                try {
                    int ratio = 100 / Integer.parseInt(leftPane.getTfProb().getText()) ;
                    modele.setProbability(ratio);
                }
                catch (ArithmeticException e){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Erreur Valeur Probabilitée");
                    alert.setContentText("Veuillez rentrer une valeure entière comprise entre 1 et 100");
                    alert.showAndWait();
                }
            });
                //Resize Tableau
            leftPane.getBtnSize().addEventHandler(MouseEvent.MOUSE_CLICKED,mouseEvent -> {
                try {
                    int newSize = Integer.parseInt(leftPane.getTfSize().getText());
                    modele.resizeBoard(newSize);
                    updateVueOfMainCells();
                }
                catch (NumberFormatException e){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Erreur Valeur Taille");
                    alert.setContentText("Veuillez rentrer une valeure entière > 1");
                    alert.showAndWait();
                }
            });

            // Service pour les itérations
            Service service = new Service() {
                @Override
                protected Task createTask() {
                    return new Task() {
                        @Override
                        protected Integer call() throws Exception {
                            while (true) {
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        modele.nextIteration();
                                        updateVueOfMainCells();
                                        midPane.getLab().setText(""+modele.iteration);
                                    }
                                });
                                Thread.sleep(500);
                            }
                        }
                    };
                }
            };

                // MidPane

            midPane.getBtn().addEventHandler(MouseEvent.MOUSE_CLICKED,mouseEvent -> {
                if (!gameStarted && !gameStopped) {
                    gameStarted = true;
                    service.restart();
                }
                if (gameStarted && !gameStopped) {
                    service.cancel();
                    gameStopped = true;
                }
                else {
                    service.restart();
                    gameStopped = false;
                }
            });
            for(int i = 0 ; i < modele.getSize() ; i++) {
                for (int j = 0; j < modele.getSize(); j++) {
                    vue.getMiddlepane().getCp().getLabTab()[i][j].addEventHandler(MouseEvent.MOUSE_CLICKED,mouseEvent -> {
                        {
                            Label currentLabel = (Label) mouseEvent.getSource();
                            boolean currentCell = listeAssociativeBoard.get(currentLabel);
                            updateLabelCellValue(currentLabel,switchCellState(currentCell));
                            colorLab(currentLabel);
                        }
                    });
                }
            }

                //RightPane
            Button[] rightPaneSwitchButtons = {rightPane.getBtnleft(), rightPane.getBtnright()};
            for (Button button: rightPaneSwitchButtons
                 ) {
                button.addEventHandler(MouseEvent.MOUSE_CLICKED,mouseEvent -> {
                    zoneTampon = models[switchCPT%2];
                    fillAssociativeTamponList();
                    updateVueOfTampon();
                    switchCPT++;
                    hasLocalPattern = true;
                });
            }

            Label[][] tamponLabels = vue.getRightpane().getCp().getLabTab();
            for (int i = 0 ; i < tamponLabels.length ; i++) {
                for (int j = 0 ; j < tamponLabels.length ; j++) {
                    tamponLabels[i][j].addEventHandler(MouseEvent.MOUSE_CLICKED,mouseEvent -> {
                        if(hasLocalPattern) {
                            Label currentLabel = (Label) mouseEvent.getSource();
                            boolean currentCell = listeAssociativeTampon.get(currentLabel);
                            updateLabelCellValue(currentLabel,switchCellState(currentCell));
                            colorLab(currentLabel);
                        }
                    });
                }
            }

        }


        /*** Fonctions locales utiles ***/

        private void colorLabAlive(Label label) {
            label.setStyle("-fx-background-color: " + DEFAULT_COLOR_LIVING_CELL);
        }
        private void colorLabDead(Label label) {
        label.setStyle("-fx-background-color: " + DEFAULT_COLOR_DEAD_CELL);
        }

        private void colorLab(Label label) {
            boolean cell = listeAssociativeTampon.get(label);
            if (cell)
                colorLabAlive(label);
            else
                colorLabDead(label);
        }

        private void updateVueOfMainCells() {
            if (modele.boardIsInit) {
                Label[][] labTab = vue.getMiddlepane().getCp().getLabTab();
                for (int i = 0 ; i < modele.getSize() ; i++) {
                    for (int j = 0 ; j < modele.getSize() ; j++) {
                        if(modele.isCellAlive(i,j))
                            colorLabAlive(labTab[i][j]);
                        else
                            colorLabDead(labTab[i][j]);
                    }
                }
            }
        }

    /**
     * NE MARCHE PAS
     */
    /*
    private void updateCellsAfterClick() {
            boolean[] newValues = new boolean[listeAssociativeBoard.size()];
            Boolean[] newVal = listeAssociativeBoard.values().toArray(new Boolean[0]);
            int cpt = 0;
            for (Boolean Bool : newVal
            ) {
                newValues[cpt] = Boolean.TRUE.equals(Bool);
            }
            boolean[][] result = new boolean[modele.getSize()][modele.getSize()];
            for (int i = 0; i < result.length; i++) {
                for (int j = 0; result.length < 3; j++) {
                    result[i][j] = newValues[(j * result.length) + i];
                }
            }
            this.modele.setBoard(result);
            updateVueOfMainCells();
        }*/

        private void updateVueOfTampon() {
            Label[][] zoneTamponLabels = vue.getRightpane().getCp().getLabTab();
            for (int i = 0 ; i < zoneTampon.length ; i++) {
                for (int j = 0 ; j < zoneTampon.length ; j++) {
                    if (zoneTampon[i][j])
                        colorLab(zoneTamponLabels[i][j]);
                    else
                        colorLabDead(zoneTamponLabels[i][j]);
                }
            }
        }

        private void fillAssociativeTamponList() {
            for(int i = 0 ; i < zoneTampon.length ; i++) {
                for(int j = 0 ; j < zoneTampon.length ; j++) {
                    listeAssociativeTampon.put(vue.getRightpane().getCp().getLabTab()[i][j],zoneTampon[i][j]);
                }
            }
        }

        private void fillAssociativeMainTamponList() {
            for (int i = 0 ; i < modele.getSize() ; i ++) {
                for (int j = 0 ; j < modele.getSize() ; j++) {
                    listeAssociativeBoard.put(vue.getMiddlepane().getCp().getLabTab()[i][j],modele.getBoard()[i][j]);
                }
            }
        }

        private boolean switchCellState(boolean cell) {
            cell = !cell;
            return cell;
        }

        private void updateLabelCellValue(Label label,boolean newCellValue) {
            listeAssociativeTampon.put(label,newCellValue);
        }


}
