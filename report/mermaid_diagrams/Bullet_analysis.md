classDiagram

    Component <|.. AbstractComponent
    AbstractComponent <|-- AbstractFireComponent
    BulletComponent <|-- AbstractComponent
    BulletComponent <|-- NotifiableComponent
    EnemiesFireComponent <|-- AbstractFireComponent
    PlayerFireComponent <|-- AbstractFireComponent
    PlayerFireComponent <|-- NotifiableComponent
    class Component {
        <<Interface>>
    }
    
    class NotifiableComponent {
    }

    class AbstractComponent {
        <<abstract>>
        +getEntity(): GameObject
        +getWorld(): World
    }

    class AbstractFireComponent {
        <<abstract>>
        *canshoot(): boolean
    }

    class BulletComponent {
        +getShoother(): GameObjectType
    }

    class EnemiesFireComponent {
    }

    class PlayerFireComponent {
    }