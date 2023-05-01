classDiagram
    class GameLoop{
        <<interface>>
        + processInput()
        + update(double elapsed)
        + render()
    }

    class GameLoopDecorator{
        <<abstract>>
    }

    GameLoopImpl ..|>GameLoop
    GameLoopDecorator --o GameLoop
    ConcurrentGameLoop --|> GameLoopDecorator
    GameEngine --o ConcurrentGameLoop
    SynchronizedGameLoop --|> GameLoopImpl

    class ConcurrentGameLoop{
        + runGameLoopThread()
    }


