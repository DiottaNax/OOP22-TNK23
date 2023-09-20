package it.unibo.tnk23.game.model.impl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.unibo.tnk23.game.model.api.GameObjectType;

/**
 * This is a utility class for creating and managing TypeObject instances.
 * It parses a JSON file to set up the types and provides methods to retrieve TypeObjects
 * for specific types and perform type-related checks.
 */
public final class GameObjectTypeManager {

    private static final Map<String, GameObjectType> TYPES = retrieveTypes();
    private static final Logger LOGGER = Logger.getLogger("TypeObjectFactoryLogger");
    /**
     * Utility classes' constructor should never be called.
     */
    private GameObjectTypeManager() {
    }

    /**
     * Retrieves types by parsing a JSON file and creating {@link GameObjectTypeImpl} instances.
     *
     * @return a map of type names to corresponding {@link GameObjectType} instances
     */
    private static Map<String, GameObjectType> retrieveTypes() {
        final JSONParser parser = new JSONParser();
        JSONArray jsonArray;
        final Map<String, GameObjectType> toReturn = new HashMap<>();
        final var name = ClassLoader.getSystemResourceAsStream("it/unibo/objectTypes.json");
        try (Reader reader = new InputStreamReader(name, "UTF-8")) {
            jsonArray = (JSONArray) parser.parse(reader);

            for (final Object o : jsonArray) {
                final JSONObject jo = (JSONObject) o;
                toReturn.put((String) jo.get("type"),
                        new GameObjectTypeImpl((long) jo.get("height"),
                                (long) jo.get("width"),
                                (double) jo.get("speed"),
                                (long) jo.get("health")));
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Json file about types not found: ", e);
        } catch (ParseException e) {
            LOGGER.log(Level.SEVERE, "Error while parsing json array", e);
        }

        return toReturn;
    }

    /**
     * Retrieves the TypeObject representing the player type.
     *
     * @return the TypeObject for the player type
     */
    public static GameObjectType getPlayerType() {
        return TYPES.get("player").copy();
    }

    /**
     * Retrieves the TypeObject representing the obstacle type.
     *
     * @return the TypeObject for the obstacle type
     */
    public static GameObjectType getObstacleType() {
        return TYPES.get("obstacle").copy();
    }

    /**
     * Retrieves the TypeObject representing the bullet type.
     *
     * @return the TypeObject for the bullet type
     */
    public static GameObjectType getBulletType() {
        return TYPES.get("bullet").copy();
    }

    /**
     * Retrieves the TypeObject representing the enemy type.
     *
     * @return the TypeObject for the enemy type
     */
    public static GameObjectType getEnemyType() {
        return TYPES.get("enemy").copy();
    }

    /**
     * Retrieves the TypeObject representing the tower type.
     *
     * @return the TypeObject for the tower type
     */
    public static GameObjectType getTowerType() {
        return TYPES.get("tower").copy();
    }

    /**
     * Retrieves the TypeObject representing the tower type.
     *
     * @return the TypeObject for the tower type
     */
    public static GameObjectType getBonusType() {
        return TYPES.get("bonus").copy();
    }

    /**
     * Checks if the given TypeObject represents the player type.
     *
     * @param type the TypeObject to check
     * @return true if the TypeObject represents the player type, false otherwise
     */
    public static boolean isPlayer(final GameObjectType type) {
        return type.equals(TYPES.get("player"));
    }

    /**
     * Checks if the given TypeObject represents the enemy type.
     *
     * @param type the TypeObject to check
     * @return true if the TypeObject represents the enemy type, false otherwise
     */
    public static boolean isEnemy(final GameObjectType type) {
        return type.equals(TYPES.get("enemy"));
    }

    /**
     * Checks if the given TypeObject represents the bullet type.
     *
     * @param type the TypeObject to check
     * @return true if the TypeObject represents the bullet type, false otherwise
     */
    public static boolean isBullet(final GameObjectType type) {
        return type.equals(TYPES.get("bullet"));
    }

    /**
     * Checks if the given TypeObject represents the obstacle type.
     *
     * @param type the TypeObject to check
     * @return true if the TypeObject represents the obstacle type, false otherwise
     */
    public static boolean isObstacle(final GameObjectType type) {
        return type.equals(TYPES.get("obstacle"));
    }

    /**
     * Checks if the given TypeObject represents the tower type.
     *
     * @param type the TypeObject to check
     * @return true if the TypeObject represents the tower type, false otherwise
     */
    public static boolean isTower(final GameObjectType type) {
        return type.equals(TYPES.get("tower"));
    }

}
