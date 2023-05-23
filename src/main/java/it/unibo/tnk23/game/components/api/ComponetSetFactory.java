package it.unibo.tnk23.game.components.api;

import java.util.Set;

import it.unibo.tnk23.game.model.api.TypeObject;

public interface ComponetSetFactory {
    
    Set<Component> getPlayerComponents();

    Set<Component> getComponents(TypeObject type);
}
