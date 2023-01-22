package myGame.View;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.File;


public  class PauseClass {


    public static void  pause( Button button) {

        GameScene.timerActive=false;
        Stage pauseStage = new Stage();
        pauseStage.setTitle("Pause Menu");
        AnchorPane pauseAncPane = new AnchorPane();


        //Immagine di sfondo della finestra di pausa
        Image bkimage = new Image(new File(MenuScene.IMAGE_PATH + "sfondoPauseClass.png").toURI().toString(), 291,300,false, true);
        BackgroundImage backgroundImage = new BackgroundImage(bkimage, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        pauseAncPane.setBackground(new Background(backgroundImage));

        //Audio
        Button volumeButton = new Button("AUDIO");
        volumeButton.setPrefSize(120.0,37.0);
        AnchorPane.setTopAnchor(volumeButton,90.0);
        AnchorPane.setLeftAnchor(volumeButton, 85.0);


        //Resume
        Button resumeButton = new Button("RESUME");
        resumeButton.setPrefSize(120.0,37.0);
        AnchorPane.setTopAnchor(resumeButton,142.0);
        AnchorPane.setLeftAnchor(resumeButton, 85.0);

        pauseAncPane.getChildren().addAll(volumeButton, resumeButton);


        Scene pauseScene = new Scene(pauseAncPane, 290, 290);
        pauseStage.setScene(pauseScene);
        pauseStage.initStyle(StageStyle.DECORATED);
        pauseStage.show();

        volumeButton.setOnAction(e -> {
            if (MenuScene.autoPlay) {
                MenuScene.autoPlay = false;
                GameScene.mediaPlayer.pause();
            } else {
                MenuScene.autoPlay = true;
                GameScene.mediaPlayer.play();
            }
        });

        resumeButton.setOnAction(e -> {
            pauseStage.close();
            GameScene.timerActive=true;
            button.setDisable(false);
        });

        pauseStage.setOnCloseRequest(we -> button.setDisable(false));

        pauseStage.setOnCloseRequest(e -> {
            pauseStage.close();
            GameScene.timerActive=true;
            button.setDisable(false);
        });
    }


}