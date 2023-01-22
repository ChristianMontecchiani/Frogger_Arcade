package myGame.View;


import javafx.scene.image.Image;
import java.io.File;

public class Vehicle extends Entity {



    double speed;
    int type;

    public Vehicle(int type, double xPos, double yPos, double speed){

        this.type=type;
        if(type==1) {
            if (speed > 0)
                setImage(new Image(new File(MenuScene.IMAGE_PATH + "car1Right.png").toURI().toString(), 30, 30, true, true));
            else
                setImage(new Image(new File(MenuScene.IMAGE_PATH + "car1Left.png").toURI().toString(), 30, 30, true, true));
        } else if(type==2) {
            if (speed > 0)
                setImage(new Image(new File(MenuScene.IMAGE_PATH + "truck1Right.png").toURI().toString(), 60, 60, true, true));
            else
                setImage(new Image(new File(MenuScene.IMAGE_PATH + "truck1Left.png").toURI().toString(), 60, 60, true, true));
        } else {
            if (speed > 0)
                setImage(new Image(new File(MenuScene.IMAGE_PATH + "truck2Right.png").toURI().toString(), 90, 90, true, true));
            else
                setImage(new Image(new File(MenuScene.IMAGE_PATH + "truck2Left.png").toURI().toString(), 90, 90, true, true));
        }
        setX(xPos);
        setY(yPos);
        this.speed= speed;

    }


    @Override
    public void movement(Long now) {
        move(speed , 0);
        if (getX() > 600 && speed>0)
            setX(-180);
        if (getX() < -50 && speed<0)
            setX(700);
    }
    public double getSpeed(){
        return  this.speed;
    }

    public int getType(){
        return  this.type;
    }
}

