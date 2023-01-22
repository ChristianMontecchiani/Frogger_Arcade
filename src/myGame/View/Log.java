package myGame.View;

import javafx.scene.image.Image;
import java.io.File;


public class Log extends Entity {

    private final double speed;
    private final int type;

    public Log(int type, int x, int y, double speed) {

        if(type==0)
            setImage(new Image(new File(MenuScene.IMAGE_PATH + "log2.png").toURI().toString(), 90, 30, true, true));
        else
            setImage(new Image(new File(MenuScene.IMAGE_PATH + "log3.png").toURI().toString(), 70, 30, true, true));

        setX(x);
        setY(y);
        this.speed = speed;
        this.type=type;


    }

    @Override
    public void movement(Long now) {
        move(speed , 0);
        if (getX()>500 && speed>0)
            setX(-180);
        if (getX()<-50 && speed<0)
            setX(700);
    }

    public double getSpeed(){
        return  this.speed;
    }

    public int getType(){
        return  this.type;
    }

}

