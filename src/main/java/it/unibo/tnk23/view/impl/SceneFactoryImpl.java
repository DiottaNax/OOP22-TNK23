package it.unibo.tnk23.view.impl;

import it.unibo.tnk23.view.api.SceneFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class SceneFactoryImpl implements SceneFactory{

    @Override
    public Scene getMenuScene() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMenuScene'");
    }

    @Override
    public Scene getGameScene(Parent root) {
        return new Scene(new GameScenePane(root));
    }

    @Override
    public Scene getGameOverScene() {
        return new Scene(null);
    }
}
