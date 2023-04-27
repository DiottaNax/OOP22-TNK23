classDiagram
    class GameLoop{
        <<interface>>
        + processInput()
        + update()
        + render()
    }

    GameLoopImpl ..|>GameLoop
    ConcurrentGameLoop --|> GameLoopImpl
    GameEngine --o GameLoop

    class ConcurrentGameLoop{
        + runGameLoopThread()
    }
