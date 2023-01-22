package myGame.Logic;



public class LogicBurrow extends LogicEntities{

    private boolean full=false;

    public LogicBurrow(double x,double y){
        setX(x);
        setY(y);
        setHeight(31);
        setWidth(29);
    }

    public void setFrogEnd(){
        full= true;
    }


    public boolean isFull(){
        return full;
    }

    @Override
    public void movement(Long now) {


    }
}
