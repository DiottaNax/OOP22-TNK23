classDiagram
    WorldEventHandler --o WorldEvent
    GameLoop --o WorldEventHandler
    GameLoop ..|> WorldEventListener
    World --o WorldEventListener
    WorldEventListener --o WorldEvent
    World --o WorldEvent
    class GameLoop{
        
    }
    class World{
        +setWorldEventListener(WorldEventListener)
        +notifyEvent(WorldEvent)
    }
    class WorldEventListener{
        <<interface>>
        +notifyEvent(WorldEvent, GameObject)
    }
    class WorldEventHandler{
        -handleShootEvent(World)
        -handleSpawnEvent(World)
        -handleDeathEvent(World)
        +handle(WorldEvent)
    }
    class WorldEvent{
        +getPosition() : Point2D
        +getEventActor(GameObject)
        +getType() : EventType
    }
