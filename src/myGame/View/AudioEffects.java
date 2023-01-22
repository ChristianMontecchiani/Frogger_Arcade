package myGame.View;

import javafx.scene.media.AudioClip;

import java.io.File;
import java.util.*;

import static myGame.View.MenuScene.AUDIO_PATH;




public class AudioEffects {  //fare update delle stringhe

    static String frogSound = new File(AUDIO_PATH + "frog.wav").toURI().toString();
    static String carPassSound= new File(AUDIO_PATH + "car-pass.wav").toURI().toString();
    static String splashSound= new File(AUDIO_PATH + "splash.wav").toURI().toString();
    static String waterSplashSound= new File(AUDIO_PATH + "water-splash.wav").toURI().toString();
    static String sirenSound= new File(AUDIO_PATH + "siren.wav").toURI().toString();
    static String hornSound= new File(AUDIO_PATH + "long-horn.wav").toURI().toString();
    static String jump = new File(AUDIO_PATH + "jump.wav").toURI().toString();
    static String goal = new File(AUDIO_PATH + "goal.wav").toURI().toString();
    static String bonusSound = new File(AUDIO_PATH + "bonus.wav").toURI().toString();
    static String die= new File(AUDIO_PATH + "frog_die.wav").toURI().toString();
    public final static AudioClip bonus = new AudioClip(bonusSound);
    public final static AudioClip frogJump = new AudioClip(jump);
    public final static AudioClip frogGoal = new AudioClip(goal);
    public final static AudioClip frogDie = new AudioClip(die);



    //FROG EFFECT
    private final static  AudioClip frogAudio = new AudioClip(frogSound);

    //WATER EFFECTS
    private final static AudioClip splash= new AudioClip(splashSound);
    public final static AudioClip waterSplash= new AudioClip(waterSplashSound);

    //ROAD EFFECTS
    private final static AudioClip siren = new AudioClip(sirenSound);
    private final static AudioClip carPass = new AudioClip(carPassSound);
    private final static AudioClip horn = new AudioClip(hornSound);



    public static void playRandomAmbientSound(double timeLeft, Frog frog){

        LinkedList<AudioClip>road_effects = new LinkedList<>();
        road_effects.add(siren);
        road_effects.add(carPass);
        road_effects.add(horn);

        LinkedList<AudioClip> water_effects = new LinkedList<>();
        water_effects.add(splash);
        water_effects.add(waterSplash);
        Random rand = new Random();

        //FROG
        if(timeLeft %20 ==0)
            frogAudio.play(20);

        //WATER
        if(timeLeft%4==0 && frog.getY() < 260 && frog.getY() > 107)
            water_effects.get(rand.nextInt(2)).play(20);


        //ROAD
        if(timeLeft%5==0 && frog.getY()< 465 && frog.getY() >260)
            road_effects.get(rand.nextInt(3)).play(20);
    }
}
