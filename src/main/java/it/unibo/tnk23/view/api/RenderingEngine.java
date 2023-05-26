package it.unibo.tnk23.view.api;

public interface RenderingEngine<X> {

    X getGamePane();

    void render();
    
}
