package myGame.View;

import javafx.scene.image.Image;
import java.io.File;


public class Turtle extends Entity {

    private final double speed;
    Image turtle1;
    Image turtle2;
    Image turtle3;
    Image turtleWet1;
    Image turtleWet2;
    Image turtleWet3;


    public Turtle(int xPos, int yPos, double speed) {
        turtle1 = new Image(new File(MenuScene.IMAGE_PATH+ "TurtleAnimation1.png").toURI().toString(), 70, 30, true, true);
        turtle2 = new Image(new File(MenuScene.IMAGE_PATH + "TurtleAnimation2.png").toURI().toString(), 70, 30,true, true);
        turtle3 = new Image(new File(MenuScene.IMAGE_PATH+ "TurtleAnimation3.png").toURI().toString(), 70, 30,true, true);
        turtleWet1 = new Image(new File(MenuScene.IMAGE_PATH + "TurtleAnimation2Wet.png").toURI().toString(), 70, 30,true, true);
        turtleWet2 = new Image(new File(MenuScene.IMAGE_PATH + "TurtleAnimation3Wet.png").toURI().toString(), 70, 30,true, true);
        turtleWet3 = new Image(new File(MenuScene.IMAGE_PATH + "TurtleAnimation4Wet.png").toURI().toString(),70, 30,true, true);
        setX(xPos);
        setY(yPos);
        this.speed = speed;
        setImage(turtle2);

    }

    public double getSpeed(){
        return this.speed;
    }

    @Override
    public void movement (Long now){
        move(speed, 0);
        if (getX() > 500 && speed > 0)
            setX(-180);
        if (getX() < -50 && speed < 0)
            setX(700);

        if ((now/3/ 900000000 +((int)this.getY()/100) )% 4 == 1) {
            setImage(turtleWet1);
        } else if ((now/3/900000000+((int)this.getY()/100))% 4 == 2) {
            setImage(turtleWet2);
        } else if ((now/3/ 900000000 +((int)this.getY()/100) )% 4== 3) {
            setImage(turtleWet3);
        } else if ((now/3/ 900000000 +((int)this.getY()/100) ) % 4== 0) {
            setImage(turtle2);
        } else if ((now/3/ 900000000 +((int)this.getY()/100) ) % 3== 1) {
            setImage(turtle1);
        } else if ((now/3/ 900000000 +((int)this.getY()/100) )% 3== 2) {
            setImage(turtle3);

        }


    }

}