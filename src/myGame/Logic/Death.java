package myGame.Logic;


public class Death {



    static int carD = 0;
    static int watD = 0;

    public static boolean carDeath(Long now, LogicFrog frog){

        boolean death= false;

        if((now% 12)==0)
            carD++;

        if(carD==1) {
            LogicFrog.position=9;
        }
        if(carD==2) {
            LogicFrog.position=10;

        }
        if(carD==3) {
            LogicFrog.position=11;
        }if (carD == 4) {
            LogicFrog.position=1;
            carD = 0;
            frog.setX(135);
            frog.setY(475);
            death=true;
            LogicFrog.froggerLives--;
            LogicFrog.lifeLost=true;
            LogicFrog.isAFK=false;
            LogicFrog.points-=300/LogicFrog.diffMult;

        }
        return death;
    }


    public static boolean waterDeath(Long now, LogicFrog frog) {

        boolean death=false;

        if((now%12)==0)
            watD++;

        if(watD==1) {
            LogicFrog.position=12;
        }

        if(watD==2) {
            LogicFrog.position=13;

        }
        if(watD==3) {
            LogicFrog.position=14;

        }if (watD == 4) {
            LogicFrog.position=1;
            watD = 0;
            frog.setX(135);
            frog.setY(475);
            death=true;
            LogicFrog.froggerLives--;
            LogicFrog.lifeLost=true;
            LogicFrog.isAFK=false;
            LogicFrog.points-=300/LogicFrog.diffMult;

        }
        return death;
    }
}