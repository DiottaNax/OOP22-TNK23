package it.unibo.tnk23.game.model.impl;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import it.unibo.tnk23.game.model.api.TypeObject;

/**
 * The factory class for creating and managing TypeObject instances.
 * It parses a JSON file to set up the types and provides methods to retrieve TypeObjects
 * for specific types and perform type-related checks.
 */
public final class TypeObjectFactory {

    private static final Map<String,TypeObject> types = setTypes();

    /**
     * Sets up the types by parsing a JSON file and creating TypeObjectImpl instances.
     *
     * @return a map of type names to corresponding TypeObject instances
     */
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

    /**
     * Retrieves the TypeObject representing the player type.
     *
     * @return the TypeObject for the player type
     */
    public static TypeObject getPlayerType() {
        return types.get("player");
    }

    /**
     * Retrieves the TypeObject representing the obstacle type.
     *
     * @return the TypeObject for the obstacle type
     */
    public static TypeObject getObstacleType() {
        return types.get("obstacle");
    }

    /**
     * Retrieves the TypeObject representing the bullet type.
     *
     * @return the TypeObject for the bullet type
     */
    public static TypeObject getBulletType() {
        return types.get("bullet");
    }

    /**
     * Retrieves the TypeObject representing the enemy type.
     *
     * @return the TypeObject for the enemy type
     */
    public static TypeObject getEnemyType() {
        return types.get("enemy");
    }

    /**
     * Retrieves the TypeObject representing the tower type.
     *
     * @return the TypeObject for the tower type
     */
    public static TypeObject getTowerType() {
        return types.get("tower");
    }

    /**
     * Checks if the given TypeObject represents the player type.
     *
     * @param type the TypeObject to check
     * @return true if the TypeObject represents the player type, false otherwise
     */
    public static boolean isPlayer(TypeObject type) {
        return type.equals(types.get("player"));
    }

    /**
     * Checks if the given TypeObject represents the enemy type.
     *
     * @param type the TypeObject to check
     * @return true if the TypeObject represents the enemy type, false otherwise
     */
    public static boolean isEnemy(TypeObject type) {
        return type.equals(types.get("enemy"));
    }

    /**
     * Checks if the given TypeObject represents the bullet type.
     *
     * @param type the TypeObject to check
     * @return true if the TypeObject represents the bullet type, false otherwise
     */
    public static boolean isBullet(TypeObject type) {
        return type.equals(types.get("bullet"));
    }

    /**
     * Checks if the given TypeObject represents the obstacle type.
     *
     * @param type the TypeObject to check
     * @return true if the TypeObject represents the obstacle type, false otherwise
     */
    public static boolean isObstacle(TypeObject type) {
        return type.equals(types.get("obstacle"));
    }

    /**
     * Checks if the given TypeObject represents the tower type.
     *
     * @param type the TypeObject to check
     * @return true if the TypeObject represents the tower type, false otherwise
     */
    public static boolean isTower(TypeObject type) {
        return type.equals(types.get("tower"));
    }

    
}
