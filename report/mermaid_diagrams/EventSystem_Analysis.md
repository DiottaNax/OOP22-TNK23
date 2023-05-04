classDiagram
    WorldEventHandler --o WorldEvent
    World ..|> WorldEventListener
    GameLoop --o WorldEventHandler
    WorldEventListener --o WorldEvent
    class WorldEvent{
        <<interface>>
        +getPosition(): Point2D
        +getCode(): EventType
    }
    class WorldEventHandler{
        <<interface>>
        +onShootEvent(World)
        +onSpawnEvent(World)
        +onDeathEvent(World)
    }
    class WorldEventListener{
        <<interface>>
        notifyEvent(WorldEvent, GameObject)
    }


