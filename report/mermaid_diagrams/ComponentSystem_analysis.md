classDiagram
    GameObject --o Type
    GameObject --o Message
    GameObject --o NotifiableComponent
    NotifiableComponent --|> Component
    NotifiableComponent --o Message
    GameObject --o Component

    class GameObject{
        <<interface>>
        +getType():Type
        +getComponents():List~Component~
        +notifyComponent(Message, NotifiableComponent)
        +update()      
    }
    class Type{
        <<interface>>
        +getTypeByName(String)$ Type
        +getName(): String
        +getHeight(): int
        +getWidth(): int
        +getHealth(): int
        +getSpeed(): double
    }
    class Message~X~{
        <<interface>>
        +getMessage(): X
    }
    class Component{
        <<interface>>
        +update()
    }
    class NotifiableComponent{
        <<interface>>
        +receive(Message)
    }


