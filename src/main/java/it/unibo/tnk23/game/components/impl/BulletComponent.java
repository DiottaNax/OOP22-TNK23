package it.unibo.tnk23.game.components.impl;

import it.unibo.tnk23.game.components.api.AbstractComponent;
import it.unibo.tnk23.game.components.api.Message;
import it.unibo.tnk23.game.components.api.NotifiableComponent;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.TypeObject;
import it.unibo.tnk23.game.model.impl.TypeObjectFactory;
import it.unibo.tnk23.game.model.api.World;

public class BulletComponent extends AbstractComponent implements NotifiableComponent {

    private static TypeObject shooterType;


    public BulletComponent(final GameObject entity, final World world) {
        super(entity, world);
        shooterType = TypeObjectFactory.getBulletType();
    }

    @Override
    public void update() {
    }

    @Override
    public <X> void receive(Message<X> x) {
        if(x.getMessage() instanceof GameObject) {
            GameObject obj = (GameObject) x.getMessage();
            shooterType = obj.getType();
        }
    }

    public TypeObject getShooter() {
        return shooterType;
    }

}
