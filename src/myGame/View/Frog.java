package myGame.View;


import javafx.scene.image.Image;
import java.io.File;

import static myGame.View.AudioEffects.*;
import static myGame.View.MenuScene.autoPlay;
import static myGame.View.Bonus.bonusX;
import static myGame.View.GameScene.timeLeft;


//modificato

public class Frog extends Entity { //da finire collisione con il coccodrillo

    private long lastUpdate = 0 ;
    Image imgW1;
    Image imgA1;
    Image imgS1;
    Image imgD1;
    Image imgW2;
    Image imgA2;
    Image imgS2;
    Image imgD2;
    Image carD1;
    Image carD2;
    Image carD3;
    Image watD1;
    Image watD2;
    Image watD3;

    public static int position = 1;
    public static double xpos = 135;
    public static double ypos = 475;
    private static boolean isPlayed=true;


    int size = 30;//serve a fare lo scaling della rana


    public Frog(String link) {
        setImage(new Image(new File(link).toURI().toString(), size, size, true, true));
        setX(135);
        setY(475);
        imgW1 = new Image(new File(MenuScene.IMAGE_PATH + "froggerUp.png").toURI().toString(), size, size, true, true);
        imgA1 = new Image(new File(MenuScene.IMAGE_PATH + "froggerLeft.png").toURI().toString(), size, size, true, true);
        imgS1 = new Image(new File(MenuScene.IMAGE_PATH + "froggerDown.png").toURI().toString(), size, size, true, true);
        imgD1 = new Image(new File(MenuScene.IMAGE_PATH + "froggerRight.png").toURI().toString(), size, size, true, true);
        imgW2 = new Image(new File(MenuScene.IMAGE_PATH + "froggerUpJump.png").toURI().toString(), size, size, true, true);
        imgA2 = new Image(new File(MenuScene.IMAGE_PATH + "froggerLeftJump.png").toURI().toString(), size, size, true, true);
        imgS2 = new Image(new File(MenuScene.IMAGE_PATH + "froggerDownJump.png").toURI().toString(), size, size, true, true);
        imgD2 = new Image(new File(MenuScene.IMAGE_PATH + "froggerRightJump.png").toURI().toString(), size, size, true, true);
        watD1 = new Image(new File(MenuScene.IMAGE_PATH + "waterdeath1.png").toURI().toString(), 30, 30, true, true);
        watD2 = new Image(new File(MenuScene.IMAGE_PATH + "waterdeath2.png").toURI().toString(), 30, 30, true, true);
        watD3 = new Image(new File(MenuScene.IMAGE_PATH + "waterdeath3.png").toURI().toString(), 30, 30, true, true);
        carD1 = new Image(new File(MenuScene.IMAGE_PATH + "cardeath1.png").toURI().toString(), 30, 30, true, true);
        carD2 = new Image(new File(MenuScene.IMAGE_PATH + "cardeath2.png").toURI().toString(), 30, 30, true, true);
        carD3 = new Image(new File(MenuScene.IMAGE_PATH + "cardeath3.png").toURI().toString(), 30, 30, true, true);

    }

    @Override
    public void movement(Long now) {


        if (position == 1) {
            this.setImage(imgW1);
            if (!isPlayed && autoPlay) {
                frogJump.play(20);
                isPlayed = true;
            }
        } else if (position == 2) {
            this.setImage(imgA1);
            if (!isPlayed && autoPlay) {
                frogJump.play(20);
                isPlayed = true;
            }
        } else if (position == 3) {
            this.setImage(imgS1);
            if (!isPlayed && autoPlay) {
                frogJump.play(20);
                isPlayed = true;
            }
        } else if (position == 4) {
            this.setImage(imgD1);
            if (!isPlayed && autoPlay) {
                frogJump.play(20);
                isPlayed = true;
            }
        } else if (position == 5) {
            this.setImage(imgW2);
            isPlayed=false;
        } else if (position == 6){
            this.setImage(imgA2);
            isPlayed=false;
        }else if(position==7){
            this.setImage(imgS2);
            isPlayed=false;
        }else if(position==8){
            this.setImage(imgD2);
            isPlayed=false;
        }else if(position==9) {
            this.setImage(carD1);
            if (!isPlayed && autoPlay) {
                frogDie.play(20);
                isPlayed = true;
            }
        } else if(position==10) {
            this.setImage(carD2);
            isPlayed=false;
        }else if(position==11) {
            this.setImage(carD3);
        }else if(position==12) {
            this.setImage(watD1);
            if (!isPlayed && autoPlay) {
                waterSplash.play(20);
                isPlayed = true;
            }
        }else if(position==13) {
            this.setImage(watD2);
            isPlayed=false;
        }else {
            this.setImage(watD3);
        }

        setX(xpos);
        setY(ypos);

        if(getY() < 107)
            if (getX() >= bonusX - 25 && getX() <= bonusX + 25 && autoPlay)
                bonus.play(20);


        if(autoPlay && now - lastUpdate >= 1_000_000_000) {
            lastUpdate=now;
            AudioEffects.playRandomAmbientSound(timeLeft,this);
        }

    }
}


