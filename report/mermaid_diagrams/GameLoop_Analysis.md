classDiagram

    ConcurrentGameLoop ..|> GameLoopDecorator
    SynchronizedGameLoop ..|> GameLoopDecorator
    GameLoopDecorator --o GameLoop
    GameLoopImpl ..|> GameLoop

    class ConcurrentGameLoop{
        runGameLoop(): void
    }

    class GameLoopDecorator{
        <<Abstract>>
    }

    class GameLoopImpl{
    }

    class GameLoop{
        <<Interface>>
        processInput(): void
        update(): void
        render(): void
    }