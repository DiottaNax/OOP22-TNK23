package it.unibo.tnk23.view.impl;

import java.util.function.Consumer;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;

public class GameScenePane extends BorderPane {
    
        public GameScenePane(Parent gameSceneRoot) {
                SubScene gameScene = new SubScene(gameSceneRoot, BASELINE_OFFSET_SAME_AS_HEIGHT,
                                BASELINE_OFFSET_SAME_AS_HEIGHT);
                SubScene leftMenu = new SubScene(gameSceneRoot, BASELINE_OFFSET_SAME_AS_HEIGHT,
                                BASELINE_OFFSET_SAME_AS_HEIGHT);
                SubScene rightMenu = new SubScene(gameSceneRoot, BASELINE_OFFSET_SAME_AS_HEIGHT,
                                BASELINE_OFFSET_SAME_AS_HEIGHT);
                this.getSetterLifeInfoMenu().accept(leftMenu);
                this.getSetterEnemiesInfoMenu().accept(rightMenu);
                this.setCenter(gameScene);
        }

        private Consumer<Node> getSetterLifeInfoMenu() {
                var dim = Screen.getPrimary().getBounds();
                return dim.getWidth() > getHeight() ? super::setLeft : super::setBottom;
        }

        private Consumer<Node> getSetterEnemiesInfoMenu() {
                var dim = Screen.getPrimary().getBounds();
                return dim.getWidth() > getHeight() ? super::setRight : super::setTop;
        }
}
