package myGame.Logic;


import myGame.View.IView;
import javafx.scene.Scene;
import java.util.ArrayList;
import java.util.List;


import static myGame.Logic.LogicBonus.newPos;
import static myGame.Logic.LogicFrog.*;


public class LogicVariables implements ILogic {

private  IView view;
private static boolean timerActive=false;
public static List<LogicEntities> logicEntities;
public static Scene scene;
public static LogicFrog fr;
public static double xBurrow=0;
private static boolean isStarted=false;

public void setView(IView view){
    this.view=view;
}
public void setDifficulty(){
    LogicFrog.difficulty=view.getDifficulty();

}
    @Override
    public double[] getVariables() {
        double[] allVar=new double[7];
        allVar[0]=timeLeft;
        allVar[1]=points;
        allVar[2]=froggerLives;
        allVar[3]=burrowCounter;
        allVar[4]=position;
        allVar[5]=fr.getX();
        allVar[6]=fr.getY();

        return allVar;
    }

    @Override
    public boolean updateLifeLost() {
        return lifeLost;
    }

    @Override
    public double updateBonus() {
        return newPos;
    }

    @Override
    public double getXBurrow() {
        return xBurrow;
    }

    public void setLifelost(){
            lifeLost=view.getLifeLost();

    }

    @Override
    public void updateLogic(long now) {
        updateTimer();
        if (!isStarted) {
            setLifelost();
            logicEntities = createLogicList();
            Scene scene = view.getScene();
            fr = new LogicFrog(logicEntities, scene);
            isStarted = true;
        }
        if(timerActive) {
            for (LogicEntities logicEntity : logicEntities)
                logicEntity.movement(now);
                fr.movement(now);

        }

    }

    public void setIsStarted(){//si deve aggiornare solo quando rinizia
        isStarted=view.updateIsStarted();
    }

    public void updateTimer(){
        timerActive=view.getTimerActive();
    }

    @SuppressWarnings("unchecked")
    public  <T extends LogicEntities> List<T>  createLogicList(){
        int size=view.getNumberOfEntites();
        ArrayList<T> someArray = new ArrayList<>();
        double[] thisEntity;
        for(int i=0;i<size;i++){
            thisEntity=view.getEntity(i);
            if(thisEntity[0]==1){
                LogicLog log=new LogicLog((int)thisEntity[4],thisEntity[1]+1.5,thisEntity[2],thisEntity[3]);
                someArray.add((T)log);
            }else if(thisEntity[0]==2){
                LogicVehicle car=new LogicVehicle((int)thisEntity[4],thisEntity[1]+1.5,thisEntity[2],thisEntity[3]);
                someArray.add((T)car);
            }else if(thisEntity[0]==3){
                LogicTurtle turt=new LogicTurtle(thisEntity[1]+1.5,thisEntity[2],thisEntity[3]);
                someArray.add((T)turt);
            }else if(thisEntity[0]==4){
                LogicSnake snake=new LogicSnake(thisEntity[1]+1.5,thisEntity[2],thisEntity[3]);
                someArray.add((T)snake);
            }else if(thisEntity[0]==5){
                LogicCrocodile croc=new LogicCrocodile(thisEntity[1]+1.5,thisEntity[2],thisEntity[3]);
                someArray.add((T)croc);
            }else if(thisEntity[0]==6){
                LogicBurrow bur=new LogicBurrow(thisEntity[1],thisEntity[2]);
                someArray.add((T)bur);
            }else if(thisEntity[0]==7){
                LogicBonus bonus=new LogicBonus();
                someArray.add((T)bonus);
            }
         }

    return someArray;
}


}
