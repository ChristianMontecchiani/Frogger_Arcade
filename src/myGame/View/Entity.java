package myGame.View;

import javafx.scene.image.ImageView;


public abstract class Entity extends ImageView {


    public void move(double dx, double dy) {
        setX(getX() + dx);
        setY(getY() + dy);
    }

    public abstract void movement(Long now);

}
