package myGame.Logic;

import javafx.scene.shape.Rectangle;

public abstract class LogicEntities extends Rectangle {

    public void move(double dx, double dy) {
        setX(getX() + dx);
        setY(getY() + dy);
    }

    public abstract void movement(Long now);

}
