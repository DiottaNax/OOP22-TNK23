classDiagram

    FxGameView ..|> GameView
    FxGameView o-- SceneFactory

    class GameView{
        <<interface>>
        renderView(): void
        setMenuScene(): void
        setGameScene(): void
    }

    class SceneFactory{
        <<interface>>
        getGameScene(): Scene
        getGameOverScene(): Scene
        getMenuScene(): Scene
    }