package myGame.View;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;

import java.io.File;
import java.io.IOException;

public class MenuScene {

    public final static String AUDIO_PATH = "Resources\\Audio\\";
    public final static String IMAGE_PATH = "Resources\\Images\\";
    public static boolean autoPlay = true;
    static Media bkMusic = new Media(new File(AUDIO_PATH + "FroggerTheme.mp3").toURI().toString());
    public static MediaPlayer mediaPlayer = new MediaPlayer(bkMusic);
    public static Scene mainScene;

public void getMenuScene(){
    mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    mediaPlayer.play();

    //BUTTON PLAY Settings
    Button playButton = new Button("PLAY");
    playButton.setPrefSize(202.0,72.0);
    playButton.setFont(new Font(30));
    Image greenImage = new Image(new File(IMAGE_PATH +"green.jpg").toURI().toString());
    BackgroundImage greenBack = new BackgroundImage(greenImage, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
    playButton.setBackground(new Background(greenBack));


    //BUTTON EXIT Settings
    Button exitButton = new Button("EXIT");
    exitButton.setPrefSize(96.0,37.0);
    Image redImage = new Image(new File(IMAGE_PATH + "red.jpg").toURI().toString());
    BackgroundImage redBack = new BackgroundImage(redImage,BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
    exitButton.setBackground(new Background(redBack));


    //PULSANTE AUDIO Settings
    Button audioButton = new Button("AUDIO");
    audioButton.setPrefSize(96.0,37.0);


    //CHOICEBOX DIFFICULTY Settings
    ChoiceBox<String> difficultyChoiceBox = new ChoiceBox<>();
    difficultyChoiceBox.setPrefSize(96.0,37.0);
    difficultyChoiceBox.getItems().addAll("EASY", "MEDIUM", "HARD");
    difficultyChoiceBox.setValue("MEDIUM");


    //PULSANTE AUDIO Settings
    Button rankingButton = new Button("RANKING");
    rankingButton.setPrefSize(96.0,37.0);

    //PANE
    AnchorPane menuPane = new AnchorPane();


    //POSITION EACH BUTTON
    AnchorPane.setTopAnchor(playButton, 101.0);
    AnchorPane.setLeftAnchor(playButton,152.0);

    AnchorPane.setTopAnchor(exitButton,227.0);
    AnchorPane.setLeftAnchor(exitButton,258.0);

    AnchorPane.setTopAnchor(difficultyChoiceBox,181.0);
    AnchorPane.setLeftAnchor(difficultyChoiceBox,258.0);

    AnchorPane.setTopAnchor(rankingButton,227.0);
    AnchorPane.setLeftAnchor(rankingButton,152.0);

    AnchorPane.setTopAnchor(audioButton,181.0);
    AnchorPane.setLeftAnchor(audioButton,152.0);


    //BACKGROUND IMAGE
    Image bkimage = new Image(new File(IMAGE_PATH + "menu..png").toURI().toString(), 498,300,false, true);
    BackgroundImage backgroundImage = new BackgroundImage(bkimage,BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
    menuPane.setBackground(new Background(backgroundImage));

    //INSERT IN PANE
    menuPane.getChildren().addAll(playButton,difficultyChoiceBox,exitButton,audioButton,rankingButton);



    //ACTION ON BUTTON PLAY
    playButton.setOnAction(e->{
        ViewVariables.sceneManager=1;
        mediaPlayer.stop();
        GameScene sc=new GameScene();

        if(difficultyChoiceBox.getValue().equals("EASY"))
            GameScene.difficulty=0;
        else if(difficultyChoiceBox.getValue().equals("MEDIUM"))
            GameScene.difficulty=1;
        else
            GameScene.difficulty=2;

        ViewVariables.restarted=false;

        sc.startGame();



    });

    //ACTION ON BUTTON AUDIO
    audioButton.setOnAction(e->{

        if(autoPlay) {
            autoPlay=false;
            mediaPlayer.pause();
        } else {
            autoPlay=true;
            mediaPlayer.play();
        }
    });


    //ACTION ON BUTTON EXIT
    exitButton.setOnAction(e->{
        ViewVariables.sceneManager=3;
        ViewVariables.exitEvent=e;
    });


    //ACTION ON BUTTON RANKING
    rankingButton.setOnAction(e-> {
        try {
            RankingTable.scoreRecord();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        if (autoPlay)
            mediaPlayer.pause();
    });
    
    mainScene= new Scene(menuPane, 460, 280);
}



}
