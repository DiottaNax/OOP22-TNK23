package it.unibo.tnk23.game.components.impl;

import java.util.HashSet;
import java.util.Set;

import it.unibo.tnk23.game.components.api.Component;
import it.unibo.tnk23.game.components.api.ComponetSetFactory;
import it.unibo.tnk23.game.model.api.TypeObject;

public class ComponentSetFactoryImpl implements ComponetSetFactory{

    @Override
    public Set<Component> getPlayerComponents() {
        Set<Component> componentSet = new HashSet<>();
        componentSet.add(new PlayerFireComponent(null, null));
        componentSet.add(new TimeFireComponent(null, null));
        return componentSet;
    }

    @Override
    public Set<Component> getComponents(TypeObject type) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getComponents'");
    }

    
}
