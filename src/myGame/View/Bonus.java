package myGame.View;

import javafx.scene.image.Image;
import java.io.File;


public class Bonus extends Entity {

    public static double bonusX=500;


    public Bonus(){
        setImage(new Image(new File(MenuScene.IMAGE_PATH +"fly.png").toURI().toString(),25,25,true,true));
        setX(500);
        setY(107);
    }


    @Override
    public void movement(Long now) {
        setX(bonusX);
    }
}