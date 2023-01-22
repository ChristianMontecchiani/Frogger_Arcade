package myGame.Logic;



public class LogicCrocodile extends LogicEntities {
    private final double speed;
    private boolean hungry=false;

    public LogicCrocodile(double xPos,double yPos,double speed){

        this.speed=speed;
        setX(xPos);
        setY(yPos);
        setWidth(90);
        setHeight(26.526);

    }

    public double getSpeed(){
        return this.speed;
    }

    @Override
    public void movement(Long now) {
        move(speed,0);

        if ((now/3/ 900000000 +((int)this.getY()/100) ) % 2 == 1)
            hungry = true;
        else if ((now/3/ 900000000 +((int)this.getY()/100) )% 2 == 0)
            hungry = false;


        if (getX()>500 && speed>0)
            setX(-180);
        if (getX()<-50 && speed<0)
            setX(700);

    }

    public boolean isHungry(){return hungry;}
}