classDiagram

    Spawn --|> SpawnImpl
    Round --|> RoundImpl
    Spawn --o Round
    class Spawn {
        +startSpawn
        +update
    }

    class Round {
        +getEnemies(): List~GameObject~
        +isOver(): boolean
        +getRound(): int
        +getWorld(): World
        +getRandomEnemiesNum(): int
        +getAIEnemiesNum(): int
        +getTotalEnemies(): int
        +update()
        +startRound()
        +notifyEnemyDeath()
    }

    class SpawnImpl {

    }

    class RoundImpl {

    }