package it.unibo.tnk23.game.components.api;

public interface NotifiableComponent extends Component{

    <X> void receive(Message<X> x);
    
}
