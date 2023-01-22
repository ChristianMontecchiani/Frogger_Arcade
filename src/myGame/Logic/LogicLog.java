package myGame.Logic;


public class LogicLog extends LogicEntities {

    private final double speed;

    public LogicLog( int type,double x, double y, double speed) {
        setX(x+1);
        setY(y);
        this.speed = speed;
        if(type==0){
            setWidth(90);
            setHeight(16.15);
        }else {
            setWidth(70);
            setHeight(17.5);
        }

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

}
