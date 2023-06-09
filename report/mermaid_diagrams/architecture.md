classDiagram 

    GameView --o RenderingEngine
    GameView --o GameEngine
    RenderingEngine --o World
    GameEngine --o GameState
    GameEngine --o World
    GameState --o Round
    Round --o Spawn
    World --o GameObject
    Round --o GameObject
    GameObject --o Component
    GameObject --o GraphicComponent
    GraphicComponent ..|> Component
    class GameEngine{
        <<Interface>>
        getWorld(): World
        getGameState(): GameState
        startEngine(): void
    }

    class World{
        <<Interface>>
        getPlayers(): List~GameObject~
        getEntities(): Set~GameObject~
        getObstacles():Set~GameObject~
        getTower(): GameObject
        update(): void
    }

    class GameState{
        <<Interface>>
        getRound(): Round
        isRoundOver(): boolean
        getPlayerLife(int): int
        isGameOver(): boolean
        update(): void
    }

    class Round{
        <<Interface>>
        getEnemies(): List~GameObject~
        isOver(): boolean
        getRound(): int
        startRound(): void
    }

    class Spawn{
        <<Interface>>
        startSpawn(): void
        update(): void
    }

    class GameObject{
        <<Interface>>
    }

    class GameView{
        <<Interface>>
        renderView(): void
        setMenuScene(): void
        setGameScene(): void
        setGameOverScene(): void
        getGameEngine(): GameEngine
    }

    class RenderingEngine~X~{
        <<Interface>>
        getGamePane(): X
        render(): void
    }

    class Component{
        <<Interface>>
        update(): void
    }

    class GraphicComponent{
        getSpriteName(): String
    }
