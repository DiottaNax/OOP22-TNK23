classDiagram

    GameEngine o-- ConcurrentGameLoop
    
    class GameEngine{
        <<Interface>>
        startEngine(): void
    }

    class ConcurrentGameLoop{
        runGameLoop(): void
    }