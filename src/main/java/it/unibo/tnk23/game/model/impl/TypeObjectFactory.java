package it.unibo.tnk23.game.model.impl;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.unibo.tnk23.game.model.api.TypeObject;

/**
 * This is a utility class for creating and managing TypeObject instances.
 * It parses a JSON file to set up the types and provides methods to retrieve TypeObjects
 * for specific types and perform type-related checks.
 */
public final class TypeObjectFactory {

    private static final Map<String, TypeObject> TYPES = retrieveTypes();
    private static final Logger LOGGER = Logger.getLogger("TypeObjectFactoryLogger");
    /**
     * Utility classes' constructor should never be called.
     */
    private TypeObjectFactory() {
    }

    /**
     * Retrieves types by parsing a JSON file and creating {@link TypeObjectImpl} instances.
     *
     * @return a map of type names to corresponding {@link TypeObject} instances
     */
    private static Map<String, TypeObject> retrieveTypes() {
        final JSONParser parser = new JSONParser();
        JSONArray jsonArray;
        final Map<String, TypeObject> toReturn = new HashMap<>();
        try {
            jsonArray = (JSONArray) parser.parse(
                    new FileReader(new File(ClassLoader.getSystemResource("it/unibo/objectTypes.json").toURI())));

            for (final Object o : jsonArray) {
                final JSONObject jo = (JSONObject) o;
                toReturn.put((String) jo.get("type"),
                        new TypeObjectImpl((long) jo.get("height"),
                                (long) jo.get("width"),
                                (double) jo.get("speed"),
                                (long) jo.get("health")));
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Json file about types not found: ", e);
        } catch (ParseException e) {
            LOGGER.log(Level.SEVERE, "Error while parsing json array", e);
        } catch (URISyntaxException e) {
            LOGGER.log(Level.SEVERE, "Error while converting file to URI", e);
        }
        return toReturn;
    }

    /**
     * Retrieves the TypeObject representing the player type.
     *
     * @return the TypeObject for the player type
     */
    public static TypeObject getPlayerType() {
        return TYPES.get("player");
    }

    /**
     * Retrieves the TypeObject representing the obstacle type.
     *
     * @return the TypeObject for the obstacle type
     */
    public static TypeObject getObstacleType() {
        return TYPES.get("obstacle");
    }

    /**
     * Retrieves the TypeObject representing the bullet type.
     *
     * @return the TypeObject for the bullet type
     */
    public static TypeObject getBulletType() {
        return TYPES.get("bullet");
    }

    /**
     * Retrieves the TypeObject representing the enemy type.
     *
     * @return the TypeObject for the enemy type
     */
    public static TypeObject getEnemyType() {
        return TYPES.get("enemy");
    }

    /**
     * Retrieves the TypeObject representing the tower type.
     *
     * @return the TypeObject for the tower type
     */
    public static TypeObject getTowerType() {
        return TYPES.get("tower");
    }

    /**
     * Checks if the given TypeObject represents the player type.
     *
     * @param type the TypeObject to check
     * @return true if the TypeObject represents the player type, false otherwise
     */
    public static boolean isPlayer(final TypeObject type) {
        return type.equals(TYPES.get("player"));
    }

    /**
     * Checks if the given TypeObject represents the enemy type.
     *
     * @param type the TypeObject to check
     * @return true if the TypeObject represents the enemy type, false otherwise
     */
    public static boolean isEnemy(final TypeObject type) {
        return type.equals(TYPES.get("enemy"));
    }

    /**
     * Checks if the given TypeObject represents the bullet type.
     *
     * @param type the TypeObject to check
     * @return true if the TypeObject represents the bullet type, false otherwise
     */
    public static boolean isBullet(final TypeObject type) {
        return type.equals(TYPES.get("bullet"));
    }

    /**
     * Checks if the given TypeObject represents the obstacle type.
     *
     * @param type the TypeObject to check
     * @return true if the TypeObject represents the obstacle type, false otherwise
     */
    public static boolean isObstacle(final TypeObject type) {
        return type.equals(TYPES.get("obstacle"));
    }

    /**
     * Checks if the given TypeObject represents the tower type.
     *
     * @param type the TypeObject to check
     * @return true if the TypeObject represents the tower type, false otherwise
     */
    public static boolean isTower(final TypeObject type) {
        return type.equals(TYPES.get("tower"));
    }


}
