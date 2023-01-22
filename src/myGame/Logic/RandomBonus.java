package myGame.Logic;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class RandomBonus {

    private static int counter = 5;
    static Integer[] pos = new Integer[]{9, 83, 158, 233, 308};
    private final static List<Integer> visible = new LinkedList<>(Arrays.asList(pos));
    static int lastIndex = -1;

    public static int visiblePos() {
        int i;
        Random rand = new Random();
        i = visible.get(rand.nextInt(visible.size()));
        lastIndex = visible.indexOf(i);
        return i;
    }



    public static void removePos(int x) {
        counter--;
        if (counter != 0) {
            for (int i = 0; i < visible.size(); i++)
                if (visible.get(i) == x)
                    visible.remove(i);
        } else {
            visible.add(500);
            visible.remove(0);
        }
    }

}


