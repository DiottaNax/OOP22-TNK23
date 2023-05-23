package it.unibo.tnk23.game.model.api;

import java.util.Set;

import it.unibo.tnk23.game.components.api.Component;

public interface ComponentSetFactory {
    
    Set<? extends Component> getComponents(TypeObject type);
}
