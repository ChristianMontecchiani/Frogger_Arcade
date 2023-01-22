package myGame.View;


import myGame.Logic.ILogic;
import javafx.event.Event;
import javafx.scene.Scene;

import static myGame.View.AudioEffects.frogGoal;
import static myGame.View.GameScene.*;
import static myGame.View.MenuScene.autoPlay;
import static myGame.View.MenuScene.mainScene;

public class ViewVariables implements IView {


    public static boolean restarted=false;
    public static Event exitEvent;
    private ILogic logic;
    double xBurrow=0;
    double checkBurr=0;
    public static int sceneManager=0;
    private static final MenuScene menu=new MenuScene();
    public static Scene activeScene;

    public void setLogic(ILogic logic){
        this.logic=logic;
    }

    @Override
    public  int getDifficulty() {
        return difficulty;
    }

    @Override
    public void updateView(long now) {
        setVariables();
        setLifeLost();
        setBonus();
        GameScene.moveView(now);
        xBurrow=logic.getXBurrow();
        if(xBurrow!=checkBurr){
            setBurrow(xBurrow);
                    }
    }

    @Override
    public int getNumberOfEntites() {
        return interceptable.size();
    }

    @Override
    public Scene getScene() {
        activeScene=GameScene.scene;
        if(sceneManager==0) {
            menu.getMenuScene();
            activeScene=mainScene;
        }
        return activeScene;
    }

    public int getSceneManager(){
        return  sceneManager;
    }


    @Override
    public boolean getLifeLost(){
        return lifelost;
    }

    @Override
    public boolean getTimerActive() {
        return timerActive;
    }

    @Override
    public boolean updateIsStarted() {
        return isStarted;
    }

    public void restart(){
        burrowCounter=0;
        FROGGER_LIVES=5;
        timeLeft=60;
        points=0;
        restarted=true;
    }


    public  void setBonus() {
        Bonus.bonusX = logic.updateBonus();
    }

    public  void setLifeLost(){
        GameScene.lifelost=logic.updateLifeLost();
    }

    public  void setVariables(){
        double[] allVar=logic.getVariables();
        GameScene.timeLeft= (int) allVar[0];
        GameScene.points= (int) allVar[1];
        FROGGER_LIVES= (int) allVar[2];
        burrowCounter= (int) allVar[3];
        Frog.position= (int) allVar[4];
        Frog.xpos= (int) allVar[5];
        Frog.ypos= (int) allVar[6];
    }

    @Override
    public double[] getEntity(int index) {
        double[] entityValues = new double[5];
                Entity entity=interceptable.get(index);
                if (entity instanceof Log) {
                    Log isLog=(Log)entity;
                    entityValues[0]=1;
                    entityValues[1]=isLog.getX();
                    entityValues[2]=isLog.getY();
                    entityValues[3]=isLog.getSpeed();
                    entityValues[4]=isLog.getType();
                } else if (entity instanceof Vehicle) {
                    Vehicle isVehicle=(Vehicle)entity;
                    entityValues[0]=2;
                    entityValues[1]=isVehicle.getX();
                    entityValues[2]=isVehicle.getY();
                    entityValues[3]=isVehicle.getSpeed();
                    entityValues[4]=isVehicle.getType();
                } else if (entity instanceof Turtle) {
                    Turtle isTurtle=(Turtle)entity;
                    entityValues[0]=3;
                    entityValues[1]=isTurtle.getX();
                    entityValues[2]=isTurtle.getY();
                    entityValues[3]=isTurtle.getSpeed();
                } else if (entity instanceof Snake) {
                    Snake isSnake=(Snake)entity;
                    entityValues[0]=4;
                    entityValues[1]=isSnake.getX();
                    entityValues[2]=isSnake.getY();
                    entityValues[3]=isSnake.getSpeed();
                } else if (entity instanceof Crocodile) {
                    Crocodile isCroc=(Crocodile)entity;
                    entityValues[0]=5;
                    entityValues[1]=isCroc.getX();
                    entityValues[2]=isCroc.getY();
                    entityValues[3]=isCroc.getSpeed();
                } else if (entity instanceof Burrow) {
                    Burrow isBurr=(Burrow)entity;
                    entityValues[0]=6;
                    entityValues[1]=isBurr.getX();
                    entityValues[2]=isBurr.getY();
                } else if (entity instanceof Bonus) {
                    entityValues[0]=7;
                }

        return entityValues;
    }

    public  void setBurrow(double xPos) {
        for(Entity entity:interceptable)
            if(entity instanceof Burrow)
                if(entity.getX()==xPos){
                    Burrow isBurr=(Burrow)entity;
                    isBurr.setFrogEnd();
                    entity=isBurr;
                }
        if(autoPlay)
            frogGoal.play(20);

        checkBurr=xBurrow;
    }


}
