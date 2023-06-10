classDiagram

AbstractComponent <|-- Component
NotifiableComponent <-- Component
AbstractHealthComponent <-- AbstractComponent
AbstractHealthComponent <|-- NotifiableComponent
BulletHealthComponent <|-- AbstractHealthComponent
EntitiesHealthComponent<|-- AbstractHealthComponent
TemporaryHealthComponent <|-- AbstractHealthComponent

class Component {
    <<interface>>
    +update() : void
}

class AbstractComponent {
    <<abstract>>
    +getEntity() : GameObject
    +getWorld() : World
}

class NotifiableComponent {
    <<interface>>
    +receive(Message~X~) : void
}

class AbstractHealthComponent {
    <<abstract>>
    +getHealth() : int
    +setHealth(int) : void
    #isTouchable() : boolean
}

class BulletHealthComponent {
    
}

class EntitiesHealthComponent {
    
}

class TemporaryHealthComponent {
    
}