classDiagram

    PlayerInfoController <|-- SideScenesController
    RoundInfoController <|-- SideScenesController
    Scene <|-- GameScene
    SideScenesController --o GameScene
    class SideScenesController {
        <<interface>>
        +updateGraphics()
    }

    class PlayerInfoController {

    }

    class RoundInfoController {

    }

    class Scene {

    }

    class GameScene {

    }