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

public enum GameObjectType {
    PLAYER,
    ENEMY,
    OBSTACLE,
    BULLET,
    TOWER,
    UNDESTRYABLE_WALL;

    private final long width;
    private final long height;
    private final double speed;
    private final long health;
    private final String spriteName;

    GameObjectType() {
        final var type = GameObjectTypeManager.get(this.name());
        this.width = type.getWidth();
        this.height = type.getHeight();
        this.speed = type.getSpeed();
        this.health = type.getHealth();
        this.spriteName = type.getSpriteName();
    }

    public
    
    /**
    * This is a utility class for creating and managing TypeObject instances.
    * It parses a JSON file to set up the types and provides methods to retrieve TypeObjects
    * for specific types and perform type-related checks.
    */
    private final class GameObjectTypeManager {

        private static final Map<String, GameObjectInfo> TYPES = retrieveTypes();
        private static final Logger LOGGER = Logger.getLogger("GameObjectTypeManagerLogger");

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
        private static Map<String, GameObjectInfo> retrieveTypes() {
            final JSONParser parser = new JSONParser();
            JSONArray jsonArray;
            final Map<String, GameObjectInfo> toReturn = new HashMap<>();
            final var name = ClassLoader.getSystemResourceAsStream("it/unibo/objectTypes.json");
            try (Reader reader = new InputStreamReader(name, "UTF-8")) {
                jsonArray = (JSONArray) parser.parse(reader);

                for (final Object o : jsonArray) {
                    final JSONObject jo = (JSONObject) o;
                    toReturn.put((String) jo.get("type"),
                            new GameObjectInfo((long) jo.get("height"),
                                    (long) jo.get("width"),
                                    (double) jo.get("speed"),
                                    (long) jo.get("health"),
                                    (String) jo.get("spriteName")));
                }
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Json file about types not found: ", e);
            } catch (ParseException e) {
                LOGGER.log(Level.SEVERE, "Error while parsing json array", e);
            }

            return toReturn;
        }

        public static GameObjectInfo get(final String name) {
            return TYPES.get(name);
        }

    }
}
