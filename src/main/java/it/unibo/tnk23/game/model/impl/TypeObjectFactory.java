package it.unibo.tnk23.game.model.impl;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import it.unibo.tnk23.game.model.api.TypeObject;

public final class TypeObjectFactory {

    private static final Map<String,TypeObject> types = setTypes();

    private static Map<String, TypeObject> setTypes() {
        JSONParser parser = new JSONParser();
        JSONArray jsonArray;
        Map<String, TypeObject> toReturn = new HashMap<>();
        try {
            jsonArray = (JSONArray) parser.parse(
                    new FileReader(new File(ClassLoader.getSystemResource("it/unibo/objectTypes.json").toURI())));

            for (Object o : jsonArray) {
                JSONObject jo = (JSONObject) o;
                toReturn.put((String) jo.get("type"),
                        new TypeObjectImpl((long) jo.get("height"),
                                (long) jo.get("width"),
                                (double) jo.get("speed"),
                                (long) jo.get("health")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return toReturn;
    }

    public static TypeObject getPlayerType() {
        return types.get("player");
    }

    public static TypeObject getObstacleType() {
        return types.get("obstacle");
    }

    public static TypeObject getBulletType() {
        return types.get("bullet");
    }

    public static TypeObject getEnemyType() {
        return types.get("enemy");
    }

    
}
