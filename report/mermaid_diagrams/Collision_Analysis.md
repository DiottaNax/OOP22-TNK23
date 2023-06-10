classDiagram
    AbstractComponent ..> Component
    CollisionComponent--|>AbstractComponent 
    NotifiableComponent--|>Component
    CollisionComponent..>NotifiableComponent

    class AbstractComponent{
        +getEntity() : Gameobject
        getWorld() : World
    }

    class CollisionComponent{
        + isCollidingWith() : boolean
    }
    class Component{
        <<Interface>>
        +update() : void
    }
    class NotifiableComponent{
        <<Interface>>
        +receive(x : Message) : void
    }