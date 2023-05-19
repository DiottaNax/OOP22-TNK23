package it.unibo.tnk23.game.components.impl;

import it.unibo.tnk23.game.components.api.AbstractComponent;
import it.unibo.tnk23.game.components.api.Message;
import it.unibo.tnk23.game.components.api.NotifiableComponent;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.TypeObject;
import it.unibo.tnk23.game.model.impl.TypeObjectFactory;
import it.unibo.tnk23.game.world.api.World;

public class BulletComponent extends AbstractComponent implements NotifiableComponent {

    private static TypeObject typeSourceObject;


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
            if(typeObj == TypeObjectFactory.getPlayerType()) {
                typeSourceObject = typeObj;
            } else if (typeObj == TypeObjectFactory.getEnemyType()) {
                typeSourceObject = typeObj;
            }
        }
    }

    public TypeObject getSourceShooter() {
        if (typeSourceObject == TypeObjectFactory.getPlayerType()) {
            return typeSourceObject;
        } else if (typeSourceObject == TypeObjectFactory.getEnemyType()) {
            return typeSourceObject;
        }
        return TypeObjectFactory.getBulletType();
    }

}
