package myGame.Logic;



import java.util.ArrayList;
import java.util.List;

public class Collision {




    public static <T extends LogicEntities> boolean specificCollision(List<LogicEntities> interceptable, LogicFrog f, Class<T> cls){
        boolean collides=false;
        List<T> sameEntities=getSpecificEntity(interceptable,cls);
        for(T entity: sameEntities)
            if(entity.intersects(f.getBoundsInLocal()))
                collides=true;



        return collides;
    }

    @SuppressWarnings("unchecked")
    public static <T extends LogicEntities> List<T> getSpecificEntity(List<LogicEntities> interceptable, Class<T> cls) {
        ArrayList<T> entityArray = new ArrayList<>();
        for (LogicEntities logicEntity : interceptable)
            if (cls.isInstance(logicEntity))
                entityArray.add((T)logicEntity);

        return entityArray;
    }

    public static <T extends LogicEntities> T getOne(List<LogicEntities> interceptable, LogicFrog f, Class<T> cls){
        T theOne=null;
        List<T> sameOnes=getSpecificEntity(interceptable,cls);
        for(T one: sameOnes)
            if(one.intersects(f.getBoundsInLocal()))
                theOne=one;

        return theOne;

    }


}