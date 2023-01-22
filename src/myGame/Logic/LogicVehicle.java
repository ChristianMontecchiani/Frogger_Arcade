package myGame.Logic;


public class LogicVehicle extends LogicEntities {
    double speed;

    public LogicVehicle(int  type, double xPos, double yPos, double speed){

        setX(xPos);
        setY(yPos);
        this.speed= speed;

        if(type==1){
            setWidth(30);
            setHeight(22.88);
        }else if(type==2){
            setWidth(60);
            setHeight(19.426);

        }else{
            setWidth(90);
            setHeight(16.847);
        }

    }

    @Override
    public void movement(Long now) {
        move(speed , 0);
        if (getX() > 600 && speed>0)
            setX(-180);
        if (getX() < -50 && speed<0)
            setX(700);
    }
}