package myGame.View;



import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.control.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static myGame.View.MenuScene.autoPlay;
import static myGame.View.RankingTable.enableAddButton;
import static myGame.View.ViewVariables.restarted;


public class GameScene {

    //componenti della scena
    public static Scene scene;
    static EasyScene easy=new EasyScene();
    static MediumScene medium=new MediumScene();
    static HardScene hard=new HardScene();
    public static Button pauseButton;
    static Label timeLabel;
    static Label scoreLabel;
    static AnchorPane root;
    static Media media;
    public static MediaPlayer mediaPlayer;
    static ImageView win;
    static ImageView lost;
    static Image w = new Image(new File(MenuScene.IMAGE_PATH + "win.png").toURI().toString(), 350, 500, true, true, false);
    static Image l = new Image(new File(MenuScene.IMAGE_PATH + "gameover.png").toURI().toString(), 350, 500, true, true, false);

    //variabili del gioco
    public static int difficulty=0;
    public static boolean timerActive=false;//usato per "fermare e avviare" il timer
    public static int FROGGER_LIVES;
    public static boolean isStarted=false;//usata per notificare se inizializzare o meno la parte logica
    public static int burrowCounter=0;
    public static int  timeLeft;
    public static int points;
    public static boolean lifelost=false;
    static Frog frog;
    public static List<Entity> interceptable;



    public  void startGame() {

        timerActive=true;
        lifelost=false;
        isStarted=false;


        win=new ImageView(w);
        lost=new ImageView(l);
        AnchorPane.setTopAnchor(win,220.0);
        AnchorPane.setTopAnchor(lost,220.0);

        //MUSICA di SOTTOFONDO
        media = new Media(new File(MenuScene.AUDIO_PATH + "theme.mp3").toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        if(autoPlay)
            mediaPlayer.play();
        else
            mediaPlayer.pause();

        if(difficulty==0)
            root=easy.setScene();
        else if(difficulty==1)
            root=medium.setScene();
        else
            root=hard.setScene();

        //Bottone Pause
        pauseButton = new Button("||");
        AnchorPane.setTopAnchor(pauseButton, 7.0);
        AnchorPane.setLeftAnchor(pauseButton, 0.0);
        //Etiichetta Time
        timeLabel=new Label("Time: "+timeLeft);
        timeLabel.setFont(new Font("Calibri", 20));
        AnchorPane.setTopAnchor(timeLabel, 10.0);
        AnchorPane.setLeftAnchor(timeLabel, 185.0);

        //Etichetta Score
        scoreLabel = new Label("Score: "+ points);
        scoreLabel.setFont(new Font("Calibri", 20));
        AnchorPane.setTopAnchor(scoreLabel, 10.0);
        AnchorPane.setLeftAnchor(scoreLabel, 30.0);

        root.getChildren().addAll(timeLabel,pauseButton,scoreLabel);

        frog=new Frog(MenuScene.IMAGE_PATH + "froggerUp.png");
        interceptable=getEntity(Entity.class);

        scene=new Scene(root,350,505);

        root.getChildren().addAll(frog);


        pauseButton.setOnAction(e ->{
            pauseButton.setDisable(true);
            PauseClass.pause(pauseButton);});

    }

    //metodo che fa muovere le componenti visive,inizializza le componenti logiche
    // mediante il metodo startLogic,gestisce l'animazione della perdita della vita e fa comparire la tabella a fine partita
    public static void moveView(long now) {
                List<Entity> objects = getEntity(Entity.class);
                for (Entity object : objects) {
                    object.movement(now);
                }
                //update dei label
                scoreLabel.setText("Score: " + points);
                timeLabel.setText("Time: " + timeLeft);


                if (lifelost)
                    root.getChildren().remove(root.getChildren().size() - 6);

                if(restarted) { //booleano che permette alle variabili statiche di riaggiornarsi
                    if (FROGGER_LIVES == 0 || burrowCounter == 5) {

                        enableAddButton = true;
                        pauseButton.setDisable(true);
                        mediaPlayer.pause();
                        timerActive = false;
                        try {
                            RankingTable.scoreRecord();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        if (FROGGER_LIVES == 0)
                            root.getChildren().add(lost);
                        else
                            root.getChildren().add(win);
                    }

                }

            }


    @SuppressWarnings("unchecked")
//per togliere il warning del cast,infatti ogni nodo che passa il controllo è sicuramente un'entità
    public static <T extends Entity > List < T >  getEntity(Class < T > cls) {
        ArrayList<T> someArray = new ArrayList<>();
        for (Node n : root.getChildren())
            if (cls.isInstance(n)) {
                someArray.add((T) n);
            }
        return someArray;

    }


}

