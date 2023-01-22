package myGame;

import myGame.Logic.LogicVariables;
import myGame.View.ViewVariables;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import java.util.Optional;


public class Main extends Application {

    public static Scene menuScene;
    public static Scene gameScene;
    public static Stage primaryStage;
    public static AnimationTimer timer;
    public static int sceneManager=0;
    private boolean waitingtoStart=true;
    private boolean time=false;



    @Override
    public void start(Stage primaryStage) throws Exception{
        ViewVariables view=new ViewVariables();
        LogicVariables logic=new LogicVariables();
        view.setLogic(logic);
        logic.setView(view);
        Main.primaryStage =primaryStage;
        primaryStage.setTitle("FROGGER THE GAME ");
        menuScene=view.getScene();
        primaryStage.setScene(menuScene);
        primaryStage.setResizable(false);
        primaryStage.show();

        primaryStage.setOnCloseRequest(Main::confirmation);


        timer=new AnimationTimer() {
            @Override
            public void handle(long now) {
                sceneManager = view.getSceneManager();
                if(view.getTimerActive() && time) {
                    logic.updateLogic(now);
                    view.updateView(now);
                }

                if(sceneManager==0) {
                    primaryStage.setScene(menuScene);
                    waitingtoStart = true;
                }

                if(waitingtoStart) {
                    if(sceneManager==1) {
                        waitingtoStart = false;
                        gameScene=view.getScene();
                        primaryStage.setScene(gameScene);
                        view.restart();
                        logic.setDifficulty();
                        logic.setIsStarted();
                        time=true;
                    }
                }

                if(sceneManager==3) {
                    primaryStage.close();
                }
            }
        };

        timer.start();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static  void confirmation(Event e){
        timer.stop();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Are you sure you want to leave?");
        alert.setContentText("Choose your option:");
        ButtonType buttonTypeOne = new ButtonType("Yes");
        ButtonType buttonTypeCancel = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            e.consume();
             primaryStage.close();
        } else {
            alert.close();
            timer.start();
            e.consume();
        }
    }

}
