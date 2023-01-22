package myGame.Logic;


import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import java.util.List;

public class LogicFrog extends LogicEntities {


    public  List<LogicEntities> logicEntities;

    public static int position=1;
    public static int difficulty;

    double movementV = 31.2;
    double movementH = 15;
    double crocSpeed=0; //serve per capire se si muove verso destra o sinistra

    Scene game;

    public static boolean isAFK=false;//per quando stai afk e il tempo finisce
    boolean  isDeath = true;//per evitare che i key pressed/realesed in eccesso spostino l'animazione della morte
    boolean noMove=false;//per evitare che la rana continui a spostarsi se morta
    boolean carDeath=false;//per continuare a rimanere nell' if anche se finisce collisione
    private boolean singleClick = true;//per evitare che la rana si sposti in maniera diversa a seconda di quanto viene premuto il comando
    public static boolean death = false;//per evitare che la rana si possa muovere mentre l'animazione della morte è in corso
    public static int timeLeft;
    public static int timeMax;
    public static int points;
    public static int diffMult;
    public static int froggerLives;
    public static boolean lifeLost;
    public static int burrowCounter;
    double[] allVar=new double[7];
    private long lastUpdate = 0 ;//per aggiornare il timer ogni secondo



    public LogicFrog(List<LogicEntities> interceptable,Scene scene) {
        setX(135);
        setY(475);
        setWidth(30);
        setHeight(27.857);
        game=scene;
        this.logicEntities =interceptable;
        timeLeft=61;
        timeMax=61;
        froggerLives=5;
        burrowCounter=0;
        points=0;
        timeLeft-=(difficulty*15);
        timeMax-=(difficulty*15);
        diffMult=difficulty+5;
        froggerLives-=difficulty;
        timeLeft-=difficulty;

    }

    @Override
    public void movement(Long now) {

        control();

        //serve ad aggiornare il timer ogni secondo
        if(now - lastUpdate >= 1_000_000_000) {
            timeLeft--;
            lastUpdate=now;
        }
        allVar[0]=timeLeft;
        allVar[1]=points;
        allVar[2]=froggerLives;
        allVar[3]=burrowCounter;
        allVar[4]=position;
        allVar[5]=getX();
        allVar[6]=getY();


        //morte causata dalla rana che è uscita dalla mappa
        if(getX()<0 || getX()>340 || getY()>505){
            carDeath=true;
            death = true;
            isDeath = false;
            isDeath = Death.carDeath(now, this);
            timeLeft=timeMax;

        }

        //controllo che permette di riportare i buleani ai valori iniziali dopo che la rana è morta
        if(!isAFK)
            if(getY()==475 && getX()==135 ){
                death=false;
                noMove=false;
                carDeath=false;
                lifeLost=false;
                isAFK=false;
            }

        //morte causata per la fine del tempo
        if(timeLeft==0) {
            carDeath=true;
            death = true;
            isDeath = false;
            if(getY()==475 && getX()==135)
                isAFK=true;

            isDeath = Death.carDeath(now, this);
            timeLeft=timeMax;


        }

        //controllo della morte causata dalla collisione con i veicoli o con il serpente
        if(getY()>260)
            if (Collision.specificCollision(logicEntities, this, LogicVehicle.class) || Collision.specificCollision(logicEntities, this, LogicSnake.class) || carDeath) {
                carDeath=true;
                death = true;
                isDeath = false;
                isDeath = Death.carDeath(now, this);
                timeLeft=timeMax;
            }


        //ACQUA
        if (getY() < 260 && getY() > 107) {
            //gestione della collisione della rana con le tartarughe
            if(Collision.specificCollision(logicEntities, this, LogicTurtle.class) && !noMove) {
                LogicTurtle turtle = Collision.getOne(logicEntities, this, LogicTurtle.class);
                if(!turtle.isWet())
                    this.move(turtle.getSpeed(), 0);
                else{
                    death = true;
                    isDeath = false;
                    noMove=true;
                    isDeath = Death.waterDeath(now, this);
                    timeLeft=timeMax;
                }
                //gestione della collisione della rana con i tronchi
            }else if (Collision.specificCollision(logicEntities, this, LogicLog.class) && !noMove) {
                LogicLog log = Collision.getOne(logicEntities, this, LogicLog.class);
                this.move(log.getSpeed(), 0);

                //gestione della collisione della rana con i coccodrilli
            }else  if(Collision.specificCollision(logicEntities, this, LogicCrocodile.class) && !noMove){
                LogicCrocodile croc=Collision.getOne(logicEntities, this, LogicCrocodile.class);
                crocSpeed=croc.getSpeed();
                this.move(crocSpeed,0);
                if(croc.isHungry())
                    if((this.getX()>=croc.getX()+55 && crocSpeed>0) || (this.getX()<=croc.getX()+30 && crocSpeed<0)){
                        death = true;
                        isDeath = false;
                        noMove=true;
                        isDeath = Death.waterDeath(now, this);
                        timeLeft=timeMax;
                    }

                //morte causata dal contatto della rana con l'acqua
            }else {
                death = true;
                isDeath = false;
                noMove=true;
                isDeath = Death.waterDeath(now, this);
                timeLeft=timeMax;
            }

        }



        //ZONA VITTORIA
        if (getY() < 107) {
            if (Collision.specificCollision(logicEntities, this, LogicBurrow.class)) {
                LogicBurrow b = Collision.getOne(logicEntities, this, LogicBurrow.class);
                if (!b.isFull()) {
                    if (Collision.specificCollision(logicEntities, this, LogicBonus.class)) {
                        points += 1000 * diffMult;

                    }
                    this.setX(135);
                    this.setY(475);
                    b.setFrogEnd();
                    LogicVariables.xBurrow=b.getX();
                    burrowCounter++;
                    points+=800*diffMult;
                    timeLeft=timeMax;
                    RandomBonus.removePos((int) b.getX());
                } else {
                    death = true;
                    isDeath = false;
                    isDeath = Death.waterDeath(now, this);

                }


            }else{
                death = true;
                isDeath = false;
                isDeath = Death.waterDeath(now, this);
            }
        }



    }

