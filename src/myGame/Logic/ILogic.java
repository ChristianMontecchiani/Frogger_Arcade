package myGame.Logic;

public interface ILogic {

     void updateLogic(long now);

     double[] getVariables();

     boolean updateLifeLost();

     double updateBonus();

     double getXBurrow();


}
