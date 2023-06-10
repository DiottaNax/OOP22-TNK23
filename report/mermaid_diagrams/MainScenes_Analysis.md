classDiagram

SceneFactoryImpl --|> SceneFactory
ColorPickerController --|> Initializable
ColorPickerController --> Scene

class SceneFactory {
    <<interface>>
    + getMenuScene(view: FxGameView): Scene
    + getColorPickerScene(view: FxGameView): Scene
    + getGameScene(gamePane: Pane, playerController: PlayerInfoControllerImpl, roundController: RoundInfoControllerImpl): Scene
    + getGameOverScene(view: FxGameView): Scene
}

class SceneFactoryImpl {
    
}

class TitleMenuController {
    + startGame(): void
    + goPickColor(): void
    + initialize(): void
}

class ColorPickerController {
    + initialize(location: URL, resources: ResourceBundle): void
    + setColor(event: ActionEvent): void
    + confirm(event: ActionEvent): void
    + setPlayerSprite(): void
}

class GameOverController {
    + restartGame(): void
    + exit(): void
}