    //gestisce i movimenti della rana
    public void control(){
        if (!death) {


            game.setOnKeyPressed(event -> {

                if ((event.getCode() == KeyCode.W ||event.getCode()== KeyCode.UP  ) && singleClick && getY() > 120) {
                    singleClick = false;
                    if (isDeath) {
                        move(0, -movementV);
                        position=5;
                    }

                } else if ((event.getCode() == KeyCode.A ||event.getCode()== KeyCode.LEFT  )  && singleClick ) {
                    singleClick = false;
                    if (isDeath) {
                        move(-movementH, 0);
                        position=6;
                    }

                } else if ((event.getCode() == KeyCode.S ||event.getCode()== KeyCode.DOWN  )  && singleClick) {
                    singleClick = false;
                    if (isDeath) {
                        move(0, movementV);
                        position=7;
                    }

                } else if ((event.getCode() == KeyCode.D ||event.getCode()== KeyCode.RIGHT  )  && singleClick) {
                    singleClick = false;
                    if (isDeath) {
                        move(movementH, 0);
                        position=8;
                    }
                }
            });
        }

        if (!death) {


            game.setOnKeyReleased(event -> {

                if (event.getCode() == KeyCode.W ||event.getCode()== KeyCode.UP  ) {
                    if (isDeath) {
                        singleClick = true;
                        position=1;
                        points+= diffMult;
                    }
                } else if (event.getCode() == KeyCode.A ||event.getCode()== KeyCode.LEFT ) {
                    if (isDeath) {
                        singleClick = true;
                        position=2;
                        points+= diffMult;
                    }
                } else if (event.getCode() == KeyCode.S ||event.getCode()== KeyCode.DOWN ) {
                    if(isDeath) {
                        singleClick = true;
                        position=3;
                        points+= diffMult;
                    }
                } else if (event.getCode() == KeyCode.D ||event.getCode()== KeyCode.RIGHT ) {
                    if(isDeath) {

                        singleClick = true;
                        position=4;
                        points+= diffMult;
                    }
                }
            });
        }
    }
}