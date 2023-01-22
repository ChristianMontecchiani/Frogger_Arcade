package myGame.View;

import javafx.scene.Scene;

public interface  IView {

       int getDifficulty();

       void updateView(long now);

       int getNumberOfEntites();

       double[] getEntity(int index);

       Scene getScene();

       boolean getLifeLost();

       boolean getTimerActive();

       boolean updateIsStarted();


}
