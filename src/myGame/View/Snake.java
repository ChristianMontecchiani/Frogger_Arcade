package myGame.View;


import javafx.scene.image.Image;
import java.io.File;


public class Snake extends Entity {

    private  double speed;
    Image snake1Right;
    Image snake2Right;
    Image snake1Left;
    Image snake2Left;

    public Snake(int xPos,int yPos,double speed){
        this.speed=speed;
        snake1Right = new Image(new File(MenuScene.IMAGE_PATH + "snake1Right.png").toURI().toString(), 70, 30, true, true);
        snake2Right = new Image(new File(MenuScene.IMAGE_PATH + "snake2Right.png").toURI().toString(), 70, 30,true, true);
        snake1Left = new Image(new File(MenuScene.IMAGE_PATH + "snake1Left.png").toURI().toString(), 70, 30,true, true);
        snake2Left = new Image(new File(MenuScene.IMAGE_PATH+ "snake2Left.png").toURI().toString(), 70, 30,true, true);
        setX(xPos);
        setY(yPos);
        setImage(snake1Right);
    }

    @Override
    public void movement(Long now) {

        move(speed,0);
        if(getX()>=280 && speed>0)
            speed=-speed;
        else if(getX()<=10 && speed<0)
            speed=-speed;

        if(speed>0) {
            if (now / 900000000 % 2 == 1) {
                setImage(snake1Right);
            } else if (now / 900000000 % 2 == 0) {
                setImage(snake2Right);
            }

        }else{
            if (now / 900000000 % 2 == 1) {
                setImage(snake1Left);
            } else if (now / 900000000 % 2 == 0) {
                setImage(snake2Left);
            }
        }
    }

    public double getSpeed(){
        return  this.speed;
    }
}
