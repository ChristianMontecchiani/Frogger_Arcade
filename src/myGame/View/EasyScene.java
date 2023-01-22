package myGame.View;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import java.io.File;



public class EasyScene  {

    Label difficultyLabel;
    public static AnchorPane backgroundScene;
    ImageView backgroundImage;
    public static ImageView life1,life2,life3,life4,life5;
    public static Image lifeURL = new Image(new File(MenuScene.IMAGE_PATH + "cuore.png").toURI().toString(), 25,25, true,true);


    public AnchorPane setScene() {

        backgroundScene = new AnchorPane();
        backgroundScene.maxHeight(400);
        backgroundScene.maxWidth(800);
        backgroundScene.setPrefSize(400, 800);


        difficultyLabel = new Label("EASY" );
        difficultyLabel.setFont(new Font("Calibri", 20));
        AnchorPane.setTopAnchor(difficultyLabel, 10.0);
        AnchorPane.setLeftAnchor(difficultyLabel, 300.0);


        Image backgroundImageURL = new Image(new File(MenuScene.IMAGE_PATH + "iKogsKW.png").toURI().toString(), 350, 500, true, true, false);
        backgroundImage = new ImageView(backgroundImageURL);
        AnchorPane.setTopAnchor(backgroundImage, 40.0);
        backgroundScene.getChildren().addAll(backgroundImage);


        //tronchi
        Log firstLog1 = new Log(0, 350, 138, 1.0);
        Log firstLog6 = new Log(1,  200, 138, 1.0);
        Log firstLog2 = new Log(0,  50, 170, -1.0);
        Log firstLog7 = new Log(1,  250, 170, -1.0);
        Log firstLog8 = new Log(0,  100, 200, 1.0);
        Log firstLog3 = new Log(1,  300, 200, 1.0);
        Log firstLog9 = new Log(0,  150, 229, -1.0);
        Log firstLog4 = new Log(1,  350, 229, -1.0);
        Log firstLog10 =new Log(0,  200, 258, 1.0);
        Log firstLog5 = new Log(0,  400, 258, 1.0);
        Log firstLog11 = new Log(0, 500, 138, 1.0);
        Log firstLog12 = new Log(1, 450, 170, -1.0);
        Log firstLog13 = new Log(1, 420, 200, 1.0);
        Log firstLog14 = new Log(0, -50, 229, -1.0);
        Log firstLog15 = new Log(1, 20, 258, 1.0);
        Log firstLog16 = new Log(1, -50, 138, 1.0);
        Log firstLog17 = new Log(1, 650, 170, -1.0);
        Log firstLog18 = new Log(1, -90, 200, 1.0);
        Log firstLog19 = new Log(1, 600, 229, -1.0);
        Log firstLog20 = new Log(1, -140, 258, 1.0);

        backgroundScene.getChildren().addAll(firstLog1, firstLog2, firstLog3, firstLog4, firstLog5,
                firstLog6,firstLog7,firstLog8,firstLog9,firstLog10,
                firstLog11,firstLog12,firstLog13,firstLog14,firstLog15,
                firstLog16,firstLog17,firstLog18,firstLog19,firstLog20);


        //tartarughe   tur2 pos 170
        Turtle tur1=new Turtle(90,136,1.0);
        Turtle tur2=new Turtle(150,168,-1.0);
        Turtle tur3=new Turtle(200,198,1.0);
        Turtle tur4=new Turtle(250,229,-1.0);
        Turtle tur5=new Turtle(300,258,1.0);

        backgroundScene.getChildren().addAll(tur1,tur2,tur3,tur4,tur5);


        //macchine
        Vehicle car1 = new Vehicle(1, 300, 321, -1.7);
        Vehicle truck1 = new Vehicle(2, 210,351, -1.3);
        Vehicle car2 = new Vehicle(1,  150, 382, -1.5);
        Vehicle bigTruck1 = new Vehicle(3, 75,413, 1.3);
        Vehicle car3 = new Vehicle(1, 250, 444, -1.5);

        backgroundScene.getChildren().addAll(car1, car2, car3, truck1, bigTruck1);


        //end
        Burrow bur1 = new Burrow(9, 102);
        Burrow bur2 = new Burrow(83, 102);
        Burrow bur3 = new Burrow(158, 102);
        Burrow bur4 = new Burrow(233, 102);
        Burrow bur5 = new Burrow(308, 102);
        Bonus b = new Bonus();

        backgroundScene.getChildren().addAll(bur1, bur2, bur3, bur4, bur5, b);


        //Vite
        life1 = new ImageView(lifeURL);
        life1.setX(230);
        life1.setY(50);

        life2 = new ImageView(lifeURL);
        life2.setX(250);
        life2.setY(50);

        life3 = new ImageView(lifeURL);
        life3.setX(270);
        life3.setY(50);

        life4 = new ImageView(lifeURL);
        life4.setX(290);
        life4.setY(50);

        life5 = new ImageView(lifeURL);
        life5.setX(310);
        life5.setY(50);

        backgroundScene.getChildren().addAll(life1,life2,life3, life4, life5,difficultyLabel);


        return  backgroundScene;
    }
}
