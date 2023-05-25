classDiagram

    GameView --|> Application
    GameView --o GameStage
    GameView --o SceneFactory
    GameView --o RenderingEngine

    class Application{
        <<interface>>
        +start()
    }

    class GameView{
    }

    class RenderingEngine{
        <<interface>>
        +getGameSceneRoot(): Parent
        +run()
    }

    class SceneFactory{
        <<interface>>
        +getMenuScene(): Scene
        +getGameScene(Parent root): Scene
        +getGameOverScene(): Scene
    }

    class GameStage{
        +setScene()
    }