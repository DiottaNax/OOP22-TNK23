package it.unibo.tnk23.game.components.impl;

import it.unibo.tnk23.game.components.api.AbstractComponent;
import it.unibo.tnk23.game.components.api.Message;
import it.unibo.tnk23.game.components.api.NotifiableComponent;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.TypeObject;
import it.unibo.tnk23.game.world.api.World;

public class BulletComponent extends AbstractComponent implements NotifiableComponent {

    private static TypeObject typeSourceObject; //come faccio a metterlo = a bullet di default?


    public BulletComponent(final GameObject entity, final World world) {
        super(entity, world);
    }

    @Override
    public void update() {
    }

    @Override
    public <X> void receive(Message<X> x) {
        if(x instanceof GameObject) {
            GameObject obj = (GameObject) x;
            TypeObject typeObj = obj.getType();
            if(typeObj.toString() == "player") {
                typeSourceObject = typeObj;
            } else if (typeObj.toString() == "enemies") {
                typeSourceObject = typeObj;
            }
        }
    }

    public TypeObject getSourceShooter() {
        if (typeSourceObject.toString() == "player") {
            return typeSourceObject;
        } else if (typeSourceObject.toString() == "enemies") {
            return typeSourceObject;
        }
        return typeSourceObject;
    }

}
