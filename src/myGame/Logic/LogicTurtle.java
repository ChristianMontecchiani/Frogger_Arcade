package myGame.Logic;


public class LogicTurtle extends LogicEntities{

    private final double speed;
    boolean sink = false;


    public LogicTurtle(double xPos, double yPos, double speed) {
        setX(xPos);
        setY(yPos);
        setWidth(70);
        setHeight(22.07);
        this.speed = speed;
    }

    public boolean isWet() {
        return sink;
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

        if ((now/3/ 900000000 +((int)this.getY()/100) )% 4 == 1)
            sink = true;
        else if ((now/3/900000000+((int)this.getY()/100))% 4 == 2)
            sink = true;
        else if ((now/3/ 900000000 +((int)this.getY()/100) )% 4== 3)
            sink = true;
        else if ((now/3/ 900000000 +((int)this.getY()/100) ) % 4== 0)
            sink=false;

        else if ((now/3/ 900000000 +((int)this.getY()/100) ) % 3== 1)
            sink=false;
        else if ((now/3/ 900000000 +((int)this.getY()/100) )% 3== 2)
            sink=false;

    }
}